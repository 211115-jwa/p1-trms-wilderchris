## AJAX/Fetch

## What is JSON?

 a standard text-based format for representing structured data based on JavaScript object syntax. It is commonly used for transmitting data in web applications 

## How do we turn a string into JSON using JavaScript?

JSON.parse() 
const obj = JSON.parse('{"name":"John", "age":30, "city":"New York"}');
Make sure the text is in JSON format, or else you will get a syntax error.

fyi ** When using the JSON.parse() on a JSON derived from an array, the method will return a JavaScript array, instead of a JavaScript object.
[ParsingDates]
Date objects are not allowed in JSON.

If you need to include a date, write it as a string.

You can convert it back into a date object later:

## What is AJAX?

AJAX stands for Asynchronous JavaScript And XML. In a nutshell, it is the use of the XMLHttpRequest
 object to communicate with servers. It can send and receive information in various formats,
including JSON, XML, HTML, and text files.

## What are the 4 steps to sending an HTTP request using AJAX?
let apiURL = 'www.somewebpageOr locaiton.com';
1 create XMLHTTPRequest object: let xhttp = new XMLHttpRequest();
2 Set a callback function for the readyStateChange event:   xhr or xhttp.onreadystatechange = recieveData;
3 Open Request: xhttp.open('GET'.apiURL + '' + userInput);
4 Send the Request: xhttp.send();    

## Why does the callback function for readystatechange have to be an inner function?

so it executes at the right time

let apiURL = 'https://api site or location';    // use forward slash so you dont need double backslash
document.getElementById('getData').onclick = getData;   //get element by id of getData.. document referes ro html doc.. or DOM
function getData() {
let userInput = document.getElementById('dataInput').value; // the .value means the value of the element  of id dataInput
let xhttp = new XMLHttpRequest();               // step 1 of AJAX
xhhtp.onreadystatechange = recieveData;         // step 2 AJAX
xhttp.open('GET',apiURL + '' + userInput);      // step 3 AJAX
xhttp.send();                                   // step 4 AJAX

function recieveData(){

}
}

only happens cause it is inside of this function.. basically when someone clicks or does the event assigned we call the function
when the state changes we call the function
waiting for the readystate the change, check to see if its done
Wrapping your code in function() { .. } delays the computation to when the callback is actually triggered.
You'd still need to wrap your code in function() { .. } to make this work.

## What is the Fetch API?

Similar to ajax only newer, works asychronously like ajax simpler to sue. SENDS REQUESTS AND GETS BACK RESPONSES



## What are Promises in JavaScript?
promises are how fetch achieves waiting readystate chagne and respponding to the diferent ready state changes
.then before  ES7
now instead of .then, with ES7 you can use await

A promise is an object that may produce a single value some time in the future: either a resolved value, 
or a reason that it's not resolved (e.g., a network error occurred). A promise may be in one of 3 possible 
states: fulfilled, rejected, or pending.

## What do the async/await keywords do?
The async keyword turns a method into an async method, which allows you to use the await keyword in its body. 
When the await keyword is applied, it suspends the calling method and yields control back to its caller until the 
awaited task is complete. await can only be used inside an async method.

## What is the syntax for sending an HTTP request with Fetch?
the syntax is 

await can only be used in the async function getData(){         // or what ever you want the name to be

let response = await fetch(apiURL + userInput);
then you want to use an if statement to check status. ie..   response.status === 200 or what ever

## How do we parse the JSON string from a Fetch response?

let data = await response.json();           // dont have to do json parse it will parse for you with the response.json() method

## Agile/SDLC

## What is SDLC?

software development life cycle

## What are the main differences between waterfall and agile?

Agile and waterfall are two distinctive methodologies of processes to complete projects or work items.
 Agile is an iterative methodology that incorporates a cyclic and collaborative process. Waterfall is 
 a sequential methodology that can also be collaborative, but tasks are generally handled in a more linear process.

## What kind of project would be best suited to waterfall?

it allows for departmetnalization and works for a more concrete time frame, agile is for a more collaborative user based project


## What are the benefits of Agile?

more collaborative, focuses on end user experience, deliverables every 2 weeks-ish

## What is an MVP?

Minimum viable product (MVP)is an Agile development technique in which a new product or website is developed 
with just enough features to satisfy early adopters. ... MVP allows product feedback from customers and assesses 
how customers interact with the product at early stages in the SDLC.

## What are user stories?

A user story is the smallest unit of work in an agile framework. It's an end goal, not a feature,
 expressed from the software user's perspective.

## What is story pointing?

A story point is a metric used in agile project management and development to estimate the difficulty
 of implementing a given user story, which is an abstract measure of effort required to implement it.
  In simple terms, a story point is a number that tells the team about the difficulty level of the story.

## What is a sprint?

A sprint is a short, time-boxed period when a scrum team works to complete a set amount of work. 
Sprints are at the very heart of scrum and agile methodologies, and getting sprints right will help
your agile team ship better software with fewer headaches.

## What is Scrum?

a type of agile methodology
Scrum is a framework that helps teams work together. ... Often thought of as an agile project management 
framework, scrum describes a set of meetings, tools, and roles that work in concert to help teams structure
 and manage their work.

## What are some Scrum ceremonies?

. These are Sprint Planning, Daily Stand-Up, Sprint Review, and Sprint Retrospective.

## What is a daily standup?

A daily stand-up meeting is a short, time-boxed team status check, held every day, usually at a set time

## What is a sprint retrospective?

The sprint retrospective is a recurring meeting held at the end of a sprint used to discuss what went well
during the previous sprint cycle and what can be improved for the next sprint.

## What are some Scrum artifacts?

In a basic sense, each Scrum artifact answers a question:

Product backlog: What will the Scrum team work on next sprint?
Sprint backlog: What will the Scrum team work on this sprint and how will they get it done?
Product increment: What will the Scrum team have made by the end of this sprint and how will they know it’s “done”?

## What is the product backlog? Sprint backlog?

product backlog     The product backlog is the long-term plan for the product, where the vision is itemized into 
concrete deliverable items that make the product more valuable.

sprint backlog   The sprint backlog is a list of tasks identified by the Scrum team to be completed during the Scrum sprint. 

## What is a burndown chart?

A burndown chart shows the amount of work that has been completed in an epic or sprint, and the total
 work remaining. Burndown charts are used to predict your team's likelihood of completing their
  work in the time available. They're also great for keeping the team aware of any scope creep that occurs.

## Cucumber

## What is BDD?

behavior driven development

## What is Cucumber?

Cucumber is a testing tool that supports Behavior Driven Development (BDD). It offers a way to write tests that anybody can understand

## What is a feature file?

A Feature File is an entry point to the Cucumber tests. This is a file where you will describe 
.your tests in Descriptive language (Like English). ... A feature file can contain a scenario or can 
contain many scenarios in a single feature file but it usually contains a list of scenarios.

## What is Gherkin?

Gherkin is a language that developers use to define tests in Cucumber. Since this language uses plain English,
 it's meant to describe use cases for a software system in a way that can be read and understood by almost anyone.

## What are some important keywords in Gherkin (en)?
The primary keywords are:
Feature.
Rule (as of Gherkin 6)
Example (or Scenario )
Given , When , Then , And , But for steps (or * )
Background.
Scenario Outline (or Scenario Template )
Examples (or Scenarios )

## What is gluecode? What are step implementations?

Glue Code is the code that interacts directly with your application. There are two kinds of Glue code – Step Definitions and Hooks.
step implementations.. the code for the tests after feature files run    

A Step Definition is a Java method with an expression that links it to one or more Gherkin steps
## Where do feature files go in a Maven project?

src/test/resources

## What happens when you run a feature file?

1st time it creates the test file for you and labels where your code goes.. after the first time you run it it will
run the tests

## Selenium

## What is Selenium WebDriver?

Selenium WebDriver is a web framework that permits you to execute cross-browser tests. 
This tool is used for automating web-based application testing to verify that it performs expectedly. 
Selenium WebDriver allows you to choose a programming language to create test scripts.

## How do we set it up in our application?

download install and use driver for the browser you will use

## What are some important interfaces in Selenium?
webElement
WebDriver

## How do we navigate to a particular page?

.navigate();
navigate.to
.foward
.refresh()
.back()

## How do we select elements from the page?

findElement

findElement(By.id("loginbutton"));

## How do we type things into an input element?

sendkeys() 

## How do we click on an element?

click() method

## What are the different types of waits? Which is the best and why?
best
fluent wait

driver.manage().timeouts().implicitlyWait(10,timeUnits.Seconds);

worst
thread.sleep(5000)

implicit
webdriver timeout

## What is the Page Object Model? What are the benefits of it?
design patteren for organizing elements for reusability and readablility
creating a class for the page testing ... as you would want it to be used/as it is to the user

## What are the PageFactory and the @FindBy annotations?

Page Factory is a class provided by Selenium WebDriver to support Page Object Design patterns. In Page Factory,
 testers use @FindBy annotation. The initElements method is used to initialize web elements. Similarly, one can
  use @FindBy with different location strategies to find web elements and perform actions on them.

## What is meant by "end to end" (e2e) testing?

End-to-end testing is a technique that tests the entire software product from beginning to end to ensure the application
flow behaves as expected. It defines the product's system dependencies and ensures all integrated pieces work together as expected.