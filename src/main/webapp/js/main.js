
$(function() {

  var tab = $('#tab');
  var createPublisherButton = $('#createPublisher');
  var createOperatorButton = $('#createOperator');
  var createApplicationButton = $('#createApplication');
  var modalContainer = $('#modalContainer');

  createPublisherButton.hide();
  createOperatorButton.hide();
  createApplicationButton.hide();


  function showAlert(message,alerttype) {
    $('#alert_placeholder').append('<div id="alertdiv" class="alert ' +  alerttype + '"><a class="close" data-dismiss="alert">×</a><span>'+message+'</span></div>')

    setTimeout(function() {
      $("#alertdiv").remove();
    }, 5000);
  }

  function appModuleFormInit(modalLabelText, submitButtonText, url, appObject)
  {
    modalContainer.html("");

    var html = '<form id="form" data-type="app" action="' + url + '">';
    if (appObject) {
      html += '<div class="form-group"><input id="idInput"  value="' + appObject.id + '" name="id" hidden/></div>';
    }

    html +=
        '<div class="form-group">' +
        '<label for="userNameInput">Application name</label> ' +
        '<input type="text" class="form-control" id="appNameInput" name="name" placeholder="Enter user name"/>' +
        '</div>';

    $.ajax({
      url: "/app/typencontent",
      dataType: "json",
      context: document.body
    }).done(function (data) {
      html += '<div  class="form-group">' +
      '<label for="contentTypes">Content types</label>' +
      '<select id="contentType" name="contentType" class="selectpicker form-control" multiple>';
      $.each(data.contentType, function (key, value) {
        html += '<option>' + value + '</option>';
      });
      html += '</select></div>';
      html += '<div class="form-group">' +
      '<label for="appType">Application type</label>' +
      '<select id="appType" name="type" class="selectpicker form-control">';
      $.each(data.appType, function (key, value) {
        html += '<option>' + value + '</option>';
      });
      html += '</select></div></form>';
      modalContainer.html(html);
      var selectpicker =  $('.selectpicker');
      selectpicker.selectpicker();

      if (appObject) {
        $('#appNameInput').val(appObject.name);
        $('#appType').val(appObject.type);
        $('#contentType').val(appObject.contentTypes.split(','));

        selectpicker.selectpicker('refresh');
      }
      $('#myModal').modal('toggle');

    });

    //if (userObject) {
    //  $('#userNameInput').val(userObject.name);
    //  $('#emailInput').val(userObject.email);
    //  $('#passwordDiv').prop('disabled', true).hide();
    //}

    $("#submitModalButton").html(submitButtonText);
    $("#myModalLabel").html(modalLabelText);
  }

  function userModuleFormInit(modalLabelText, submitButtonText, userType, operation, userObject) {
    modalContainer.html("");
    var html = '<form id="form" data-type="'+userType+'" action="' + "/" +userType  + "/" + operation + '">';
    if (userObject) {
      html += '<div class="form-group"><input id="idInput"  value="' + userObject.id + '" name="id" hidden/></div>';
    }
    html +=
        '<div class="form-group">' +
        '<label for="userNameInput">User name</label> ' +
        '<input type="text" class="form-control" id="userNameInput" name="name" placeholder="Enter user name"/>' +
        '</div>' +
        '<div class="form-group">' +
        '<label for="emailInput">Email address</label>' +
        '<input type="email" class="form-control" id="emailInput" name="email" aria-describedby="emailHelp" placeholder="Enter email"/>' +
        '</div>' +
        '<div id="passwordDiv" class="form-group">' +
        '<label for="passwordInput">Password</label>' +
        '<input type="password" class="form-control" id="passwordInput" name="password" placeholder="Enter password"/>' +
        '</div></form>';

    modalContainer.html(html);
    if (userObject) {
      $('#userNameInput').val(userObject.name);
      $('#emailInput').val(userObject.email);
      $('#passwordDiv').prop('disabled', true).hide();
    }

    $("#submitModalButton").html(submitButtonText);
    $("#myModalLabel").html(modalLabelText);
    $('#myModal').modal('toggle');
  }

  function builtAppInformTable() {
    tab.html("");
    tab.append("<thead>"
    + "<th>" + "ID" + "</th>"
    + "<th>" + "Name" + "</th>"
    + "<th>" + "Application Type" + "</th>"
    + "<th>" + "Content Type" + "</th>"
    + "<th>" + "Owner" + "</th>"
    + "<th>" + "Edit" + "</th>"
    + "<th>" + "Remove" + "</th>"
    + "</thead><tbody>");

    $.ajax({
      url: "/app/get",
      dataType: "json",
      context: document.body
    }).done(function (data) {
      $.each(data, function (key, value) {
        tab.append("<tr>"
        + "<td name='idField'>" + value.id + "</td>"
        + "<td name='nameField'>" + value.name + "</td>"
        + "<td name='typeField'>" + value.type + "</td>"
        + "<td name='contentTypesField'>" + value.contentTypes + "</td>"
        + "<td>" + value.user.name + "</td>"
        + "<td>" + "<a data-type='app' class='glyphicon glyphicon-pencil editApp' aria-hidden='true' >" + "</td>"
        + "<td>" + "<a data-type='app' class='glyphicon glyphicon-remove remove' aria-hidden='true'>" + "</td>"
        + "</tr>");
      });
      tab.append("</tbody>");
    });

  }

  function builtUserInformTable(userType) {
    tab.html("");
    tab.append("<thead>"
        + "<th>" + "ID" + "</th>"
        + "<th>" + "Name" + "</th>"
        + "<th>" + "Email" + "</th>"
        + "<th>" + "Role" + "</th>"
        + "<th>" + "Edit" + "</th>"
        + "<th>" + "Remove" + "</th>"
        + "</thead><tbody>");

    $.ajax({
      url: "/"+userType +"/get",
      dataType: "json",
      context: document.body
    }).done(function (data) {
      $.each(data, function (key, value) {
        tab.append("<tr>"
        + "<td name='idField'>" + value.id + "</td>"
        + "<td name='nameField'>" + value.name + "</td>"
        + "<td name='emailField'>" + value.email + "</td>"
        + "<td>" + value.userRole + "</td>"
        + "<td>" + "<a data-type='"+userType+"' class='glyphicon glyphicon-pencil editUser' aria-hidden='true' >" + "</td>"
        + "<td>" + "<a data-type='"+userType+"' class='glyphicon glyphicon-remove remove' aria-hidden='true'>" + "</td>"
        + "</tr>");
      });
      tab.append("</tbody>");
    });
  }
// -------------------------------------------   light funcs -------------------------------
  function removeFunc(event){
    var targetSelector = $(event.target);
    var userType = targetSelector.attr("data-type");
    var elementId = targetSelector.parent().parent().children("td:first").text();
    $.ajax({
      url: "/"+userType+"/delete?id="+elementId
    }).done(function() {
      if(userType === 'app')
      {
        builtAppInformTable();
        return;
      }
      builtUserInformTable(userType);
    });
  }

  function editFunc(event) {
    var targetSelector = $(event.target);
    var userType = targetSelector.attr("data-type");
    var par = targetSelector.parent().parent();
    if(userType === 'app')
    {
      var app = {
        id: par.children("td[name=idField]").text(),
        name: par.children("td[name=nameField]").text(),
        type : par.children("td[name=nameField]").text(),
        contentTypes : par.children("td[name=contentTypesField]").text()
      };

      appModuleFormInit("Edit application", "Save changes", "/app/edit", app);
      return;
    }

    var user = {
      id: par.children("td[name=idField]").text(),
      name: par.children("td[name=nameField]").text(),
      email: par.children("td[name=emailField]").text()
    };

    userModuleFormInit('Edit ' + userType, 'Save changes', userType, 'edit', user);
  }

  //--------------------------------------------------- Listeners -----------------------------------------

  $('#submitModalButton').on('click', function(){
    var form = $('#form');
    var operationType = form.attr('data-type');
    $.ajax({
      url:form.attr('action'),
      type:'post',
      data:form.serialize(),
      success:function(){
        $('#myModal').modal('toggle');
        showAlert("<strong>Operation success</strong>", 'alert-success');
        if(operationType === 'app'){
          builtAppInformTable();
          return;
        }
        builtUserInformTable(operationType);
      },
      error:function(XMLHttpRequest) {
        $('#myModal').modal('toggle');
        showAlert(XMLHttpRequest['responseText'], 'alert-danger');
      }
    });
  });


  $('#publishers').on('click', function(event){
    builtUserInformTable("publisher");
    createPublisherButton.show();
    createOperatorButton.hide();
    createApplicationButton.hide();
  } );

  $('#operators').on("click", function(event){
    builtUserInformTable("operator");
    createPublisherButton.hide();
    createOperatorButton.show();
    createApplicationButton.hide();
  } );

  $('#apps').on("click", function(event){
    builtAppInformTable();
    createPublisherButton.hide();
    createOperatorButton.hide();
    createApplicationButton.show();
  } );

  createPublisherButton.on('click', function(){
    userModuleFormInit('Register new publisher','Create new publisher','publisher','create');

  });

  createOperatorButton.on('click', function(){
    userModuleFormInit('Register new operator','Create new operator','operator','create');

  });

  createApplicationButton.on('click', function(){
    appModuleFormInit('Register new application','Create new application','/app/create');

  });

  tab.on('click', 'a.editUser', function(event){
    editFunc(event);
  });

  tab.on('click', 'a.editApp', function(event){
    editFunc(event);
  });

  tab.on('click', 'a.remove', function(event){
    removeFunc(event);
  });
});






