<%@page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ include file="framework/header.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <title>用户列表</title>
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
    <div class="widget-box">
        <div class="widget-title"><span class="icon"> <i class="icon-align-justify"></i> </span>
            <h5>条件查询</h5>
        </div>
        <div class="widget-content nopadding">
            <form id="fm" class="form-horizontal" action="${ctx}/user/userList.do?currPage=1" method="post"
                  enctype="multipart/form-data">
                <div class="control-group">
                    <label class="control-label">用户名：</label>
                    <div class="controls">
                        <input type="text"  name="userName"
                               value="${par.userName}"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">部门：</label>
                    <div class="controls">
                        <select id="deptId" name="deptId">

                        </select>
                    </div>
                </div>
                <div class="contorl-group">
                    <label class="control-label">审核状态 :</label>
                    <div class="controls">
                        <input  name="valid" value="" type="radio"  placeholder=" "/>全部&nbsp;&nbsp;&nbsp;&nbsp;
                        <input  name="valid" value="1" type="radio"  placeholder=" "/>审核通过&nbsp;&nbsp;&nbsp;&nbsp;
                        <input  name="valid" value="0" type="radio"  placeholder=" "/>待审核
                    </div>
                </div>
                <div class="form-actions">
                    <button type="submit" class="btn btn-success">查询</button>
                    <%--<button type="submit" class="btn btn-danger">取消</button>--%>
                    <button type="button" onclick="toAdd()" class="btn btn-success">添加用户</button>
                </div>
            </form>
        </div>
    </div>
    <!--查询END-->

    <!--End-breadcrumbs-->
    <div class="widget-box">
        <div class="widget-title">
            <h5>用户列表</h5>
        </div>
        <div class="widget-content" style="padding-bottom: 0px;">
            <table id="table-article" class="table table-bordered data-table">
                <thead>
                <tr>
                    <td>序号</td>
                    <td>用户名</td>
                    <td>真实姓名</td>
                    <td>部门</td>
                    <td>角色</td>
                    <td>手机</td>
                    <td>邮箱</td>
                    <td>审核状态</td>
                    <td>注册时间</td>
                    <td>操作</td>
                </tr>
                </thead>
                <tbody id="tableData">
                <c:set var="index" value="0" />
                <c:forEach var="user" items="${pageInfo.result}" varStatus="status">
                    <tr>
                        <c:set var="index" value="${index+1}" />
                        <td>${index}</td>
                        <td>${user.userName}</td>
                        <td>${user.realName}</td>
                        <td>${user.deptName}</td>
                        <td>${user.roleName}</td>
                        <td>${user.telphone}</td>
                        <td>${user.email}</td>
                        <c:choose>
                            <c:when test="${user.valid == 0}">
                                <td>待审核</td>
                            </c:when>
                            <c:otherwise>
                                <td>审核通过</td>
                            </c:otherwise>
                        </c:choose>
                        <td>${user.createDate}</td>
                        <td>
                            <p>
                                <c:if test="${user.valid == 0}">
                                    <button class="btn btn-mini btn-success" id="check" onclick="javascript:if(confirm('确定要通过该用户注册吗?'))location='${ctx}/user/checkUser.do?id=${user.id}'">通过审核</button>
                                </c:if>
                                <button class="btn btn-mini btn-danger" id="delete" onclick="javascript:if(confirm('确定要删除该用户吗?'))location='${ctx}/user/delete.do?id=${user.id}'">删除</button>
                            </p>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="pager">${pageInfo.pageHtml}</div
        </div>
    </div>

</div>
</body>
<script type="text/javascript">
    $(document).ready(function(){
        //初始化部门
        initDepts();
        selectOption();
    });

    function selectOption(){
        $("input[name='valid'][value='${params.valid}']").attr('checked','true');
    }

    function initDepts(){
        var options = new Array();
        options.push("<option value=\"\">全部</option>")
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
                        if("${params.deptId}" == result[dept].id){
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