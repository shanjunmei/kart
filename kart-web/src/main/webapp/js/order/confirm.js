$(function () {

    var _getID = $.frontEngine.getUrlParameter('id');
    initialOrderInfo(_getID);


    $('.changeSite').click(function () {
        location.href = 'siteChoose.html';
    });


})


function initialOrderInfo(id) {

    var _basePath = _pathTotal + 'game.do?gameCode=' + id;
    common_ajax({
        url: _basePath, success: function (data) {

            var _price = data.preferentialPrice;
            var _oldprice = data.retailPrice;
            var _address = '深圳市南山区海德大道中68海的农场旁200米红路灯处';
            var _playtime = '时长:' + data.time + '分钟';
            // var _iphone = '18565689555';
            var _playTime = data.effectiveTime + ' ' + data.gameNum + '场（' + data.gameTime + '）';
            var _nub = 1;
            var _total = _price * parseInt(_nub);

            $('.price').html(_price);
            $('.oldprice').html(_oldprice);
            $('.address p').html(_address);
            $('.playtime').append(_playtime);
            $('.screenings-time').html(_playTime);
            //$('.iphone').html(_iphone);
            $('.num-div span').html(_nub);
            $('.price-total span').html(_total);


            $('.num-div').on('click', 'i', function () {
                var $price = $('.price');
                var $nub = $('.num-div span');
                var $total = $('.price-total span');
                var _nub = parseInt($nub.html());
                if ($(this).hasClass("nubadd")) {
                    var _addNumb = parseInt(data.bespeakNum) - (data.participantsNumber == null ? 0 : data.participantsNumber);
                    if (_addNumb > _nub) {
                        $nub.html(_nub + 1);
                    }

                } else {
                    if (_nub <= 1) {
                        return;
                    } else {
                        $nub.html(_nub - 1);
                    }
                }
                var _total = $price.html() * parseInt($nub.html());
                $total.html(_total);

            });


        }
    })


}


function confirmOrder() {

    var pageTabId = $.frontEngine.getUrlParameter('id');
    //var _phone = $('.iphone').html();
    var _phone = $('#phone').val();
    var _number = $('.num-div span').html();
    var _basePath = _pathTotal + 'buyTicket.do?gameCode=' + pageTabId + '&qty=' + _number + '&telePhone=' + _phone;
    var _getSInfoJson = {};
    common_ajax({
        url: _basePath, success: function (getSInfoResult) {
            //$.frontEngineAjax.executeAjax(_basePath,"get",_getSInfoJson,function(getSInfoResult){
            console.log(getSInfoResult);

            if (getSInfoResult.code == 0) {
                kartpay(getSInfoResult.data.code);
            } else {
                $.alert(getSInfoResult.msg, '提示', '确定');
            }


        }
    })
}

//马上购买支付函数
function kartpay(orderNo, callback) {
    console.log('支付接口');

    location.href = 'orderDetails.html?id=' + orderNo;

    /*
     $.ajax({
     type:"get",
     url:'api/orderCharge',
     data:orderNo,
     dataType: 'json',
     success: function(rsp){

     pingpp.createPayment(rsp.obj, function(result, err){
     console.log(result);
     console.log(err.msg);
     console.log(err.extra);
     if (result == "success") {
     // 只有微信公众账号 wx_pub 支付成功的结果会在这里返回，其他的支付结果都会跳转到 extra 中对应的 URL。
     console.log("支付成功");

     if(null != callback)
     {
     callback(true);
     }
     } else if (result == "fail") {
     console.log("付款失败"+err.msg + err.extra);
     if(null != callback)
     {
     callback(false);
     }
     } else if (result == "cancel") {
     console.log("取消付款");
     if(null != callback)
     {
     callback(false);
     }
     }
     });


     }
     })

     */
}
