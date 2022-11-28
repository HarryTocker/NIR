function findLaw() {
    flushResponse()
    flushError();
    let value = document.getElementById("input-data").value;
    if (!value) {
        setError("Поисковый запрос пуст");
        return;
    }

    fetch("http://localhost:8080/api/laws/find/max_punishment/" + encodeURI(value)).then(function(response) {
        return response.json();
    }).then(function(data) {
        console.log(data)
        if (data.code && data.message) {
            setError(data.message);
            return;
        }
        processResponse(data);
    }).catch(function() {
        flushError();
        setError("Непредвиденная ошибка");
    });
}

function setError(message) {
    let parent = document.getElementById("error-data");
    let text = document.createElement("p");
    text.appendChild(document.createTextNode(message))
    text.classList.add("error-text");
    text.id = "error-text-id";
    parent.appendChild(text);
}

function flushError() {
    let parent = document.getElementById("error-text-id");
    if (parent) {
        parent.remove();
    }
}

function flushResponse() {
    let parent = document.getElementById("result-list-container");
    if (parent) {
        parent.remove();
    }
}

function processResponse(data) {
    let parent = document.getElementById("result-list");

    let container = document.createElement("div");
    container.id = "result-list-container"
    container.classList.add("result-list-data");

    let header = document.createElement("div");
    header.classList.add("result-header");
    let article = document.createElement("h2");
    article.innerText = "УК РФ " + data.article + " - " + '"' + data.name + '"';
    header.appendChild(article);

    container.appendChild(header);

    for (const part of data.parts) {
        let partItem = document.createElement("div");
        partItem.classList.add("result-item");

        let partHeader = document.createElement("h4");
        partHeader.innerText = "Часть: " + part.part;
        partHeader.classList.add("part-header");
        partItem.appendChild(partHeader);

        let punishment = document.createElement("p");
        punishment.innerText = (part.punishment.type === "Штраф" ? part.punishment.type + " " : part.punishment.type + " до ") + part.punishment.maxTime + " " + part.punishment.dateType;
        punishment.classList.add("part-content");
        partItem.appendChild(punishment);

        container.appendChild(partItem);
    }

    parent.appendChild(container);
}