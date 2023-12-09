function changeTab(selectedTabId) {
    let tabs = document.getElementsByClassName("tab_content");

    for(let i = 0; i < tabs.length; i++) {
        let tab = tabs[i];
        if (tab.id === selectedTabId) {
            tab.style.display = "block";
        } else {
            tab.style.display = "none";
        }
    }

    initializeTab(selectedTabId);
}

function initializeTab(selectedTabId) {
    switch (selectedTabId) {
        case "settings_tab_law_types": initializeLawTypesTab(); return;
        default: console.log("Could not initialize tab: " + selectedTabId);
    }
}

function initializeLawTypesTab() {
    let intentionalTableSelector = $('#intentional_law_types_table');
    let negligenceTableSelector = $('#negligence_law_types_table');
    let unknownTableSelector = $('#unknown_law_types_table');

    intentionalTableSelector.empty();
    negligenceTableSelector.empty();
    unknownTableSelector.empty();

    getLawsByType(json => {
        json['intentional'].forEach(value => {
            let row = $(document.createElement('div')).addClass('law_types_table_element')
                .text(value)
                .click(() => {
                    row.parent().find('.law_types_table_element').removeClass('law_types_table_element_active');
                    row.addClass('law_types_table_element_active');
                });
            intentionalTableSelector.append(row);
        });

        json['negligence'].forEach(value => {
            let row = $(document.createElement('div')).addClass('law_types_table_element')
                .text(value)
                .click(() => {
                    row.parent().find('.law_types_table_element').removeClass('law_types_table_element_active');
                    row.addClass('law_types_table_element_active');
                });
            negligenceTableSelector.append(row);
        });

        json['unknown'].forEach(value => {
            let row = $(document.createElement('div')).addClass('law_types_table_element')
                .text(value)
                .click(() => {
                    row.parent().find('.law_types_table_element').removeClass('law_types_table_element_active');
                    row.addClass('law_types_table_element_active');
                });
            unknownTableSelector.append(row);
        })
    }, error => console.log(error));
}

function saveAllLaws() {
    let functionGetPayload = (selector) => {
        let payloadData = [];
        selector.children().each((key, value) => {
            payloadData.push(value.innerText);
        })
        return payloadData;
    };

    let intentionalTableSelector = $('#intentional_law_types_table');
    let negligenceTableSelector = $('#negligence_law_types_table');
    let unknownTableSelector = $('#unknown_law_types_table');

    let intentionalData = functionGetPayload(intentionalTableSelector);
    let negligenceData = functionGetPayload(negligenceTableSelector);
    let unknownData = functionGetPayload(unknownTableSelector);

    saveLawsByType(supplyCreateSaveLawsByTypePayload(intentionalData, negligenceData, unknownData), null, error => console.log(error))
}

function moveLawElementToTable(idCurrentTable, idTargetTable) {
    let currentTableSelector = $('#'+idCurrentTable);
    let targetTableSelector = $('#'+idTargetTable);
    let elementToMove = currentTableSelector.find('.law_types_table_element_active').detach();
    elementToMove.removeClass('law_types_table_element_active');
    targetTableSelector.append(elementToMove);
}