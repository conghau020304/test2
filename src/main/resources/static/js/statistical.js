$(document).ready(function(){
    assignDataToTable();
    function assignDataToTable() {
        $.ajax({
          type:"GET",
          contentType: "application/json",
          url:"http://localhost:8080/admin/api/statistical",
          success: function(data) {
            var list = JSON.parse(JSON.stringify(data));
            dom(list);
          },
          error: function(err) {
            console.log(err);
          }
        });
    }
    function dom(list){
        $("#statistical-body").empty();
        var total = 0;
        if(list.length > 0){
            for (var i in list) {
                total += list[i].quantity;
                $("#statistical-body").
                append("<tr> \
                            <td>" +  list[i].productId + "</td> \
                            <td>" +  list[i].productName + "</td> \
                            <td>" +  list[i].quantity + "</td> \
                            <td>" +  list[i].billId + "</td> \
                            <td>" +  list[i].month + "</td> \
                            <td>" +  list[i].year + "</td> \
                        </tr>");
            }
             $("#statistical-body").
                append("<tr> \
                    <td colspan='2'> </td> \
                    <td>Total: " + total + " </td> \
                    <td colspan='3'> </td> \
                </tr>")
        }else{
            $("#statistical-body").
                append("<tr> \
                   <td colspan ='6'><h4 style='color:red'> Table is empty!</h4></td> \
               </tr>");
        }
    }
     $("#select-month").change(function(){
         var m = $(this).children("option:selected").val();
         if(m === "none"){
            $('#export').hide();
         }else{
            $('#export').show();
         }
         $.ajax({
              type:"GET",
              url:"http://localhost:8080/admin/api/statistical?m=" + m,
              success: function(data){
                  var list = JSON.parse(JSON.stringify(data));
                   dom(list);
              },
              error: function(err) {
                 console.log(err);
              }
         });
     });
});