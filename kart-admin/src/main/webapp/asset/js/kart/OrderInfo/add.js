//修改购买数量
$("#buyNum").keyup(function(){
	var buyCount = $("#buyCount").val();                                    //可购买数量
	var preferentialPrice = $("#preferentialPrice").val();                 //优惠价格
	var buyNum = $("#buyNum").val();                                        //购买数量
	if (isNaN(buyNum)) {
		dialog({quickClose : true,content : '请输入数字！'}).show();
		return false;
	}
	if(buyNum*1 > buyCount*1){
		$("#buyNum").val(buyCount);                                        //购买数量
		buyNum = buyCount;
		dialog({quickClose : true,content :"本场次购买数量剩余:"+buyCount}).show();
	}
	$("#selBuyNum").html(buyNum);                                           //可以选择数量
	$("#countPrice").val((buyNum*preferentialPrice).toFixed(2));            //订单价格


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
});

//修改单价事件
$("#preferentialPrice").change(function(){
	var buyCount = $("#buyCount").val();                                    //可购买数量
	var preferentialPrice = $("#preferentialPrice").val();                 //优惠价格
	var buyNum = $("#buyNum").val();                                        //购买数量
	var exp = /^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/;
	if(!exp.test(preferentialPrice)){
		dialog({quickClose : true,content :"请输入正确的金额！"}).show();
		return false;
	}
	$("#countPrice").val((buyNum*preferentialPrice).toFixed(2));            //订单价格
});
//选择车辆
function selNumber(id){
	var number = $("#"+id).html();
	var classvar =  $("#"+id).attr('class');
	if(classvar == "disabled"){
		return false;
	}
	//取消选中
	if(classvar == "on"){
		$("#"+id).removeClass("on");
		$(".numberInput" + number).val("");
		$(".numberInput" + number).removeClass("numberInput" + number);
		return false;
	}

	var buyNum = $("#buyNum").val();    //购买数量
	var onLength = $(".optional-ul .on").length;
	if(onLength*1 >= buyNum*1){          //选择数量不能超过购买数量
		return false;
	}
	//选中
	$("#"+id).addClass("on");
	var numbers = $("#table1 input[name='number']");
	for(var i = 0; i < numbers.length; i++){
		if($(numbers[i]).val() == ""){
			$(numbers[i]).val(number);
			$(numbers[i]).addClass("numberInput" + number);
			return false;
		}
	}
}

//完整参与人信息验证
function inputVerification(){
	if($("#selGame").val() == ""){
		dialog({quickClose : true,content :"请选择场次！"}).show();
		return false;
	}

	var numbers = $("input[name=number]");
	var fullNames = $("input[name=fullName]");
	var phones = $("input[name=phone]");
	var idNumbers = $("input[name=idNumber]");
	for(var i = 0; i < numbers.length; i++){
		if($(numbers[i]).val() == ""){
			dialog({quickClose : true,content :"序号"+ (i+1) +"、车号不能为空！"}).show();
			return false;
		}
		if($(fullNames[i]).val() == ""){
			dialog({quickClose : true,content :"序号"+ (i+1) +"、姓名不能为空！"}).show();
			return false;
		}
		if($(phones[i]).val() == ""){
			dialog({quickClose : true,content :"序号"+ (i+1) +"、电话不能为空！"}).show();
			return false;
		}
		if($(idNumbers[i]).val() == ""){
			dialog({quickClose : true,content :"序号"+ (i+1) +"、身份证号不能为空！"}).show();
			return false;
		}
	}
	return true;
}