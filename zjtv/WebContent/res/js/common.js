/**
 * Created with IntelliJ IDEA.
 * User: Yin Jian Feng
 * Date: 13-5-9
 * Time: 下午1:07
 * To change this template use File | Settings | File Templates.
 */
function ajaxRefresh(targetId, action, formId, data, callback, async){
    var isAsync = false;
    if(async!=undefined&&async!=null){
        isAsync = async;
    }
    var paramsData = $('#'+formId).serialize();
    if(data != null && data != undefined){
        var arr = data.split("&");
        for(var i = 0; i < arr.length; i++){
            var tmpArr = arr[i].split("=");
            paramsData += "&" + tmpArr[0] + "=" + encodeURIComponent(tmpArr[1]);

        }
    }
//    $('#'+targetId).empty();
//    $('#'+targetId).append("<div id='" + targetId + "Process' class='offset5 loading_medium'></div>");
    $.ajax({
        type:"POST",
        url:action,
        data:paramsData,
        async:isAsync,
        success:function(response){ //       alert(response);
            $('#'+targetId).html(response);
            if(callback!=undefined) callback(response);
        }
    });
}
function ajaxRefreshAsync(targetId, action, formId, data, callback){
    ajaxRefresh(targetId, action, formId, data, callback, true);
}

function sendAjaxRequest(action, formId, queryString, callback,async) {
    var isAsync = false;
    if(async!=undefined&&async!=null){
        isAsync = async;
    }
    //alert(action+(form).serialize());
    try{
    var data = $('#'+formId).serialize()+'&'+queryString;
    $.ajax({
        type:"POST",
        async:isAsync,
        url:action,
        data:data,
        success:function(response){
            if(callback!=undefined) callback(response);
        }
    });
    }catch (e){alert(e)}
}
function sendAjaxRequestAsync(action, formId, queryString, callback){
    sendAjaxRequest(action, formId, queryString, callback, true);
}

function sendSimpleAjaxRequest(action, queryString, callback, async) {
    var isAsync = false;
    if(async!=undefined&&async!=null){
        isAsync = async;
    }
    $.ajax({
        type:"POST",
        async:isAsync,
        url:action,
        data:queryString,
        success:function(response){
            if(callback!=undefined) callback(response);
        }
    });
}
function sendSimpleAjaxRequestAsync(action, queryString, callback){
    sendSimpleAjaxRequest(action, queryString, callback, true);
}