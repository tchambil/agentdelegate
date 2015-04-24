/**
 * Created by teo on 22/04/15.
 */

$(document).ready(function() {
    // Random Person AJAX Request
        $('#btndefinition').click(function(){
         $.ajax({
            type:'POST',
            url: '../users/test-user-1/agent_definitions',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: $('#txtadefinition').val(),
            success: function(data){
                $('#txtgetadefinition').empty();
                $('#txtgetadefinition').append(JSON.stringify(data,null,"\t"));
            },
            error: function(err){
                $('#txtgetadefinition').empty();
                $('#txtgetadefinition').append(JSON.stringify(err,null,2));
            }
        }); //-- END of Ajax


    });

    $('#btngetdefinition').click(function(){
       $.get('../users/test-user-1/agent_definitions',function(data)
        {
            $('#txtgetadefinition').empty();
            $('#txtgetadefinition').append(JSON.stringify(data,null,"\t"));

        }); //-- END of Ajax

    });


});

