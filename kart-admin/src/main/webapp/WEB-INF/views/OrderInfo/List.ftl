<html>
<head>
    <meta name="decorator" content="v2"/>
    <title>订单管理</title>
</head>
<body>

<form id="find-page-orderby-form" action="${BasePath !}/OrderInfo/queryData.do" method="post" class="ff-form">
    <input id="find-page-size" type="hidden" name="pageSize" value="${(pageObj.pageSize) !}"/>
    <input id="find-page-index" type="hidden" name="pageIndex" value="1"/>
    <input id="find-page-count" type="hidden" value="${(pageObj.pageCount) !}"/>

    <div class="form-group">
        <label>用户手机：</label>
        <div><input name="memberPhone" type="text" ></div>
    </div>

    <div class="form-group">
        <label>订单号：</label>
        <div><input name="code" type="text" ></div>
    </div>

    <div class="form-group">
        <label>场次：</label>
        <div><input name="gameCode" type="text"  ></div>
    </div>

    <div class="form-group">
        <label>验证时间：</label>
        <div> <input name="useTime" id="useTime"  class="form-control txt_mid input-sm"
                     value=""  readonly="readonly"/>
        </div>
    </div>
    <div class="form-group">
        <label>验证码：</label>
        <div><input name="verificationCode" type="text" value="${(verificationCode)!}"></div>
    </div>

    <div class="form-group">
        <label>订单类型：</label>
        <div >
            <select class="form-control input-sm txt_mid" name="orderSource">
                <option value="" >--请选择--</option>
                <option value="0" <#if (entity.orderSource)?? && entity.orderSource=='0' >selected="selected" </#if>>网上购买</option>
                <option value="1" <#if (entity.orderSource)?? && entity.orderSource=='1' >selected="selected" </#if>>门店购买</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label>订单状态：</label>
        <div >
            <select class="form-control input-sm txt_mid" name="status">
                <option value="" >--请选择--</option>
                <option value="0" <#if (entity.status)?? && entity.status=='0' >selected="selected" </#if>>未支付</option>
                <option value="1" <#if (entity.status)?? && entity.status=='1' >selected="selected" </#if>>已支付</option>
                <option value="2" <#if (entity.status)?? && entity.status=='2' >selected="selected" </#if>>已退款</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label>使用状态：</label>
        <div >
            <select class="form-control input-sm txt_mid" name="useState">
                <option value="" >--请选择--</option>
                <option value="0" <#if (entity.useState)?? && entity.useState=='0' >selected="selected" </#if>>未使用</option>
                <option value="2" <#if (entity.useState)?? && entity.useState=='2' >selected="selected" </#if>>不在使用期</option>
                <option value="4" <#if (entity.useState)?? && entity.useState=='4' >selected="selected" </#if>>已验证</option>
                <option value="3" <#if (entity.useState)?? && entity.useState=='3' >selected="selected" </#if>>已使用</option>
                <option value="1" <#if (entity.useState)?? && entity.useState=='1' >selected="selected" </#if>>已过期</option>
            </select>
        </div>
    </div>


    <button id="find-page-orderby-button" class="ff-btn sm btn-inquire" type="button"><i class="fa fa-search"></i>&nbsp;&nbsp;查询</button>
    <a href="javascript:iframeFullPage('${BasePath !}/OrderInfo/toAdd.do')" class="ff-btn sm"><i class="fa fa-plus"></i>&nbsp;&nbsp;添加</a>
    <button class="ff-btn sm white btn-clear-keyword" data-target="data_list"><i class="fa fa-trash-o"></i>&nbsp;&nbsp;清空</button>
</form>

<div id="data_list" class="ff_DataTable"></div>

<style>.clm_action {
    width: 150px !important;
}</style>
<script>

    $(document).ready(function () {
        ffzx.ui(['datepicker'], function(){
            //单个日期 input
            ffzx.init.dateInput({
                id_input: 'useTime', //input id
                showTime: false //显示小时分秒
            });
        });

        requirejs(['ff/init_datatable'], function (initDataTable) {
            var dt_role_list = new initDataTable({
				div_id: 'data_list',
				url: rootPath + "/OrderInfo/queryData.do",
				columns:[
					{ data: "orderInfo.memberPhone", label: '用户手机', class:'text-nowrap'},
					{ data: "orderInfo.code", label: '订单号', class:'text-nowrap'},
					{ data: "orderInfo.verificationCode", label: '验证码', class:'text-nowrap'},
					{ data: "game", label: '场次', class:'text-nowrap',render: function(game){return game.effectiveTime + "&nbsp;" + game.name}},
					{ data: "orderInfo.buyCount", label: '数量', class:'text-nowrap'},
					{ data: "orderInfo", label: '验证时间', class: 'text-nowrap', render:function(orderInfo){return getUseTime(orderInfo)}},
                    { data: "orderInfo", label: '支付时间', class: 'text-nowrap', render:function(orderInfo){return getPayTime(orderInfo)}},
					{ data: "orderInfo", label: '订单类型', class:'text-nowrap', render:function(orderInfo){return getSource(orderInfo)}},
                    { data: "orderInfo", label: '订单状态', class:'text-nowrap', render:function(orderInfo){return getStatus(orderInfo)}},
                    { data: "useState", label: '使用状态', class:'text-nowrap', render:function(useState){return getUseState(useState)}}
				],
                show_checkbox: true,
                gen_permission: function () {
                    var map = [];
                    map.push('isRefund');
                    map.push('form');
                    return map;
                },
                clm_action: function (item) {
                    return gen_action(item);
                }
            });
        });
    });
</script>
<script type="text/javascript" src="${BasePath !}/asset/js/kart/OrderInfo/orderInfo_list.js"></script>
</body>
</html>