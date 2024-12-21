$(document).ready(function () {
    $("#checkbutton").click(function () {
        $(".dettagli").remove()
        const targa=$("#targa").val().trim().toUpperCase()
        const codice=$("#code").val().trim().toUpperCase()
        if (targa.length >0 && codice.length > 0){
        $.get("checkStatus",{"targa":targa,"code":codice},function (data) {
           if (data.id === 0){
               $(".error-message").show()
               $(".success-message").hide()
           }else {
               $(".error-message").hide()
               const lista=$("#lista")
               const name=$("<li class='dettagli'></li>").html("<strong> Cliente: </strong> " +data.targa.idCliente.nome +" "+ data.targa.idCliente.cognome)
               const marca=$("<li class='dettagli'></li>").html("<strong> Marca: </strong> " +data.targa.marca)
               const modello=$("<li class='dettagli'></li>").html("<strong> Modello: </strong> " +data.targa.modello)
               const stato=$("<li class='dettagli'></li>").html("<strong> Stato: </strong> " +data.stato)
               let li;
               if (data.stato !== "In attesa"){
                   li=$("<li class='dettagli'></li>").html("<strong> Ore di Lavorazione: </strong> " +data.ore)
               }
               lista.append(name)
               lista.append(marca)
               lista.append(modello)
               lista.append(stato)
               lista.append(li)
               const listaNote=$("<ul class='dettagli'><strong> Interventi effettuati: </strong></ul>")
               if (data.lavorazioni !== null) {
                   const note = data.lavorazioni.split(",")
                   note.forEach(nota => {
                       const li = $("<li class='dettagli'></li>").html(nota)
                       listaNote.append(li)
                   })
                   lista.append(listaNote)
               }
               $(".success-message").show()
           }
        });
    }else{
            alert("Campi vuoti")
        }
    })
})