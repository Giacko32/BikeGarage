$(document).ready(function(){
    $(".vehicle-item").click(function(){
        $("#selectedVehicle").show()
        $("#noVehicleSelected").hide()
        $("#work_hour").hide()
        $("#work_note").hide()
        $(".add-part-section").hide()
        $("#update-vehicle").show()
        $("#update-vehicle-lav").hide()
        $("#aggiunta").remove()
        $("#vehiclePlate").text($(this).find("span").eq(0).text().replace("Targa: ","")).attr("idrip",$(this).attr("id"));
        $("#currentState").text("In attesa")
    })

    $("#update-vehicle").click(function(){
        const ripId=$("#vehiclePlate").attr("idrip");
        const status=$("#new_status").val();
        const mecId=$("#ImpId").text();
        $.post("meccanico/aggiornastato",{"ripId":ripId,"stato":status,"mecId":mecId},function(riparazione){
            console.log(riparazione);
            if (riparazione.id !== -1){
                $(".vehicle-item").each(function(){
                    const id=parseInt($(this).attr("id"));
                    if(id===riparazione.id){
                        const li=$("<li class='vehicle-item-lav'></li>")
                        li.attr("id",$(this).attr("id"));
                        const targa=$("<span></span>").html("Targa: <strong>"+riparazione.targa.targa+ "</strong>")
                        const stato=$("<span></span>").html("Stato: <strong>In lavorazione</strong>")
                        li.append(targa);
                        li.append(stato);
                        $("#vehicle-list-mine").append(li)
                        $(this).remove()
                    }
                })
                alert("Stato della moto aggiornato con successo")
                $("#selectedVehicle").hide()
                $("#noVehicleSelected").show()
            }
        })
    })

    $("#vehicle-list-mine").on("click",".vehicle-item-lav",function(){
        $(".pezzi-aggiunti").remove()
        $("#selectedVehicle").show()
        $("#noVehicleSelected").hide()
        $("#work_hour").show()
        $("#work_note").show()
        $("#update-vehicle").hide()
        $("#work_notes").show().val($(this).find("span").eq(2).text());
        $("#work_hours").show().val($(this).find("span").eq(3).text()).attr("min",$(this).find("span").eq(3).text());
        $("#update-vehicle-lav").show()
        $("#vehiclePlate").text($(this).find("span").eq(0).text().replace("Targa: ","")).attr("idrip",$(this).attr("id"));
        $("#currentState").text("In lavorazione")
        const list=$("#new_status");
        if(list.find("option").length<2) {
            const option = $("<option id='aggiunta' value='Completata'>Completata</option>");
            list.append(option);
        }
        const ripId=$("#vehiclePlate").attr("idrip");
        $.get("meccanico/getPezzi",{idRip:ripId},function(collezione){
            collezione.forEach((item)=>{
                const li=$("<li style='color: black' class='pezzi-aggiunti'></li>")
                const quantita=$("<span></span>").html(" Quantità: <strong> "+ item.quantita + " </strong>" );
                const nome=$("<span></span>").html("<strong>"+item.idRicambio.nome +" </strong>");
                li.append(nome);
                li.append(quantita);
                $("#parts_list").append(li)
            })
        })
        $(".add-part-section").show()
    })

    $("#update-vehicle-lav").click(function(){
        const span=$("#vehiclePlate")
        const ripId=span.attr("idrip");
        const status=$("#new_status").val();
        const lavorazioni=$("#work_notes").val()
        const ore=$("#work_hours").val()
        // Crea un oggetto con i parametri da inviare
        var riparazioneData = {
            idRip: ripId,           // ID della riparazione
            stato: status, // Stato della riparazione
            notes: lavorazioni,  // Note sulla riparazione
            hours: ore,           // Ore impiegate per la riparazione
        };
        $.post("meccanico/updateriparazione",riparazioneData,function(riparazione){
            alert("Riparazione aggiornata con successo")
            $(".vehicle-item-lav").each(function(){
                console.log(riparazione);
                const id=parseInt($(this).attr("id"));
                if (id===riparazione.id){
                    $(this).find("span").eq(2).text(riparazione.lavorazioni);
                    $(this).find("span").eq(3).text(riparazione.ore);
                }
            })
        })
    })

    $("#add-used-part").click(function(){
        const pezzo=$("#parts_used").val()
        const li=$("<li style='color: black' class='pezzi-aggiunti'></li>")
        const quantita=$("<span></span>").html(" Quantità: <strong> "+ 1 + " </strong>" );
        const nome=$("<span></span>").html("<strong>"+ pezzo +" </strong>");
        li.append(nome);
        li.append(quantita);
        $("#parts_list_temp").append(li)
    })
})