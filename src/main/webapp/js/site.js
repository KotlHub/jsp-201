document.addEventListener('DOMContentLoaded', function() {
    // var elems = document.querySelectorAll('.modal');
    //var instances =
        M.Modal.init(document.querySelectorAll('.modal'), {
        opacity: 0.6,
        inDuration: 200
    });
        const createButton = document.getElementById("db-create-button");
        if(createButton)
        {
            createButton.addEventListener('click', createButtonClick);
        }

    const insertButton = document.getElementById("db-insert-button");
    if(createButton)
    {
        insertButton.addEventListener('click', insertButtonClick);
    }
});

function createButtonClick() {
    fetch(window.location.href, {
        method: 'PUT'
    }).then(r => r.json()).then(j => {
        console.log(j);
    });
}
function insertButtonClick() {
    const nameInput = document.querySelector('[name="user-name"]');
    if( ! nameInput ) throw '[name="user-name"] not found' ;
    const phoneInput = document.querySelector('[name="user-phone"]');
    if( ! phoneInput ) throw '[name="user-phone"] not found' ;

    fetch(window.location.href, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            name: nameInput.value,
            phone: phoneInput.value
        })
    }).then(r => r.json()).then(j => {
        console.log(j);
    });
}