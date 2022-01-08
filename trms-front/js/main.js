//let reqAppUrl ='http://localhost:8080/';

getEmployees();

async function getEmployees() {
    let response = await fetch(reqAppUrl + 'employee');  //requestor/4');
    // let response = await fetch(reqAppUrl + 'requestor/1',{method:'PUT', body:JSON.stringify(reqObj)});
    
    if (response.status === 200) {
        let employees = await response.json();
        console.log(employees);
        showEmployees(employees);
    }
}



function showEmployees(employees) {
    let employeesTable = document.getElementById('employee');//all

    // for each pet in the array of pets that we got from the back end
    for (let req of employees) {
        let rowForEmployees = document.createElement('tr');

        // for each field in the pet (yes, we can iterate through fields)
        for (let field in employees) {
            let column = document.createElement('td');
            // if (field!=='status') {
            //     // pet[field] gets the value of the field
            //     column.innerText = pet[field];
            // } else {
            //     column.innerHTML=`<button id="adopt${req.reqId}">Test</button>`;
            // }
            rowForEmployees.appendChild(column);
        }
        employeesTable.appendChild(rowForEmployees);
       // document.getElementById(`adopt${pet.petId}`).onclick = adoptPet;
    }
}