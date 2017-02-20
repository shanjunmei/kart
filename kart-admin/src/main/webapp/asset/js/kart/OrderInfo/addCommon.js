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