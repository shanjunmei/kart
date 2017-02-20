<html>
<head>
    <meta name="decorator" content="v2"/>
    <title>用户管理</title>
    <#include "../common/share_macro.ftl" encoding="utf-8">
</head>
<body>

<form id="find-page-orderby-form" action="${BasePath !}/Vehicle/queryData.do" method="post" class="ff-form">
    <input id="find-page-size" type="hidden" name="pageSize" value="${(pageObj.pageSize) !}"/>
    <input id="find-page-index" type="hidden" name="pageIndex" value="1"/>
    <input id="find-page-count" type="hidden" value="${(pageObj.pageCount) !}"/>

    <div class="form-group">
        <label>车辆编号：</label>
        <div><input name="code" type="text" placeholder="" value="${(entity.code) !}"></div>
    </div>

    <button id="find-page-orderby-button" class="ff-btn sm btn-inquire" type="button"><i class="fa fa-search"></i>&nbsp;&nbsp;查询
    </button>
    <a href="javascript:iframeFullPage('${BasePath !}/Vehicle/toForm.do')" class="ff-btn sm"><i class="fa fa-plus"></i>&nbsp;&nbsp;添加</a>
    <button class="ff-btn sm white btn-clear-keyword" data-target="data_list"><i class="fa fa-trash-o"></i>&nbsp;&nbsp;清空
    </button>

</form>

<div id="data_list" class="ff_DataTable"></div>
<script type="text/javascript" src="${BasePath !}/asset/js/kart/vehicle/vehicle_list.js?v=${ver !}"></script>
<style>.clm_action {
    width: 150px !important;
}</style>
<script>

	var vehicle_stauts = {"0":"可用", "1":"不可用"};
	var vehicle_type = {"0":"入门级", "1":"专业级","2":"自备车"};

    $(document).ready(function () {

        requirejs(['ff/select2'], function () {
            $('select').select2();
        });

        requirejs(['ff/init_datatable'], function (initDataTable) {

            var dt_role_list = new initDataTable({
                div_id: 'data_list',
                url: rootPath + "/Vehicle/queryData.do",
                columns: [
					{data: "code", label: '车辆编号', class: 'text-nowrap'},
                    {data: "name", label: '用户名称', class: 'text-nowrap'},
                    {data: "type", label: '车辆类型', data_dict:{"0":"入门级", "1":"专业级","2":"自备车"}},
                    {data: "status", label: '状态', data_dict:{"0":"可用", "1":"不可用"}}
                ],
                show_checkbox: false,
                gen_permission: function () {
                    var map = [];
                    map.push('edit');
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
