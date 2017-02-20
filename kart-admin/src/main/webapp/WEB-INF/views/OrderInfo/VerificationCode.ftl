<!DOCTYPE html>
<html>
<head>
    <meta name="decorator" content="v2"/>
    <title>验证码</title>
</head>
<body>

    <form action="${BasePath !}/OrderInfo/verificationCode.do" method="post" id="myform" class="ff-form" onsubmit="return toVaild()">
        <input type="hidden" id="verificationCode" value="${(verificationCode)!}">
        <div id="error_con" class="tips-form">
            <ul></ul>
        </div>

        <div class="form-group">
            <label><i>*</i>验证码：</label>
            <div>
                <input type="text" id="code" name="code" value="${(code) !}">
            </div>
        </div>

        <div class="wrapper-btn">
            <input type="submit" class="ff-btn" value="验证">
        </div>

    </form>
<script type="text/javascript">
$(function(){
    ffzx.ui([
        // 以下依赖可任意组合）
        'dialog' // 浮动弹窗
    ], function(){

        //可直接使用各插件的原生方法初始化；
        //原来的旧方法依然有效，例如：引入'dialog'后，原来的 $.frontEngineDialog.executeDialog() 仍可用
        var code = $("#code").val();
        if($("#verificationCode").val() == "2"){
            dialog({quickClose : true,content :"该验证码没有找到有效的订单，请重新输入！<br>如有疑问请到订单列表查询！"}).show();
            FFZX.openPageTab('1', {"verificationCode": code});
        }
    });
});
function toVaild(){
    if($("#code").val() == ""){
        dialog({quickClose : true,content :"请输入验证码！"}).show();
        return false;
    }
    return true;
}
</script>
</body>
</html>
