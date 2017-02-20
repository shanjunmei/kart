<!DOCTYPE html>
<html>
<head>
    <meta name="decorator" content="v2"/>
    <title>线下订单</title>
</head>
<body>
<link  rel="stylesheet" href="../../kart-admin/asset/css/css.css">
<form action="${BasePath !}/OrderInfo/saveOrUpdate.do" method="post" id="myform" class="ff-form">
<input type="hidden" id="toPath" name="toPath" value="Add">
<div class="centerbody2">
    <ul class="order-ul">
        <li><label>日期：</label>
            <div>
                <input name="startDate" id="startDate" readonly="readonly" class="form-control txt_mid input-sm"
                       data-rule-required="true" data-msg-required="日期不能为空"/>
            </div>
        </li>
        <li><label>场次：</label><div><select id="selGame"><option></option></select></div></li>
        <input type="hidden" id="gameCode" name="gameCode" value="">
        <input type="hidden" id="detailList" name="detailList" value="">
        <li><label>该场次剩余名额：</label> <div id="gameNum"></div></li>
        <input type="hidden" id="buyCount" value="">
        <li class="bor1"><label>赛事级别：</label> <div id="gameType"></div></li>
        <li style="width: 50%; float: left; margin-top: 5px;"><label>人数：</label><div><input type="text" class="" id="buyNum" name="buyNum" value=""></div></li>
        <li style="width: 50%; float: left; margin-top: 5px;"><label>单价：</label><div><input type="text" class="" id="preferentialPrice" name="preferentialPrice" value="">元</div></li>
        <li class="bor1"><label>总价：</label><div><input type="text" class="text-block" id="countPrice" name="countPrice" value="">元</div></li>
    </ul>
    <table cellpadding="0" cellspacing="0" class="order-tab" id="table1">
        <tr>
            <th width="8%">序号</th>
            <th width="12%">车号</th>
            <th width="20%">姓名</th>
            <th width="30%">电话</th>
            <th width="30%">身份证号</th>
        </tr>
        <tr>
            <td>1、</td>
            <td><input type="text" class="" name="number" value="" readonly="readonly"></td>
            <td><input type="text" class="" name="fullName" value=""></td>
            <td><input type="text" class="" name="phone" value=""></td>
            <td><input type="text" class="" name="idNumber" value=""></td>
        </tr>
    </table>
    <p class="tips2">提示：请填写有效的姓名，手机号和身份证，否则保险无法生效</p>

    <div class="optional-car">
        <h3>可选择车辆：    可选 <span id="selBuyNum">0</span> 辆</h3>
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

        ffzx.ui([ 'dialog'], function(){});
        //选择日期更换场次事件
        $("#startDate").change(function(){
            $.ajax({
                url :  "${BasePath !}/OrderInfo/getGames.do",
                dataType : "json",
                data : {"date" : $("#startDate").val()},
                success : function(data) {
                    $("#selGame option").remove();
                    $("#selGame").append("<option value=''>请选择场次</option>");
                    $.map(data, function(item) {
                        $("#selGame").append("<option value='"+item.code+"'>"+item.name+"</option>");
                    });
                }
            });
        });

        //选择场次事件
        $("#selGame").change(function(){
            $.ajax({
                url :  "${BasePath !}/OrderInfo/getGameDetail.do",
                dataType : "json",
                data : {"code" : $("#selGame").val()},
                success : function(data) {
                    var game = data.game;
                    $("#gameNum").html((game.bespeakNum - game.participantsNumber) + "&emsp;&emsp;&emsp;" +
                            game.participantsNumber + "/" + game.bespeakNum);           //场次购买数量情况
                    if(game.type == "0"){$("#gameType").html("公共车(入门级)");}
                    else if(game.type == "1"){$("#gameType").html("公共车(专业级)");}
                    else if(game.type == "2"){$("#gameType").html("自备车");}         //场次级别
                    $("#gameCode").val(game.code);                                      //当前选择的场次code
                    $("#buyCount").val(game.bespeakNum - game.participantsNumber);      //可购买数量
                    $("#preferentialPrice").val(game.preferentialPrice);               //优惠价格
                    $("#buyNum").val("1");                                              //购买数量
                    $("#selBuyNum").html("1");                                           //可以选择数量
                    $("#countPrice").val(game.preferentialPrice);                       //订单价格

                    $("#table1  tr:not(:first)").remove();//删除首行外所有行
                    var trHTML = '<tr><td>1、</td><td><input type="text" class="" name="number" value="" readonly="readonly"></td>'+
                        ' <td><input type="text" class="" name="fullName" value=""></td>'+
                        ' <td><input type="text" class="" name="phone" value=""></td>'+
                        '<td><input type="text" class="" name="idNumber" value=""></td></tr>';
                    $("#table1").append(trHTML);//在table最后面添加一行

                    //车辆选择设置
                    $(".optional-ul").html("");
                    for(var i = 1; i <= game.bespeakNum;i++){
                        $(".optional-ul").append('<li onclick="selNumber(\'number'+i+'\')" id="number'+i+'">'+i+'</li>');
                    }
                    var list = data.list;
                    $.map(list, function(item) {
                        $(".optional-ul li").eq(item.number - 1).addClass("disabled");
                    });

                }
            });
        });

        requirejs(['ff/validate'], function(){
            executeValidateFrom('myform', 'inputVerification');
        });

        ffzx.ui(['datepicker'], function(){
            //单个日期 input
            ffzx.init.dateInput({
                id_input: 'startDate', //input id
                showTime: false //显示小时分秒
            });

        });
    });
</script>
<script type="text/javascript" src="${BasePath !}/asset/js/kart/OrderInfo/add.js"/>
</body>
</html>
