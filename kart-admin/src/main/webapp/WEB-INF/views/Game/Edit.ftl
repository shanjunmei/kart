<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="v2"/>
<title>场次编辑</title>

</head>
<body>
	
	<form action="${BasePath !}/Game/updateGame.do" method="post" id="myform" class="ff-form">
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
		       <td valign="top" align="right"><i>*</i>场次名称：</td>
		       <td valign="top" align="left">${(entity.name) !}</td>
	     	</tr>
	     	
	     	<tr>
		       <td valign="top" align="right"><i>*</i>日期：</td>
		       <td valign="top" align="left">${(entity.effectiveTime) !}</td>
	     	</tr>
	     	
	     	<tr>
		       <td valign="top" align="right"><i>*</i>场次/场：</td>
		       <td valign="top" align="left">${(entity.gameTime) !}</td>
	     	</tr>
	     	
	     	<tr>
		       <td valign="top" align="right"><i>*</i>场次时间/分钟：</td>
		       <td valign="top" align="left"><input name="time" value="${(entity.time) !}" class="form-control txt_mid input-sm" data-rule-required="true" data-msg-required="场次时间/分钟不能为空"/></td>
	     	</tr>
	     	
	     	<tr>
		       <td valign="top" align="right"><i>*</i>场次类型：</td>
		       <td valign="top" align="left">
		       		<select class="form-control input-sm txt_mid" name="type" >
						<option value="0" <#if (entity.type)?? && entity.type=='0' >selected="selected" </#if>>公共车(入门级)</option>
						<option value="1" <#if (entity.type)?? && entity.type=='1' >selected="selected" </#if>>公共车(专业级)</option>
						<option value="2" <#if (entity.type)?? && entity.type=='2' >selected="selected" </#if>>自备车</option>
					</select>
		       </td>
	     	</tr>
	     	
			<tr>
		       <td valign="top" align="right"><i>*</i>价钱/元：</td>
		       <td valign="top" align="left">
		       		<input name="preferentialPrice" id="preferentialPrice" class="form-control txt_mid input-sm"
               		  data-rule-required="true" data-msg-required="价钱/元不能为空" value="${(entity.preferentialPrice) !}"  >
		       </td>
	     	</tr>
	     	
	     	<tr>
		       <td valign="top" align="right"><i>*</i>限制名额：</td>
		       <td valign="top" align="left">${(entity.bespeakNum) !}</td>
	     	</tr>
	     	
	     	<tr>
		       <td valign="top" align="right"><i>*</i>实际参赛人数：</td>
		       <td valign="top" align="left">${(entity.participantsNumber) !}</td>
	     	</tr>
	     	
	     	<input type="hidden"  name="predeterminedState" value="0" >
	     	<!-- <tr>
		       <td valign="top" align="right"><i>*</i>预定状态：</td>
		       <td valign="top" align="left">
		       		<select class="form-control input-sm txt_mid" name="predeterminedState">
						<option value="0" <#if (entity.predeterminedState)?? && entity.predeterminedState=='0' >selected="selected" </#if>>可预定</option>
						<option value="1" <#if (entity.predeterminedState)?? && entity.predeterminedState=='1' >selected="selected" </#if>>不可预定</option>
					</select>
		       </td>
	     	</tr> -->
       	  
       	 
        </table>  
             
		<div class="wrapper-btn">
		
		<#if (view)?? && view=='0' >
			<input type="submit" class="ff-btn" value="保存">
			<input type="button" class="ff-btn white btn-close-iframeFullPage" value="返回">
			<#else>
			<div class="form-tr">
						<div class="form-td">
							<label>该场次购买记录：</label>
							<div class="div-form">
							<table class="table table-hover table-striped bor2 table-common">
		                        <thead>
		                        <tr>
		                            <th width="10%px">车号</th>
		                            <th width="10%px">订单号</th>
		                            <th width="10%px">姓名</th>
		                            <th width="10%px">电话</th> 
		                            <th width="10%px">身份证号</th>
		                            <th width="10%px">价格</th>
		                        </tr>
		                        </thead> 
		                        <tbody>
		                        	<#if gameUserInfoModelList?? >
			                        	<#list gameUserInfoModelList as item >
			                         		<tr >
				                             <td width="10%px">${(item.carNum) !}</td>
				                             <td width="10%px">${(item.orderCode) !}</td>
				                             <td width="10%px">${(item.name) !}</td>
				                             <td width="10%px">${(item.phone) !}</td>
				                             <td width="10%px">${(item.cardNum) !}</td>
				                             <td width="10%px">${(item.favorablePrice) !}</td>
				                        	</tr>
				                      	</#list>
			                   		</#if>
		                     	</tbody>
		                    </table>
							</div>
						</div>
					</div>
			<input type="button" class="ff-btn white btn-close-iframeFullPage" value="返回">
		</#if>
		            
		</div>	

	</form>
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
