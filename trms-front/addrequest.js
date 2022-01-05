

//document.getElementById(`reqbutton`).onclick = getRequests;
console.log(loggedInPerson);

async function getRequests() {
    let userInput = document.getElementById('dataInput').value; 

    let response = await fetch(reqAppUrl + 'requests/' + getRequest);
    
    
    if (response.status === 200 || response == '') {
        let requests = await response.json();
        
        console.log(requests);
        showRequests(requests);
    }else{
    alert('404 Not Found: no Request by ID exists');
    }
}

function getRequest(){

    let request = {
        'emp-id':username,
        'event_date':password,
        'event_time':username,
        'location':password,
        'description':username,
        'cost':password,
        'grading_format_id':username,
        'event_type_id':password,
        'status_id':4,
        'submitted_at':(new Date().today() + " " + new Date().timeNow())
    };
}

//document.getElementById('submitbutton').onclick = sendRequest;

                async function sendRequest() {
                    console.log('a');
                    if (loggedInPerson) {
                        let petId = event.target.id;
                        petId = petId.replace('adopt', '');
                
                        let tokenHeader = {"Token":loggedInPerson.id};
                        console.log(tokenHeader);
                        let response = await fetch(petAppUrl + 'pets/adopt/' + petId, {
                            method:'PUT',
                            body:JSON.stringify(loggedInPerson),
                            headers:tokenHeader
                        });
                
                        if (response.status===200) {
                            let updatedPerson = await response.json();
                            loggedInPerson = updatedPerson;
                        } else {
                            let msg = await response.text();
                            alert(msg);
                        }
                    } else {
                        alert('You need to be logged in to adopt pets.');
                    }
                }
                