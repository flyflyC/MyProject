$(document).ready(function () {
    //账号登录
    $("#login").click(function () {
        window.location.href = "login.html";
    });
    //刷新验证码
    $("#refresh_vcode").click(function () {
        $("#refresh_vcode").attr('src', "validate?time="+new Date().getTime());
    });
});