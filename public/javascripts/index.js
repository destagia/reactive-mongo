$(function(){
    var end_point = "http://localhost:9000";
    $("#register").bind('click', function(){
        $.ajax({
            url: end_point + "/insert",
            type: 'POST',
            data: {
                id: $("#r_id").val(),
                age: $("#r_age").val()
            },
            success: function(data){
                console.log(data);
            },
            error: function(e){
                console.log(e);
            }
        });
    });

    $("#search").bind('click', function(){
        $.ajax({
            url: end_point + "/search",
            data: {
                id: $("#s_id").val()
            },
            success: function(data){
                console.log(data);
            },
            error: function(e){
                console.log(e);
            }
        });
    });
});