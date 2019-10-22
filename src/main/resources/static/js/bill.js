$(document).ready(function(){

     var formatter = new Intl.NumberFormat('vi-VI', {
        style: 'currency',
        currency: 'vnd',
     });
     var details = [];
     $('#table-products').on('click','button[id="buy"]',function(e){
         var productId = $(this).closest('tr').children('td:first').text();
         var productName = $(this).closest('tr').children('td:nth-child(2)').text();
         var quantity = $(this).closest('tr').children('td:nth-child(3)').text();
         var price = $(this).closest('tr').children('td:nth-child(4)').text();
         var quantityPurchased =  $(this).closest('tr').children('td:nth-child(7)').find('input').val();
         if(quantityPurchased != ""){
             var temp = quantity - quantityPurchased;
             if(temp < 0){
                $.notify("Quantity purchased must be less than or equal to the quantity in stock", "error");
                $(this).closest('tr').children('td:nth-child(7)').find('input').css('background', 'yellow');
             }else{
                $(this).closest('tr').children('td:nth-child(3)').text(quantity-quantityPurchased);
                $(this).closest('tr').children('td:nth-child(7)').find('input').css('background', 'transparent');
                var temp = getPrice(price);
                var detail = {
                    productId: productId,
                    productName: productName,
                    quantity: quantityPurchased,
                    price: getPrice(price)
                };
                for(i in details){
                    var check = false;
                    if(detail.productId === details[i].productId){
                        details[i].quantity = parseInt(details[i].quantity, 10) + parseInt(detail.quantity, 10);
                        check = true;
                        break;
                    }
                }
                if(!check){
                    details.push(detail);
                }
                domTableDetail();
             }
          }else{
            $.notify("Quantity purchased is required, please enter!", "error");
            $(this).closest('tr').children('td:nth-child(7)').find('input').css('background', 'yellow');
          }
     });

     $('#table-buy').on('click','button[id="delete"]',function(e){
        var productId = $(this).closest('tr').children('td:first').text();
       var quantity = $(this).closest('tr').children('td:nth-child(3)').text();
        details = details.filter(x => {
          return x.productId != productId;
        })
        domTableDetail();

       // var $row = $('').closest("tr").children('td').text();
       $("#table-products tbody tr").each(function() {
       		if($(this).children('td:first').text() === productId){
       		    var temp =  $(this).children('td:nth-child(3)').text();
       		    $(this).children('td:nth-child(3)').text(Number(temp) + Number(quantity));
       		}
       });

     });

    function arrayRemove(arr, value) {
       return arr.filter(function(ele){
           return ele != value;
       });

    }

     function getPrice(str){
        var temp = str.substring(0, str.indexOf(' ')).replace(',','');
        return temp;
     }

     function domTableDetail(){
        var total = 0;
        var intoMoney = 1;
        $('#tbody-buy').empty();
        for(i in details){
            intoMoney = details[i].price * details[i].quantity;
            total += intoMoney;
            $("#tbody-buy").
                append("<tr> \
                    <td>" +  details[i].productId + "</td> \
                    <td>" +  details[i].productName + "</td> \
                    <td>" +  details[i].quantity + "</td> \
                    <td>" +  formatter.format(details[i].price) + "</td> \
                    <td>" +  formatter.format(intoMoney) + "</td> \
                    <td><button id='delete' class='btn btn-link'> \ <i class='fas fa-trash-alt'></i> \ </button> \ </td>\
                 </tr>");
        }
        $("#tbody-buy").append("<tr> \
           <td colspan='4'>" + "</td> \
           <td colspan='2'> Total: " + formatter.format(total) + "</td> \
           </tr>");
     }

     $("#save").click(function() {
        for(i in details){
            delete details[i].productName;
        }
        var jsonVar = {
            customerName: $('#customerName').val(),
            customerDateOfBirth: $('#customerDateOfBirth').val(),
            customerPhone: $('#customerPhone').val(),
            customerAddress: $('#customerAddress').val(),
            note: $('#note').val(),
            productBillList: details
        };

        $.ajax({
            type: "POST",
            url: "http://localhost:8080/user/api/bills",
            data: JSON.stringify(jsonVar),
            contentType: "application/json",
            success: function(data){
                console.log(data);
                window.location = "http://localhost:8080/user/bill-list";
            },
            error: function(err){
                var errMes = JSON.parse(JSON.stringify(err.responseJSON));
                console.log(errMes);
                validatedBill(errMes);
            }
        });

     });

     function validatedBill(err){
          if(err.customerAddress != null){
              $.notify(err.customerAddress, "error");
              $('#customerAddress').focus();
              $('#customerAddress').css('background', 'yellow');
          }else{
               $('#customerAddress').css('background', 'transparent');
          }
          if(err.customerPhone != null){
             $.notify(err.customerPhone, "error");
             $('#customerPhone').focus();
             $('#customerPhone').css('background', 'yellow');
          }else{
             $('#customerPhone').css('background', 'transparent');
          }
          if(err.customerDateOfBirth != null){
             $.notify(err.customerDateOfBirth, "error");
             $('#customerDateOfBirth').focus();
             $('#customerDateOfBirth').css('background', 'yellow');
          }else{
             $('#customerDateOfBirth').css('background', 'transparent');
          }
          if(err.customerName != null){
              $.notify(err.customerName, "error");
              $('#customerName').focus();
              $('#customerName').css('background', 'yellow');
           }else{
              $('#customerName').css('background', 'transparent');
           }
       }
});