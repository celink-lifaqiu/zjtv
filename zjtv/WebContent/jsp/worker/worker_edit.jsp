<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jfeng" uri="/WEB-INF/jfeng.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<jfeng:appContext var="appContext"/>
<div class="col-lg-7">
    <div class="panel panel-default">
        <div class="panel-heading">员工信息</div>
        <div class="panel-body">
            <spring:form id="workerForm" action="${appContext}/worker/saveOrUpdateWorker" modelAttribute="worker" cssClass="form-horizontal">
                <spring:hidden path="id"/>
                
				<div class="form-group">
                    <label class="col-lg-3 control-label" for="name">真实姓名</label>
                    <div class="col-lg-9">
                        <spring:input id="name" path="name" cssClass="form-control" data-rule-required="true"/>
                    </div>
                </div>
				<div class="form-group">
					<label class="col-lg-3 control-label" for="gender">性别</label>
					<div class="col-lg-9" id="gender">
						<spring:radiobutton path="gender" value="0" label="男" />
						<spring:radiobutton path="gender" value="1" label="女" />
					</div>
				</div>
				<div class="form-group">
                    <label class="col-lg-3 control-label" for="idCard">身份证号码</label>
                    <div class="col-lg-9">
                        <spring:input id="idCard" path="idCard" cssClass="form-control" data-rule-required="true"/>
                    </div>
                </div>
				<div class="form-group">
                    <label class="col-lg-3 control-label" for="phone">联系电话</label>
                    <div class="col-lg-9">
                        <spring:input id="phone" path="phone" cssClass="form-control" data-rule-required="true"/>
                    </div>
                </div>
                <div class="form-group">
					<label class="col-lg-3 control-label" for="address">住址</label>
					<div class="col-lg-9">
						<spring:textarea id="address" path="address" cssClass="form-control"
							data-rule-required="true" />
					</div>
				</div>
				
                <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                    	<c:if test="${worker.id eq null}">
                            <label class="checkbox inline">
                                <input type="checkbox" name="continue" checked="checked" value="true"> 是否继续添加？
                            </label>
                        </c:if>
                        <button type="button" class="btn btn-primary" onclick="saveOrUpdateWorker()">保存</button>
                        <button type="button" class="btn btn-default" onclick="back()">返回</button>
                    </div>
                </div>
            </spring:form>
        </div>
    </div>
</div>
<script type="text/javascript">
    function saveOrUpdateWorker(){
        var name=$("#name").val();
        var phone=$("#phone").val();
        var idCard=$("#idCard").val();
        var address=$("#address").val();
        if(name.trim()==null||name.trim()==""){
            alert("真实姓名不能为空！");
            return;
        }
        
        if(!IdCardValidate(idCard)){
            alert("身份证号码不正确！");
            return;
        }
        var myreg = /^(((13[0-9]{1})|159|153)+\d{8})$/;
        if(!myreg.test(phone.trim()))
        {
            alert("请输入有效的手机号码！");
            return ;
        }
        if(address.trim()==null||address.trim()==""){
            alert("住址不能为空！");
            return;
        }
        var form = $('#workerForm');
        if (form.valid() && confirm("是否确认保存？")) {
            form.submit();
        }
    }

    function back(){
        location.href = "<jfeng:appContext/>/worker/worker";
    }
    
    
    
    var Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 ];    // 加权因子   
	var ValideCode = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ];            // 身份证验证位值.10代表X   
	function IdCardValidate(idCard) { 
	    idCard = trim(idCard.replace(/ /g, ""));               //去掉字符串头尾空格                     
	    if (idCard.length == 15) {   
	        return isValidityBrithBy15IdCard(idCard);       //进行15位身份证的验证    
	    } else if (idCard.length == 18) {   
	        var a_idCard = idCard.split("");                // 得到身份证数组   
	        if(isValidityBrithBy18IdCard(idCard)&&isTrueValidateCodeBy18IdCard(a_idCard)){   //进行18位身份证的基本验证和第18位的验证
	            return true;   
	        }else {   
	            return false;   
	        }   
	    } else {   
	        return false;   
	    }   
	}   
	/**  
	 * 判断身份证号码为18位时最后的验证位是否正确  
	 * @param a_idCard 身份证号码数组  
	 * @return  
	 */  
	function isTrueValidateCodeBy18IdCard(a_idCard) {   
	    var sum = 0;                             // 声明加权求和变量   
	    if (a_idCard[17].toLowerCase() == 'x') {   
	        a_idCard[17] = 10;                    // 将最后位为x的验证码替换为10方便后续操作   
	    }   
	    for ( var i = 0; i < 17; i++) {   
	        sum += Wi[i] * a_idCard[i];            // 加权求和   
	    }   
	    valCodePosition = sum % 11;                // 得到验证码所位置   
	    if (a_idCard[17] == ValideCode[valCodePosition]) {   
	        return true;   
	    } else {   
	        return false;   
	    }   
	}   
	/**  
	  * 验证18位数身份证号码中的生日是否是有效生日  
	  * @param idCard 18位书身份证字符串  
	  * @return  
	  */  
	function isValidityBrithBy18IdCard(idCard18){   
	    var year =  idCard18.substring(6,10);   
	    var month = idCard18.substring(10,12);   
	    var day = idCard18.substring(12,14);   
	    var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
	    // 这里用getFullYear()获取年份，避免千年虫问题   
	    if(temp_date.getFullYear()!=parseFloat(year)   
	          ||temp_date.getMonth()!=parseFloat(month)-1   
	          ||temp_date.getDate()!=parseFloat(day)){   
	            return false;   
	    }else{   
	        return true;   
	    }   
	}   
  /**  
   * 验证15位数身份证号码中的生日是否是有效生日  
   * @param idCard15 15位书身份证字符串  
   * @return  
   */  
  function isValidityBrithBy15IdCard(idCard15){   
      var year =  idCard15.substring(6,8);   
      var month = idCard15.substring(8,10);   
      var day = idCard15.substring(10,12);   
      var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
      // 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法   
      if(temp_date.getYear()!=parseFloat(year)   
              ||temp_date.getMonth()!=parseFloat(month)-1   
              ||temp_date.getDate()!=parseFloat(day)){   
                return false;   
        }else{   
            return true;   
        }   
  }  
    
    /**  
 * 通过身份证判断是男是女  
 * @param idCard 15/18位身份证号码   
 * @return 'female'-女、'male'-男  
 */  
function maleOrFemalByIdCard(idCard){   
    idCard = trim(idCard.replace(/ /g, ""));        // 对身份证号码做处理。包括字符间有空格。   
    if(idCard.length==15){   
        if(idCard.substring(14,15)%2==0){   
            return 1;   
        }else{   
            return 0;   
        }   
    }else if(idCard.length ==18){   
        if(idCard.substring(14,17)%2==0){   
            return 1;   
        }else{   
            return 0;   
        }   
    }else{   
        return null;   
    }   
}  

//去掉字符串头尾空格   
function trim(str) {   
    return str.replace(/(^\s*)|(\s*$)/g, "");   
}  
</script>