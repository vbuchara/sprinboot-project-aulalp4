{
    document.addEventListener('DOMContentLoaded', function() {
        const elems = document.querySelectorAll('select');
        const instances = M.FormSelect.init(elems);
    });

    document.addEventListener('DOMContentLoaded', function() {
        const elems = document.querySelectorAll('.datepicker');
        const instances = M.Datepicker.init(elems, {
            format: "dd/mm/yyyy",
            yearRange: 40,
            minDate: new Date(1940, 01, 01),
            maxDate: new Date(),
            autoClose: true,
            i18n: {
                months: [
                    "Janeiro", 
                    "Fevereiro", 
                    "Março", 
                    "Abril",
                    "Maio",
                    "Junho",
                    "Julho",
                    "Agosto",
                    "Setembro",
                    "Outubro",
                    "Novembro",
                    "Dezembro"
                ],
                monthsShort: [
                    "Jan", 
                    "Fev", 
                    "Mar", 
                    "Abr",
                    "Mai",
                    "Jun",
                    "Jul",
                    "Ago",
                    "Set",
                    "Out",
                    "Nov",
                    "Dez"
                ],
                weekdays: [
                    "Domingo",
                    "Segunda",
                    "Terça",
                    "Quarta",
                    "Quinta",
                    "Sexta",
                    "Sábado"
                ],
                weekdaysShort: [
                    "Dom",
                    "Seg",
                    "Ter",
                    "Qua",
                    "Qui",
                    "Sex",
                    "Sab"
                ],
                weekdaysAbbrev: [
                    "D",
                    "S",
                    "T",
                    "Q",
                    "Q",
                    "S",
                    "S"
                ]
            }
        });
    });

    document.addEventListener('DOMContentLoaded', function() {
        const elems = document.querySelectorAll('.sidenav');
        const instances = M.Sidenav.init(elems);
    });

    $(document).find('.logout-button').on('click', (event) => {
        $.ajax({
            method: 'GET',
            url: '/logout',
            success: () => {
                window.location.replace(`${window.location.origin}/login`);
            },
            error: (error) => {
                console.error(error);
            }
        });
    });

    $(document).on('click', '#toast-action-ok', (event) => {
        const toast = $('.toast');
        M.Toast.getInstance(toast).dismiss();
    })

    const toastContainer = $(document).find('#toast-container');

    if(toastContainer.find('span > i').text()){
        M.toast({
            html: toastContainer.html(),
            displayLength: 15 * 1000,
            classes: toastContainer.data('class')
        });
    }
}