
function ocultarMostrarSectionYHeader() {
//     document.getElementById('section').classList.add('d-none');
//     document.getElementById('header').classList.add('d-none');
    isSectionHide = document.getElementById('section').classList.contains('d-none');
    isHeaderHide = document.getElementById('header').classList.contains('d-none');
    if (isSectionHide ) {
        document.getElementById('section').classList.remove('d-none');
    }
    if (isHeaderHide ) {
        document.getElementById('header').classList.remove('d-none');
    }
}