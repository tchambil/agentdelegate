package dcc.com.agent.config;

import dcc.com.agent.agentserver.AgentDefinition;
import dcc.com.agent.agentserver.AgentInstance;
import dcc.com.agent.agentserver.User;
import dcc.com.agent.mailaccessmanager.MailAccessManager;
import dcc.com.agent.script.runtine.ScriptState;

public class AgentProperties {
    public static final int appServerPort = AgentVariable.DEFAULT_APP_SERVER_PORT;
    public static final String agentServerName = "YourAgentServer1";
    public static final String agentServerDescription = "Your Agent Server";
    public static final String adminPassword = "your-admin-password";
    public static final String mailServerHostName = "";
    public static final String mailServerUserName = "";
    public static final String mailServerUserPassword = "";
    public static final int mailServerPort = 0;
    public static final String mailServerFromEmail = "";
    public static final String mailServerFromName = "";
    public static final String testUserEmail = "";
    public static final String testUserName = "";
    public static final String supportContactEmail = "jack@basetechnology.com";
    public static final String website = "http://your-website.com";
    public static final String adminApproveUserCreate = Boolean
            .toString(User.DEFAULT_ADMIN_ONLY_USER_CREATE);
    ;
    public static final String mailConfirmUserCreate = Boolean
            .toString(User.DEFAULT_ADMIN_ONLY_USER_CREATE);
    ;
    public static final String persistent_store_dir = "./persistent_store";
    public static final String userAgentName = AgentVariable.DEFAULT_USER_AGENT_NAME;
    public static final String defaultWebPageRefreshInterval = Long
            .toString(AgentVariable.DEFAULT_DEFAULT_WEB_PAGE_REFRESH_INTERVAL);
    public static final String minimumWebPageRefreshInterval = Long
            .toString(AgentVariable.DEFAULT_MINIMUM_WEB_PAGE_REFRESH_INTERVAL);
    ;
    public static final String minimumWebSiteAccess_interval = Long
            .toString(AgentVariable.DEFAULT_MINIMUM_WEB_SITE_ACCESS_INTERVAL);
    ;
    public static final String minimumWebAccessInterval = Long
            .toString(AgentVariable.DEFAULT_MINIMUM_WEB_ACCESS_INTERVAL);
    ;
    public static final String execution_limit_level_1 = Long
            .toString(ScriptState.NODE_EXECUTION_LEVEL_1_LIMIT);
    ;
    public static final String executionLimitLevel2 = Long
            .toString(ScriptState.NODE_EXECUTION_LEVEL_2_LIMIT);
    ;
    public static final String executionLimitLevel3 = Long
            .toString(ScriptState.NODE_EXECUTION_LEVEL_3_LIMIT);
    ;
    public static final String executionLimitLevel4 = Long
            .toString(ScriptState.NODE_EXECUTION_LEVEL_4_LIMIT);
    ;
    public static final String executionLimitDefaultLevel = Long
            .toString(ScriptState.DEFAULT_EXECUTION_LEVEL);
    public static final String maxUsers = Integer
            .toString(User.DEFAULT_MAX_USERS);
    ;
    public static final String maxInstances = Integer
            .toString(AgentInstance.DEFAULT_MAX_INSTANCES);
    public static final String implicitlyDenyWebAccess = AgentVariable.DEFAULT_IMPLICITLY_DENY_WEB_ACCESS ? "true"
            : "false";
    public static final String implicitlyDenyWebWriteAccess = AgentVariable.DEFAULT_IMPLICITLY_DENY_WEB_WRITE_ACCESS ? "true"
            : "false";
    public static final String defaultTriggerInterval = AgentDefinition.DEFAULT_TRIGGER_INTERVAL_EXPRESSION;
    public static final String defaultReportingInterval = AgentDefinition.DEFAULT_REPORTING_INTERVAL_EXPRESSION;
    public static final String minimumTriggerInterval = AgentDefinition.DEFAULT_MINIMUM_TRIGGER_INTERVAL_EXPRESSION;
    public static final String minimumReportingInterval = AgentDefinition.DEFAULT_MINIMUM_REPORTING_INTERVAL_EXPRESSION;
    public static final String mailAccessEnabled = Boolean
            .toString(MailAccessManager.DEFAULT_MAIL_ACCESS_ENABLED);
    public static final String minimumMailAccessInterval = Long
            .toString(MailAccessManager.DEFAULT_MINIMUM_MAIL_ACCESS_INTERVAL);
    public static final String minimumHostMailAccessInterval = Long
            .toString(MailAccessManager.DEFAULT_MINIMUM_HOST_MAIL_ACCESS_INTERVAL);
    public static final String minimumAddressMailAccessInterval = Long
            .toString(MailAccessManager.DEFAULT_MINIMUM_ADDRESS_MAIL_ACCESS_INTERVAL);
    public static final String maximumLimitInstanceStatesStored = Integer
            .toString(AgentInstance.DEFAULT_LIMIT_INSTANCE_STATES_STORED);
    public static final String defaultLimitInstanceStatesStored = Integer
            .toString(AgentInstance.DEFAULT_MAXIMUM_LIMIT_INSTANCE_STATES_STORED);
    public static final String maximumLimitInstanceStatesReturned = Integer
            .toString(AgentInstance.DEFAULT_LIMIT_INSTANCE_STATES_RETURNED);
    public static final String defaultLimitInstanceStatesReturned = Integer
            .toString(AgentInstance.DEFAULT_MAXIMUM_LIMIT_INSTANCE_STATES_RETURNED);
}
