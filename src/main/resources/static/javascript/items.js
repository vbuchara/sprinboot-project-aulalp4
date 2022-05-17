// @ts-check

const toastContainer = $(document).find('#toast-container');

$(document).find('.item-delete').on('click', (event) => {
    const id = $(event.currentTarget).data('id');
    const confirmDialog = confirm(`Tem certeza que deseja deletar o item de id ${id} ?`);

    if(confirmDialog) {
        $.ajax({
            method: 'DELETE',
            url: `/items/${id}`,
            success: (response) => {
                console.log(response);
                showSuccessToast(response);
                deleteRow(id);
            },
            error: (error) => {
                console.log(error);
            }
        });
    }
});

/**
 * @param {any} response
 */
 function showSuccessToast(response){
    const toastContainer = $(document).find('#toast-container');
    const toastMsgElement = toastContainer.find('span').first();
    const toastIconElement = toastMsgElement.find('i').first();

    toastContainer.addClass("success-msg");
    toastIconElement.text("check_circle");
    toastMsgElement.html(toastIconElement.get()[0].outerHTML + response.mensagem);

    M.toast({
        html: toastContainer.html(),
        displayLength: 15 * 1000,
        classes: toastContainer.data('class')
    });
}

/**
 * @param {number} id 
 */
function deleteRow(id){
    $('table').first().find(`tr > td:contains(${id})`).parent().remove();
}