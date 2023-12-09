const LOCALIZATION_API = '/api/localization/';

const FOR_TEXT = 'for/text/';

function getLocalizationText(value, textCallback) {
    getRequest(LOCALIZATION_API + FOR_TEXT + value, response => responseAsText(response, textCallback));
}

async function getLocalizationTextAsync(value) {
    let response = await getRequestAsync(LOCALIZATION_API + FOR_TEXT + value);
    return responseAsTextAsync(response);
}