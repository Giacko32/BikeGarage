$(document).ready(function () {
    $("#filter-cliente").on("input", function () {
        const filtro = $("#filter-cliente").val().toLowerCase();
        const utenti = $(".user-row")
        if (filtro.length > 0) {
            utenti.each(function () {
                const utente = $(this);
                const nome = utente.find("td:nth-child(1)").text().toLowerCase(); // Prende il testo del primo <td> (colonna Nome)

                if (nome.includes(filtro)) {
                    utente.show();
                } else {
                    utente.hide();
                }
            })
        }else{
            let i=0;
            utenti.each(function () {
                const utente = $(this);
                if (i<5){
                    utente.show();
                }else{
                    utente.hide();
                }
                i+=1;
            })
        }

    })

    $("#crea-cliente").click(function (){
        $(".registration-form-section").show()
    })

    $("#register-button").click(function (event){
        event.preventDefault()
        const nome= $("#nome").val().trim()
        const email= $("#email").val().trim()
        const cognome= $("#cognome").val().trim()
        if(nome.length > 0 && email.length > 0 && cognome.length > 0){
        $.post("/registra", {
            "nome": nome,
            "cognome": cognome,
            "email": email
        }, function (data) {
            if (data != null){
            const new_row = $("<tr></tr>")
            new_row.addClass("user-row")
            new_row.id = data.id;
            const name_td = $("<td></td>").text(data.nome)
            const cognome_td = $("<td></td>").text(data.cognome)
            const email_td = $("<td></td>").text(data.email)
            new_row.append(name_td, cognome_td, email_td)
            $("tbody").append(new_row)
            $(".registration-form-section").hide()
            alert("Utente registrato con successo")
        }else{

            }})
        }else{

        }
    })
})