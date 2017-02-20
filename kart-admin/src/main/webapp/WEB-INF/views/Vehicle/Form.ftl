<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="v2"/>
<title>车辆编辑</title>
</head>
<body>
	
	<form action="${BasePath !}/Vehicle/saveOrUpdate.do" method="post" id="myform" class="ff-form">
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
       <td valign="top" align="right" width="100"><i>*</i>车辆编号：</td>
       <td valign="top" align="left">
       	<#if (entity.type)?? >
       		<label>${(entity.code) !}</label>
       	<#else>
	       	<input type="text"  name="code" value="${(entity.code) !}" data-rule-required="true" data-msg-required="车辆编号不能为空">
       	</#if>
       </td>
     </tr>
     <tr>
       <td valign="top" align="right"><i>*</i>车辆名称：</td>
       <td valign="top" align="left">	<input type="text"  name="name" value="${(entity.name) !}" data-rule-required="true" data-msg-required="车辆名称不能为空">
			</td>
     </tr>
     <tr>
       <td valign="top" align="right">车辆类型：</td>
       <td valign="top" align="left"><select class="form-control input-sm txt_mid" name="type">
					<option value="0" <#if (entity.type)?? && entity.type=='0' >selected="selected" </#if>>入门级</option>
					<option value="1" <#if (entity.type)?? && entity.type=='1' >selected="selected" </#if>>专业级</option>
					<option value="2" <#if (entity.type)?? && entity.type=='2' >selected="selected" </#if>>自备车</option>
				</select></td>
     </tr>
     <tr>
       <td valign="top" align="right" height="26">状　　态：</td>
       <td valign="top" align="left"><select class="form-control input-sm txt_mid" name="status" >
					<option value="1" <#if (entity.status)?? && entity.status=='1' >selected="selected" </#if>>不可用</option>
					<option value="0" <#if (entity.status)?? && entity.status=='0' >selected="selected" </#if>>可用</option>
				</select></td>
     </tr>
     <tr>
       <td valign="top" align="right" height="26">备　　注：</td>
       <td valign="top" align="left"><input type="text"  name="remarks" value="${(entity.remarks) !}" >
				</td>
     </tr>
     </table>
	
		<div class="wrapper-btn">

			<input type="submit" class="ff-btn" value="保存">

			<input type="button" class="ff-btn white btn-close-iframeFullPage" value="返回">
		</div>	

	</form>

	<script type="text/javascript" src="${BasePath !}/asset/js/kart/vehicle/vehicle_form.js?v=${ver !}"></script>
	<script type="text/javascript">

		$(function() {
		
			requirejs(['ff/select2'], function(){
				$("select").select2();
			});
			
			requirejs(['ff/validate'], function(){			
				executeValidateFrom('myform');
			});
		});

	</script>
</body>
</html>
