<!DOCTYPE html>
<html>
<head>
    <meta name="decorator" content="v2"/>
    <title>线下订单</title>
</head>
<body>
<link  rel="stylesheet" href="../../kart-admin/asset/css/css.css">
<form action="${BasePath !}/OrderInfo/saveOrUpdate.do" method="post" id="myform" class="ff-form">
    <input type="hidden" id="toPath" name="toPath" value="UseVerificationCode">
    <div class="centerbody2">
        <ul class="order-ul">
            <li class="bor1"><label>预定场次：</label> <div><input type="text" class="text-block" name=""
			  value="${(entity.game.effectiveTime) !}      ${(entity.game.name) !}   ${(entity.game.gameTime) !}"></div></li>
        </ul>
        <input type="hidden" id="gameCode" name="gameCode" value="${(entity.game.code) !}">
        <input type="hidden" id="gameCode" name="orderCode" value="${(entity.orderInfo.code) !}">
        <input type="hidden" id="buyNum" value="${(entity.orderInfo.buyCount) !}">
        <table cellpadding="0" cellspacing="0" class="order-tab" id="table1">
            <tr>
                <th width="8%">序号</th>
                <th width="12%">车号</th>
                <th width="20%">姓名</th>
                <th width="30%">电话</th>
                <th width="30%">身份证号</th>
            </tr>
        </table>
        <p class="tips2">提示：请填写有效的姓名，手机号和身份证，否则保险无法生效</p>

        <div class="optional-car">
            <h3>可选择车辆：    可选 <span id="selBuyNum">${(entity.orderInfo.buyCount) !}</span> 辆</h3>
            <ul class="optional-ul">
            </ul>
        </div>

        <div class="tips3">
            <ul>
                <li><div class=""></div>可选择</li>
                <li><div class="disabled"></div>不可选择</li>
            </ul>
        </div>

        <div class="wrapper-btn" style="text-align:center">
            <input type="submit" class="ff-btn" value="保存">
            <input type="button" class="ff-btn white btn-close-iframeFullPage" value="返回">
        </div>

    </div>
</form>
<script type="text/javascript">
    $(function() {

        //加载择场次车辆信息
		$.ajax({
			url :  "${BasePath !}/OrderInfo/getGameDetail.do",
			dataType : "json",
			data : {"code" : $("#gameCode").val()},
			success : function(data) {
				var game = data.game;

				//车辆选择设置
				$(".optional-ul").html("");
				for(var i = 1; i <= game.participantsNumber;i++){
					$(".optional-ul").append('<li onclick="selNumber(\'number'+i+'\')" id="number'+i+'">'+i+'</li>');
				}
				var list = data.list;
				$.map(list, function(item) {
					$(".optional-ul li").eq(item.number - 1).addClass("disabled");
				});

			}
		});

        //购买数量
        var buyNum = $("#buyNum").val();                                    //购买数量
        $("#table1  tr:not(:first)").remove();//删除首行外所有行
        var t01 = $("#table1 tr").length - 1;
        if (t01 < buyNum){
            var trHTML = "";
            for(var i = 1; i <= (buyNum - t01);i++){
                trHTML += '<tr><td>'+i+'、</td><td><input type="text" class="" name="number" value="" readonly="readonly"></td>'+
                        ' <td><input type="text" class="" name="fullName" value=""></td>'+
                        ' <td><input type="text" class="" name="phone" value=""></td>'+
                        '<td><input type="text" class="" name="idNumber" value=""></td></tr>';
            }
            $("#table1").append(trHTML);//在table最后面添加一行
        }

        requirejs(['ff/validate'], function(){
            executeValidateFrom('myform', 'inputVerification');
        });
    });

</script>
<script type="text/javascript" src="${BasePath !}/asset/js/kart/OrderInfo/addCommon.js"/>
</body>
</html>
