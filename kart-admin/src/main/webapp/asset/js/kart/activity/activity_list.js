function gen_action(item) {
	var id = item.id;
	var objAction = {
		edit: [
			{
				label: '编辑',
				href: "javascript: iframeFullPage('"+ rootPath +"/Activity/details.do?id="+ id +"')"
			}
		],

		deletes: [
			{
				label: '删除',
				href: rootPath + '/Activity/delete.do?id=' + id,
				class:'action_delete'
			}
		],
		isEnabled: [
			{
				// SSUI: 前面如果有 render 过此列数据，要用转换后的数据来判断！
				label: (item.status == dict_actFlag['1']) ? dict_actFlag['0'] : dict_actFlag['1'],
				href: "javascript:isEnabled('"+id+"', '"+(item.status == dict_actFlag['1'] ? '0' : '1')+"')"
			}       
		]
	};
	
	return objAction;
}


function isdelete(id,name) {
    $.frontEngineDialog.executeDialog(
            'isdelete', 
            '删除', 
            '是否确定删除“'+name+'”？', 
            "250px", 
            "35px", 
            function() {
        $.ajax({
            url : rootPath + "/user/delete.do",
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
                        content : '操作成功！'
                    }).show();
                    setTimeout('window.location.href="' + rootPath+ '/user/toList.do"', 1000);
                } else {
                    dialog({
                        quickClose : true,
                        content : result.msg
                    }).show();
                }
            }
        });
    });
}


function isEnabled(id,actFlag) {
	debugger;
	var titleStr = "禁用";
	if (actFlag == 0) {
		 titleStr = "启用";
	}
	ffzx.ui(['datatable', 'dialog', 'ztree', 'treetable', 'validate', 'select2'], function(){
		$.frontEngineDialog.executeDialog(
				'enabled_table_info', 
				'信息', 
				'<i class="fa fa-question-circle fa-3x" style="color: #86CFF9;vertical-align:middle;"></i>　是否' + titleStr + '!',
				"200px", 
				"35px", 
				function() {
			$.ajax({
				url : rootPath + "/Activity/updateActFlag.do",
				data : {
					id : id,
					status : actFlag
				},// 给服务器的参数
				type : "POST",
				dataType : "json",
				async : false,
				cache : false,
				success : function(result) {
					if (result.status == 'success') {
						$.frontEngineDialog.executeDialogContentTime(result.infoStr);
						// SSUI: 重新载入当前页的数据
						reloadData('data_list');
					} else {
						dialog({
							quickClose : true,
							content : result.msg
						}).show();
					}
				}
			});
		});
	});
}

/*function isEnabled(id, actFlag) {
	titleStr = "禁用";
	if (actFlag == 0) {
		var titleStr = "启用";
	}

	$.frontEngineDialog.executeDialog(
			'enabled_table_info', 
			'信息', 
			'<i class="fa fa-question-circle fa-3x" style="color: #86CFF9;vertical-align:middle;"></i>　是否' + titleStr + '!',
			"200px", 
			"35px", 
			function() {
		$.ajax({
			url : rootPath + "/Activity/updateActFlag.do",
			data : {
				id : id,
				status : actFlag
			},// 给服务器的参数
			type : "POST",
			dataType : "json",
			async : false,
			cache : false,
			success : function(result) {
				if (result.status == 'success') {
					$.frontEngineDialog.executeDialogContentTime(result.infoStr);
					// SSUI: 重新载入当前页的数据
					reloadData('data_list');
				} else {
					dialog({
						quickClose : true,
						content : result.msg
					}).show();
				}
			}
		});
	});
}*/



$(document).ready(function(){
//页面初始化预处理


});