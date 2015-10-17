<%@ taglib prefix="jfeng" uri="http://www.jfeng-app.com" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: luoli
  Date: 14-5-12
  Time: 下午6:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<jfeng:appContext var="appContext"/>--%>
<link href="${appContext}/hbzh/res/css/flot.css" rel="stylesheet" type="text/css">
<script language="javascript" type="text/javascript" src="${appContext}/hbzh/res/js/jquery.flot.js"></script>
<script language="javascript" type="text/javascript" src="${appContext}/hbzh/res/js/jquery.flot.time.js"></script>

<script type="text/javascript">
    var format = function(time, format){
        var t = new Date(time);
        var tf = function(i){return (i < 10 ? '0' : '') + i};
        return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a){
            switch(a){
                case 'yyyy':
                    return tf(t.getFullYear());
                    break;
                case 'MM':
                    return tf(t.getMonth() + 1);
                    break;
                case 'mm':
                    return tf(t.getMinutes());
                    break;
                case 'dd':
                    return tf(t.getDate());
                    break;
                case 'HH':
                    return tf(t.getHours());
                    break;
                case 'ss':
                    return tf(t.getSeconds());
                    break;
            }
        })
    }
    var previousPoint = null, previousLabel = null;
    $.fn.UseTooltip = function () {
        $(this).bind("plothover", function (event, pos, item) {
            if (item) {
                if ((previousLabel != item.series.label) || (previousPoint != item.dataIndex)) {
                    previousPoint = item.dataIndex;
                    previousLabel = item.series.label;
                    $("#tooltip").remove();

                    var x = item.datapoint[0];
                    var y = item.datapoint[1];
                    var color = item.series.color;
                    var day = new Date(x).getDay();

                    if (item.seriesIndex == 0) {
                        showTooltip(item.pageX,
                                item.pageY,
                                color,
                                        "<strong>" + item.series.label + "</strong><br> <strong>"+format(x, "yyyy/MM/dd")+"<br>" +
                                               y+ "</strong>");
                    } else {
                        showTooltip(item.pageX,
                                item.pageY,
                                color,
                                        "<strong>" + item.series.label + "</strong><br> <strong>"+format(x, "yyyy/MM/dd")+"<br>"  +
                                                y  + "</strong>");
                    }
                }
            } else {
                $("#tooltip").remove();
                previousPoint = null;
            }
        });
    };
    function showTooltip(x, y, color, contents) {
        $('<div id="tooltip">' + contents + '</div>').css({
            position: 'absolute',
            display: 'none',
            top: y - 40,
            left: x - 120,
            border: '2px solid ' + color,
            padding: '3px',
            'font-size': '9px',
            'border-radius': '5px',
            'background-color': '#fff',
            'font-family': 'Verdana, Arial, Helvetica, Tahoma, sans-serif',
            opacity: 0.9
        }).appendTo("body").fadeIn(200);
    }
    $(function() {
//        sendSimpleAjaxRequestAsync("systemData", "",function(msg){
//            var dd=eval(msg);
//            var data =dd;
//            $.plot("#placeholder", data,
//                    {
//                        xaxis: { mode: "time" ,timeformat:"%y/%m/%d",tickDecimals: 0,tickSize:[1,"day"]},
//                        lines:{show:true},
//                        points:{show:true},
//                        grid: {
//                            hoverable: true,
//                            borderWidth: 2,
//                            borderColor: "#633200",
//                            backgroundColor: { colors: ["#ffffff", "#EDF5FF"] }
//                        }
//                    });
//            $("#placeholder").UseTooltip();
//        });
        selectTime();

    });
    function selectTime(){
        var date_range=$("#date_range").val();
        if(date_range==null||date_range==""){
            alert("请选择您要查询的日期！");
            return;
        }
        sendAjaxRequestAsync("systemData", "reportForm", 'date_range='+date_range,function(msg){
            var dd=eval(msg);
            var data =dd;
            $.plot("#placeholder", data, {
                xaxis: { mode: "time" ,timeformat:"%y/%m/%d",tickDecimals: 0,tickSize:[1,"day"]},
                lines:{show:true},
                points:{show:true},

                grid: {
                    hoverable: true,
                    borderWidth: 2,
                    borderColor: "#633200",
                    backgroundColor: { colors: ["#ffffff", "#EDF5FF"] }
                }
            });
            $("#placeholder").UseTooltip();
        });
    }

</script>
<div class="row-fluid">
    <div class="col-lg-6">
        <div class="panel">
            <div class="panel-body">
                <div class="alert alert-success">图表显示选择日期的前10天数据</div>
                <spring:form id="reportForm" action="systemData" modelAttribute="report">
                    <%--<jfeng:daterange path="dateRange" id="date_range" cssClass="form-control"/>--%>
                    <div class="input-group">
                        <jfeng:calendar path="beginTime" id="date_range" cssClass="form-control"/>
                        <span class="input-group-btn">
                            <button class="btn btn-default" onclick="selectTime()" type="button">查询</button>
                        </span>
                    </div>
                </spring:form>
            </div>
        </div>
    </div>
</div>
<div id="content">
    <div class="flot-container">
        <div id="placeholder" class="flot-placeholder"></div>
    </div>
</div>

