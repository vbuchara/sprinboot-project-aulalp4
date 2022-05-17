// @ts-check

$(document).find('.user-delete').on('click', (event) => {
    const id = Number($(event.currentTarget).data('id'));
    const confirmDialog = confirm(`Tem certeza que deseja deletar o usuário de id ${id} ?`);

    if(confirmDialog) {
        $.ajax({
            method: 'DELETE',
            url: `${window.location.href}/delete/${id}`,
            success: (response) => {
                window.location.reload();
            },
            error: (error) => {
                console.log(error);
            }
        });
    }
});

/**
 * @param {number} id 
 */
function showSuccessToast(id){

}