<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="v2"/>
<title>场次添加</title>
<style type="text/css">

.tab td i{color:#ff0000; margin-right:0.5em;}
.tab td{padding:0 10px 15px 0;}
.form-group.tab.col-lg-2.col-sm-3{width:100%;}
    
</style>
</head>
<body>
	
	<form action="${BasePath !}/Game/saveOrUpdate.do" method="post" id="myform" class="ff-form">
		<input type="hidden" name="id" value="${(entity.id) !}" />
		<div id="error_con" class="tips-form">
			<ul></ul>
		</div>
		
		<style type="text/css">

		.tab td i{color:#ff0000; margin-right:0.5em;}
		.tab td{padding:0 10px 15px 0;}
		.form-group.tab.col-lg-2.col-sm-3{width:100%;}
		    
		</style>
		
		<table border="0" cellpadding="0" cellspacing="0" class="form-group tab">
     <tr>
       <td valign="top" align="right" width="180"><i>*</i>名　　称：</td>
       <td valign="top" align="left"><input type="text"  name="name"  data-rule-required="true" data-msg-required="名称不能为空"></td>
     </tr>
     <tr>
       <td valign="top" align="right"><i>*</i>启用时间：</td>
       <td valign="top" align="left">   <input name="startDate" id="startDate" readonly="readonly" class="form-control txt_mid input-sm" data-rule-required="true" data-msg-required="开始时间不能为空"/>
           </td>
     </tr>
     <tr>
       <td valign="top" align="right"><i>*</i>结束时间：</td>
       <td valign="top" align="left"><input name="endDate" id="endDate" readonly="readonly" class="form-control txt_mid input-sm"
               		  data-rule-required="true" data-msg-required="结束时间不能为空"
                        value=""  ></td>
     </tr>
     </table>
     <table border="0" cellpadding="0" cellspacing="0" class="form-group tab" style="  padding:10px 0 0 0;  border-top: 1px dotted rgb(221, 221, 221);   border-bottom: 1px dotted rgb(221, 221, 221);">
     <tr>
       <td valign="top" colspan="4" ><p style="padding:0 0 0 92px;margin:0;"><i>*</i>上午场次时间设置：</p></td>
       </tr>
     <tr>
       <td valign="top" align="right" width="180">场次开始时间：</td>
       <td valign="top" align="left"><input name="morningStartTime" id="morningStartTime" class="form-control txt_mid input-sm"
               		  data-rule-required="true" data-msg-required="上午场次开始时间不能为空" value=""  ></td>
  
       <td valign="top" align="right">场次结束时间：</td><td>
       <input name="morningEndTime" id="morningEndTime"  class="form-control txt_mid input-sm"
               		  data-rule-required="true" data-msg-required="上午场次结束时间不能为空"  value=""  ></td>
     </tr>
     <tr>
       <td valign="top" colspan="4" ><p style="padding:0 0 0 92px;margin:0;"><i>*</i>下午场次时间设置：</p></td>
       </tr>
          <tr>
       <td valign="top" align="right">场次开始时间：</td>
       <td valign="top" align="left"><input name="afternoonStartTime" id="afternoonStartTime" class="form-control txt_mid input-sm"
               		  data-rule-required="true" data-msg-required="下午场次开始时间不能为空" value=""  ></td>

       <td valign="top" align="right">场次结束时间：</td>
       <td valign="top" align="left"> <input name="afternoonEndTime" id="afternoonEndTime" class="form-control txt_mid input-sm"
               		  data-rule-required="true" data-msg-required="下午场次开始时间不能为空"  value=""  ></td>
     </tr>
      <tr>
       <td valign="top" colspan="4" ><p style="padding:0 0 0 92px;margin:0;">晚上场次时间设置：</p></td>
       </tr>
     <tr>
       <td valign="top" align="right">场次开始时间：</td>
       <td valign="top" align="left"><input name="nightStartTime" id="nightStartTime" class="form-control txt_mid input-sm"  value=""  >
            </td>
       <td valign="top" align="right">场次结束时间：</td>
       <td valign="top" align="left"> <input name="nightEndTime" id="nightEndTime" class="form-control txt_mid input-sm" value=""  ></td>
     </tr>
      </table>
     <table border="0" cellpadding="0" cellspacing="0" class="form-group tab">
     <tr>
       <td valign="top" align="right" width="180"><i>*</i>单场时间：</td>
       <td valign="top" align="left"><input type="text"  onkeyup='javascript:CheckInputIntFloat(this);'  name="singleGameTime" ></td>
     </tr>
     <tr>
       <td valign="top" align="right"><i>*</i>间隔时间：</td>
       <td valign="top" align="left"> <input type="text" onkeyup='javascript:CheckInputIntFloat(this);'   name="intervalTime" ></td>
     </tr>
     <tr>
       <td valign="top" align="right">可用时段：</td>
       <td valign="top" align="left">
       <table border="0" cellpadding="0" cellspacing="0">
     <tr>
       <td> <select class="form-control input-sm txt_mid" name="canUseStratTime">
					<option value="1">周一</option>
					<option value="2">周二</option>
					<option value="3">周三</option>
					<option value="4">周四</option>
					<option value="5">周五</option>
					<option value="6">周六</option>
					<option value="7">周日</option>
				</select></td>
    <td> - </td>
    <td><select class="form-control input-sm txt_mid" name="canUseEndTime">
					<option value="1">周一</option>
					<option value="2">周二</option>
					<option value="3">周三</option>
					<option value="4">周四</option>
					<option value="5">周五</option>
					<option value="6">周六</option>
					<option value="7">周日</option>
				</select></td>
     </tr></table>
       
      </td>
     </tr>
     <tr>
       <td valign="top" align="right"><i>*</i>参赛人数：</td>
       <td valign="top" align="left"><input type="text" onkeyup='javascript:CheckInputIntFloat(this);'   name="bespeakNum" data-rule-required="true" data-msg-required="参赛人数不能为空"></td>
     </tr>
     <tr>
       <td valign="top" align="right"><i>*</i>门市价格：</td>
       <td valign="top" align="left"><input type="text"  name="price" data-rule-required="true" 
       					onkeyup='javascript:CheckInputIntFloatByDay(this);'
       					data-msg-required="门市价钱不能为空"></td>
     </tr>
     <tr>
       <td valign="top" align="right"><i>*</i>优惠价格：</td>
       <td valign="top" align="left"><input name="preferentialPrice"  class="form-control txt_mid input-sm" 
               			 value=""  onkeyup='javascript:CheckInputIntFloatByDay(this);'
               			 data-rule-required="true" data-msg-required="优惠价钱不能为空"/></td>
     </tr>
     
     <input type="hidden"  name="predeterminedState" value="0" >
     <!-- <tr>
       <td valign="top" align="right"><i>*</i>预定状态：</td>
       <td valign="top" align="left"><select class="form-control input-sm txt_mid" name="predeterminedState">
					<option value="0">可预定</option>
					<option value="1">不可预定</option>
				</select></td>
     </tr> -->
     <tr>
       <td valign="top" align="right">场次类型：</td>
       <td valign="top" align="left"><select class="form-control input-sm txt_mid" name="type" >
					<option value="0">公共车(入门级)</option>
					<option value="1">公共车(专业级)</option>
					<option value="2">自备车</option>
				</select></td>
     </tr>
     </table>

		<div class="wrapper-btn">

			<input type="submit" class="ff-btn" value="批量生成场次">

			<input type="button" class="ff-btn white btn-close-iframeFullPage" value="返回">
		</div>	

	</form>
	<script type="text/javascript">

		$(function() {
		
			requirejs(['ff/select2'], function(){
				$("select").select2();
			});
			
			requirejs(['ff/validate'], function(){			
				executeValidateFrom('myform', 'checkValueJosn');
			});
			
			ffzx.ui(['datepicker'], function(){
			     
			    //日期区间，两个 input   
			    /* ffzx.init.dateRange({
			        id_from: 'beginLastUpdateDateStr', //input id
			        id_to: 'endLastUpdateDateStr', //input id
			        showTime: true //显示小时分秒
			    }); */
			     
			    //单个日期 input
			    ffzx.init.dateInput({
			        id_input: 'startDate', //input id
			        //showTime: true //显示小时分秒
			    });
			    
			    ffzx.init.dateInput({
			        id_input: 'endDate', //input id
			        //showTime: true //显示小时分秒
			    });
			    
			    ffzx.init.dateInput({
			        id_input: 'morningStartTime', //input id
			        showCalendar:false,
			        timeFormat:"HH:mm",
			        showTime: true //显示小时分秒
			    });
			    
			    ffzx.init.dateInput({
			        id_input: 'morningEndTime', //input id
			        showCalendar:false,
			        timeFormat:"HH:mm",
			        showTime: true //显示小时分秒
			    });
			    
			    ffzx.init.dateInput({
			        id_input: 'afternoonStartTime', //input id
			        showCalendar:false,
			        timeFormat:"HH:mm",
			        showTime: true //显示小时分秒
			    });
			    
			    ffzx.init.dateInput({
			        id_input: 'afternoonEndTime', //input id
			        showCalendar:false,
			        timeFormat:"HH:mm",
			        showTime: true //显示小时分秒
			    });
			    
			    ffzx.init.dateInput({
			        id_input: 'nightStartTime', //input id
			        showCalendar:false,
			        timeFormat:"HH:mm",
			        showTime: true //显示小时分秒
			    });
			    
			    ffzx.init.dateInput({
			        id_input: 'nightEndTime', //input id
			        showCalendar:false,
			        timeFormat:"HH:mm",
			        showTime: true //显示小时分秒
			    });
			});
		});
		
		
		function CheckInputIntFloat(oInput) {
			  if ('' != oInput.value.replace(/[\d]+/, '')) {
			    oInput.value = oInput.value.match(/[\d]+/) == null ? '' : oInput.value.match(/[\d]+/);
			  }
		}

		function CheckInputIntFloatByDay(oInput) {
			  if ('' != oInput.value.replace(/\d{1,}\.{0,1}\d{0,2}/, '')) {
			    oInput.value = oInput.value.match(/\d{1,}\.{0,1}\d{0,2}/) == null ? '' : oInput.value.match(/\d{1,}\.{0,1}\d{0,2}/);
			  }
		}
		
		function checkValueJosn(){
			var startDate = $("#startDate").val(); // 开始日期
			var endDate = $("#endDate").val(); // 结束日期
			
			var morningStartTime = startDate+" "+$("#morningStartTime").val()+":00"; 
			var morningEndTime = startDate+" "+$("#morningEndTime").val()+":00";
			var afternoonStartTime = startDate+" "+$("#afternoonStartTime").val()+":00";
			var afternoonEndTime = startDate+" "+$("#afternoonEndTime").val()+":00";
			var nightStartTime = $("#nightStartTime").val();
			var nightEndTime = 	$("#nightEndTime").val();
			
			if(CompareDate(startDate,endDate)){
				var d = dialog({quickClose: true,content: '启用时间不能大于结束时间'}).show();
		        setTimeout(function(){d.close();},4000);
				return false;
			}
			
			if(CompareDate(morningStartTime,morningEndTime)){
				var d = dialog({quickClose: true,content: '上午场次开始时间不能大于上午场次结束时间'}).show();
		        setTimeout(function(){d.close();},4000);
				return false;
			}
			
			if(CompareDate(morningEndTime,afternoonStartTime)){
				var d = dialog({quickClose: true,content: '上午场次结束时间不能大于下午场次开始时间'}).show();
		        setTimeout(function(){d.close();},4000);
				return false;
			}
			
			if(CompareDate(afternoonStartTime,afternoonEndTime)){
				var d = dialog({quickClose: true,content: '下午场次开始时间不能大于下午场次结束时间'}).show();
		        setTimeout(function(){d.close();},4000);
				return false;
			}
			
			debugger;
			
			if((nightStartTime!="" && (nightEndTime==null || nightEndTime=="" || typeof(nightEndTime)=="undefined")) || (nightEndTime!="" && (nightStartTime==null || nightStartTime=="" || typeof(nightStartTime)=="undefined"))){
				var d = dialog({quickClose: true,content: '晚上场次可以同时为空,也可以同时不为空'}).show();
		        setTimeout(function(){d.close();},4000);
				return false;
			}else if(nightStartTime!=null && nightEndTime!=null){
				nightStartTime = startDate+" "+nightStartTime+":00";
				nightEndTime = startDate+" "+nightEndTime+":00";
				if(CompareDate(afternoonEndTime,nightStartTime)){
					var d = dialog({quickClose: true,content: '下午场次结束时间不能大于晚上场次开始时间'}).show();
			        setTimeout(function(){d.close();},4000);
					return false;
				}
				
				if(CompareDate(nightStartTime,nightEndTime)){
					var d = dialog({quickClose: true,content: '晚上场次开始时间不能大于晚上场次结束时间'}).show();
			        setTimeout(function(){d.close();},4000);
					return false;
				}
			}
			
			return true;
		}
		
		
		function CompareDate(d1,d2){
		  return ((new Date(d1.replace(/-/g,"\/"))) > (new Date(d2.replace(/-/g,"\/"))));
		}
	</script>
</body>
</html>
