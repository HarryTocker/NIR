// Регистрируем действие на форме при нажатии кнопки Enter, чтобы выполнить поиск по законам.
// Регистрации происходит при выполнении события DOMContentLoaded - когда DOM элемент построен браузером.
document.addEventListener("DOMContentLoaded", function () {
    let inputForm = document.getElementById("input-data");
    inputForm.addEventListener("keypress", function (event) {
        if (event.key !== "Enter") {
            return;
        }

        event.preventDefault();
        findLaw();
    })
});

// Найти закон по поисковой строке.
function findLaw() {
    // Очищаем поля результата и ошибки.
    flushResponse()
    flushError();

    // Получаем значение в поисковой строке.
    let value = document.getElementById("input-data").value;
    if (!value) {
        setError("Поисковый запрос пуст");
        return;
    }

    // Отправляем запрос на API для получения результата.
    fetch("http://localhost:8080/api/laws/find/max_punishment/" + encodeURI(value)).then(function(response) {
        return response.json();
    }).then(function(data) {
        if (data.errorCode && data.message) {
            setError(data.message);
            return;
        }
        // Если ответ пришел без ошибок, обрабатываем результат.
        processResponse(data);
    }).catch(function(exception) {
        // Если произошла непредвиденная ошибка.
        setError("Непредвиденная ошибка. Возможно отсутствует подключение к серверу.");
    });
}

// Очистить текст ошибки.
function flushError() {
    let parent = document.getElementById("error-text-id");
    if (parent) {
        parent.remove();
    }
}

// Очистить результат запроса.
function flushResponse() {
    let parent = document.getElementById("result-list-container");
    if (parent) {
        parent.remove();
    }
}

// Установить ошибку с текстом.
function setError(message) {
    let parent = document.getElementById("error-data");
    let text = document.createElement("p");
    text.appendChild(document.createTextNode(message))
    text.classList.add("error-text");
    text.id = "error-text-id";
    parent.appendChild(text);
}

// Установить результат запроса.
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

        let partPunishment = part.punishments[0];
        let punishment = document.createElement("p");
        // Устанавливаем текст наказания
        let punishmentText;
        if (partPunishment.isLifeTime === true) {
            punishmentText = partPunishment.type;
        } else if (partPunishment.type === "Штраф") {
            punishmentText = partPunishment.type + " " + partPunishment.maxTime;
        } else {
            punishmentText = partPunishment.type + " до " + partPunishment.maxTime + " " + partPunishment.dateType;
        }
        punishment.innerText = punishmentText;

        punishment.classList.add("part-content");
        partItem.appendChild(punishment);

        container.appendChild(partItem);
    }

    parent.appendChild(container);
}