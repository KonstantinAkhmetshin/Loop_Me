
$(function() {

  var tab = $('#tab');

  function showAlert(message,alerttype) {
    $('#alert_placeholder').append('<div id="alertdiv" class="alert ' +  alerttype + '"><a class="close" data-dismiss="alert">×</a><span>'+message+'</span></div>')

    setTimeout(function() {
      $("#alertdiv").remove();
    }, 5000);
  }

  function builtUserInformTable(url) {
    tab.html("");
    tab.append("<thead>"
        + "<th>" + "ID" + "</th>"
        + "<th>" + "Name" + "</th>"
        + "<th>" + "Email" + "</th>"
        + "<th>" + "Role" + "</th>"
        + "<th>" + "Edit" + "</th>"
        + "<th>" + "Remove" + "</th>"
        + "</thead><tbody>")

    $.ajax({
      url: url,
      dataType: "json",
      context: document.body
    }).done(function (data) {
      $.each(data, function (key, value) {
        tab.append("<tr>"
        + "<td name='idField'>" + value.id + "</td>"
        + "<td name='nameField'>" + value.name + "</td>"
        + "<td name='emailField'>" + value.email + "</td>"
        + "<td>" + value.userRole + "</td>"
        + "<td>" + "<a data-id='value.id' class='glyphicon glyphicon-pencil edit' aria-hidden='true' >" + "</td>"
        + "<td>" + "<a class='glyphicon glyphicon-remove remove' aria-hidden='true'>" + "</td>"
        + "</tr>");
      });
      tab.append("</tbody>");
    });
  }

  var removeFunc = function(event){
    var elementId = $(event.target).parent().parent().children("td:first").text();
    $.ajax({
      url: "/publisher/delete?id="+elementId
    }).done(function() {
      builtUserInformTable("/publisher/get");
    });
  };

  var editFunc = function(event){

    console.log($(event.target).parent().parent().children("td[name=idField]").text());
  };


  function userModuleFormInit(modalLabelText, submitButtonText, submitUrl, userObject){
        $('#modalContainer').append(
        '<form id="form" method="post" action="'+ submitUrl +'">' +
        '<div class="form-group">' +
        '<label for="userNameInput">User name</label> ' +
        '<input type="text" class="form-control" id="userNameInput" name="name" placeholder="Enter user name"/>' +
        '</div>' +
        '<div class="form-group">' +
        '<label for="emailInput">Email address</label>' +
        '<input type="email" class="form-control" id="emailInput" name="email" aria-describedby="emailHelp" placeholder="Enter email"/>' +
        '</div>' +
        '<div class="form-group">' +
        '<label for="passwordInput">Password</label>' +
        '<input type="password" class="form-control" id="passwordInput" name="password" placeholder="Enter password"/>' +
        '</div>' +
        '</form>');


    if(userObject.name){
      $('#userNameInput').val(userObject.name);
    }
    if(userObject.email){
      $('#userNameInput').val(userObject.email);
    }

    $("#submitModalButton").html(submitButtonText);
    $("#myModalLabel").html(modalLabelText);
    $('#myModal').modal('toggle');
  }

  //--------------------------------------------------- Listeners -----------------------------------------

  $('#submitModal').on('click', function(){
    $.ajax({
      url:'/publisher/create',
      type:'post',
      data:$('#form').serialize(),
      success:function(){
        $('#myModal').modal('toggle');
        showAlert("<strong>New publisher created!</strong>", 'alert-success');
        builtUserInformTable("/publisher/get");
      },
      error:function(XMLHttpRequest) {
        $('#myModal').modal('toggle');
        console.log(XMLHttpRequest['responseText']);
        showAlert(XMLHttpRequest['responseText'], 'alert-danger');
      }
    });
  });


  $('#createOperator').on('click', function(){
    userModuleFormInit('Register new publisher','Create new publisher','/publisher/create',{id:1,name:'ee'});
  });


  $('#publishers').on('click', function(event){
    builtUserInformTable("/publisher/get");
  } );

  $('#operators').on("click", function(event){
    builtUserInformTable("/operator/get");
  } );

  tab.on('click', 'a.edit', editFunc);
  tab.on('click', 'a.remove', removeFunc);
});






