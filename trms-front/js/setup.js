"use strict";

// global variables to be referenced in other scripts
let reqAppUrl = 'http://localhost:8080/';
let loggedInPerson;

// async functions implicitly return promises, so you
// can set a callback function for after the promise/function
// is resolved. here, i'm having the "setupNav" be called
// after checkLogin resolves.
checkLogin().then(setupNav);

// this checks if someone is currently logged in in the
// browser session (local storage) then retrieves their info
// before setting up the page
async function checkLogin() {
    let personId = localStorage.getItem('Token');
    if (personId) {
        let response = await fetch(reqAppUrl + 'users/' + personId + '/auth');
        if (response.status === 200) {
            loggedInPerson = await response.json();
        }
    }
}

// sets up the nav bar that appears on all pages
// depending on whether the user is logged in and
// what their role is
function setupNav() {
    let nav = document.getElementById('nav');
    console.log(loggedInPerson);
    if (!loggedInPerson) {
        nav.innerHTML = `<a href="index.html"><b>Home</b align="left"></a><span id="navLeft">
        <a hidden>View Requests by Employee id</a>
        <a hidden>Submit a Request</a>
        </span>
        <span id="navRight">
        <button id="login">Log In</button>
        </span>`;

        document.getElementById('login').addEventListener('click',openLogin);
    } else if (loggedInPerson.role.roleId < 11) {// set for supervisor
        nav.innerHTML = `<a href="index.html"><b>Home</b align="left"></a><span id="navLeft"> 
        <span></span>
        <a href="requests.html">&#x1F465 View Requests by id</a> 
        <div>
        <a href="addrequest.html">&#x2709 Submit a Request</a></span>
        </div>
        <span><a href="manage.html">  &#x1F5F8 Check Pending Requests</a></span> 
        <span><a href="view.html"> &#x1F441 View your Requests</a></span>
        <span id="navRight"><span>&#x1F916</span> 
        <a href="index.html">${loggedInPerson.username}</a>
        <button id="logout">Log Out</button>
        </span>`;

        document.getElementById('logout').addEventListener('click',logOut);
    } else {// default
        nav.innerHTML = `<a href="index.html"><b>Home</b align="left"></a><span id="navLeft">
        <a hidden>View Requests by Employee id</a>
        <a href="addrequest.html"> &#x2709 Submit a Request</a>

        <span><a href="view.html"> &#x1F441 View your Requests</a></span> 
        </span>
        <span id="navRight">
        <a href="index.html">${loggedInPerson.username}</a>
        <button id="logout">Log Out</button>
        </span>`;

        document.getElementById('logout').addEventListener('click',logOut);
    }

    closeLogin();
}

function openLogin() {
    let loginPane = document.createElement('section');
    loginPane.id = 'loginPane';
    loginPane.innerHTML = `
        <form class="loginForm" id="loginForm">
            <h3>You need to be Logged In to Access the Tuition Management System</h3>
            <label for="username">Username:</label>
            <input type="text" id="username" name="username">
            &nbsp;&nbsp;&nbsp;
            <label for="password">Password:</label>
            <input type="password" id="password" name="password">
            <button id="loginBtn" type="button">Submit</button>
        </form>`;
    document.getElementsByTagName('main')[0].insertAdjacentElement("beforebegin",loginPane);

    document.getElementById('loginBtn').addEventListener('click', submitLogin);

    document.getElementById('login').removeEventListener('click',openLogin);
    document.getElementById('login').addEventListener('click',closeLogin);
}

function closeLogin() {
    if (document.getElementById('loginForm')) {
        document.getElementById('loginForm').remove();
    }
    if (document.getElementById('login')) {
        document.getElementById('login').addEventListener('click',openLogin);
    }
}

async function submitLogin() {
    let username = document.getElementById('username').value;
    let password = document.getElementById('password').value;

    let credentials = {
        'username':username,
        'password':password
    };

    let response = await fetch(reqAppUrl + 'users/auth',{method:'POST',body:JSON.stringify(credentials)});
    if (response.status===200) {
        let token = await response.text();
        localStorage.setItem('Token', token);
        checkLogin().then(setupNav);
    } else if (response.status===404) {
        // TODO
        let msg = await response.text();
        alert(msg);
    }
}

function logOut() {
    localStorage.removeItem('Token');
    loggedInPerson=null;
    window.location.href = 'C:/Users/cwild/Documents/revature/p1-trms-wilderchris/trms-front/index.html';
   
    checkLogin().then(setupNav);

}

