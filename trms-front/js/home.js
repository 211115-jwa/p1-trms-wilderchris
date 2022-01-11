
checkLogin().then(getWelcome);


let mainDiv = document.getElementById('info');

function getWelcome(){
    if (!loggedInPerson) {
        
    openLogin();

} else if (loggedInPerson.role.roleId < 11) {// manager welcome
   console.log(loggedInPerson);
   mainDiv.innerHTML = `<h3 class="text-center">Welcome, ${loggedInPerson.firstName + " " + loggedInPerson.lastName + "  "}!</h3>
   <p class="text-center" class="text-dark">This is the TRMS Admin Site. Here's a guide to what you can do here: </p>
   <ul class="list-group-item list-group-item-dark" class="text-center">
    <li class="text-center" class="list-group-item list-group-item-dark">Looking to Search for a reimbusement request?</li>
    <li class="text-center" class="list-group-item list-group-item-dark" > Try "View Requests by Id".</li>
       <li class="text-center" class="list-group-item list-group-item-dark">Looking to submit a reimbusement request?</li>
       <li class="text-center" class="list-group-item list-group-item-dark" > Try "Submit Requests".</li>
       <li class="text-center" class="list-group-item list-group-item-dark">want to check Reimbursements Pending your Approval?</li>
       <li class="text-center" class="list-group-item list-group-item-dark" > Try "Check Pending Requests"</li>
       <li  class="text-center" class="list-group-item list-group-item-dark">Need to check on your submited reimbursements?</li>
       <li class="text-center" class="list-group-item list-group-item-dark" >Try "View Your Requests".</li>
   </ul>`;
 }else{
    mainDiv.innerHTML = `<h3>Welcome, ${loggedInPerson.firstName + " " + loggedInPerson.lastName + "  "}!</h3>
    <p>This is the TRMS. Here's a guide to what you can do here: </p>
    <ul>
        <li>Looking to submit a reimbusement request? Try "Submit Requests".</li>
        <li></li>
        <li>Need to check on your submited reimbursements?Try "View Your Requests".</li>
    </ul>`;
}



}
