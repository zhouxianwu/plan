<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ include file="framework/header.jsp" %>
<html>
<head>
    <title>用户修改</title>
    <script>
        $(document).ready(function(){
            //初始化部门
            initDepts();
        });
        function initDepts(){
            var options = new Array();
            var deptId = "${user.deptId}";
            $.ajax({
                url:"getDepts.do",
                dataType:"json",
                success:function(result){
                    if (result == "" || result == "{}" || result == "[]" || result == "null") {
                        alert("未获取到部门");
                    } else{
                        for(var dept in  result){
                            options.push("<option value=\"")
                            options.push(result[dept].id)
                            if(deptId = (result[dept].deptId)){
                                options.push("\" selected>");
                            }else{
                                options.push("\">");
                            }
                            options.push(result[dept].name);
                            options.push("</option>");
                        }
                    }
                    $("#deptId").html(options.join(""))
                }
            });
        }
        //表单校验
        function validateForm(){
            if($("#passWord").val()==""){
                alert("密码不能为空");
                return;
            }
            if($("#realName").val()==""){
                alert("真实姓名不能为空");
                return;
            }
            if($("#deptId").val()==""){
                alert("部门不能为空");
                return;
            }
        }
    </script>
</head>
<body>
<div id="content">
    <!--breadcrumbs-->
    <div id="content-header">
        <div id="breadcrumb"><a href="index.do" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a>
            <a href="${ctx}/user/userList.do">用户管理</a><a href="${ctx}/user/toUserAdd.do" class="current">用户修改</a></div>
    </div>
    <!--End-breadcrumbs-->

    <div class="widget-box">
        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
            <h5>用户信息修改</h5>
        </div>
        <div class="widget-content nopadding">
            <form id="fm"  action="${ctx}/user/saveUser.do" method="post" enctype="multipart/form-data" class="form-horizontal">
                <div class="control-group">
                    <label class="control-label">用户名:</label>
                    <div class="controls">
                        <input type="input" required="required"  id="userName" name="userName"
                               value="${user.userName}" disabled="disabled"/>
                    </div>
                    <label class="control-label">密码:</label>
                    <div class="controls">
                        <input type="password" required="required" id="passWord" name="passWord" value="${user.passWord}" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">真实姓名:</label>
                    <div class="controls">
                        <input type="input" required="required" id="realName" name="realName"
                               value="${user.realName}" disabled="disabled" />
                    </div>
                    <label class="control-label">角色:</label>
                    <div class="controls">
                        <input type="input"  id="roleName" name="roleName"
                               value="${user.roleName}" disabled="disabled" />
                    </div>
                    <label class="control-label">部门:</label>
                    <div class="controls">
                        <select id="deptId" required="required" name="deptId">

                        </select>
                    </div>
                    <label class="control-label">手机号:</label>
                    <div class="controls">
                        <input type="input" id="telphone" name="telphone" value="${user.telphone}"/>
                    </div>
                    <label class="control-label">邮箱:</label>
                    <div class="controls">
                        <input type="input" id="email" name="email"  value="${user.email}"/>
                    </div>
                </div>
                <div class="form-actions">
                    <button type="button" onclick="validateForm()" class="btn btn-success" >提交</button>
                    <button type="button" onclick="toList()" class="btn btn-success">取消</button>
                </div>
            </form>
        </div>
    </div>


</div>
</body>
<script type="text/javascript">
    function toList(){
        window.location.href="${ctx}/plan/planList.do?currPage=1";
    }
</script>
</html>
<%@ include file="framework/footer.jsp" %>
