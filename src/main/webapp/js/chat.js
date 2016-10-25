/**
 * Created by Huyuanzhi on 2016/9/29.
 */
var userName;

var chatServer;
var loginServer=io.connect('http://120.25.66.175:9002');

function sendDisconnect() {
    chatServer.disconnect();
}

function sendMessage() {
    var message = $('#msg').val();
    $('#msg').val('');

    var jsonObject = {
        userName: userName,
        message: message
    };
    chatServer.emit('chatevent', jsonObject);
}

function output(message) {
    var currentTime = "<span class='time'>" + moment().format('HH:mm:ss') + "</span>";
    var element = $("<div>" + currentTime + " " + message + "</div>");
    $('#console').append(element);
}

$(function(){
    $('.loginTips').hide();
    $('.registerTips').hide();
    $showdialogs($('.dialogs'));
    //$('#main').hide();
    $('#register').hide();
    $('#registerArea').hide();
    $('#goRegister').click(function(){
        $('#title').text("注册");
        $('.register').show();
        $('.login').hide();
    });
    $('#goLogin').click(function(){
        $('#title').text("登陆");
        $('.register').hide();
        $('.login').show();
    });

    $('#btnRegister').click(function(){
        var account=$('#name').val().trim();
        var pass1=$('#pass').val();
        var pass2=$('#pass1').val();
        var nickName=$('#nickname').val().trim();
        if(account.length === 0){
            $('.registerTips').text("请输入登陆账号");
            $('.registerTips').show();
        }
        if(pass1.length === 0){
            $('.registerTips').text("请输入密码");
            $('.registerTips').show();
        }
        if(pass1  != pass2){
            $('.registerTips').text("两次输入密码不一致");
            $('.registerTips').show();
        }
        if(nickName.length === 0){
            $('.registerTips').text("请输入昵称");
            $('.registerTips').show();
        }
        var jsonObject={
            account:account,
            password:pass1,
            nickName:nickName
        }
        loginServer.emit('register', jsonObject);
    });

    $('#btnLogin').click(function(){
        var account=$('#username').val().trim();
        var password=$('#password').val();
        if(account.length === 0){
            $('.loginTips').text("请输入账号");
            $('.loginTips').show();
            return;
        }
        if(password.length === 0){
            $('.loginTips').text("请输入密码");
            $('.loginTips').show();
            return;
        }
        var jsonObject={
            account:account,
            password:password,
        }

        loginServer.emit('login', jsonObject);
    });

});


    loginServer.on('register', function (data) {
        if(data.statues){
            userName=data.ext;
            chatServer=io.connect('http://120.25.66.175:9003')
            $('#close').click();

            chatServer.on('connect', function () {
                output('<span class="connect-msg">Client has connected to the server!</span>');
            });

            chatServer.on('chatevent', function (data) {
                output('<span class="username-msg">' + data.userName==userName?'我':data.userName + ':</span> ' + data.message);
            });
            chatServer.on('disconnect', function () {
                output('<span class="disconnect-msg">The client has disconnected!</span>');
            });
        }else{
            $('.registerTips').text(data.ext+'已被注册，请重新填写');
            $('.registerTips').show();
            $('#name').val('');
            $('#pass').val('');
        }
    });

    loginServer.on('login', function (data) {
        if(data.statues){
            userName=data.ext;
            chatServer=io.connect('http://120.25.66.175:9003')
            $('#close').click();
            chatServer.on('connect', function (data) {
                if(data){
                    output('<span class="connect-msg">'+data+'</span>');
                }
            });

            chatServer.on('chatevent', function (data) {
                output('<span class="username-msg">' + data.userName + ':</span> ' + data.message);
            });
            chatServer.on('disconnect', function () {
                output('<span class="disconnect-msg">The client has disconnected!</span>');
            });
        }else{
            $('.loginTips').text("账号或者密码错误，请重新填写！");
            $('.loginTips').show();
            $('#username').val('');
            $('#password').val('');
        }
    });


$(document).keydown(function (e) {
    if (e.keyCode == 13) {
        $('#send').click();
    }
});