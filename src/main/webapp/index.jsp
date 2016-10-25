<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<!--
Template Name: Metronic - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.0.3
Version: 1.5.5
Author: KeenThemes
Website: http://www.keenthemes.com/
Purchase: http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes
-->
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8"/>
    <title>登录</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="" name="author"/>
    <meta name="MobileOptimized" content="320">
    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link rel="stylesheet" type="text/css" href="assets/plugins/select2/select2_metro.css"/>
    <!-- END PAGE LEVEL SCRIPTS -->
    <!-- BEGIN THEME STYLES -->
    <link href="assets/css/style-metronic.css" rel="stylesheet" type="text/css"/>
    <link href="assets/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="assets/css/style-responsive.css" rel="stylesheet" type="text/css"/>
    <link href="assets/css/plugins.css" rel="stylesheet" type="text/css"/>
    <link href="assets/css/pages/login-soft.css" rel="stylesheet" type="text/css"/>
    <link href="assets/css/custom.css" rel="stylesheet" type="text/css"/>
    <!-- END THEME STYLES -->
    <link rel="shortcut icon" href=" app/img/favicon.ico"/>


</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="login">
<!-- BEGIN LOGO -->
<div class="logo">
    <%--<img src="assets/img/logo-big.png" alt=""/>--%>
</div>
<!-- END LOGO -->
<!-- BEGIN LOGIN -->
<div class="content">
    <!-- BEGIN LOGIN FORM -->
    <form class="login-form" action="login" method="post">
        <h3 class="form-title">登入</h3>
        <div class="alert alert-danger display-hide">
            <button class="close" data-close="alert"></button>
            <span>
				 输入您的用户名和密码
			</span>
        </div>
        <div class="form-group">
            <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
            <label class="control-label visible-ie8 visible-ie9">用户名</label>
            <div class="input-icon">
                <i class="fa fa-user"></i>
                <input name="email" id="username" size="25"  class="form-control placeholder-no-fix"
                       type="text" autocomplete="off" placeholder="用户名"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">密码</label>
            <div class="input-icon">
                <i class="fa fa-lock"></i>
                <input name="password" id="password" size="25"  class="form-control placeholder-no-fix"
                       type="password" autocomplete="off" placeholder="密码"/>
            </div>
        </div>
        <div class="form-actions">
           <%-- <label class="checkbox">
                <input type="checkbox" name="remember" value="1"/> 记住我 </label>--%>
            <button id="loginBut" type="submit" class="btn blue">
                登录 <i class="m-icon-swapright m-icon-white"></i>
            </button>
            <span style="margin-left: 10px;">
                <a href="javascript:;" id="forget-password">忘记密码？</a>
            </span>
        </div>
        <div>
            <p>或者使用社交账号登入&nbsp;
                <a href="<%=basePath%>/qq" onclick="layer.msg('正在通过QQ登入',{icon:16,shade:0.1,time:0})" title="QQ登陆">
                    <img src="assets/img/qq.png">
                </a>
            </p>
        </div>
        <%--<div class="forget-password">
            <span>或者使用社交账号登陆</span>
            点击 <a href="javascript:;" id="">这里</a> 重置您的密码.

        </div>--%>
        <%--<div class="forget-password">
            <h4>忘记密码 ?</h4>
            <p>点击 <a href="javascript:;" id="forget-password">这里</a> 重置您的密码.
            </p>
        </div>--%>
        <div class="create-account">
            <p>
                还没有账号 ?&nbsp; <a href="javascript:;" id="register-btn">创建一个账号</a>
            </p>
        </div>
    </form>
    <!-- END LOGIN FORM -->
    <!-- BEGIN FORGOT PASSWORD FORM -->
    <form class="forget-form" action="index.html" method="post">
        <h3>忘记密码 ?</h3>
        <p>
            请输入您的电子邮箱地址来重置您的密码.
        </p>
        <div class="form-group">
            <div class="input-icon">
                <i class="fa fa-envelope"></i>
                <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="Email"
                       name="email"/>
            </div>
        </div>
        <div class="form-actions">
            <button type="button" id="back-btn" class="btn">
                <i class="m-icon-swapleft"></i> 返回
            </button>
            <button type="submit" class="btn blue pull-right">
                提交 <i class="m-icon-swapright m-icon-white"></i>
            </button>
        </div>
    </form>
    <!-- END FORGOT PASSWORD FORM -->
    <!-- BEGIN REGISTRATION FORM -->
    <form class="register-form" action="/user/register" method="post">
        <h3>注 册</h3>
        <p>
            请输入您的个人信息:
        </p>
        <div class="form-group">
            <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
            <label class="control-label visible-ie8 visible-ie9">Email</label>
            <div class="input-icon">
                <i class="fa fa-envelope"></i>
                <input class="form-control placeholder-no-fix" type="text" id="email" placeholder="邮箱" name="email"/>
            </div>
        </div>
        <p>
            请输入账号信息:
        </p>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">昵称</label>
            <div class="input-icon">
                <i class="fa fa-user"></i>
                <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="昵称"
                       name="username"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">性别</label>
            <select name="gender" id="select2_sample4" class="select2 form-control">
                <option value=""></option>
                <option value="male">男</option>
                <option value="female">女</option>
            </select>
        </div>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">密码</label>
            <div class="input-icon">
                <i class="fa fa-lock"></i>
                <input class="form-control placeholder-no-fix" type="password" autocomplete="off" id="register_password"
                       placeholder="密码" name="password"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">再次输入密码</label>
            <div class="controls">
                <div class="input-icon">
                    <i class="fa fa-check"></i>
                    <input class="form-control placeholder-no-fix" type="password" autocomplete="off"
                           placeholder="再次输入密码" name="rpassword"/>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label>
                <input type="checkbox" name="tnc"/> 我同意 <a href="#">服务条款</a> 和 <a href="#">隐私政策</a>
            </label>
            <div id="register_tnc_error">
            </div>
        </div>
        <div class="form-actions">
            <button id="register-back-btn" type="button" class="btn">
                <i class="m-icon-swapleft"></i> 返回
            </button>
            <button type="submit" id="register-submit-btn" class="btn blue pull-right">
                注册 <i class="m-icon-swapright m-icon-white"></i>
            </button>
        </div>
    </form>
    <!-- END REGISTRATION FORM -->
</div>
<!-- END LOGIN -->
<!-- BEGIN COPYRIGHT -->
<div class="copyright">
    2016 &copy; darkshadow
</div>
<!-- END COPYRIGHT -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="assets/plugins/respond.min.js"></script>
<script src="assets/plugins/excanvas.min.js"></script>
<![endif]-->
<script src="assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>

<script src="assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="assets/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="assets/plugins/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>
<script src="assets/plugins/backstretch/jquery.backstretch.min.js" type="text/javascript"></script>
<script type="text/javascript" src="assets/plugins/select2/select2.min.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="assets/scripts/app.js" type="text/javascript"></script>
<script src="assets/scripts/login-soft.js" type="text/javascript"></script>

<script src="app/lib/security/sha256.js" type="text/javascript"></script>
<script src="js/lib/layer/layer.js" type="text/javascript"></script>

<!-- END PAGE LEVEL SCRIPTS -->
<script>
    jQuery(document).ready(function () {
        App.init();
        Login.init();
    });
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>