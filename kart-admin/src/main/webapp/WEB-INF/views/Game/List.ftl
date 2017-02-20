<html>
<head>
    <meta name="decorator" content="v2"/>
    <title>用户管理</title>
    <#include "../common/share_macro.ftl" encoding="utf-8">
</head>
<body>

<form id="find-page-orderby-form" action="${BasePath !}/Game/list.do" method="post" class="ff-form">
    <input id="find-page-size" type="hidden" name="pageSize" value="${(pageObj.pageSize) !}"/>
    <input id="find-page-index" type="hidden" name="pageIndex" value="1"/>
    <input id="find-page-count" type="hidden" value="${(pageObj.pageCount) !}"/>

     <div class="form-group">
        <label>开始日期：</label>
        <div> <input name="startDate" id="startDate"  class="form-control txt_mid input-sm" 
               			 value=""  readonly="readonly"/>
         </div>
    </div>
    
    <div class="form-group">
        <label>结束日期：</label>
        <div> 
        	<input name="endDate" id="endDate" class="form-control txt_mid input-sm" 
        		readonly="readonly"
               			 value="" >
               			 </div>
    </div> 
    
    <div class="form-group">
        <label>场次编码：</label>
        <div> 
        	<input name="code" id="code" class="form-control txt_mid input-sm" 
               			 value="" >
               			 </div>
    </div> 
    
    <div class="form-group">
        <label>场次类型：</label>
        <div >
			<select class="form-control input-sm txt_mid" name="type">
				<option value="" >--请选择--</option>
				<option value="0" <#if (entity.type)?? && entity.type=='0' >selected="selected" </#if>>公共车(入门级)</option>
				<option value="1" <#if (entity.type)?? && entity.type=='1' >selected="selected" </#if>>公共车(专业级)</option>
				<option value="2" <#if (entity.type)?? && entity.type=='2' >selected="selected" </#if>>自备车</option>
			</select>
		</div>
    </div>

    <button id="find-page-orderby-button" class="ff-btn sm btn-inquire" type="button"><i class="fa fa-search"></i>&nbsp;&nbsp;查询
    </button>
    <a href="javascript:iframeFullPage('${BasePath !}/Game/toForm.do')" class="ff-btn sm"><i class="fa fa-plus"></i>&nbsp;&nbsp;添加</a>
    <button class="ff-btn sm white btn-clear-keyword" data-target="data_list"><i class="fa fa-trash-o"></i>&nbsp;&nbsp;清空
    </button>

</form>

<div id="data_list" class="ff_DataTable"></div>
<script type="text/javascript" src="${BasePath !}/asset/js/kart/game/game_list.js?v=${ver !}"></script>
<style>.clm_action {
    width: 150px !important;
}</style>
<script>
	var dict_actFlag = {"0":"启用", "1":"禁用"};
	var predetermined_state = {"0":"可预订", "1":"不可预定"};
	var vehicle_type = {"0":"公共车(入门级)", "1":"公共车(专业级)","2":"自备车"};

    $(document).ready(function () {
    	
    	var date=new Date();
    	var str = date.toLocaleDateString().replace(/(\d+)[^\d](\d+)[^\d](\d+)[^\d]?/, "$1-$2-$3");
    	$("#startDate").val(str);
    	$("#endDate").val(str);

        requirejs(['ff/select2'], function () {
            $('select').select2();
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
		});

        requirejs(['ff/init_datatable'], function (initDataTable) {

            var dt_role_list = new initDataTable({
                div_id: 'data_list',
                url: rootPath + "/Game/list.do",
                columns: [
                    {data: "gameNum", label: '场次序号', class: 'text-nowrap'},
                    {data: "name", label: '场次名称', class: 'text-nowrap'},
                    {data: "code", label: '场次编码', class: 'text-nowrap'},
                    {
                        data: "effectiveTime",
                        label: '日期',
                        class: 'text-nowrap',
                        format: {datetime: 'yyyy-MM-dd'}
                    },
                    {data: "gameTime", label: '场次时间',class: 'text-nowrap'},
                    {data: "time", label: '场次时长/分钟',class: 'text-nowrap'},
                    {data: "retailPrice", label: '价钱/元',class: 'text-nowrap'},
                    {data: "preferentialPrice", label: '优惠价/元',class: 'text-nowrap'},
                    {data: "type", label: '场次类型',class: 'text-nowrap' , data_dict: {"0":"公共车(入门级)", "1":"公共车(专业级)","2":"自备车"}},
                    {data: "bespeakNum", label: '限制名额',class: 'text-nowrap'},
                   // {data: "predeterminedState", label: '预定状态',class: 'text-nowrap',data_dict: {"0":"可预订", "1":"不可预定"}},
                   // {data: "status", label: '是否禁用状态',class: 'text-nowrap',data_dict: {"0":"启动", "1":"禁用"}},
                    {data: "bespeakNum", label: '预约数量',class: 'text-nowrap',render: function( data, type, full, meta){return getBespeakNum(data, type, full, meta)}}
                ],
                show_checkbox: false,
                gen_permission: function () {
                    var map = [];
                    map.push('edit');
                   // map.push('isEnabled');
                    map.push('details');
                    return map;
                },
                clm_action: function (item) {
                    return gen_action(item);
                }
            });
        });
    });

</script>
</body>
</html>
