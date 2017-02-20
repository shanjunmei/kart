function gen_action(item) {
	var id = item.orderInfo.id;
	var objAction = "";
	if(item.orderInfo.status == "1"){
		objAction = {
			isRefund: [{
					label: '退款',
					href: "javascript: isRefund('"+ id +"')"
				}],
			form: [{
					label: '详情',
					href: "javascript: iframeFullPage('"+ rootPath +"/OrderInfo/toForm.do?id="+ id +"')"
				}]
		};
	}else{
		objAction = {
			form: [{
				label: '详情',
					href: "javascript: iframeFullPage('"+ rootPath +"/OrderInfo/toForm.do?id="+ id +"')"
				}]
		};
	}
	return objAction;
}

function isRefund(id) {
	ffzx.ui([
		// 以下依赖可任意组合）
		'dialog' // 浮动弹窗
	], function(){
	$.frontEngineDialog.executeDialog(
		'isRefund',
		'退款',
		'是否确定退款？',
		"250px",
		"35px",
		function() {
			$.ajax({
				url : rootPath + "/OrderInfo/isRefund.do",
				data : {
					id : id
				},// 给服务器的参数
				type : "POST",
				dataType : "json",
				async : false,
				cache : false,
				success : function(result) {
					if (result.status == 'success' || result.code == 0) {
						dialog({
							quickClose : true,
							content : result.infoStr
						}).show();
						setTimeout('window.location.href="' + rootPath+ '/OrderInfo/toList.do"', 1000);
					} else {
						dialog({
							quickClose : true,
							content : result.infoStr
						}).show();
					}
				}
			});
		});
	});
}

function getUseTime(orderInfo) {
	if(orderInfo.useTime == "" || orderInfo.useTime == null){
		return "";
	}
	return (new Date(orderInfo.useTime)).format("yyyy-MM-dd HH:mm:ss");
}
function getPayTime(orderInfo) {
	if(orderInfo.payTime == "" || orderInfo.payTime == null){
		return "";
	}
	return (new Date(orderInfo.payTime)).format("yyyy-MM-dd HH:mm:ss");
}
function getSource(orderInfo){
	if(orderInfo.orderSource == "0"){
		return "网上购买";
	}else{
		return "门店购买";
	}
}
function getStatus(orderInfo){
	if(orderInfo.status == "0"){
		return "未支付";
	}else if(orderInfo.status == "1"){
		return "已支付";
	}else{
		return "已退款";
	}
}
function getUseState(useState){
	if(useState == "0"){
		return "未使用";
	}else if(useState == "1"){
		return "已过期";
	}else if(useState == "2"){
		return "不在使用期";
	}else if(useState == "3"){
		return "已使用";
	}else if(useState == "4"){
        return "已验证";
    }
}
$(document).ready(function(){
//页面初始化预处理


});