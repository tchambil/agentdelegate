$(document).ready(function () {
    // Random Person AJAX Request

    $('#getstart').click(function (e) {
        $.ajax({
            type: "GET",
            url: '../status/start',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data, status, jqXHR) {
                $('#personResponse').empty();
                $('#personResponse').append(JSON.stringify(data, null, "\t"));
            },

            error: function (jqXHR, status) {
                // alert(jqXHR.responseText + status);
                $('#personResponse').empty();
                $('#personResponse').append(jqXHR.responseText);

            }
        });
    });
    $('#putpause').click(function (e) {
        $.ajax({
            type: "PUT",
            url: '../status/pause/',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data, status, jqXHR) {
                $('#personResponse').empty();
                $('#personResponse').append(JSON.stringify(data, null, "\t"));
            },

            error: function (jqXHR, status) {
                // alert(jqXHR.responseText + status);
                $('#personResponse').empty();
                $('#personResponse').append(jqXHR.responseText);

            }
        });
    });

    $('#putrestart').click(function (e) {
        $.ajax({
            type: "PUT",
            url: '../status/restart',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data, status, jqXHR) {
                $('#personResponse').empty();
                $('#personResponse').append(JSON.stringify(data, null, "\t"));
            },

            error: function (jqXHR, status) {
                $('#personResponse').empty();
                $('#personResponse').append(jqXHR.responseText);

            }
        });
    });

    $('#putresume').click(function (e) {
        $.ajax({
            type: "PUT",
            url: '../status/resume',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data, status, jqXHR) {
                $('#personResponse').empty();
                $('#personResponse').append(JSON.stringify(data, null, "\t"));
            },

            error: function (jqXHR, status) {
                $('#personResponse').empty();
                $('#personResponse').append(jqXHR.responseText);

            }
        });
    });

    $('#putstop').click(function (e) {
        $.ajax({
            type: "PUT",
            url: '../status/stop',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data, status, jqXHR) {
                $('#personResponse').empty();
                $('#personResponse').append(JSON.stringify(data, null, "\t"));
            },

            error: function (jqXHR, status) {
                $('#personResponse').empty();
                $('#personResponse').append(jqXHR.responseText);

            }
        });
    });
    $('#putabout').click(function (e) {
        $.ajax({
            type: "GET",
            url: '../about',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data, status, jqXHR) {
                $('#personResponse').empty();
                $('#personResponse').append(JSON.stringify(data, null, "\t"));
            },

            error: function (jqXHR, status) {
                $('#personResponse').empty();
                $('#personResponse').append(jqXHR.responseText);

            }
        });
    });

    $("#btnstatus").click(function (e) {
        $.get('../status', function (server) {
            $('#personResponse').empty();
            $('#personResponse').append(JSON.stringify(server, null, "\t"));
        });
    });

    $("#btnconfig").click(function (e) {
        $.get('../config', function (config) {
            $('#personResponse').empty();
            $('#personResponse').append(JSON.stringify(config, null, "\t"));
            /*
             $('#config').append(
             "name: "  + config.name +" <br/>" +
             "description: " + config.description +" <br/>" +
             "software: " + config.software +" <br/>" +
             "version: " + config.version +" <br/>" +
             "website: " + config.website +" <br/>" +
             "admin_approve_user_create: " + config.admin_approve_user_create +" <br/>" +
             "mail_confirm_user_create: " + config.mail_confirm_user_create +" <br/>" +
             "contact: " + config.contact +" <br/>" +
             "user_agent_name: " + config.user_agent_name +" <br/>" +
             "default_web_page_refresh_interval: " + config.default_web_page_refresh_interval +" <br/>" +
             "minimum_web_page_refresh_interval: " + config.minimum_web_page_refresh_interval +" <br/>" +
             "minimum_web_site_access_interval: " + config.minimum_web_site_access_interval +" <br/>" +
             "minimum_web_access_interval: " + config.minimum_web_access_interval +" <br/>" +
             "execution_limit_level_1: " + config.execution_limit_level_1 +" <br/>" +
             "execution_limit_level_2: " + config.execution_limit_level_2 +" <br/>" +
             "execution_limit_level_3: " + config.execution_limit_level_3 +" <br/>" +
             "execution_limit_level_4: " + config.execution_limit_level_4 +" <br/>" +
             "execution_limit_default_level: "+ config.execution_limit_default_level +" <br/>" +
             "max_users: " + config.max_users +" <br/>" +
             "max_instances: " + config.max_instances +" <br/>" +
             "implicitly_deny_web_access: " + config.implicitly_deny_web_access +" <br/>" +
             "implicitly_deny_web_write_access: "+ config.implicitly_deny_web_write_access +" <br/>" +
             "default_trigger_interval: " + config.default_trigger_interval +" <br/>" +
             "default_reporting_interval: " + config.default_reporting_interval +" <br/>" +
             "minimum_trigger_interval: " + config.minimum_trigger_interval +" <br/>" +
             "minimum_reporting_interval: " + config.minimum_reporting_interval +" <br/>" +
             "default_limit_instance_states_stored: " + config.default_limit_instance_states_stored +" <br/>" +
             "maximum_limit_instance_states_stored: " + config.maximum_limit_instance_states_stored +" <br/>" +
             "default_limit_instance_states_returned: " + config.default_limit_instance_states_returned +" <br/>" +
             "maximum_limit_instance_states_returned: " + config.maximum_limit_instance_states_returned +" <br/>" +
             "mail_access_enabled: " + config.mail_access_enabled +" <br/>" +
             "minimum_mail_access_interval: " + config.minimum_mail_access_interval +" <br/>" +
             "minimum_host_mail_access_interval: " + config.minimum_host_mail_access_interval +" <br/>" +
             "minimum_address_mail_access_interval: " + config.minimum_address_mail_access_interval +" <br/>"
             );*/
        });
    });


});

