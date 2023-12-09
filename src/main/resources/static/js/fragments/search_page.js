$(document).ready(() => {
    $('#search_input').on('keypress', (e) => {
        if (e.which !== 13) {
            return;
        }

        e.preventDefault();
        findLaw();
    });
});

async function findLaw() {
    flushError();
    flushResult();

    let value = document.getElementById("search_input").value;

    if (!value) {
        await getLocalizationText(LOCALIZATION_TEXT_EMPTY_REQUEST, text => showError(text));
        return;
    }

    await findLawWithMaxPunishments(value, async json => {
        let lawElement = $(document.createElement('div')).addClass('search_result_container_element');
        let lawElementHeader = $(document.createElement('div')).addClass('search_result_container_element_header')
            .append($(document.createElement('h1')).text(json['article']))
            .append($(document.createElement('h1')).text(json['name']));
        let lawElementType = $(document.createElement('div')).addClass('search_result_container_element_header')
            .append($(document.createElement('h3')).text(json['type']))

        lawElement
            .append(lawElementHeader)
            .append(lawElementType);

        for (let i = 0; i < json['parts'].length; i++) {
            let lawPart = json['parts'][i];
            let lawPartElement = $(document.createElement('div')).addClass('search_result_container_element_part');
            lawPartElement.append($(document.createElement('h4')).text(lawPart['name']));

            if (lawPart['lifeTime'] === true) {
                lawPartElement.append($(document.createElement('p')).text(lawPart['partPunishType']));
            } else {
                lawPartElement.append($(document.createElement('p')).text(lawPart['partPunishType'] + ' ' + lawPart['number'] + ' ' + lawPart['punishType']));
            }

            if (lawPart['optionalCategoryType']) {
                let intentionalType = await getLocalizationTextAsync(LOCALIZATION_TEXT_INTENTIONAL_LAW_TYPE);
                let negligenceType = await getLocalizationTextAsync(LOCALIZATION_TEXT_NEGLIGENCE_LAW_TYPE);
                lawPartElement.append($(document.createElement('p')).text(intentionalType + ' ' + lawPart['categoryType']));
                lawPartElement.append($(document.createElement('p')).text(negligenceType + ' ' + lawPart['optionalCategoryType']));
            } else {
                lawPartElement.append($(document.createElement('p')).text(lawPart['categoryType']));
            }

            lawElement.append(lawPartElement);
        }

        $('#result_container').append(lawElement);
    });
}

function showError(text) {
    let textElement = $(document.createElement('p')).append(document.createTextNode(text));
    let errorBox = $(document.createElement('div')).addClass('rounded_edges').addClass('error_box').append(textElement);
    let errorContainer = $('#error_container');
    errorContainer.append(errorBox);
    errorContainer[0].classList.toggle(UI_SHOW_CLASS);
}

function flushError() {
    let errorContainer = $('#error_container');
    errorContainer.empty();
    errorContainer[0].classList.remove(UI_SHOW_CLASS);
}

function flushResult() {
    $('#result_container').empty();
}