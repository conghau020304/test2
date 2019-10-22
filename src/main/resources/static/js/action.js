$(document).ready(function(){

    $("#update").hide();
    assignDataToTable();

    //DOM TABLE
    function assignDataToTable() {
        var formatter = new Intl.NumberFormat('vi-VI', {
          style: 'currency',
          currency: 'vnd',
        });

        $("#products-table").empty();
        $.ajax({
          type:"GET",
          contentType: "application/json",
          url:"http://localhost:8080/api/products",
          success: function(data) {
            var products = JSON.parse(JSON.stringify(data));
            for (var i in products) {
                $("#products-table").
                append("<tr> \
                            <td>" +  products[i].productId + "</td> \
                            <td>" +  products[i].productName + "</td> \
                            <td>" +  formatNumber(products[i].quantity) + "</td> \
                            <td>" +  formatter.format(products[i].price) + "</td> \
                            <td>" +  products[i].dateAdded + "</td> \
                            <td>" +  products[i].measureWord + "</td> \
                            <td><button id='delete' class='btn btn-link'> \ <i class='fas fa-trash-alt'></i> \ </button> \ </td> \
                            <td><button id='edit' class='btn btn-link'> \ <i class='far fa-edit'></i> \ </button> \ </td> \
                        </tr>");
                }
            },
          error: function(err) {
            console.log(err);
            }
        });
    }
    // END DOM TABLE

    // FORMAT NUMBER
    function formatNumber(num) {
      return num.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1.')
    }
    //END FORMAT NUMBER

    // FUNCTION SAVE DATA
    $("#save").click(function() {
        //var dateAdded =  new Date().toISOString().slice(0,19);
        var jsonVar = {
            productId: $("#productId").val(),
            productName: $("#productName").val(),
            quantity: $("#quantity").val(),
            price: $("#price").val(),
            measureWord: $("#measureWord").val()
        };
        $.ajax({
            type:"POST",
            url:"http://localhost:8080/api/products",
            data: JSON.stringify(jsonVar),
            contentType: "application/json",
            success: function(data){
                assignDataToTable();
                clearData();
                $.notify("Insert product " + jsonVar.productId + " success!", "success");
            },
            error: function(err) {
                var validate = JSON.parse(JSON.stringify(err.responseJSON));
                validated(validate);
            }
        });

    });
    // END FUNCTION SAVE DATA

    $("#reset").click(function() {
        clearData();
    });

      //CLEAR DATA
      function clearData(){
        $("#update").hide();
        $("#save").show();
        $('#productId').prop('readonly', false);
        $("#productId").val("");
        $("#productName").val("");
        $("#quantity").val("");
        $("#price").val("");

        $('#quantity').css('background', 'transparent');
        $('#price').css('background', 'transparent');
        $('#productName').css('background', 'transparent');
        $('#productId').css('background', 'transparent');
      } //END CLEAR DATA

      //DELETE PRODUCT
      $('table').on('click', 'button[id="delete"]', function(e){
         var productId = $(this).closest('tr').children('td:first').text();
         var name = $(this).closest('tr').children('td:nth-child(2)').text();

         var r = confirm("Confirm delete product " + name + " !");
         if(r){
             $.ajax({
                  type:"DELETE",
                  url:"http://localhost:8080/api/products/" + productId,
                  success: function(data){
                      assignDataToTable();
                      $.notify("Delete product " + productId + " success!", "success");
                  },
                  error: function(err) {
                      console.log(err);
                       var errMes = JSON.parse(JSON.stringify(err.responseJSON));
                       $.notify(errMes.message, "error");
                  }
              });
          }else{
            return false;
          }
      });

      //UPDATE PRODUCT
      $('table').on('click','button[id="edit"]',function(e){
        var productId = $(this).closest('tr').children('td:first').text();
        $('#productId').prop('readonly', true);
        $('#productId').css('background','#eee')
        $("#update").show();
        $("#save").hide();
        // get data
        $.ajax({
            type:"GET",
            url:"http://localhost:8080/api/products/" + productId,
            success: function(data){
                var product = JSON.parse(JSON.stringify(data));
                $('#productId').val(product.productId);
                $('#productName').val(product.productName);
                $('#quantity').val(product.quantity);
                $('#price').val(product.price);
                $('#measureWord').val(product.measureWord);
            },
            error: function(err){
               console.log(err);
               var errMes = JSON.parse(JSON.stringify(err.responseJSON));
               $.notify(errMes.message, "error");
            }
        });
      });

   // edit data
    $('#update').on('click', function(){
        var productId = $("#productId").val();
        console.log(productId);
        var jsonVar = {
            productName: $("#productName").val(),
            quantity: $("#quantity").val(),
            price: $("#price").val(),
            measureWord: $("#measureWord").val()
        };

        $.ajax({
            type: "PUT",
            data: JSON.stringify(jsonVar),
            contentType: "application/json",
            url:"http://localhost:8080/api/products/" + productId,
            success: function(data){
                $.notify("Update product " + productId + " success!", "success");
                assignDataToTable();
                clearData();
            },
            error: function(err){
                console.log(err);
                var errMes = JSON.parse(JSON.stringify(err.responseJSON));
                validated(errMes);
            }
        });
    });

      function validated(err){
         if(err.price != null){
             $.notify(err.price, "error");
             $('#price').focus();
             $('#price').css('background', 'yellow');
         }else{
              $('#price').css('background', 'transparent');
         }
         if(err.quantity != null){
            $.notify(err.quantity, "error");
            $('#quantity').focus();
            $('#quantity').css('background', 'yellow');
         }else{
            $('#quantity').css('background', 'transparent');
         }
         if(err.productName != null){
            $.notify(err.productName, "error");
            $('#productName').focus();
            $('#productName').css('background', 'yellow');
         }else{
            $('#productName').css('background', 'transparent');
         }
         if(err.productId != null){
             $.notify(err.productId, "error");
             $('#productId').focus();
             $('#productId').css('background', 'yellow');
          }else{
             $('#productId').css('background', 'transparent');
          }
      }
});