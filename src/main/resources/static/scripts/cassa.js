$(document).ready(function(){
    let id_m = 0
    let targa = ""

    $(".search-repair").click(function(){
        const id = $("#repair-code").val()
        if(id !== "" && !isNaN(id)){
            $.get("/cassa/getRiparazione", {"id":id}, function(data){
                console.log(data)
                if(!data){
                    alert("Nessuna riparazione completata con l'id inserito")
                } else {
                    $("#modal").show();
                }
            })
        }
    })

    $("#cancel-pagamento").click(function(){
        $("#modal").hide();
    })

    $("#pagamento").click(function(){
        $.get("/cassa/getDatiPagamento", {"id": $("#repair-code").val()}, function(data){
            let prezzo = 0;
            $("#pezziTable").find(".pezzo_row").remove();
            data.forEach(function(element){
                const new_row = $("<tr></tr>")
                new_row.addClass("pezzo_row")
                const nome_td = $("<td></td>").text(element.idRicambio.nome)
                const quantita_td = $("<td></td>").text(element.quantita)
                const prezzo_td = $("<td></td>").text(element.idRicambio.prezzo + "€")
                prezzo = prezzo + element.quantita * element.idRicambio.prezzo;
                new_row.append(nome_td, quantita_td, prezzo_td)
                $("#pezziTable").append(new_row)
            })
            $("#ore").text(data[0].idRiparazione.ore)
            prezzo = prezzo + data[0].idRiparazione.ore*40;
            id_m = data[0].idRiparazione.idMeccanico.id
            targa = data[0].idRiparazione.targa.targa
            $("#totale").text(prezzo)
            $("#askmodal").hide()
            $("#paymodal").show()
        })
    })

    $("#confirm-pay").click(function(){
        $.post("/cassa/pagamentoCompletato", {"id": $("#repair-code").val(), "ore":$("#ore").text(), "id_m":id_m, "targa":targa})
        alert("Pagamento Completato")
        $("#modal").hide();
        $("#askmodal").show()
        $("#paymodal").hide()
    })


})