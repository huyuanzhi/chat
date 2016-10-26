<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Huyuanzhi
  Date: 2016/10/25
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title></title>
    <link rel="stylesheet" href="<%=basePath%>css/layui.css" media="all">
    <style type="text/css">
        body {
            margin: 10px;
        }
    </style>
    <script src="<%=basePath%>js/lib/jquery.js"></script>
    <script src="<%=basePath%>js/lib/layer/layer.js"></script>
    <script src="<%=basePath%>js/lib/socket.io.js"></script>
    <script type="text/javascript">
        var socket = io.connect('http://localhost:9002');
        var type = '${type}';
        var turn = true;
        var canvas;
        var context;
        var isWell = false;//设置该局棋盘是否赢了，如果赢了就不能再走了
        var img_b = new Image();
        img_b.src = "<%=basePath%>img/b.png";//白棋图片
        var img_w = new Image();
        img_w.src = "<%=basePath%>img/w.png";//黑棋图片

        var chessData = new Array(15);//这个为棋盘的二维数组用来保存棋盘信息，初始化0为没有走过的，1为白棋走的，2为黑棋走的
        for (var x = 0; x < 15; x++) {
            chessData[x] = new Array(15);
            for (var y = 0; y < 15; y++) {
                chessData[x][y] = 0;
            }
        }

        socket.on("chessClick",function(data){
            if('${sessionScope.user.id}'==data.to){
                turn = true;
                drawChess(data.type,data.posX,data.posY);
            }
        });

        socket.on("isWell",function(data){
            if(data.to == '${sessionScope.user.id}'){
                isWell = true;
                if (data.chess == '${type}') {
                    layer.msg("恭喜，你赢了！",{icon:6},function(){
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    });
                }
                else {
                    layer.msg("很遗憾，再接再厉！",{icon:5},function(){
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    });
                }
            }
        });

        function drawRect() {//页面加载完毕调用函数，初始化棋盘
            canvas = document.getElementById("canvas");
            context = canvas.getContext("2d");

            for (var i = 0; i <= 640; i += 40) {//绘制棋盘的线
                context.beginPath();
                context.moveTo(0, i);
                context.lineTo(640, i);
                context.closePath();
                context.stroke();

                context.beginPath();
                context.moveTo(i, 0);
                context.lineTo(i, 640);
                context.closePath();
                context.stroke();
            }
        }
        function play(e) {//鼠标点击时发生
            var x = parseInt((e.clientX - 20) / 40);//计算鼠标点击的区域，如果点击了（65，65），那么就是点击了（1，1）的位置
            var y = parseInt((e.clientY - 20) / 40);

            if(isWell){
                layer.msg("已经结束了再来一盘吧！",{icon:6});
                return;
            }

            if (chessData[x][y] != 0) {//判断该位置是否被下过了
                layer.msg("你不能在这个位置下棋!");
                return;
            }

            if(turn){
                turn = false;
                drawChess(type, x, y);
                var msg = {
                    type:type,
                    posX:x,
                    posY:y,
                    to:'${to}'
                };
                socket.emit("chessClick",msg);
            }else{
                layer.msg("先让对手下完吧！");
            }

        }

        function drawChess(chess, x, y) {//参数为，棋（1为白棋，2为黑棋），数组位置
            if (x >= 0 && x < 15 && y >= 0 && y < 15) {
                if (chess == 1) {
                    context.drawImage(img_w, x * 40 + 20, y * 40 + 20);//绘制白棋
                    chessData[x][y] = 1;
                }
                else {
                    context.drawImage(img_b, x * 40 + 20, y * 40 + 20);
                    chessData[x][y] = 2;
                }
                judge(x, y, chess);
            }
        }
        function judge(x, y, chess) {//判断该局棋盘是否赢了
            var count1 = 0;
            var count2 = 0;
            var count3 = 0;
            var count4 = 0;

            //左右判断
            for (var i = x; i >= 0; i--) {
                if (chessData[i][y] != chess) {
                    break;
                }
                count1++;
            }
            for (var i = x + 1; i < 15; i++) {
                if (chessData[i][y] != chess) {
                    break;
                }
                count1++;
            }
            //上下判断
            for (var i = y; i >= 0; i--) {
                if (chessData[x][i] != chess) {
                    break;
                }
                count2++;
            }
            for (var i = y + 1; i < 15; i++) {
                if (chessData[x][i] != chess) {
                    break;
                }
                count2++;
            }
            //左上右下判断
            for (var i = x, j = y; i >= 0, j >= 0; i--, j--) {
                if (chessData[i][j] != chess) {
                    break;
                }
                count3++;
            }
            for (var i = x + 1, j = y + 1; i < 15, j < 15; i++, j++) {
                if (chessData[i][j] != chess) {
                    break;
                }
                count3++;
            }
            //右上左下判断
            for (var i = x, j = y; i >= 0, j < 15; i--, j++) {
                if (chessData[i][j] != chess) {
                    break;
                }
                count4++;
            }
            for (var i = x + 1, j = y - 1; i < 15, j >= 0; i++, j--) {
                if (chessData[i][j] != chess) {
                    break;
                }
                count4++;
            }

            if (count1 >= 5 || count2 >= 5 || count3 >= 5 || count4 >= 5) {
                isWell = true;
                var msg={
                    to:'${to}',
                    chess:chess
                }
                socket.emit("isWell",msg);
                if (chess == '${type}') {
                    layer.msg("恭喜，你赢了！",{icon:6},function(){
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    });
                }
                else {
                    layer.msg("很遗憾，再接再厉！",{icon:5},function(){
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    });
                }
            }
        }
    </script>
</head>
<body onload="drawRect()">
<div>
    <canvas width="640" id="canvas" onmousedown="play(event)" height="640">你的浏览器不支持HTML5 canvas  ，请使用 google chrome 浏览器 打开.
    </canvas>
</div>

</body>
</html>
