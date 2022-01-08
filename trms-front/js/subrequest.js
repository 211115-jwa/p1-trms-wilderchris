

document.getElementById(`submitbutton`).onclick = submitRequest;

async function submitRequest() {

        
    let etp = document.getElementById("eventtype").value;
    let et = {
        "eventId": etp
    }
    let st = {
        "statusId": 4,//
        "name": "Pending Sup",//
        "approver": "Supervisor"//
    };
    let gfid =  document.getElementById("gformat").value;//
    let gf  = {
        "formatId":  gfid
    }
                
    let edate = document.getElementById("eventdate").value;
    let etime = String(document.getElementById("etime").value);
    etime = etime + ":01";
    let local = document.getElementById("street").value + ", "+
                document.getElementById("city").value + ", "+
                document.getElementById("state").value;//
    let desc = document.getElementById("descBox").value;//
    let cost = document.getElementById("cost").value;//
    
    console.log(etime);
    console.log(st);
    console.log(gf);
    console.log(edate);
    let request = {
        "requestor": loggedInPerson,
        "eventDate": edate,
        "eventTime": etime,
        "location": local,
        "description": desc,
        "cost": cost,
        "gradingFormat": gf,
        "eventType": et,
        "status":st,
        "submittedAt": ''
    };
    console.log(request);
    if(request.cost == "" || request.address == "" || request.city){
        alert("Please Enter Proper Form Data")
    }
    else{


        let file = document.getElementById("file").files[0];
        let formData = new FormData();
     
        formData.append("file", file);
        fetch('C/Users/cwild/Documents/revature/p1-trms-wilderchris/trms-front/upload', {method: "POST", body: formData});
        
        let response = await fetch(reqAppUrl + 'requests', {
        method: 'POST',
        body: JSON.stringify(request),
        });
        if (response.status === 201) {
        alert("Request has been sent");
        }
        else
            alert("Something went wrong");
    }    
}

