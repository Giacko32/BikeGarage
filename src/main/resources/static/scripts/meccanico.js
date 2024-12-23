$(document).ready(function(){

    $(".vehicle-item").click(function(){
        $("#selectedVehicle").show()
        $("#noVehicleSelected").hide()
        $("#work_hour").hide()
        $("#work_note").hide()
        $(".add-part-section").hide()
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
        $("#selectedVehicle").show()
        $("#noVehicleSelected").hide()
        $("#work_hour").show()
        $("#work_note").show()
        $("#vehiclePlate").text($(this).find("span").eq(0).text().replace("Targa: ","")).attr("idrip",$(this).attr("id"));
        $("#currentState").text("In lavorazione")
        const list=$("#new_status");
        if(list.find("option").length<2) {
            const option = $("<option id='aggiunta' value='Completata'>Completata</option>");
            list.append(option);
        }
        $(".add-part-section").show()
    })
})