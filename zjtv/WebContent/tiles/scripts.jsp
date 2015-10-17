<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jfeng" uri="http://www.jfeng-app.com" %>

<div id="blueimp-gallery" class="blueimp-gallery">
    <!-- The container for the modal slides -->
    <div class="slides"></div>
    <!-- Controls for the borderless lightbox -->
    <h3 class="title"></h3>
    <a class="prev">‹</a>
    <a class="next">›</a>
    <a class="close">×</a>
    <a class="play-pause"></a>
    <ol class="indicator"></ol>
    <!-- The modal dialog, which will be used to wrap the lightbox content -->
    <div class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" aria-hidden="true">&times;</button>
                    <h4 class="modal-title"></h4>
                </div>
                <div class="modal-body next"></div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default pull-left prev">
                        <i class="glyphicon glyphicon-chevron-left"></i>
                        上一张
                    </button>
                    <button type="button" class="btn btn-primary next">
                        下一张
                        <i class="glyphicon glyphicon-chevron-right"></i>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="<jfeng:appContext/>/res/js/bootstrap.js"></script>
<script type="text/javascript" src="<jfeng:appContext/>/res/js/bootstrap-confirm.js"></script>
<script type="text/javascript" src="<jfeng:appContext/>/res/js/date.js"></script>
<script type="text/javascript" src="<jfeng:appContext/>/res/js/daterangepicker.js"></script>

<script type="text/javascript" src="<jfeng:appContext/>/res/js/moment.js"></script>
<script type="text/javascript" src="<jfeng:appContext/>/res/js/transition.js"></script>
<script type="text/javascript" src="<jfeng:appContext/>/res/js/collapse.js"></script>
<script type="text/javascript" src="<jfeng:appContext/>/res/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<jfeng:appContext/>/res/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>

<script type="text/javascript" src="<jfeng:appContext/>/res/js/bootstrapx-clickover.js"></script>

<%--<script type="text/javascript" src="<jfeng:appContext/>/res/js/alertify.min.js"></script>--%>
<script type="text/javascript" src="<jfeng:appContext/>/res/js/jquery.json-2.2.min.js"></script>
<script type="text/javascript" src="<jfeng:appContext/>/res/js/jquery.form.js"></script>
<script type="text/javascript" src="<jfeng:appContext/>/res/js/jquery.atmosphere.js"></script>

<%--<script type="text/javascript" src="<jfeng:appContext/>/res/js/iframe.xss.response-3.6.4.js"></script>--%>
<%--<script type="text/javascript" src="<jfeng:appContext/>/res/js/jquery.fineuploader-3.6.4.min.js"></script>--%>

<script type="text/javascript" src="<jfeng:appContext/>/res/js/jquery.radioImageSelect.min.js"></script>
<%--<script type="text/javascript" src="<jfeng:appContext/>/res/js/jquery.sticky-kit.min.js"></script>--%>
<script type="text/javascript" src="<jfeng:appContext/>/res/js/jquery.sticky-kit.js"></script>
<%--<script type="text/javascript" src="<jfeng:appContext/>/res/js/jquery.lockfixed.min.js"></script>--%>

<%--<script type="text/javascript" src="<jfeng:appContext/>/res/js/socket.io.js"></script>
<script type="text/javascript" src="<jfeng:appContext/>/res/js/application.js"></script>
<script type="text/javascript" src="<jfeng:appContext/>/res/js/jquery.stringifyjson.js"></script>--%>

<script type="text/javascript" src="<jfeng:appContext/>/res/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="<jfeng:appContext/>/res/js/jquery.dataTables.reloadAjax.js"></script>
<script type="text/javascript" src="<jfeng:appContext/>/res/js/jquery.dataTables-bootstrap.js"></script>
<script type="text/javascript" src="<jfeng:appContext/>/res/js/jquery.desknoty.js"></script>
<script type="text/javascript" src="<jfeng:appContext/>/res/js/jquery.pnotify.min.js"></script>
<%--<script type="text/javascript" src="<jfeng:appContext/>/res/js/jquery.websocket-0.0.1.js"></script>--%>
<script type="text/javascript" src="<jfeng:appContext/>/res/js/jquery.maskMoney.js"></script>
<%--<script type="text/javascript" src="<jfeng:appContext/>/res/js/jquery.matrixMultiFileUpload.js"></script>--%>
<%--<script type="text/javascript" src="<jfeng:appContext/>/res/js/jquery.filedrop.js"></script>--%>

<script type="text/javascript" src="<jfeng:appContext/>/res/js/jquery.validate.js"></script>
<script type="text/javascript" src="<jfeng:appContext/>/res/js/jquery.validate.message_cn.js"></script>
<script type="text/javascript" src="<jfeng:appContext/>/res/js/jquery.validate.bootstrap.js"></script>
<%--<script type="text/javascript" src="<jfeng:appContext/>/res/js/jqBootstrapValidation-1.3.7.min.js"></script>--%>

<script type="text/javascript" src="<jfeng:appContext/>/res/js/jquery.blueimp-gallery.min.js"></script>
<script type="text/javascript" src="<jfeng:appContext/>/res/js/bootstrap-image-gallery.min.js"></script>
<script type="text/javascript" src="<jfeng:appContext/>/res/js/load-image.js"></script>

<script type="text/javascript" src="<jfeng:appContext/>/res/js/dropzone.js"></script>

<script type="text/javascript" src="<jfeng:appContext/>/res/js/bootstrap.autocomplete.js"></script>
<script type="text/javascript" src="<jfeng:appContext/>/res/js/common.js"></script>

<script type="text/javascript" src="<jfeng:appContext/>/res/ckeditor/ckeditor.js"></script>

<%--<script type="text/javascript" src="<jfeng:appContext/>/res/chart/excanvas.min.js"></script>--%>
<%--<script type="text/javascript" src="<jfeng:appContext/>/res/chart/jquery.flot.min.js"></script>--%>
<script type="text/javascript" src="<jfeng:appContext/>/res/chart/jquery.flot.tooltip.min.js"></script>
<%--
<script type="text/javascript">
    $(document).ajaxSuccess(function() {
//        $('form').ajaxForm();
//        $('form').validate();
        submitForm('staffDialogForm', function(){
            alert('submitted');
        });
    });
</script>--%>
