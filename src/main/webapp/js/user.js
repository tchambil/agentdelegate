/**
 * Created by teo on 22/04/15.
 */
$(document).ready(function () {
    // Random Person AJAX Request
    $("#btnGetUser").click(function (e) {
        $.get('../users/test-user-1', function (user) {
            $('#outUser').empty();
            $('#outUser').append(JSON.stringify(user, null, "\t"));
            /*
             $('#outUser').append("Id: " + user.id +" <br/>" +
             "display_name:" + user.displayName+" <br/>" +
             "full_name:"+ user.fullName +"<br/>" +
             "email:" + user.email + " <br/>" +
             "nick_name:" + user.nickName +" <br/>" +
             "organization:" + user.organization +" <br/>" +
             "interests:"+ user.interests +" <br/>");*/
        });
    });
    $("#btnGetUserall").click(function (e) {
        $.get('../users', function (user) {
            $('#outUser').empty();
            $('#outUser').append(JSON.stringify(user, null, "\t"));

        });
    });
    $("#btnputUser").click(function (e) {
        $.ajax({
            type: "PUT",
            url: '../users/' + $('#idInput').val().toString() + '',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify({
                display_name: $('#display_nameInput').val().toString(),
                full_name: $('#full_nameInput').val().toString(),
                email: $('#emailInput').val().toString(),
                nick_name: $('#nicknameInput').val().toString(),
                organization: $('#companyInput').val().toString(),
                interests: $('#interestsInput').val().toString()

            }),
            dataType: "json",
            success: function (data, status, jqXHR) {
                $('#outUser').empty();
                $('#outUser').append(JSON.stringify(data, null, "\t"));
            },

            error: function (jqXHR, status) {
                $('#outUser').empty();
                $('#outUser').append(jqXHR.responseText);

            }
        });
    });

    $('#btnnewUser').click(function (e) {

        $.post('../users?id=' + $('#idInput').val().toString() +
            '&password=' + $('#passwordInput').val().toString() +
            '&display_name=' + $('#display_nameInput').val().toString() +
            '&full_name=' + $('#full_nameInput').val().toString() +
            '&email=' + $('#emailInput').val().toString() +
            '&nickname=' + $('#nicknameInput').val().toString() +
            '&company=' + $('#companyInput').val().toString() +
            '&interests=' + $('#interestsInput').val().toString(), function (response) {
            $('#outUser').empty();
            $('#outUser').append(JSON.stringify(response, null, "\t"));
        });

        e.preventDefault(); // prevent actual form submit and page reload
    });

});
