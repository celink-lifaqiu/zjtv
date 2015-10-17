<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jfeng" uri="/WEB-INF/jfeng.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>

<div class="box">
    <div class="box-main" id="image">
        <a href="javascript:void(0)">
            <img src="${advertise.image}">
        </a>
    </div>
    <div class="box-footer">
        <ul>
            <li>
                <a href="javascript:void(0)" class="trash" title="删除图片"
                   onclick="">&nbsp;&nbsp;</a>
            </li>
            <li>
                <a id="img_dropzone" href="javascript:void(0)" class="upload" title="上传图片"
                   onclick="">&nbsp;&nbsp;</a>
            </li>
        </ul>
    </div>
</div>
