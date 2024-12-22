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
        $.post("/accettazione/registra", {
            "nome": nome,
            "cognome": cognome,
            "email": email
        }, function (data) {
            if (data.id !== 0){
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
                alert("Mail già registrata")
            }})
        }else{
            alert("Campi vuoti, impossibile procedere")
        }
    })

    $(".user-row").click(function (){
        $("#motoTable").find(".user-row").remove()
        $.get("/accettazione/motoById", {"id": $(this).attr("id")}, function (data) {
            data.forEach(function (element) {
                const new_row = $("<tr></tr>")
                new_row.addClass("user-row")
                const targa_td = $("<td></td>").text(element.targa)
                const marca_td = $("<td></td>").text(element.marca)
                const modello_td = $("<td></td>").text(element.modello)
                new_row.append(targa_td, marca_td, modello_td)
                $("#motoTable").append(new_row)
            })

        })
    })
})