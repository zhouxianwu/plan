<%@page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ include file="framework/header.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <title>权限错误</title>
    <script language="javascript" type="text/javascript" src="${ctx}/My97DatePicker/WdatePicker.js"></script>
    <style>

    </style>
</head>
<body>

<div id="content">
    <!--breadcrumbs-->
    <div id="content-header">
        <div id="breadcrumb"><a href="index.do" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a>
            <a href="#">用户管理</a><a href="#" class="current">用户列表</a></div>
    </div>

    <!--查询-->
    <div style="text-align: center" class="widget-box">
     <label>${error}</label>
    </div>

</div>
</body>
<script type="text/javascript">
    $(document).ready(function(){
        //初始化部门
        initDepts();
    });
    function initDepts(){
        var options = new Array();
        options.push("<option value=\"0\">全部</option>")
        $.ajax({
            url:"getDepts.do",
            dataType:"json",
            success:function(result){
                if (result == "" || result == "{}" || result == "[]" || result == "null") {
                    alert("未获取到标签");
                } else{
                    for(var dept in  result){
                        options.push("<option value=\"")
                        options.push(result[dept].id)
                        options.push("\">");
                        options.push(result[dept].name);
                        options.push("</option>");
                    }
                }
                $("#deptId").html(options.join(""))
            }
        });
    }
    function jump(curr){
        var fm=$("#fm");
        fm.attr("action", "${ctx}/user/userList.do?currPage="+curr);
        $("#fm").submit();
    }
    function toAdd(){
        window.location.href="${ctx}/user/toUserAdd.do";
    }

</script>
</html>
<%@ include file="framework/footer.jsp" %>