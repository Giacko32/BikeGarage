$(document).ready(function () {
    $("#checkbutton").click(function () {
        $.get("checkStatus",{"targa":$("#targa").val(),"code":$("#code").val()},function (data) {
            console.log(data);
        });
    })
})