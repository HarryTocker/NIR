function getCookie(cookieName) {
    let name = cookieName + '=';
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(';');
    for(let i = 0; i <ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) === ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) === 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function setCookie(name, value) {
    document.cookie = name + '=' + value;
}

function getPathToImage(path, name) {
    return path + name + IMAGES_EXTENSION;
}

function responseAsText(response, callback) {
    response.text().then(text => callback(text));
}

async function responseAsTextAsync(response) {
    return await response.text();
}

function responseAsJson(response, callback) {
    response.json().then(json => callback(json));
}

async function getRequestAsync(path) {
    let response = await fetch(path);
    if (response.status !== 200) {
        console.log(response);
    }

    return response;
}

function getRequest(path, callback, error) {
    fetch(path).then(response => {
        if (response.status === 200) {
            if (callback != null) {
                callback(response);
            }
        } else {
            if (error != null) {
                error(response);
            }
        }
    });
}

function postRequest(path, payload, callback, error) {
    fetch(path, {
        method: "POST",
        body: JSON.stringify(payload),
        headers: {"Content-type": "application/json; charset=UTF-8"}
    }).then(response => {
        if (response.status === 200) {
            if (callback != null) {
                callback(response);
            }
        } else {
            if (error != null) {
                error(response);
            }
        }
    })
}