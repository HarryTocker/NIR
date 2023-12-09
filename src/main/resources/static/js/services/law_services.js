const LAWS_API = '/api/laws/';

function getLawsByType(jsonCallback, error) {
    getRequest(LAWS_API + "types", response => responseAsJson(response, jsonCallback), error);
}

function saveLawsByType(payload, callback, error) {
    postRequest(LAWS_API + "types", payload, callback, error);
}

function findLawWithMaxPunishments(searchQuery, jsonCallback, error) {
    getRequest(LAWS_API + "find/max_punishment/" + searchQuery, response => responseAsJson(response, jsonCallback), error)
}

function supplyCreateSaveLawsByTypePayload(intentionalLawsCollection, negligenceLawsCollection, unknownLawsCollection) {
    return {
        intentional: intentionalLawsCollection,
        negligence: negligenceLawsCollection,
        unknown: unknownLawsCollection
    }
}