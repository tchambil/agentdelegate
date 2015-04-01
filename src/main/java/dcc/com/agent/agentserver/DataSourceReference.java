/**
 * Copyright 2012 John W. Krupansky d/b/a Base Technology
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dcc.com.agent.agentserver;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;


import dcc.com.agent.activities.AgentActivityTriggerInputChanged;
import dcc.com.agent.script.intermediate.SymbolException;
import dcc.com.agent.script.intermediate.SymbolValues;
import dcc.com.agent.script.parser.ParserException;
import dcc.com.agent.script.parser.tokenizer.TokenizerException;
import dcc.com.agent.util.JsonListMap;

public class DataSourceReference {
  static final Logger log = Logger.getLogger(AgentActivityTriggerInputChanged.class);

  public String name;
  public AgentDefinition dataSource;
  public SymbolValues parameterValues;

  public DataSourceReference(String name, AgentDefinition dataSource){
    this(name, dataSource, null);
  }

  public DataSourceReference(String name, AgentDefinition dataSource, SymbolValues parameterValues){
    this.name = name;
    this.dataSource = dataSource;
    this.parameterValues = parameterValues == null ? new SymbolValues() : parameterValues;
  }

  public AgentInstance instantiate(AgentInstance refererInstance, User user, AgentServer agentServer) throws AgentServerException {
    // Get new or shared instance
    AgentInstance agentInstance = agentServer.getAgentInstance(user, dataSource, parameterValues, true);
    
    // Optionally record reference to instance
    if (refererInstance != null)
      agentInstance.addReference(refererInstance);
    
    // Mark the instance as being "auto-created"
    agentInstance.autoCreated = true;
    
    // Return the new instance
    return agentInstance;
  }
  
  public JSONObject toJson() throws AgentServerException {
    JSONObject dsrJson = new JsonListMap();
    try {
      dsrJson.put("name", name);
      dsrJson.put("data_source", dataSource.name);
      dsrJson.put("parameter_values", parameterValues.toJson());
    } catch (JSONException e){
      log.info("Unable to output AgentState as string - " + e.getMessage());
      e.printStackTrace();
    }
    return dsrJson;
  }
  
  public String toString(){
    return "Data source name: '" + name + "' data_source: '" + dataSource.name + "', parameter_values: " + parameterValues.toString();
  }
}
