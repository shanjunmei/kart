function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}


$(function () {
    var wxOpenid = getUrlParam('code');
    if (!wxOpenid) {
        $.alert('网络出了点小问题');
        location.href = 'index.html';
    }
    common_ajax({
        url: _pathTotal + 'login.do', data: {'wxOpenid': wxOpenid}, success: function (res) {

            if (res) {
                location.href = 'index.html';
            } else {
                $.alert('自动登录失败，请重试');
            }

        }
    });

});

