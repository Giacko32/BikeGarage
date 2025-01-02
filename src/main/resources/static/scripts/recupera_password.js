$(document).ready(function () {
    let code = ""
    let mail = ""
    $("#recuperoPassword").click(function () {
        $("#modal").show()
    })

    $("#cancel").click(function () {
        $("#modal").hide()
    })

    $("#sendCode").click(function (){
        mailvalue = $("#email_field").val()
        if( mailvalue !== ""){
            $.post("/passwordRecovery", {email: mailvalue}, function(data){
                if (data === "not found"){
                    alert("Mail non trovata")
                } else {
                    mail = mailvalue
                    code = data
                    $("#code-modal").hide()
                    $("#verifica-codice-modal").show()
                }
            })
        }else {
            alert("Campo mail vuoto")
        }
    })

    $("#checkCode").click(function () {
        if ($("#code_field").val() !== ""){
            if ($("#code_field").val() === code){
                $("#verifica-codice-modal").hide()
                $("#password-modal").show()
            } else {
                alert("Codice errato")
            }
        }else {
            alert("Campo codice vuoto")
        }
    })

    $("#setNewPassword").click(function () {
        new_password = $("#new_pswd_field").val()
        regex = /^[a-zA-Z0-9_!?@]{6,}$/
        if (regex.test(new_password)){
            $.post("setNewPassword", {password: new_password, mail: mail}, function(data){
                if (data === "changed"){
                    $("#password-modal").hide()
                    $("#code-modal").show()
                    $("#modal").hide()
                    alert("Password cambiata con successo")
                }
            })
        }else{
            alert("Campo password invalido")
        }
    })

})