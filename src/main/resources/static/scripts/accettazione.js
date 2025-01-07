$(document).ready(function () {

    let id_utente_current = null
    let targa_moto_current = null

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
        } else {
            let i = 0;
            utenti.each(function () {
                const utente = $(this);
                if (i < 5) {
                    utente.show();
                } else {
                    utente.hide();
                }
                i += 1;
            })
        }

    })

    $("#crea-cliente").click(function () {
        $("#moto-search").hide()
        $("#add-moto").hide()
        $(".registration-form-section").show()
    })

    $("#register-button").click(function (event) {
        event.preventDefault()
        const nome = $("#nome").val().trim()
        const email = $("#email").val().trim()
        const cognome = $("#cognome").val().trim()
        if (nome.length > 0 && email.length > 0 && cognome.length > 0) {
            $.post("/accettazione/registra", {
                "nome": nome,
                "cognome": cognome,
                "email": email
            }, function (data, status) {
                if (status === "success") {
                    if (data.id !== 0) {
                        const new_row = $("<tr></tr>")
                        new_row.addClass("user-row")
                        new_row.attr("id", data.id);
                        const name_td = $("<td></td>").text(data.nome)
                        const cognome_td = $("<td></td>").text(data.cognome)
                        const email_td = $("<td></td>").text(data.email)
                        new_row.append(name_td, cognome_td, email_td)
                        $("tbody").append(new_row)
                        $(".registration-form-section").hide()
                        alert("Utente registrato con successo")
                    } else {
                        alert("Mail già registrata")
                    }
                } else {
                    alert("Errore comunicazione con il server")
                }
            })
        } else {
            alert("Campi vuoti, impossibile procedere")
        }
    })


    $("#clientTable").on("click", ".user-row", function () {
        $(".registration-form-section").hide()
        $("#add-moto").hide()
        $("#motoTable").find(".moto-row").remove()
        id_utente_current = $(this).attr("id")
        $.get("/accettazione/motoById", {"id": id_utente_current}, function (data, status) {
            if(status === "success") {
                data.forEach(element => addMotoRow(element))
            } else {
                alert("Errore comunicazione con il server")
            }
        })
        $("#moto-search").show()
    })


    $("#crea-moto").click(function () {
        $("#moto-search").hide()
        $("#add-moto").show()
    })

    const addMotoRow = element => {
        const new_row = $("<tr></tr>")
        new_row.addClass("moto-row")
        new_row.attr("id", element.targa)
        const targa_td = $("<td></td>").text(element.targa)
        const marca_td = $("<td></td>").text(element.marca)
        const modello_td = $("<td></td>").text(element.modello)
        new_row.append(targa_td, marca_td, modello_td)
        $("#motoTable").append(new_row)
    }

    $("#register-moto-button").click(function (event) {
        event.preventDefault()
        const regexTarga = /^[A-Z]{2}[0-9]{5}$/
        const targa = $("#targa").val().trim()
        const marca = $("#marca").val().trim()
        const modello = $("#modello").val().trim()
        if (regexTarga.test(targa) && marca.length > 0 && modello.length > 0) {
            $.post("/accettazione/registraMoto", {
                "targa": targa,
                "marca": marca,
                "modello": modello,
                "id": id_utente_current
            }, (data, status) => {
                if(status === "success") {
                    if (data.targa !== "-1") {
                        alert("Moto registrata con successo")
                        addMotoRow(data)
                    } else {
                        alert("Targa già registrata")
                    }
                    $("#add-moto").hide()
                    $("#moto-search").show()
                }else{
                    alert("Errore comunicazione con il server")
                }
            })
        } else {
            alert("Campi vuoti o errati, impossibile procedere")
        }
    })

    $("#moto-search").on("click", ".moto-row", function () {
        $("#modal").show()
        targa_moto_current = $(this).attr("id")
    });

    $("#modal").on("click", "#registra-pratica", function () {
        $.post("/accettazione/creaRiparazione", {
            "targa": targa_moto_current,
            "note": $("#note-bar").val()
        }, function (data, status) {
            if (status === "success") {
                if (data !== -1) {
                    alert("Pratica creata con codice " + data)
                } else {
                    alert("Errore Pratica")
                }
                $("#modal").hide()
            } else {
                alert("Errore comunicazione con il server")
            }
        })
    })

    $("#cancel-pratica").click(function () {
        $("#modal").hide()
    })
})

