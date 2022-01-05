
document.getElementById(`reqbutton`).onclick = getRequests;

async function getRequests() {
    let userInput = document.getElementById('dataInput').value; 

    let response = await fetch(reqAppUrl + 'requests/requestor/' + userInput);
    
    

    if (response.status === 200 || response == '') {
        let requests = await response.json();
        
        console.log(requests);
        showRequests(requests);
    }else{
    alert('404 Not Found: no Request by ID exists');
    }
}
function showRequests(requests) {
   

    let requestsTable = document.getElementById('allRequests');//all
       
       let i = 0;
   //for each request
    for (let req of requests) 
    {
        let submitted = requests[i].submittedAt;
        
        sub = (submitted.monthValue + '/' + submitted.dayOfMonth + '/' + submitted.year);
        console.log(sub);
        let status = (requests[i].status);
        stat = (status.statusId + ': ' + status.name);
        let eventType = (requests[i].eventType);
        eType = (eventType.eventId + ': ' + eventType.name);
        let gradingFormat = (requests[i].gradingFormat);
        gFormat = (gradingFormat.formatId + ': ' + gradingFormat.name);
        let eventTime = (requests[i].eventTime);
        eTime = (eventTime.hour + ':' + eventTime.minute + ':' + eventTime.second);
        let eventDate = (requests[i].eventDate);
        eDate = (eventDate.monthValue + '/' + eventDate.dayOfMonth + '/' + eventDate.year);
        let employees = (requests[i].requestor);
        //console.log(eDate);
        console.log(employees);
        let rowForRequests = document.createElement('tr');
        
        // for each field in the request (yes, we can iterate through fields)
        for (let field in req) {
           
        
                    //
             if (field == 'requestor'){
            let column = document.createElement('td');
            column.innerText = employees.empId;
            rowForRequests.appendChild(column);
            let column1 = document.createElement('td');
            column1.innerText = (employees.firstName +" " + employees.lastName);
            rowForRequests.appendChild(column1);

             }else if(field == 'eventDate'){ 
                let column = document.createElement('td');
                column.innerText = (eDate);
                rowForRequests.appendChild(column);
            }else if(field == 'eventTime'){ 
                let column = document.createElement('td');
                column.innerText = (eTime);
                rowForRequests.appendChild(column);
            }else if(field == 'gradingFormat'){ 
                let column = document.createElement('td');
                column.innerText = (gFormat);
                rowForRequests.appendChild(column);
            }else if(field == 'eventType'){ 
                let column = document.createElement('td');
                column.innerText = (eType);
                rowForRequests.appendChild(column);
            }else if(field == 'status'){ 
                let column = document.createElement('td');
                column.innerText = (stat);
                rowForRequests.appendChild(column);
            }else if(field == 'submittedAt'){ 
                let column = document.createElement('td');
                column.innerText = (sub);
                rowForRequests.appendChild(column);
            }else{
                let column = document.createElement('td');
                column.innerText = req[field];
                //column.innerText = req[field];
                rowForRequests.appendChild(column);

             
             }
           
        }
        i++;
            requestsTable.appendChild(rowForRequests);
         
    }
  
}
