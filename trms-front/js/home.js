let mainDiv = document.getElementById('info');

if (loggedInPerson) {
    mainDiv.innerHTML = `<h3>Welcome, ${loggedInPerson.fullName}!</h3>
    <p>This is the TRMS. Here's a guide to what you can do here: </p>
    <ul>
        <li>Looking to submit a reimbusement request? Try "add requests".</li>
        <li>want to setup a new Employee? Try "register".</li>
        <li>Need to change some account information? Try clicking on your username.</li>
    </ul>`;
} else {
    mainDiv.innerHTML = ``;
}
