$(document).ready(function () {
    //用户注册
    $("#register").click(function () {
        window.location.href = "regist.html";
    });
    //刷新验证码
    $("#refresh_vcode").click(function () {
        $("#refresh_vcode").attr('src', "/user/validate?time="+new Date().getTime());
    });
});