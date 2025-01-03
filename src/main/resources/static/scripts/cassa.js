$(document).ready(function(){
    $(".search-repair").click(function(){
        const id = $("#repair-code").val()
        if(id !== "" && !isNaN(id)){
            $.get("/cassa/getRiparazione", {"id":id}, function(data){
                console.log(data)
                if(!data){
                    alert("Nessuna riparazione con l'id inserito")
                } else {
                    $("#idspan").text(data.id)
                    $("#targaspan").text(data.targa.targa)
                    $("#lavorazionispan").text(data.lavorazioni)
                    $("#orespan").text(data.ore)
                    $("#statospan").text(data.stato)
                    if(data.idMeccanico) {
                        $("#meccanicospan").text(data.idMeccanico.nome + " " + data.idMeccanico.cognome)
                    }
                    $("#modal").show();
                }
            })
        }
    })

    $(".cancel-pagamento").click(function(){
        $("#paymodal").hide();
        $("#askmodal").show();
        $("#modal").hide();
    })

    $("#pagamento").click(function(){
        if($("#statospan").text() === "Completata"){
        $.get("/cassa/getDatiPagamento", {"id": $("#repair-code").val()}, function(data) {
            console.log(data)
            let prezzo = 0;
            $("#pezziTable").find(".pezzo_row").remove();
            data.forEach(function (element) {
                const new_row = $("<tr></tr>")
                new_row.addClass("pezzo_row")
                const nome_td = $("<td></td>").text(element.idRicambio.nome)
                const quantita_td = $("<td></td>").text(element.quantita)
                const prezzo_td = $("<td></td>").text(element.idRicambio.prezzo + "€")
                prezzo = prezzo + element.quantita * element.idRicambio.prezzo;
                new_row.append(nome_td, quantita_td, prezzo_td)
                $("#pezziTable").append(new_row)
            })
            if(data.length > 0){
                $("#ore").text(data[0].idRiparazione.ore)
                prezzo = prezzo + data[0].idRiparazione.ore * 40;
            } else {
                $("#ore").text(0)
            }
            $("#totale").text(prezzo)
            $("#askmodal").hide()
            $("#paymodal").show()
        })
        } else {
            alert("La riparazione non è completata")
        }
    })

    $("#confirm-pay").click(function(){
        $.post("/cassa/pagamentoCompletato", {"id": $("#repair-code").val()})
        alert("Pagamento Completato")
        $("#modal").hide();
        $("#askmodal").show()
        $("#paymodal").hide()
    })


})