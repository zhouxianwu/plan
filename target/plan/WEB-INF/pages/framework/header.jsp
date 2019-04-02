<%@ page import="com.chn.energy.model.User" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%
    response.setContentType("text/html;charset=UTF-8");
%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<!--[if lt IE 7 ]><html lang="en" class="ie6 ielt7 ielt8 ielt9"><![endif]-->
<!--[if IE 7 ]><html lang="en" class="ie7 ielt8 ielt9"><![endif]-->
<!--[if IE 8 ]><html lang="en" class="ie8 ielt9"><![endif]-->
<!--[if IE 9 ]><html lang="en" class="ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en">
<!--<![endif]-->

<head>
    <title>国家能源集团计划管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="/plan/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/plan/css/bootstrap-responsive.min.css"/>
    <link rel="stylesheet" href="/plan/css/fullcalendar.css"/>
    <link rel="stylesheet" href="/plan/css/colorpicker.css"/>
    <link rel="stylesheet" href="/plan/css/datepicker.css"/>
    <link rel="stylesheet" href="/plan/css/uniform.css"/>
    <link rel="stylesheet" href="/plan/css/select2.css"/>
    <link rel="stylesheet" href="/plan/css/matrix-style.css"/>
    <link rel="stylesheet" href="/plan/css/matrix-media.css"/>
    <link rel="stylesheet" href="/plan/css/bootstrap-wysihtml5.css"/>
    <link rel="stylesheet" href="/plan/font-awesome/css/font-awesome.css"/>
    <link rel="stylesheet" href="/plan/css/jquery.gritter.css"/>
    <link rel="stylesheet" href="/plan/css/dataTables.bootstrap.min.css"/>
    <link rel="stylesheet" href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' type='text/css'>
    <script src="/plan/js/jquery.min.js"></script>
    <script src="/plan/js/jquery.ui.custom.js"></script>
    <script src="/plan/js/jquery.peity.min.js"></script>
    <script src="/plan/js/bootstrap.min.js"></script>
    <script src="/plan/js/jquery.uniform.js"></script>
    <script src="/plan/js/jquery-confirm.min.js"></script>
    <script src="/plan/js/jquery.dataTables.min.js"></script>
    <script src="/plan/js/highcharts.js"></script>
    <script src="/plan/js/exporting.js"></script>
    <script src="/plan/js/export-excel.js"></script>
    <script src="/plan/js/angular.js"></script>
    <script src="/plan/js/angular-route.js"></script>
    <script src="/plan/js/angular-resource.js"></script>
</head>
<body>

<!--Header-part-->
<div id="header">
    <h1><a href="dashboard.html">国家能源集团计划管理系统</a></h1>
</div>
<!--close-Header-part-->

<!--top-Header-menu-->
<div id="user-nav" class="navbar navbar-inverse">
    <ul class="nav">
        <li class="dropdown" id="profile-messages">
            <a title="" href="#" data-toggle="dropdown">
                <i class="icon icon-user"></i>
                <span class="text"> ${sessionScope.loginedUser.realName}，您好！</span>
            </a>

        </li>
        <li class="">
            <a title="" href="/plan/user/detail.do"><i class="icon icon-cog"></i> <span class="text">个人信息</span></a>
        </li>
        <li class="">
            <a title="" href="/plan/user/toUpdate.do"><i class="icon icon-cog"></i> <span class="text">修改个人信息|密码</span></a>
        </li>
        <li class="">
            <a title="" href="/plan/login/loginOut.do">
                <i class="icon icon-share-alt"></i>
                <span class="text">退出登录</span>
            </a>
        </li>
    </ul>
</div>
<!--close-top-Header-menu-->

<!--start-top-serch-->
<div id="search">
    <input type="text" size="800" placeholder="Search here..."/>
    <button type="submit" class="tip-bottom" title="Search"><i class="icon-search icon-white"></i></button>
</div>
<!--close-top-serch-->

<!--sidebar-menu-->
<div id="sidebar">
    <ul>
        <li id="articleMenu" class="submenu">
            <a href="#">
                <i class="icon icon-road"></i>
                <span>计划管理</span>
                <span class="label label-important">2</span>
            </a>
            <ul>
                <li id="plan"><a href="${ctx}/plan/planList.do">&nbsp; &nbsp; 计划列表</a></li>
                <li id="planAdd"><a href="${ctx}/plan/toAdd.do">&nbsp; &nbsp; 计划录入</a></li>
            </ul>
        </li>

        <li id="platformMenu" class="submenu">
            <a href="#">
                <i class="icon icon-asterisk"></i>
                <span>用户管理</span>
                <span class="label label-important">2</span>
            </a>
            <ul>
                <li id="userManager"><a href="${ctx}/user/userList.do?currPage=1">&nbsp; &nbsp; 用户列表</a></li>
                <li id="publishManager"><a href="${ctx}/user/toUserAdd.do?currPage=1">&nbsp; &nbsp; 用户注册</a></li>
            </ul>
        </li>
    </ul>
</div>

<script language="JavaScript">
    $(document).ready(function(){

    });
</script>

<!--sidebar-menu-->
