<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jfeng" uri="/WEB-INF/jfeng.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<jfeng:appContext var="appContext"/>
<div class="col-lg-6">
    <div class="panel panel-default">
        <div class="panel-heading">广告飞字信息</div>
        <div class="panel-body">
            <spring:form id="marqueeForm" action="${appContext}/advertise/saveOrUpdateMarquee" modelAttribute="marquee" cssClass="form-horizontal">
                <spring:hidden path="id"/>
                <div class="form-group">
                    <label class="col-lg-2 control-label" for="content">广告飞字内容</label>
                    <div class="col-lg-10">
                        <spring:textarea id="content" path="content" cssClass="form-control" data-rule-required="true"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                        <button type="submit" class="btn btn-primary" onclick="saveOrUpdateMarquee()">保存</button>
                    </div>
                </div>
            </spring:form>
        </div>
    </div>
</div>
<script type="text/javascript">

    function saveOrUpdateMarquee(){
        var form = $('#marqueeForm');
        if (form.valid() && confirm("是否确认保存？")) {
            form.submit();
        }
    }
</script>