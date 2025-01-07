$(document).ready(function () {
    $(".inventory-list").on("click", ".inventory-item", function () {
        $("#add-button").show()
        $("#update").show()
        $("#add-button-ricambio").hide()
        // Ottieni l'ID del ricambio
        var partId = $(this).attr("id");

        // Ottieni il nome, la quantità e il prezzo del ricambio
        var partName = $(this).find("span").eq(0).text().replace("Nome:", "").trim();
        var partPrice = $(this).find("span").eq(2).text().replace("Prezzo:", "").trim().replace("€", "");
        var partQuantity = $(this).find("span").eq(1).text().replace("Quantità:", "").trim();

        // Imposta i valori negli input del form
        $("#part_id").attr("readonly", true).val(partId);
        $("#part_name").attr("readonly", true).val(partName);
        $("#part_price").attr("readonly", true).val(partPrice);
        $("#part_quantity").attr("readonly", true).val(partQuantity);

        // Mostra la sezione per aggiungere il ricambio
        $(".add-part-section").show();
    });

    $("#add-button").click(function () {
        const id = $("#part_id").val();
        const quantity = $("#part_quantity_add").val();
        $.get("magazzino/aggiungi", {id: id, quantity: quantity}, function (ricambio, status) {
            if (status === "success") {
                $(".inventory-item").each(function () {
                    const id = parseInt($(this).attr("id"));
                    if (id === ricambio.id) {
                        $(this).find("span").eq(1).html("<strong>Quantità:</strong> " + ricambio.quantita + " ");
                    }
                })
                alert("Articolo aggiornato con successo")
                $(".add-part-section").hide();
            } else {
                alert("Errore comunicazione con il server")
            }
        })
    })

    $("#crea-ricambio").click(function () {
        $("#add-button-ricambio").show()
        $("#add-button").hide()
        $("#update").hide()
        $("#part_id").removeAttr("readonly").val("");
        $("#part_price").removeAttr("readonly").val("");
        $("#part_name").removeAttr("readonly").val("");
        $("#part_quantity").removeAttr("readonly").val("");
        $(".add-part-section").show();
    })

    $("#add-button-ricambio").click(function () {
        const id = $("#part_id").val().trim();
        const quantity = $("#part_quantity").val().trim();
        const price = $("#part_price").val().trim();
        const name = $("#part_name").val().trim();
        if (id.length > 0 && quantity.length > 0 && price.length > 0 && name.length > 0) {
            $.get("magazzino/aggiunginuovo", {
                id: id,
                quantity: quantity,
                price: price,
                name: name
            }, function (ricambio, status) {
                if (status === "success") {
                    if (ricambio.id > 0) {
                        const li = $("<li class='inventory-item'></li>")
                        li.attr("id", ricambio.id);
                        const nome = $("<span></span>").html("<strong>Nome:</strong> " + ricambio.nome + " ")
                        const price = $("<span></span>").html("<strong>Prezzo:</strong> " + ricambio.prezzo + "€")
                        const quantita = $("<span></span>").html("<strong>Quantità:</strong> " + ricambio.quantita + " ")
                        li.append(nome);
                        li.append(quantita);
                        li.append(price);
                        $(".inventory-list").append(li)
                        alert("Aricolo inserito con successo")
                        $(".add-part-section").hide();
                    } else {
                        if (ricambio.id === 0) {
                            alert("Codice prodotto già esistente")
                        } else {
                            alert("Codice Prodotto non formattato correttamente")
                        }
                    }
                } else {
                    alert("Errore comunicazione con il server")
                }
            })
        } else {
            alert("Campi vuoti")
        }
    })

});
