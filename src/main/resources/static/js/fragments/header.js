$(document).ready(() => {
    $('#language_button').click(() => {
        $('#languages_list')[0].classList.toggle(UI_SHOW_CLASS);
    });

    $(document.body).click(e => {
        let target = e.target;
        let button = $('#language_button')[0];
        let list = $('#languages_list')[0];

        if (!button.contains(target) && !list.contains(target)) {
            list.classList.remove(UI_SHOW_CLASS);
        }
    });

    let currentLanguage = getCookie(COOKIE_CURRENT_LANGUAGE);
    if (!currentLanguage) {
        changeLanguage(COOKIE_DEFAULT_LANGUAGE);
        return;
    }

    $('#selected_language_ico')[0].src = getPathToImage(IMAGES_FLAGS_PATH, currentLanguage);
});

function changeLanguage(language) {
    setCookie(COOKIE_CURRENT_LANGUAGE, language);
    location.reload();
}