$(document).ready(function () {
    $('#btnagent').click(function () {
        $.ajax({
            type: 'POST',
            url: '../users/test-user-1/agents',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: $('#txtagent').val(),
            success: function (data) {
                $('#txtagent').empty();
                $('#txtagent').append(JSON.stringify(data, null, "\t"));
            },
            error: function (err) {
                $('#txtagent').empty();
                $('#txtagent').append(JSON.stringify(err, null, 2));
            }
        }); //-- END of Ajax
    });

    $('#btngetagentall').click(function () {
        $.ajax({
            type: 'GET',
            url: '../users/test-user-1/agents/',
            // url: '../users/test-user-1/agents/Agent-1/',
            contentType: "application/json; charset=utf-8",
            dataType: "json",

            success: function (data) {
                $('#txtagent').empty();
                $('#txtagent').append(JSON.stringify(data, null, "\t"));
            },
            error: function (err) {
                $('#txtagent').empty();
                $('#txtagent').append(JSON.stringify(err, null, 2));
            }
        }); //-- END of Ajax
    });
    $('#btngetagentid').click(function () {
        $.ajax({
            type: 'GET',
            url: '../users/test-user-1/agents/Agent-1',
            // url: '../users/test-user-1/agents/Agent-1/',
            contentType: "application/json; charset=utf-8",
            dataType: "json",

            success: function (data) {
                $('#txtagent').empty();
                $('#txtagent').append(JSON.stringify(data, null, "\t"));
            },
            error: function (err) {
                $('#txtagent').empty();
                $('#txtagent').append(JSON.stringify(err, null, 2));
            }
        }); //-- END of Ajax
    });
    $('#btnagentpause').click(function () {
        $.ajax({
            type: 'PUT',
            url: '../users/test-user-1/agents/Agent-1/pause',
            // url: '../users/test-user-1/agents/Agent-1/',
            contentType: "application/json; charset=utf-8",
            dataType: "json",

            success: function (data) {
                $('#txtagent').empty();
                $('#txtagent').append(JSON.stringify(data, null, "\t"));
            },
            error: function (err) {
                $('#txtagent').empty();
                $('#txtagent').append(JSON.stringify(err, null, 2));
            }
        }); //-- END of Ajax
    });

    $('#btnagentdisable').click(function () {
        $.ajax({
            type: 'PUT',
            url: '../users/test-user-1/agents/Agent-1/disable',
            // url: '../users/test-user-1/agents/Agent-1/',
            contentType: "application/json; charset=utf-8",
            dataType: "json",

            success: function (data) {
                $('#txtagent').empty();
                $('#txtagent').append(JSON.stringify(data, null, "\t"));
            },
            error: function (err) {
                $('#txtagent').empty();
                $('#txtagent').append(JSON.stringify(err, null, 2));
            }
        }); //-- END of Ajax
    });

    $('#btnagentresume').click(function () {
        $.ajax({
            type: 'PUT',
            url: '../users/test-user-1/agents/Agent-1/resume',
            // url: '../users/test-user-1/agents/Agent-1/',
            contentType: "application/json; charset=utf-8",
            dataType: "json",

            success: function (data) {
                $('#txtagent').empty();
                $('#txtagent').append(JSON.stringify(data, null, "\t"));
            },
            error: function (err) {
                $('#txtagent').empty();
                $('#txtagent').append(JSON.stringify(err, null, 2));
            }
        }); //-- END of Ajax
    });

    $('#btnagentenable').click(function () {
        $.ajax({
            type: 'PUT',
            url: '../users/test-user-1/agents/Agent-1/enable',
            // url: '../users/test-user-1/agents/Agent-1/',
            contentType: "application/json; charset=utf-8",
            dataType: "json",

            success: function (data) {
                $('#txtagent').empty();
                $('#txtagent').append(JSON.stringify(data, null, "\t"));
            },
            error: function (err) {
                $('#txtagent').empty();
                $('#txtagent').append(JSON.stringify(err, null, 2));
            }
        }); //-- END of Ajax
    });
    $('#btnagentstatus').click(function () {
        $.ajax({
            type: 'GET',
            url: '../users/test-user-1/agents/Agent-1/status?state=yes&count=2',
            // url: '../users/test-user-1/agents/Agent-1/',
            contentType: "application/json; charset=utf-8",
            dataType: "json",

            success: function (data) {
                $('#txtagent').empty();
                $('#txtagent').append(JSON.stringify(data, null, "\t"));
            },
            error: function (err) {
                $('#txtagent').empty();
                $('#txtagent').append(JSON.stringify(err, null, 2));
            }
        }); //-- END of Ajax
    });
    $('#btnagentoutput').click(function () {
        $.ajax({
            type: 'GET',
            url: '../users/test-user-1/agents/Agent-1/output',
            // url: '../users/test-user-1/agents/Agent-1/',
            contentType: "application/json; charset=utf-8",
            dataType: "json",

            success: function (data) {
                $('#txtagent').empty();
                $('#txtagent').append(JSON.stringify(data, null, "\t"));
            },
            error: function (err) {
                $('#txtagent').empty();
                $('#txtagent').append(JSON.stringify(err, null, 2));
            }
        }); //-- END of Ajax
    });
});
