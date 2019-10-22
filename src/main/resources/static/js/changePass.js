$(document).ready(function(){

    $("#change").click(function() {
       if($('#rePassword').val() != $('#passwordNew').val()){
            $.notify("Re password not equals new password!", "error");
            $('#rePassword').css('background', 'yellow');
       }else{
            $('#rePassword').css('background', 'transparent');
            var jsonVar = {
                password : $('#rePassword').val(),
                oldPassword: $('#passwordOld').val()
            }
            $.ajax({
                type:"POST",
                url:"http://localhost:8080/user/action-change-password/",
                data: JSON.stringify(jsonVar),
                contentType: "application/json",
                success: function(data){
                   // $.notify("Insert product " + jsonVar.productId + " success!", "success");
                },
                error: function(err) {
                    console.log(err);
                }
            });
       }
    });
});