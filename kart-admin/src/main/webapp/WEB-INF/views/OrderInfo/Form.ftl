<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="v2"/>
<title>订单详情</title>
</head>
<body>
<link  rel="stylesheet" href="../../kart-admin/asset/css/css.css">
<div class="centerbody">
	<form action="${BasePath !}/User/save.do" method="post" id="myform" class="ff-form">
    <#if verificationCode??>
        <div class="verification-title">
            <#if entity.useState == '0'>验证成功！
            <#elseif entity.useState == '2'>验证异常！
            <#else>验证失败！</#if>
        </div>
        <div id="error_con" class="tips-form">
            <!-- 这里是验证信息  -->
            <#if entity.useState == '0'>
            <#elseif entity.useState == '2'>提示：用户预定场次时间不在今天
            <#elseif entity.useState == '3'>提示：用户验证码已使用
            <#elseif entity.useState == '1'>提示：用户验证码已过期
			<#elseif entity.useState == '4'>提示：用户验证码已验证</#if>
        </div>
    </#if>

		<div class="order-num">${(entity.orderInfo.orderName) !}|订单号：${(entity.orderInfo.code) !}</div>
		<ul class="order-ul bgGray">
			<li>
				<label>验证码：</label>
				<div>
					<input type="text" class="text-block" name="entity.orderInfo.verificationCode" value="${(entity.orderInfo.verificationCode) !}">
				</div>
			</li>

			<li>
				<label>使用方式：</label>
				<div>到俱乐部前台验证</div>
			</li>
			<li class="bor1">
				<label>购买类型：</label>
	            <div>
	                <input type="text" class="text-block" name="entity.orderInfo.orderSource" value="<#if entity.orderInfo.orderSource == '0'>网上购买<#else>门店购买</#if>">
	            </div>
			</li>
			<li>
				<label>手机号码：</label>
		        <div>
		            <input type="text" class="text-block" name="entity.orderInfo.memberPhone" value="${(entity.orderInfo.memberPhone) !}">
		        </div>
			</li>
            <li>
                <label>使用日期：</label>
                <div>
                    <input type="text" class="text-block" name="entity.game.effectiveTime" value="${(entity.game.effectiveTime) !}"
                    <#if entity.useState == '1' || entity.useState == '2'>style="color:red;font-size: 16px" </#if>>
                </div>
            </li>
			<li class="bor1">
				<label>预定场次：</label>
		        <div>
		            <input type="text" class="text-block" name="entity.game.name" value="${(entity.game.name) !}">
					<#if entity.game.type == '0'>入门级<#elseif entity.game.type == '1'>专业级<#elseif entity.game.type == '2'>自备车</#if>
		        </div>
			</li>
			<li>
				<label>数量：</label>
		        <div>
		            <input type="text" class="text-block" name="entity.orderInfo.buyCount" value="X${(entity.orderInfo.buyCount) !}">
				</div>
			</li>
            <li>
                <label>总价：</label>
                <div>
                    <input type="text" class="text-block" name="entity.orderInfo.actualPrice" value="RMB${(entity.orderInfo.actualPrice) !}">
                </div>
            </li>
            <li>
                <label>订单状态：</label>
                <div style="color:red;font-size: 16px">
				<#if entity.orderInfo.status == '0'>未支付
				<#elseif entity.orderInfo.status == '1'>已支付
				<#elseif entity.orderInfo.status == '2'>已退款</#if>
                </div>
            </li>
		</ul>
		<ul class="order-ul">
            <li>
                <label>验证时间：</label>
                <div>
                    <input type="text" class="text-block" name="entity.orderInfo.useTime" value="${(entity.orderInfo.useTime?string("yyyy-MM-dd HH:mm:ss")) !}"
                           <#if entity.useState == '3'>style="color:red;font-size: 16px" </#if>>
                </div>
            </li>
			<li>
				<label>可用人数：</label>
		        <div>
		            <input type="text" class="text-block" name="entity.orderInfo.buyCount" value="${(entity.orderInfo.buyCount) !}人">
		        </div>
			</li>
			<li>
				<label>使用状态：</label>
		        <div style="color:red;font-size: 16px">
                    <#if entity.useState == '0'>未使用
                    <#elseif entity.useState == '2'>不在使用期
                    <#elseif entity.useState == '3'>已使用
                    <#elseif entity.useState == '1'>已过期
					<#elseif entity.useState == '4'>已验证</#if>
		        </div>
			</li>
			<li>
				<label>提示：</label>
		        <div>请管理员确认实际入场人数！</div>
			</li>

		<#if detailList??>
		<BR>
            <li>
                <label>使用人员信息：</label>
            </li>
            <table cellpadding="0" cellspacing="0" class="order-tab" id="table1">
                <tr>
                    <th width="8%">序号</th>
                    <th width="12%">车号</th>
                    <th width="20%">姓名</th>
                    <th width="30%">电话</th>
                    <th width="30%">身份证号</th>
                </tr>
				<#list detailList as item>
                    <tr>
                        <td>${item_index + 1}、</td>
                        <td>${(item.carNum)!}</td>
                        <td>${(item.name)!}</td>
                        <td>${(item.phone)!}</td>
                        <td>${(item.cardNum)!}</td>
                    </tr>
				</#list>
            </table>
		</#if>
			<li>
				<div class="wrapper-btn">
					<input type="button" class="ff-btn white btn-close-iframeFullPage" value="返回">
				</div>
			</li>
		</ul>
</form>
</div>
</body>
</html>
