$(document).ready(function () {
    $("body").gradientify({
        gradients: [
            {start: [49, 76, 172], stop: [242, 159, 191]},
            {start: [255, 103, 69], stop: [240, 154, 241]},
            {start: [33, 229, 241], stop: [235, 236, 117]}
        ]
    });

    $(".inp-use").blur(function () {
        usernameVer();
    });
    $(".inp-pas").blur(function () {
        passwordVer();
    });

});

<!--用户名验证码提示信息-->
function promptNameInfo() {
    $("#span-use").css("color", "#FFCF73");
    $("#span-use").text("用户名位数(3-10)可为字符/数字/字母");
}

<!--密码验证码提示信息-->
function promptPasInfo() {
    $("#span-pas").css("color", "#FFCF73");
    $("#span-pas").text("密码位数(3-30)需包含字符-数字-字母");
}

<!--用户名验证函数-->
function usernameVer() {
    var reg = /^[\u4e00-\u9fa5a-zA-Z0-9]{3,10}$/
    var userName = $("#username").val();
    if (userName == null || userName == "") {
        $("#span-use").css("color", "red");
        $("#span-use").text("❌用户名不能为空");
    } else if (!reg.test(userName)) {
        $("#span-use").css("color", "red");
        $("#span-use").text("❌用户名格式错误");

    } else {
        $("#span-use").css("color", "green");
        $("#span-use").text("✔用户名格式正确");
        return true;
    }
    setTimeout(function () {
        promptNameInfo();
    }, 2000);
    return false;
}

<!--密码验证函数-->
function passwordVer() {
    // /^(?!\D+$)(?![^a-zA-Z]+$)\S{8,}$/ 验证密码
    var regCode = /(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{3,30}/;
    var userPassword = $("#password").val();
    if (userPassword == null || userPassword == "") {
        $("#span-pas").css("color", "red");
        $("#span-pas").text("❌密码不能为空");

    } else if (!regCode.test(userPassword)) {
        $("#span-pas").css("color", "green");
        $("#span-pas").text("❌密码格式错误");
    } else {
        $("#span-pas").css("color", "green");
        $("#span-pas").text("✔密码格式正确");
        return true;
    }
    setTimeout(function () {
        promptPasInfo();
    }, 1000);
    return false;
}

function submitForm() {
    if (usernameVer() && passwordVer()) {
        //alert("输入成功！");
        form - login.submit();
    } else {
        alert("输入错误，不能提交");
    }
}