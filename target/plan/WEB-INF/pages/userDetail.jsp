<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ include file="framework/header.jsp" %>
<html>
<head>
    <title>用户信息</title>
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
    </script>
</head>
<body>
<div id="content">
    <!--breadcrumbs-->
    <div id="content-header">
        <div id="breadcrumb"><a href="index.do" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a>
            <a href="${ctx}/user/userList.do">用户管理</a><a href="${ctx}/user/detail.do" class="current">用户信息</a></div>
    </div>
    <!--End-breadcrumbs-->

    <div class="widget-box">
        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
            <h5>用户信息</h5>
        </div>
        <div class="widget-content nopadding">
            <form id="fm"  action="${ctx}/user/saveUser.do" method="post" enctype="multipart/form-data" class="form-horizontal">
                <div class="control-group">
                    <label class="control-label">用户名:</label>
                    <div class="controls">
                        <input type="input" required="required"  id="userName" name="userName"
                               value="${user.userName}" disabled="disabled"/>
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
                        ${user.roleName}
                    </div>
                    <label class="control-label">部门:</label>
                    <div class="controls">
                        <select id="deptId" required="required" name="deptId" disabled="disabled">

                        </select>
                    </div>
                    <label class="control-label">手机号:</label>
                    <div class="controls">
                        <input type="input" id="telphone" name="telphone" value="${user.telphone}" disabled="disabled"/>
                    </div>
                    <label class="control-label">邮箱:</label>
                    <div class="controls">
                        <input type="input" id="email" name="email"  value="${user.email}" disabled="disabled"/>
                    </div>
                </div>
                <div class="form-actions">
                    <button type="button" onclick="toUpdate()" class="btn btn-success" >修改</button>
                </div>
            </form>
        </div>
    </div>


</div>
</body>
<script type="text/javascript">
    function toUpdate(){
        window.location.href="${ctx}/user/update.do";
    }
</script>
</html>
<%@ include file="framework/footer.jsp" %>
