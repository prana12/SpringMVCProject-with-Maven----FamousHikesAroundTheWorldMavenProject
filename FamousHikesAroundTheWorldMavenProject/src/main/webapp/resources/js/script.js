$(function(){
    $('div#messages').click(function(){
        $(this).fadeOut();
    });
    $('div#flash-messages').click(function(){
        $(this).fadeOut();
    });
    
    
    /*$("#btnSubmit").click(function () {
        var password = $("#password").val();
        var confirmPassword = $("#confirmPassword").val();
        if ( confirmPassword != "" && password != confirmPassword) {
            var errorConfirmPasswordText = $("#errorConfirmPassword").text();
            $( "#errorConfirmPassword" ).text("Passwords do not match");
            return false;
        }
        return true;
    });*/

    
});

