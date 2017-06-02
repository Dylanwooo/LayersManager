/**
 * Created by Kay on 2016/3/8.
 */

function keyLogin(event) {

    var browser = navigator.appName;
    var userAgent = navigator.userAgent;
    var code;
    if(browser.indexOf('Internet')>-1) //IE
    code = window.event.keyCode;
    else if(userAgent.indexOf("Firefox")>-1)  //火狐
    code = event.which;
    else  //其它浏览器
    code = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;

    if ( code == 13)  //按Enter键的键值为13
        document.getElementById("btn_login").click();  //调用登录按钮的登录事件
}


window.onload = function () {
    var btn_login = document.getElementById('btn_login');
    var btn_register = document.getElementById('btn_register');


    btn_login.onclick = function login() {

        var username = document.getElementById("username");
        var pass = document.getElementById("password");

        if (username.value == "") {

            alert("请输入用户名");

        } else if (pass.value == "") {

            alert("请输入密码");

        } else if (username.value == "admin" && pass.value == "123456") {

            window.location.href = "http://localhost:8080/LayersManager/";

            ////  全屏方法
            //function fullScreen(element) {
            //    if (element.requestFullscreen) {
            //        element.requestFullscreen();
            //    } else if (element.mozRequestFullScreen) {
            //        element.mozRequestFullScreen();
            //    } else if (element.webkitRequestFullscreen) {
            //        element.webkitRequestFullscreen();
            //    } else if (element.msRequestFullscreen) {
            //        element.msRequestFullscreen();
            //    }
            //}
            ////  再让当前页面全屏
            //fullScreen(document.documentElement);

        } else {

            alert("请输入正确的用户名和密码！");

        }
    }

    btn_register.onclick = function register() {
        window.open("register.html");
    }
}


