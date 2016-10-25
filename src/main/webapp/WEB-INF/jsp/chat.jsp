<%--
  Created by IntelliJ IDEA.
  User: Huyuanzhi
  Date: 2016/10/13
  Time: 17:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>chat</title>
    <link rel="stylesheet" href="<%=basePath%>css/layui.css" media="all">
</head>
<body>

</body>
<script src="<%=basePath%>js/lib/jquery.js"></script>
<script src="<%=basePath%>js/lib/layui/layui.js"></script>
<script src="<%=basePath%>js/lib/socket.io.js"></script>
<script type="text/javascript">

    layui.use('layim', function () {
        var layim = layui.layim;
        //基础配置
        layim.config({
            //初始化接口
            init: {
                url: '<%=basePath%>group'
                , data: {userId: '${sessionScope.user.uid}'}
            }, mine: {
                "username": "${sessionScope.user.username}" //我的昵称
                , "id": "${sessionScope.user.id}" //我的ID
                , "status": "online" //在线状态 online：在线、hide：隐身
                , "sign": "${sessionScope.user.sign}" //我的签名
                , "avatar": "${sessionScope.user.avatar}" //我的头像
            }
            //查看群员接口
            , members: {
                url: '/layim/json/getMembers.json'
                , data: {}
            }

            , uploadImage: {
                url: '<%=basePath%>user/upload?type=img' //（返回的数据格式见下文）
                , type: 'post' //默认post
            }
            , uploadFile: {
                url: '<%=basePath%>user/upload?type=file' //（返回的数据格式见下文）
                , type: 'post' //默认post
            }

            //,skin: ['aaa.jpg'] //新增皮肤
            //,isfriend: false //是否开启好友
            //,isgroup: false //是否开启群组
            , title: '51cheer' //主面板最小化后显示的名称
            , chatLog: '/layim/demo/chatlog.html' //聊天记录地址
            , find: '/layimdemo/find.html'
        });
        layim.on('ready', function(options){
            var socket = io.connect('http://localhost:9002');
            socket.emit("init", "${sessionScope.user.id}");
            socket.on("chatEvent", function (data) {
                layim.getMessage(data)
            });
            socket.on("playChess",function(data){
                layer.confirm(data.msg, {
                    btn: ['应战','害怕'] //按钮
                }, function(){
                    var message = {
                        mine:"${sessionScope.user.id}",
                        to:data.from
                    };
                    socket.emit("acceptChess",message);
                }, function(){
                    layer.msg('宝宝希望你下次不要怂！', {icon: 1});
                });
            });
            socket.on("acceptChess",function(data){
                alert(data.from);
                layer.open({
                    type: 2,
                    title: '与'+data.againstName+"进行五子棋中。。。",
                    shadeClose: true,
                    shade: false,
                    maxmin: false, //开启最大化最小化按钮
                    area: ['893px', '600px'],
                    content: '<%=basePath%>app/playChess?to='+data.from+"&type="+data.type
                });
            });
            //监听发送消息
            layim.on('sendMessage', function (data) {
                var message = {
                    mine: data.mine,
                    to: {
                        avatar: data.to.avatar,
                        id: data.to.id,
                        type: data.to.type,
                        name: data.to.name,
                        username: data.to.username,
                        sign: data.to.sign
                    }
                };
                socket.emit("chatEvent", message);
            });
            //监听在线状态的切换事件
            layim.on('online', function (data) {
                console.log(data);
            });
            //监听查看群员
            layim.on('members', function (data) {
                console.log(data);
            });

            //监听聊天窗口的切换
            layim.on('chatChange', function (data) {
                console.log(data);
            });

            //给好友列表配置右键
            $(".layim-list-friend .layui-layim-list").on("contextmenu", "li", function(e) {
                e.preventDefault();
                var othis = $(this);
                var html = '<ul data-id="'+ othis[0].id +'" data-index="'+ othis.data('index') +'"><li id="playChess"  layim-event="playChess" data-type="one">邀请玩五子棋</li><li layim-event="menuFriend" data-type="all">加入黑名单</li></ul>';
                var hide = function(){
                    layer.closeAll('tips');
                };
                var play = function(){
                    var message = {
                        mine:"${sessionScope.user.id}",
                        mineName:"${sessionScope.user.username}",
                        to:othis[0].id.replace("layim-friend","")
                    };
                    socket.emit("playChess",message);
                }
                //组件事件冒泡
                var stope = function(e){
                    e = e || window.event;
                    e.stopPropagation ? e.stopPropagation() : e.cancelBubble = true;
                };
                if(othis.hasClass('layim-null')) return;
                layer.tips(html, othis, {
                    tips: 1
                    ,time: 0
                    ,shift: 5
                    ,fix: true
                    ,skin: 'layui-box layui-layim-contextmenu'
                    ,success: function(layero){
                        var stopmp = function(e){ stope(e); };
                        layero.off('mousedown', stopmp).on('mousedown', stopmp);
                    }
                });
                $(document).off('mousedown', hide).on('mousedown', hide);
                $('#playChess').off('click', play).on('click', play);
                $(window).off('resize', hide).on('resize', hide);
            });
        });

    });

</script>
</html>
