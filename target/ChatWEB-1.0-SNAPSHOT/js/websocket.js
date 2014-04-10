var websocket;
var name;
var apelido;
var data;
var email;
var cidade;
var frase;
var output;
var wsUri;

function replaceAll(string, token, newtoken) {
    while (string.indexOf(token) != -1) {
        string = string.replace(token, newtoken);
    }
    return string;

}
function init(user) {
    wsUri = "ws://" + document.location.host + document.location.pathname + "chat/" + user;
    websocket = new WebSocket(wsUri);

    websocket.onopen = function(evt) {
        onOpen(evt)
    };
    websocket.onmessage = function(evt) {
        onMessage(evt)
    };
    websocket.onerror = function(evt) {
        onError(evt)
    };
    output = document.getElementById("output");
}


function join() {
    frase = textFrase.value;
    name = textNome.value;
    $.ajax({
        type: "POST",
        url: "./cadastro",
        data: {nome: textNome.value, apelido: textApelido.value, email: textEmail.value, cidade: textCidade.value, data: textData.value, frase: textFrase.value},
        success: function(data) {
            if (data == '500') {
                $('#aviso').dialog('open');
            } else {
                var html = ''
                $.each(data, function(key, value) {
                    html += '<p><span class="glyphicon glyphicon-user"></span> ' + value + '</p>';
                });
                historico.innerHTML = html;

                titulo.innerHTML = "<div style='border-radius:50%;-moz-border-radius:50%;-webkit-border-radius:50%;'></div>" + name + " esta online! </br> " + frase;
                $('#bt_cadastrar').hide();
                $('#tabs').show();
                init(name);
                setTimeout(function() {
                    websocket.send(name + " ::user");
                }, 500);

            }


        },
        error: function(data) {
            alert("data.code");
        }
    });



}

function send_message(to) {
    var msg = $('#fieldText' + replaceAll(to, " ", "_")).val();
    var data = new Date();
    var dd = data.getDate();
    var mm = data.getMonth() + 1;
    var yyyy = data.getFullYear();
    var dataHora = dd + "/" + mm + "/" + yyyy + " " + data.toLocaleTimeString();
    websocket.send(dataHora + " || " + name + " >> " + msg + ' to:: ' + to);
    $('#fieldText' + replaceAll(to, " ", "_")).val("");
}

function onOpen() {
    writeToScreen("Connected to " + wsUri);
}

function sair(from, to) {
    var fromId = replaceAll(from.toLowerCase().trim(), " ", "_");
    var toId = replaceAll(to.toLowerCase().trim(), " ", "_");
    $.ajax({
        type: "POST",
        url: "./mensagem",
        data: {fromNome: from, toNome: to, msg: $('#painel' + toId).html()},
        success: function(result) {

            console.log((result));
            var html = ''
            $.each(result, function(key, value) {
                html += '<p><span class="glyphicon glyphicon-user"></span> ' + value + '</p>';
            });
            historico.innerHTML = html;

        },
        error: function(data) {
            alert("data.code");
        }
    });
    console.log(from);
    console.log(to);

    console.log($('#painel' + toId).html());
    $('#dialog' + toId).dialog('close');
}
function infUser(name) {

    $.ajax({
        type: "POST",
        url: "./informacao",
        data: {nome: name},
        success: function(result) {

            console.log(result['data']);
            console.log(result);
            $("#dialogInf").dialog("open");
            infNome.innerHTML = (result['nome']) ? result['nome'] : "";
            infApelido.innerHTML = (result['apelido']) ? result['apelido'] : "";
            infCidade.innerHTML = (result['cidade']) ? result['cidade'] : "";
            infData.innerHTML = (result['data']) ? result['data'] : "";
            infFrase.innerHTML = (result['frase']) ? result['frase'] : "";
            infEmail.innerHTML = (result['email']) ? result['email'] : "";

        },
        error: function(data) {
            alert("data.code");
        }
    });
}

function onMessage(evt) {
    if (evt.data.indexOf("]") != -1) {
        var data = evt.data.replace("[", "").replace("]", "");
        var users = data.split(",");
        var dialog = '';
        var dialogInf = '';
        var html = '';
        for (var x = 0; x < users.length; x++)
        {
            var usuarioId = replaceAll(users[x].toLowerCase().trim(), " ", "_");
            if (users[x].trim() != name.trim()) {
                dialog += '<div id="dialog' + usuarioId + '"  title="Chat - ' + users[x] + '" >'
                        + '<div class="panel panel-default" style="width: 370px">'
                        + '<div class="panel-heading">'
                        + '<a href="#"  data-toggle="modal" onclick="infUser(\'' + users[x].trim() + '\')" data-target="#modal-info"><span class="glyphicon glyphicon-info-sign"></span> Informações</a>'
                        + '</div>'
                        + '<div class="panel-body" id="painel' + usuarioId + '" style="height: 200px;overflow-y: scroll">'
                        + '</div>'
                        + '<div class="panel-footer">'
                        + '<div class="row">'
                        + '<div class="col-xs-10">'
                        + '<textarea class="form-control" rows="3" id="fieldText' + usuarioId + '" style="resize: none;width: 250px"></textarea>'
                        + '</div>'
                        + '<div class="col-xs-2">'
                        + '<p><button type="button" class="btn btn-primary btn-lg" onclick="send_message(\'' + users[x].trim() + '\')" style="float: right">Enviar</button></p>'
                        + '<p><button type="button" class="btn btn-default btn-lg"  onclick="sair(\'' + name.trim() + '\',\'' + users[x].trim() + '\');" style="float: right;margin-top: 5px;width: 78px;">Sair</button></p>'
                        + '</div>'
                        + '</div>'
                        + '</div>'
                        + '</div>'
                        + '</div>';


                html += '<p><div class="dropdown">'
                        + '<a data-toggle="dropdown" href="#"><span class="glyphicon glyphicon-user"></span> ' + users[x].trim() + '</a>'
                        + '<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel" >'
                        + '<p style="margin-left: 5px"><a href="#" class="conversa" onclick="$(\'#dialog' + usuarioId + '\').dialog(\'open\');$(\'#dialog' + usuarioId + '\').dialog({width: 405});" ><span class="glyphicon glyphicon-phone"></span> Iniciar Conversa</a></p>'
                        + '<p style="margin-left: 5px"><a href="#"  data-toggle="modal" onclick="infUser(\'' + users[x].trim() + '\')" data-target="#modal-info"><span class="glyphicon glyphicon-info-sign"></span> Informações</a></p>'
                        + '</ul></div></p>';

            }


        }
        userTab.innerHTML = html;
        dialogsUser.innerHTML = dialog;


        for (var x = 0; x < users.length; x++)
        {
            var usuarioId = replaceAll(users[x].toLowerCase().trim(), " ", "_");
            $("#dialog" + usuarioId).dialog({autoOpen: false});
        }
    } else {
        var msg = evt.data.split("to::")
        var dds = msg[0].split(">>")[1];
        var nameID = replaceAll(name.trim(), " ", "_");
        var from = evt.data.split("||")[1].split(">>")[0].trim();
        var to = replaceAll(msg[1].trim(), " ", "_");
        var dataHora = msg[0].split("||")[0];
        if (to == name.trim()) {
            $("#dialog" + from).dialog("open");
            $('#dialog' + from).dialog({width: 405});
            var historicoMsg = $('#painel' + from).html();
            $('#painel' + from).html(historicoMsg + "<p>" + dataHora + " - <strong>" + from.toUpperCase() + ": </strong>" + dds + "</p>");
        }
        if (from == name.trim()) {
            var historicoMsg = $('#painel' + to).html();
            $('#painel' + to).html(historicoMsg + "<p>" + dataHora + " - <strong>" + from.toUpperCase() + ": </strong>" + dds + "</p>");
        }



    }
}

function onError(evt) {
    writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
}

function writeToScreen(message) {
    output.innerHTML += message + "<br>";
}
