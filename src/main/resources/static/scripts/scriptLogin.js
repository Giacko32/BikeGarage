$(document).ready(function () {
    $(".login-button").click(function () {
        const username = $("#usrnm_field").val().trim();
        const password = $("#pswd_field").val().trim();
        const regex = new RegExp(/^[a-zA-Z0-9_!?@]{6,}$/);
        if (regex.test(password)) {
            $.post("login", {"username": username,"password" : password}, function (data) {
                console.log(data);
            });
        } else {
            console.log("Invalid password");
        }
    })
})