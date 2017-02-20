<html>
<head>
    <meta name="decorator" content="v2"/>
    <title>用户管理</title>
    <#include "../common/share_macro.ftl" encoding="utf-8">
</head>
<body>

<form id="find-page-orderby-form" action="${BasePath !}/Activity/queryData.do" method="post" class="ff-form">
    <input id="find-page-size" type="hidden" name="pageSize" value="${(pageObj.pageSize) !}"/>
    <input id="find-page-index" type="hidden" name="pageIndex" value="1"/>
    <input id="find-page-count" type="hidden" value="${(pageObj.pageCount) !}"/>

    <div class="form-group">
        <label>名称/标题：</label>
        <div><input name="name" type="text" placeholder="" value="${(entity.name) !}"></div>
    </div>
    
    <div class="form-group">
        <label>类型：</label>
        <div >
			<select class="form-control input-sm txt_mid" name="type">
				<option value="" >--请选择--</option>
				<option value="0" <#if (entity.type)?? && entity.type=='0' >selected="selected" </#if>>产品详情</option>
				<option value="1" <#if (entity.type)?? && entity.type=='1' >selected="selected" </#if>>文章详情</option>
				<option value="2" <#if (entity.type)?? && entity.type=='2' >selected="selected" </#if>>web链接</option>
			</select>
		</div>
    </div>
    
    <div class="form-group">
        <label>状态：</label>
        <div>
			<select class="form-control input-sm txt_mid" name="type">
				<option value="" >--请选择--</option>
				<option value="0" <#if (entity.status)?? && entity.status=='0' >selected="selected" </#if>>启用</option>
				<option value="1" <#if (entity.status)?? && entity.status=='1' >selected="selected" </#if>>禁用</option>
			</select>
		</div>
    </div>

    <button id="find-page-orderby-button" class="ff-btn sm btn-inquire" type="button"><i class="fa fa-search"></i>&nbsp;&nbsp;查询
    </button>
    <a href="javascript:iframeFullPage('${BasePath !}/Activity/toForm.do')" class="ff-btn sm"><i class="fa fa-plus"></i>&nbsp;&nbsp;添加</a>
    <button class="ff-btn sm white btn-clear-keyword" data-target="data_list"><i class="fa fa-trash-o"></i>&nbsp;&nbsp;清空
    </button>

</form>

<div id="data_list" class="ff_DataTable"></div>
<script type="text/javascript" src="${BasePath !}/asset/js/kart/activity/activity_list.js?v=${ver !}"></script>
<style>.clm_action {
    width: 150px !important;
}</style>
<script>

	var dict_actFlag = {"0":"启用", "1":"禁用"};
	var vehicle_type = {"0":"产品详情", "1":"文章详情","2":"web链接"};

    $(document).ready(function () {

        requirejs(['ff/select2'], function () {
            $('select').select2();
        });

        requirejs(['ff/init_datatable'], function (initDataTable) {

            var dt_role_list = new initDataTable({
                div_id: 'data_list',
                url: rootPath + "/Activity/queryData.do",
                columns: [
                    {data: "name", label: 'banner名称/标题', class: 'text-nowrap'},
                    {data: "type", label: '类型', data_dict:{"0":"产品详情", "1":"文章详情","2":"web链接"}},
                    {data: "status", label: '状态', data_dict:{"0":"启用", "1":"禁用"}},
                    {
                        data: "startDateTime",
                        label: '开始时间',
                        class: 'text-nowrap',
                        format: {datetime: 'yyyy-MM-dd hh:mm:ss'}
                    },
                    {
                        data: "endDateTime",
                        label: '结束时间',
                        class: 'text-nowrap',
                        format: {datetime: 'yyyy-MM-dd HH:mm:ss'}
                    },
                    {data: "createBy", label: '发布人', class: 'text-nowrap'},
                ],
                show_checkbox: false,
                gen_permission: function () {
                    var map = [];
                    map.push('edit');
                    map.push('deletes');
                    map.push('isEnabled');
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
