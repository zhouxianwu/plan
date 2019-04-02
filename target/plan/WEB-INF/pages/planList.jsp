<%@page language="java" contentType="text/html;charset=UTF-8" %>
<%@page import="com.chn.energy.model.Plan" %>
<%@ include file="framework/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="utf-8">
    <title>计划管理</title>
    <style>

    </style>
    <script>
        $(document).ready(function(){
            initStatus();
            initDepts();
        });

        function jump(current){
            $("#currPage").val(current);
            $("#articleQuery").submit()
        }

        function initStatus(){
            var options = new Array();
            options.push("<option value=\"\">全部</option>")
            $.ajax({
                url:"getAllStatus.do",
                dataType:"json",
                success:function(result){
                    if (result == "" || result == "{}" || result == "[]" || result == "null") {
                        alert("未获取到标签");
                    } else{
                        for(var status in  result){
                            options.push("<option value=\"")
                            options.push(result[status].id)
                            if("${params.status}" == result[status].id){
                                options.push("\" selected>");
                            }else{
                                options.push("\">");
                            }
                            options.push(result[status].name);
                            options.push("</option>");
                        }
                    }
                    $("#status").html(options.join(""))
                }
            });
        }

        function initDepts(){
            var options = new Array();
            options.push("<option value=\"\">全部</option>")
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

        // 提交到后台操作删除
        function articleDelete(id) {
            if (confirm("确定删除吗？")) {
                var url = "taks/delete.do?id=" + id;
                $.getJSON(url, function (data) {
                    if (data) {
                        window.location.reload();
                        alert("删除成功！");
                    }
                    else {
                        alert("操作发生异常,删除失败！");
                    }
                })
            }
        }
    </script>
</head>
<body>
<div id="content">
    <!--breadcrumbs-->
    <div id="content-header">
        <div id="breadcrumb"><a href="index.do" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a>
            <a href="#">计划管理</a><a href="#" class="current">计划列表</a></div>
    </div>
    <div class="widget-box">
        <div class="widget-title">
            <span class="icon"> <i class="icon-align-justify"></i> </span>
            <h5>条件查询</h5>
        </div>
        <div class="widget-content">
            <form id="articleQuery" action="/plan/plan/planList.do" method="post" class="form-horizontal">
                <input id="currPage" name="currPage" type="hidden" value="1">
                <div class="control-group">
                    <label class="control-label">计划名称 :</label>
                    <div class="controls">
                        <input id="planName" name="planName" value="${params.planName}" type="text" class="span3" placeholder=" "/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">创建者 :</label>
                    <div class="controls">
                        <input id="creatorName" name="creatorName" value="${params.creatorName}" type="text" class="span3" placeholder=" "/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">部门：</label>
                    <div class="controls">
                        <select id="deptId" name="deptId">

                        </select>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">状态 :</label>
                    <div class="controls">
                        <select id="status" name="status">

                        </select>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">结束时间 :</label>
                    <div class="controls">
                        <input id="startETime" name="startETime" type="text" placeholder="请选择日期" class="laydate-icon span3"
                               onclick="laydate({istime: true, format:'YYYY-MM-DD hh:mm:ss'})" value="${params.startETime}"/>
                        <input id="endETime" name="endETime" type="text" placeholder="请选择日期" class="laydate-icon span3"
                               onclick="laydate({istime: true, format:'YYYY-MM-DD hh:mm:ss'})" value="${params.endETime}"/>
                    </div>
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn btn-success" onclick="">查询</button>
                    <button type="button" class="btn btn-success" onclick="window.location='toAdd.do'">新增</button>
                </div>
            </form>
        </div>
    </div>
    <!--End-breadcrumbs-->
    <div class="widget-box">
        <div class="widget-title">
            <span class="icon"> <i class="icon-align-justify"></i> </span>
            <h5>计划管理</h5>
        </div>
        <div class="widget-content">

            <table id="table-article" class="table table-bordered data-table">
                <thead>
                <tr>
                    <td>编号</td>
                    <td>标题</td>
                    <td>计划内容</td>
                    <td>创建人</td>
                    <td>部门</td>
                    <td>紧急程度</td>
                    <td>状态</td>
                    <td>开始时间</td>
                    <td>结束时间</td>
                    <td>创建时间</td>
                    <td>更新时间</td>
                    <td>操作</td>
                </tr>
                </thead>
                <tbody id="tableData">
                <c:forEach var="plan" items="${pageInfo.result}" varStatus="status">
                    <tr>
                        <td> ${plan.planId}</td>
                        <td><a href="detail.do?id=${plan.planId}"><b>${plan.planName}</b></a></td>
                        <td>${plan.planContent}</td>
                        <td>${plan.creatorName}</td>
                        <td>${plan.deptName}</td>
                        <td>${plan.urgencyDesc}</td>
                        <td>${plan.statusDesc}</td>
                        <td>${plan.startTime}</td>
                        <td>${plan.endTime}</td>
                        <td>${plan.createTime}</td>
                        <td>${plan.updateTime}</td>
                        <td></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="pager">${pageInfo.pageHtml}</div>
        </div>
    </div>
</div>
</body>
</html>
<script src="/plan/laydate/laydate.js"></script>
<%@ include file="framework/footer.jsp" %>