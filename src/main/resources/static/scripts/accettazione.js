$(document).ready(function () {
    $("#filter-cliente").on("input", function () {
        const filtro = $("#filter-cliente").val();
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
})