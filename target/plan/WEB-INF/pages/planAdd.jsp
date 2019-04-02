<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ include file="framework/header.jsp" %>
<html>
<head>
    <title>添加计划</title>
    <script>
        $(document).ready(function(){
            //初始化部门
            initDepts();
        });
        function initDepts(){
            var options = new Array();
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
                            options.push("\">");
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
            if($("#planName").val()==""){
                alert("计划名称不能为空");
                return;
            }
            if($("#planContent").val()==""){
                alert("计划内容不能为空");
                return;
            }
            var startTime = $("#startTime").val();
            var start = new Date(startTime.replace("-", "/").replace("-", "/"));
            if(start<new Date()){
                alert("开始时间不能晚于当前时间")
                return
            }
            $("#fm").submit();
        }
    </script>
</head>
<body>
<div id="content">
    <!--breadcrumbs-->
    <div id="content-header">
        <div id="breadcrumb"><a href="index.do" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a>
            <a href="${ctx}/user/userList.do">计划管理</a><a href="${ctx}/plan/toAdd.do" class="current">添加计划</a></div>
    </div>
    <!--End-breadcrumbs-->

    <div class="widget-box">
        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
            <h5>添加计划</h5>
        </div>
        <div class="widget-content nopadding">
            <form id="fm"  action="${ctx}/plan/savePlan.do" method="post" enctype="multipart/form-data" class="form-horizontal">
                <div class="control-group">
                    <label class="control-label">计划名称:</label>
                    <div class="controls">
                        <input type="input" required="required"  id="planName" name="planName" />
                    </div>
                    <label class="control-label">计划内容:</label>
                    <div class="controls">
                        <textarea  required="required" id="planContent" name="planContent" ></textarea>
                    </div>
                    <label class="control-label">紧急程度:</label>
                    <div class="controls">
                        <select id="urgency" required="required" name="urgency">
                            <option value="1" >非常紧急</option>
                            <option value="2">紧急</option>
                            <option value="3"selected>普通</option>
                            <option value="4">不紧急</option>
                        </select>
                    </div>
                    <label class="control-label">所属部门:</label>
                    <div class="controls">
                        <select id="deptId" required="required" name="deptId">

                        </select>
                    </div>
                    <label class="control-label">开始时间 :</label>
                    <div class="controls">
                        <input id="startTime" name="startTime" type="text" placeholder="请选择日期" class="laydate-icon span3"
                               onclick="laydate({istime: true, format:'YYYY-MM-DD hh:mm:ss'})" />
                    </div>
                    <label class="control-label">结束时间 :</label>
                    <div class="controls">
                        <input id="endTime" name="endTime" type="text" placeholder="请选择日期" class="laydate-icon span3"
                               onclick="laydate({istime: true, format:'YYYY-MM-DD hh:mm:ss'})"/>
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
<script src="/plan/laydate/laydate.js"></script>
<%@ include file="framework/footer.jsp" %>
