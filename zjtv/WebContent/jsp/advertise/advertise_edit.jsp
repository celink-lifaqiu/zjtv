<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jfeng" uri="/WEB-INF/jfeng.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<style>
    /* image box */
    .gallery a img {
        width: 250px;
        height: 280px;
    }
    .gallery .box{
        margin: 5px;
        height: 320px;
    }
    .gallery .box-main{
        height: 290px;
    }
    .gallery .box-footer{
        height: 30px;
    }
</style>
<jfeng:appContext var="appContext"/>
<div class="col-lg-6">
    <div class="panel panel-default">
        <div class="panel-heading">广告信息</div>
        <div class="panel-body">
            <spring:form id="advertiseForm" action="${appContext}/advertise/saveOrUpdateAdvertise" modelAttribute="advertise" cssClass="form-horizontal">
                <spring:hidden path="id"/>
                <input type="hidden" id="iconHash" name="iconHash">
                <div class="form-group">
                    <label class="col-lg-2 control-label" for="title">广告标题</label>
                    <div class="col-lg-10">
                        <spring:input id="title" path="title" cssClass="form-control" data-rule-required="true"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                        <c:if test="${advertise.id eq null}">
                            <label class="checkbox inline">
                                <input type="checkbox" name="continue" checked="checked" value="true"> 是否继续添加？
                            </label>
                        </c:if>
                        <button type="submit" class="btn btn-primary" onclick="saveOrUpdateAdvertise()">保存</button>
                        <button type="button" class="btn btn-default" onclick="back()">返回</button>
                    </div>
                </div>
            </spring:form>
        </div>
    </div>
</div>
<div class="col-lg-5">
    <div class="panel panel-default">
        <div class="panel-heading">商家ICON</div>
        <div class="panel-body gallery" id="preview">
            <jsp:include page="advertise_image.jsp"/>
        </div>
    </div>
</div>
<script type="text/javascript">
$(document).ready(function(){
    initImgDrop(${advertise.id});
 });
 function initImgDrop(id){
     $("#img_dropzone").dropzone({ url: "http://up.qiniu.com/",
         clearBeforePreview:true,
         method: "post",
         paramName: "file",
         maxFilesize: 5,
         params:{"token":"${uptoken}"},
         init:function(){
             this.on(Dropzone.SUCCESS, function(file, resp){
                 if(resp.error != null && resp.error != undefined){
                     alert(resp.error);
                 }
                 $('#iconHash').val(resp.hash);
                 ajaxRefreshAsync("preview", "<jfeng:appContext/>/advertise/previewImage", "", "hash="+resp.hash, function(){
                     initImgDrop(id);
                 });
             });
             this.on(Dropzone.ERROR, function(file, message){
                 alert(message);
             });
             this.on("sending", function(file, xhr, formData){
             });
             this.previewsContainer = $("#preview")[0];
         }
     });
 }

    function saveOrUpdateAdvertise(){
        var form = $('#advertiseForm');
        if (form.valid() && confirm("是否确认保存？")) {
            form.submit();
        }
    }
    function assignTicket(){

    }
    function back(){
        location.href = "<jfeng:appContext/>/advertise/advertiseinfo";
    }
</script>