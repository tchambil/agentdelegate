package dcc.com.agent.restful;

import dcc.com.agent.agentserver.*;
import dcc.com.agent.appserver.AgentAppServer;
import dcc.com.agent.appserver.AgentAppServerShutdown;
import dcc.com.agent.field.Field;
import dcc.com.agent.scheduler.AgentScheduler;
import dcc.com.agent.script.intermediate.ScriptNode;
import dcc.com.agent.script.parser.ScriptParser;
import dcc.com.agent.script.runtime.value.Value;
import dcc.com.agent.script.runtine.ScriptRuntime;
import dcc.com.agent.util.DateUtils;
import dcc.com.agent.util.JsonListMap;
import dcc.com.agent.util.NameValue;
import dcc.com.agent.util.Utils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

@RestController
public class PlataformController {
    public static AgentServer agentServer;
    static public Thread shutdownThread;
    protected static Logger logger = Logger.getLogger(PlataformController.class);
    public Utils util;

    public AgentServer getAgentServer() {
        return this.agentServer;
    }

    @RequestMapping(value = {"/status/start", "/status/start2"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String Start() throws Exception {
        logger.info("Starting agent server");

        AgentAppServer agentAppServer = new AgentAppServer();
        // Start the agent server.
        agentServer = new AgentServer(agentAppServer);
        agentServer.start();

        JSONObject message = new JSONObject();
        message.put("message", "Starting agent Server successful");
        return message.toString();

    }

    @RequestMapping(value = "/config", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String getConfig() throws JSONException {
        JSONObject configJson = agentServer.config.toJson();
        return configJson.toString();
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String getAbout() throws JSONException {
        JSONObject aboutJson = new JsonListMap();
        aboutJson.put("name", agentServer.config.get("name"));
        aboutJson.put("software", agentServer.config.get("software"));
        aboutJson.put("version", agentServer.config.get("version"));
        aboutJson.put("description", agentServer.config.get("description"));
        aboutJson.put("website", agentServer.config.get("website"));
        aboutJson.put("contact", agentServer.config.get("contact"));
        return aboutJson.toString();
    }

    @RequestMapping(value = "/agent_definitions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String getAgentDefinitions() throws JSONException {
        logger.info("Getting list of agent definitions");
        JSONArray agentDefinitionsArrayJson = new JSONArray();
        // Get all agents for all users
        for (NameValue<AgentDefinitionList> userAgentDefinitions : agentServer.agentDefinitions) {
            // Get all agents for this user
            for (AgentDefinition agentDefinition : agentServer.agentDefinitions
                    .get(userAgentDefinitions.name)) {
                // Generate JSON for short summary of agent definition
                JSONObject agentDefinitionJson = new JsonListMap();
                agentDefinitionJson.put("user", agentDefinition.user.id);
                agentDefinitionJson.put("name", agentDefinition.name);
                agentDefinitionJson.put("description",
                        agentDefinition.description);
                agentDefinitionsArrayJson.put(agentDefinitionJson);
            }
        }
        JSONObject agentDefinitionsJson = new JSONObject();
        agentDefinitionsJson
                .put("agent_definitions", agentDefinitionsArrayJson);

        return agentDefinitionsJson.toString();
    }

    @RequestMapping(value = "/agents", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String getagents() throws JSONException {
        logger.info("Getting list of agent instances for all users");
        JSONArray agentInstancesArrayJson = new JSONArray();
        // Get all agents for all users
        for (NameValue<AgentInstanceList> userAgentInstances : agentServer.agentInstances) {
            // Get all agents for this user
            for (AgentInstance agentInstance : agentServer.agentInstances
                    .get(userAgentInstances.name)) {
                // Generate JSON for short summary of agent instance
                JSONObject agentInstanceJson = new JsonListMap();
                agentInstanceJson.put("user", agentInstance.user.id);
                agentInstanceJson.put("name", agentInstance.name);
                agentInstanceJson.put("definition",
                        agentInstance.agentDefinition.name);
                agentInstanceJson.put("description", agentInstance.description);
                agentInstancesArrayJson.put(agentInstanceJson);
            }
        }
        JSONObject agentInstancesJson = new JSONObject();
        agentInstancesJson.put("agent_instances", agentInstancesArrayJson);

        return agentInstancesJson.toString();
    }

    @RequestMapping(value = "/field_types", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String getFieldTypes() throws JSONException, AgentServerException {

        try {
            logger.info("Getting list of field types");
            JSONArray fieldTypesArrayJson = new JSONArray();
            for (String fieldType : Field.types)
                fieldTypesArrayJson.put(fieldType);
            JSONObject fieldTypesJson = new JSONObject();
            fieldTypesJson.put("field_types", fieldTypesArrayJson);
            return fieldTypesJson.toString(4);
        } catch (JSONException e) {
            throw new AgentServerException(
                    "JSON error generating JSON for agent definition status - "
                            + e);
        }
    }

    @RequestMapping(value = "/status", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String getStatus() throws JSONException, InterruptedException {

        logger.info("Getting status info");

        // Sleep a little to assure status reflects any recent operation
        Thread.sleep(100);

        // Get the status info
        JSONObject aboutJson = new JsonListMap();
        AgentScheduler agentScheduler = AgentScheduler.singleton;
        aboutJson.put("status", agentScheduler == null ? "shutdown"
                : agentScheduler.getStatus());
        aboutJson.put("since", DateUtils.toRfcString(agentServer.startTime));
        aboutJson.put("num_registered_users", agentServer.users.size());
        int numActiveUsers = 0;
        for (NameValue<AgentInstanceList> agentInstanceListNameValue : agentServer.agentInstances)
            if (agentInstanceListNameValue.value.size() > 0)
                numActiveUsers++;
        aboutJson.put("num_active_users", numActiveUsers);
        int num_registered_agents = 0;
        for (NameValue<AgentDefinitionList> agentDefinitionListNameValue : agentServer.agentDefinitions)
            num_registered_agents += agentDefinitionListNameValue.value.size();
        aboutJson.put("num_registered_agents", num_registered_agents);
        int num_active_agents = 0;
        for (NameValue<AgentInstanceList> agentInstanceListNameValue : agentServer.agentInstances)
            num_active_agents += agentInstanceListNameValue.value.size();
        aboutJson.put("num_active_agents", num_active_agents);


        return aboutJson.toString(4);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String getUsers() throws Exception {

        JSONArray usersArrayJson = new JSONArray();
        for (NameValue<User> userIdValue : agentServer.users) {
            User user = userIdValue.value;
            JSONObject userJson = new JSONObject();
            userJson.put("id", user.id);
            userJson.put("display_name", user.incognito ? "(Incognito)"
                    : (user.displayName == null ? "" : user.displayName));
            usersArrayJson.put(userJson);
        }
        JSONObject usersJson = new JSONObject();
        usersJson.put("users", usersArrayJson);
        return usersJson.toString(4);

    }

    @RequestMapping(value = "/config", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String putConfig(HttpServletRequest request) throws Exception {
        JSONObject configJson = util.getJsonRequest(request);
        logger.info("Updating configuration settings");

        // Update the config settings as requested
        agentServer.config.update(configJson);

        // Update was successful
        return configJson.toString();

    }

    @RequestMapping(value = "/config/reset", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String putConfigRest() throws Exception {


        logger.info("Resetting to original configuration settings");

        // Reset config settings to original defaults
        agentServer.config.restoreDefaults();
        logger.info("Reseted config Agent server");
        // Update was successful
        JSONObject message = new JSONObject();
        message.put("message", "Reseted config Agent server");
        return message.toString();

    }

    @RequestMapping(value = "/shutdown", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public boolean putshutdown() throws Exception {
        // Request the agent app server to shutdown
        // TODO: Can we really do this here and still return a response?
        // Or do we need to set a timer, return, and shutdown independent of
        // current request

        // Spin up a separate thread to gracefully shutdown the server in a
        // timely manner
        AgentAppServerShutdown agentAppServerShutdown = new AgentAppServerShutdown(
                agentServer);
        shutdownThread = new Thread(agentAppServerShutdown);
        shutdownThread.start();
        logger.info("Shutdown Agent server");
        // Done
        return true;

    }

    @RequestMapping(value = "/status/pause", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String putStatusPause() throws Exception {

        // Request the agent scheduler to pause
        AgentScheduler.singleton.pause();
        logger.info("Pause Agent server");
        JSONObject message = new JSONObject();
        message.put("message", "Pause Agent server");
        return message.toString();

    }

    @RequestMapping(value = "/run", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String getRun(HttpServletRequest request) throws Exception {

        try {
            BufferedReader reader = request.getReader();
            String scriptString = null;
            try {
                StringBuilder builder = new StringBuilder();
                char[] buffer = new char[8192];
                int read;
                while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                    builder.append(buffer, 0, read);
                }
                scriptString = builder.toString();
            } catch (Exception e) {
                logger.info("Exception reading script text : " + e);
            }

            logger.info("Running script: " + scriptString);
            AgentDefinition dummyAgentDefinition = new AgentDefinition(
                    agentServer);
            AgentInstance dummyAgentInstance = new AgentInstance(
                    dummyAgentDefinition);
            ScriptParser parser = new ScriptParser(dummyAgentInstance);
            ScriptRuntime scriptRuntime = new ScriptRuntime(
                    dummyAgentInstance);
            ScriptNode scriptNode = parser.parseScriptString(scriptString);
            Value valueNode = scriptRuntime.runScript(scriptString,
                    scriptNode);
            String resultString = valueNode.getStringValue();
            logger.info("Script result: " + resultString);

        } catch (Exception e) {
            logger.info("Run Exception: " + e);
        }
        return null;

    }

    @RequestMapping(value = "/status/restart", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String putStatusrestart() throws Exception {

        // Request the agent scheduler to shutdown
        AgentScheduler.singleton.shutdown();

        // Sleep a little to wait for shutdown to complete
        Thread.sleep(250);

        // Make sure scheduler is no longer running
        if (AgentScheduler.singleton != null)
            // Sleep a little longer to wait for shutdown
            Thread.sleep(250);

        // Force the scheduler to start
        AgentScheduler agentScheduler = new AgentScheduler(agentServer);
        logger.info("Restart Agent server");
        JSONObject message = new JSONObject();
        message.put("message", "Restart Agent server");
        return message.toString();


    }

    @RequestMapping(value = "/status/resume", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String putStatusresume() throws Exception {

        // Request the agent scheduler to resume
        AgentScheduler.singleton.resume();
        logger.info("Resume Agent server");

        JSONObject message = new JSONObject();
        message.put("message", "Resume Agent server");
        return message.toString();

    }

    @RequestMapping(value = "/status/shutdown", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String putStatusShutdown() throws Exception {

        // Request the agent scheduler to shutdown
        logger.info("Shutting down agent server");
        AgentScheduler.singleton.shutdown();
        logger.info("Agent server shut down");

        JSONObject message = new JSONObject();
        message.put("message", "Agent server shut down");
        return message.toString();
    }

    @RequestMapping(value = "/status/start", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String putStatusStart() throws Exception {
        JSONObject message = new JSONObject();
        // Make sure scheduler is not already running
        if (AgentScheduler.singleton == null) {
            // Force the scheduler to start
            AgentScheduler agentScheduler = new AgentScheduler(agentServer);
            logger.info("Re-Start Agent server");

            message.put("message", "Re-Start Agent server");

        }
        return message.toString();

    }

    @RequestMapping(value = "/status/stop", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String putStatusStop() throws Exception {

        // Request the agent scheduler to shutdown
        logger.info("Shutting down agent server");
        AgentScheduler.singleton.shutdown();
        logger.info("Agent server shut down");

        JSONObject message = new JSONObject();
        message.put("message", "Agent server shut down");
        return message.toString();
    }
}
