<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!--[if lt IE 7 ]><html lang="en" class="ie6 ielt7 ielt8 ielt9"><![endif]-->
<!--[if IE 7 ]><html lang="en" class="ie7 ielt8 ielt9"><![endif]-->
<!--[if IE 8 ]><html lang="en" class="ie8 ielt9"><![endif]-->
<!--[if IE 9 ]><html lang="en" class="ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en">
<!--<![endif]-->

<head>
    <title>计划管理系统</title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="/plan/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/plan/css/bootstrap-responsive.min.css"/>
    <link rel="stylesheet" href="/plan/css/matrix-login.css"/>
    <link href="/plan/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>

</head>
<body>
<div id="loginbox">
    <div style="font-size: 22px; text-align: center;color: #DCDCDC;font-family: '微软雅黑',serif;letter-spacing:3px">
        山东国电发电工程计划管理系统
    </div>
    <br>

    <form id="loginform" class="form-vertical" action="login.do" method="post">
        <div class="control-group">
            <div class="controls">
                <div class="main_input_box">
                    <span class="add-on bg_lg"><i class="icon-user"></i></span><input name="username"
                                                                                      type="text"
                                                                                      placeholder="Username"/>
                </div>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <div class="main_input_box">
                    <span class="add-on bg_ly"><i class="icon-lock"></i></span><input name="password"
                                                                                      type="password"
                                                                                      placeholder="Password"/>
                </div>
            </div>
        </div>
        <div class="form-actions">
            <span class="pull-left"><a href="../user/toUserAdd.do" class="flip-link btn btn-info" id="to-regest">没有账户？点这注册</a></span>
            <span class="pull-right"><input type="submit" class="btn btn-success" value=" 登录 "/></span>
        </div>
    </form>
</div>

<script src="/plan/js/jquery.min.js"></script>
<script src="/plan/js/matrix.login.js"></script>
<script>
    $(document).ready(function(){
        loginChecked();
    });
    function loginChecked(){
        if('NoUser'=='${message}'){
            alert('用户名不存在');
        }
        if('PassError'=='${message}'){
            alert('密码错误');
        }
        if('NoCheck'=='${message}'){
            alert('用户注册后需经过管理员审核，才能生效，请联系管理员！');
        }
    }
</script>
</body>

</html>