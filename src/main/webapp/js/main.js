var tab = $('#tab');

var builtUserInformTable = function(event, url){

  tab.html("");

  var text = "<thead>"
      + "<th>" + "ID" + "</th>"
      + "<th>" + "Name" + "</th>"
      + "<th>" + "Email" + "</th>"
      + "<th>" + "Role" + "</th>"
      + "<th>" + "Edit" + "</th>"
      + "<th>" + "Remove" + "</th>"
      + "</thead><tbody>";

  $.ajax({
    url: url,
    dataType: "json",
    context: document.body
  }).done(function(data) {
    $.each(data, function(key, value){
      console.log(value);
      text += "<tr>"
      + "<td>" + value.id + "</td>"
      + "<td>" + value.name + "</td>"
      + "<td>" + value.email + "</td>"
      + "<td>" + value.userRole + "</td>"
      + "<td>" + "<a class='glyphicon glyphicon-pencil edit' aria-hidden='true' >" + "</td>"
      + "<td>" + "<a class='glyphicon glyphicon-remove remove' aria-hidden='true'>"  + "</td>"
      + "</tr>";
    });
    text += "</tbody>";
    tab.append(text);
  });


};

var removeFunc = function(event){
  console.log("remove");
  console.log(event);
};

var editFunc = function(event){
  console.log("edit");
  console.log(event);
};


$('#publishers').on("click", function(event){
  console.log("Hi");
  builtUserInformTable(event, "/publisher/get");
} );

//$('#operators').on("click", builtUserInformTable);

tab.on('click', 'a.edit', editFunc);
tab.on('click', 'a.remove', removeFunc);




