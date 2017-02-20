<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="v2"/>
<title>营销编辑</title>

</head>
<body>
	
	<form action="${BasePath !}/Activity/saveOrUpdate.do" method="post" id="myform" class="ff-form">
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
	       <td valign="top" align="right" width="140"><i>*</i>banner名称/标题：</td>
	       <td valign="top" align="left"><input type="text"  name="name" value="${(entity.name) !}" data-rule-required="true" data-msg-required="banner名称/标题不能为空"></td>
     	</tr>
     	
     	<tr>
	       <td valign="top" align="right" width="100"><i>*</i>是否轮播图：</td>
	       <td valign="top" align="left"><input name="isBanner" type="radio" value="0" <#if (entity.isBanner) ??><#if entity.isBanner == '0'>checked="checked"</#if><#else>checked="checked"</#if>/>是
	       <input name="isBanner" type="radio" value="1" <#if (entity.isBanner) ??><#if entity.isBanner == '1'>checked="checked"</#if></#if>/>否</td>
     	</tr>
     	
     	<tr>
	       <td valign="top" align="right" width="100"><i>*</i>排序：</td>
	       <td valign="top" align="left"><input type="text"  name="sort" value="${(entity.sort) !}" onkeyup='javascript:CheckInputIntFloat(this);'
	       				data-rule-required="true" data-msg-required="排序不能为空"></td>
     	</tr>
     	
     	<tr>
	       <td valign="top" align="right" width="100">类型：</td>
	       <td valign="top" align="left">
	       		<select class="form-control input-sm txt_mid" name="type">
					<option value="0" <#if (entity.type)?? && entity.type=='0' >selected="selected" </#if>>产品详情</option>
					<option value="1" <#if (entity.type)?? && entity.type=='1' >selected="selected" </#if>>文章详情</option>
					<option value="2" <#if (entity.type)?? && entity.type=='2' >selected="selected" </#if>>web链接</option>
				</select>
	       </td>
     	</tr>
     	
     	<tr>
	       <td valign="top" align="right" width="100"><i>*</i>连接到(url)：</td>
	       <td valign="top" align="left"><input type="text"  name="url" value="${(entity.url) !}" data-rule-required="true" data-msg-required="url不能为空"></td>
     	</tr>
     	
     	<tr>
	       <td valign="top" align="right" width="100"><i>*</i>开始时间：</td>
	       <td valign="top" align="left"><input name="startDateTimeStr" id="startDateTimeStr" class="form-control txt_mid input-sm" readonly="readonly"
               			 value="<#if entity.startDateTime ?? >${entity.startDateTime?string('yyyy-MM-dd HH:mm:ss') !}</#if>" 
               			 data-rule-required="true" data-msg-required="开始时间不能为空"/></td>
     	</tr>
     	
     	<tr>
	       <td valign="top" align="right" width="100"><i>*</i>结束时间：</td>
	       <td valign="top" align="left"><input name="endDateTimeStr" id="endDateTimeStr" class="form-control txt_mid input-sm"
               		  data-rule-required="true" data-msg-required="结束时间不能为空" readonly="readonly"
                        value="<#if entity.endDateTime ?? >${entity.endDateTime?string('yyyy-MM-dd HH:mm:ss') !}</#if>"></td>
     	</tr>
       	 
       	<tr>
	       <td valign="top" align="right" width="100"><i>*</i>上传图片：</td>
	       <td valign="top" align="left">
	       		<input type="hidden" id="image" name="image" value="${(entity.image) !}" >
	       		<div class="form-group single-row">
						<!--  <#if (entity.image)??><img alt="" src="${(entity.image) !}"></#if> -->
					    <div class="webuploader" id="upload_img_single"></div>
				</div>
	       </td>
     	</tr> 
       	 
		 <!-- <div class="form-group">
    				<label>上传图片：</label> 
    				<input type="hidden" id="image" name="image" value="${(entity.image) !}" >
					<div class="form-group single-row">
						<#if (entity.image)??><img alt="" src="${(entity.image) !}"></#if> 
					    <div class="webuploader" id="upload_img_single"></div>
					</div>
           </div>  -->   
           
         <tr>
	       <td valign="top" align="right" width="100"><i>*</i>状态：</td>
	       <td valign="top" align="left">
	       		<select class="form-control input-sm txt_mid" name="status" >
					<option value="0" <#if (entity.status)?? && entity.status=='0' >selected="selected" </#if>>启用</option>
					<option value="1" <#if (entity.status)?? && entity.status=='1' >selected="selected" </#if>>禁用</option>
				</select>
	       </td>
     	</tr>  
     	
     	<tr>
	       <td valign="top" align="right" width="100"><i>*</i>内容描述：</td>
	       <td valign="top" align="left">
	       		<textarea rows="5" cols="50" name="introduction" >${(entity.introduction) !}</textarea>
	       </td>
     	</tr>
		
        </table> 
		<div class="wrapper-btn">

			<input type="submit" class="ff-btn" value="保存">

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
			        id_input: 'startDateTimeStr' //input id
			    });
			    
			    ffzx.init.dateInput({
			        id_input: 'endDateTimeStr' //input id
			    });
			});
			
			ffzx.ui(['upload'], function(){
				var image=$("#image").val();
			    //上传单个图片
			    var uploadInfo="";
			
			    ffzx.init.upload({
			        id: 'upload_img_single',
			        type: 'image', // type: image, file
			        multiple: false, // false: 限制只上传一个图片/文件; 默认为 true: 可上传多个
			        server: '/kart-admin/Activity/upload.do', // Backend script receiving the file(s)
			        
			        
			        <#if entity.image ??>
			          uploaded:[
				   				{
				   					id: '',
				   					name: '',
				   					src: image
				   				}
				   		],
			    	</#if>
			        	
			         
			        // 以下为可选
			        callback: {
			             
			            // Before single file selected
			            beforeFileQueued: function(file){
			                //console.log(uploaderInstance.getStats())
			            },
			                     
			            // When single file selected
			            fileQueued: function(file){ },
			             
			            // When multiple files selected
			            filesQueued: function(files){ },
			             
			            // When single file deleted
			            fileDeleted: function(file){ },
			             
			            // Uploading
			            uploadProgress: function(file, percentage){ },
			             
			            // 'response' is returned from server
			            uploadSuccess: function(file, response){ 
			            	var path=rootPath+"/FileRepo/file.do?id="+response.id; 
			           		$("#image").val(path);
			                console.log(arguments);
			            },
			             
			            // Detailed error messages are printed in console
			            uploadError: function(file){ },
			             
			            // Single file finished no matter it is uploaded successfully or not
			            uploadComplete: function(file){ },
			             
			            // All finished
			            uploadFinished: function(){ }
			        }
			    });
			     
			});
		});
		
		function checkValueJosn(){
			var startDateTimeStr = $("#startDateTimeStr").val(); // 开始日期
			var endDateTimeStr = $("#endDateTimeStr").val(); // 结束日期
			
			
			if(CompareDate(startDateTimeStr,endDateTimeStr)){
				var d = dialog({quickClose: true,content: '开始时间不能大于结束时间'}).show();
		        setTimeout(function(){d.close();},4000);
				return false;
			}
			
			
			return true;
		}
		
		
		function CompareDate(d1,d2){
			  return ((new Date(d1.replace(/-/g,"\/"))) > (new Date(d2.replace(/-/g,"\/"))));
		}
		
		function CheckInputIntFloat(oInput) {
			  if ('' != oInput.value.replace(/[\d]+/, '')) {
			    oInput.value = oInput.value.match(/[\d]+/) == null ? '' : oInput.value.match(/[\d]+/);
			  }
		}

	</script>
</body>
</html>
