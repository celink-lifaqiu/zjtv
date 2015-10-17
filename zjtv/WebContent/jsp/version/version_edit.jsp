<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jfeng" uri="/WEB-INF/jfeng.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<jfeng:appContext var="appContext"/>
<div class="col-lg-7">
    <div class="panel panel-default">
        <div class="panel-heading">版本信息</div>
        <div class="panel-body">
            <spring:form id="versionForm" action="${appContext}/version/saveOrUpdateVersion" modelAttribute="version" cssClass="form-horizontal">
                <spring:hidden path="id"/>
                <div class="form-group">
                    <label class="col-lg-2 control-label" for="filename">文件名称</label>
                    <div class="col-lg-10">
                        <spring:input id="filename" path="filename" cssClass="form-control" data-rule-required="true"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label" for="platform">平台</label>
                    <div class="col-lg-10">
                        <spring:input id="platform" path="platform" cssClass="form-control" data-rule-required="true"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label" for="versionCode">版本号</label>
                    <div class="col-lg-10">
                        <spring:input id="versionCode" path="versionCode" cssClass="form-control" data-rule-required="true"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label" for="majorVersion">主版本号</label>
                    <div class="col-lg-10">
                        <spring:input id="majorVersion" path="majorVersion" cssClass="form-control"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label" for="minorVersion">子版本号</label>
                    <div class="col-lg-10">
                        <spring:input id="minorVersion" path="minorVersion" cssClass="form-control"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label" for="revisionVersion">修订版本号</label>
                    <div class="col-lg-10">
                        <spring:input id="revisionVersion" path="revisionVersion" cssClass="form-control"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label" for="downloadUrl">下载链接</label>
                    <div class="col-lg-10">
                        <spring:input id="downloadUrl" path="downloadUrl" cssClass="form-control" data-rule-required="true"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label" for="type">类型</label>
                    <div class="col-lg-10" id="type"  data-rule-required="true">
                        <spring:radiobutton path="type" value="1" label="正式版本"/>
                        <spring:radiobutton path="type" value="2" label="测试版本"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label" for="appstoreOnline">苹果商店号</label>
                    <div class="col-lg-10">
                        <spring:input id="appstoreOnline" path="appstoreOnline" cssClass="form-control"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label" for="appstoreDownloadurl">苹果商店在线下载链接</label>
                    <div class="col-lg-10">
                        <spring:input id="appstoreDownloadurl" path="appstoreDownloadurl" cssClass="form-control" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label" for="appstoreVersionCode">苹果商店版本号</label>
                    <div class="col-lg-10">
                        <spring:input id="appstoreVersionCode" path="appstoreVersionCode" cssClass="form-control" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                        <button type="submit" class="btn btn-primary" onclick="saveOrUpdateVersion()">保存</button>
                        <button type="button" class="btn btn-default" onclick="back()">返回</button>
                    </div>
                </div>
            </spring:form>
        </div>
    </div>
</div>
<script type="text/javascript">
    function saveOrUpdateVersion(){
        var form = $('#versionForm');
        if (form.valid() && confirm("是否确认保存？")) {
            form.submit();
        }
    }

    function back(){
        location.href = "<jfeng:appContext/>/version/versioninfo";
    }
</script>