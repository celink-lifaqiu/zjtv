<%--
  Created by IntelliJ IDEA.
  User: YinJianFeng
  Date: 14-5-12
  Time: 上午11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="panel">
    <div class="panel-body" id="placeholder">
    </div>
</div>

<script>
    var options;
    $(function(){
        options = {
            lines: {
                show:true
            },
            points: {
                show:true
            },
            xaxis: {
                tickDecimals:0,
                tickSize:1
            }
        }
    });

    $(document).ready(function(){
        loadData();
    });

    function loadData(){
        sendSimpleAjaxRequestAsync("/admin/systemData","", function(resp){
            var data = $.parseJSON(resp);
            $.plot("#placeholder", data, options)
        });
    }
</script>