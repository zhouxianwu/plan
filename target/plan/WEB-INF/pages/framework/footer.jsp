<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%
    response.setContentType("text/html;charset=UTF-8");
%>
<div class="row-fluid">
    <div id="footer" class="span12"> 2019 &copy; TRT.Inc CMS. Brought to you by <a
            href="#">国家能源集团山东发电工程有限公司</a>
    </div>
</div>

<!--end-Footer-part-->

<script src="/plan/js/excanvas.min.js"></script>
<script src="/plan/js/jquery.min.js"></script>
<script src="/plan/js/jquery.ui.custom.js"></script>
<script src="/plan/js/bootstrap.min.js"></script>
<script src="/plan/js/bootstrap-colorpicker.js"></script>
<script src="/plan/js/bootstrap-datepicker.js"></script>
<script src="/plan/js/masked.js"></script>
<script src="/plan/js/jquery.flot.min.js"></script>
<script src="/plan/js/jquery.flot.resize.min.js"></script>
<script src="/plan/js/jquery.flot.pie.min.js"></script>
<script src="/plan/js/jquery.peity.min.js"></script>
<script src="/plan/js/fullcalendar.min.js"></script>
<script src="/plan/js/matrix.js"></script>
<script src="/plan/js/matrix.dashboard.js"></script>
<script src="/plan/js/jquery.gritter.min.js"></script>
<script src="/plan/js/matrix.interface.js"></script>
<script src="/plan/js/matrix.chat.js"></script>
<script src="/plan/js/jquery.validate.js"></script>
<script src="/plan/js/matrix.form_validation.js"></script>
<script src="/plan/js/jquery.wizard.js"></script>
<script src="/plan/js/jquery.uniform.js"></script>
<script src="/plan/js/select2.min.js"></script>
<script src="/plan/js/matrix.popover.js"></script>
<script src="/plan/js/jquery.dataTables.min.js"></script>
<script src="/plan/js/matrix.tables.js"></script>
<script src="/plan/js/matrix.form_common.js"></script>
<script src="/plan/js/wysihtml5-0.3.0.js"></script>
<script src="/plan/js/bootstrap-wysihtml5.js"></script>
<script src="/plan/js/matrix.charts.js"></script>

<script type="text/javascript">
    // This function is called from the pop-up menus to transfer to
    // a different page. Ignore if the value returned is a null string:
    function goPage(newURL) {

        // if url is empty, skip the menu dividers and reset the menu selection to default
        if (newURL != "") {

            // if url is "-", it is this page -- reset the menu:
            if (newURL == "-") {
                resetMenu();
            }
            // else, send page to designated URL
            else {
                document.location.href = newURL;
            }
        }
    }

    // resets the menu selection upon entry to this page:
    function resetMenu() {
        document.gomenu.selector.selectedIndex = 2;
    }

    $('.textarea_editor').wysihtml5();
</script>
</body>
</html>
