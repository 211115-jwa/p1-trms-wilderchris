let reqAppUrl ='http://localhost:8080/';

getRequests();
//getPetsAjax();

async function getRequests() {
    let response = await fetch(reqAppUrl + 'requestor/1');
    // let response = await fetch(reqAppUrl + 'requestor/1',{method:'PUT', body:JSON.stringify(reqObj)});
    
    if (response.status === 200) {
        let requests = await response.json();
        console.log(requests);
        showRequests(requests);
    }
}



function showRequests(requests) {
    let requestsTable = document.getElementById('allRequests');//all

    // for each pet in the array of pets that we got from the back end
    for (let req of requests) {
        let rowForRequests = document.createElement('tr');

        // for each field in the pet (yes, we can iterate through fields)
        for (let field in req) {
            let column = document.createElement('td');
            // if (field!=='status') {
            //     // pet[field] gets the value of the field
            //     column.innerText = pet[field];
            // } else {
            //     column.innerHTML=`<button id="adopt${req.reqId}">Test</button>`;
            // }
            rowForRequests.appendChild(column);
        }
        requestsTable.appendChild(rowForRequests);
       // document.getElementById(`adopt${pet.petId}`).onclick = adoptPet;
    }
}

// async function adoptPet() {
//     if (loggedInPerson) {
//         let petId = event.target.id;
//         petId = petId.replace('adopt', '');

//         let response = await fetch(petAppUrl + 'pets/adopt/' + petId,
//             {method:'PUT', body:JSON.stringify(loggedInPerson)});

//         if (response.status===200) {
//             let updatedPerson = await response.json();
//             loggedInPerson = updatedPerson;
//         } else {
//             // TODO something went wrong
//         }
//     } else {
//         alert('You need to be logged in to adopt pets.');
//     }
// }
