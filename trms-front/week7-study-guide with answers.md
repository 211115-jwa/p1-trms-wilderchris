## AWS S3

## What is AWS S3?

Simple storage service
stores things as objects in buckets

## What is a bucket?

secure available durable, 
virtual storage container for your "objects"

An Amazon S3 bucket is a public cloud storage resource available in Amazon Web Services' 
(AWS) Simple Storage Service (S3), an object storage offering

## How are files stored in buckets?
files stored as objects

When you upload a folder, Amazon S3 uploads all of the files and subfolders from the specified folder 
to your bucket. It then assigns an object key name that is a combination of the uploaded file name and the folder name.
## How do you host a static website using S3?

goto properties then at the bottom choose static website hosting
and fix the permissions in the a policy for public access.

## DevOps

## What is devops?
one team / qa/development/operations
by using automation

## What is CI/CD?
an automation pipeline for integration testing, delivery and deployment

continuous integration
continuous delivery
and /or  continuous deployment

## What is the difference between continuous delivery and continuous deployment?
CD1  pushes of code trigger a deliverable artifact, but not deployed
continuous deployment cd2 have pushes triggering CI the testing, CD1 the deliverable atrifact, then CD2 the artifact is deployed

## What is GitHub Actions?

where you can set up different programs for CI/CD dev ops options available by github

## What is SonarCloud?
is another app that will automate the CI portion. testing your code when everything is pushed.

## What is a devops or CI/CD pipeline?
tests automated to run when code contributions are pushed to github, cd, builds initiated when code is pushed, and cd2 deployed 
when the other 2 have completed... can be with or without the deploye. can mean the 1st two or all

## What are the benefits of devops and CI/CD? What are the drawbacks?

1human error can be a big problem like with the knight capitol group error
2more automated tests means more time to work less time manually testing

drawbacks
if you 

## Logging

## What is logging?
keeping record of the application as it runs

## What are the most common logging levels?

OFF
FATAL-- log errors -> stack overflow, outofmemory, etc.
ERROR-- log exceptions
WARN-- log risks
INFO-- gerneral state - user story- user logging in
DEBUG-- useful info for debugging -> similar to sysout " username entered was:__"
TRACE-- every detailed -> method calls, vaariables values, etc
ALL--all above PLUS custom levels

## What logging level is the most granular?
ALL

## What is the value of logging over just using sysouts?

persistent, data saved to file, hard copy

## Design Patterns

## What is the singleton design pattern?
make sure
one instance of obj
if only one instance is needed/used at a time, setting up a singleton gives only one obj.


## In what situations is it useful?
good to use for objects that you only want to exist once, such as a Scanner or a database Connection

connection or scanner
if only one instance is needed/used at a time

## How is it achieved in Java?
with a private static commection obj for out instance
private constructor
public static synchronized getter method

## What is the factory design pattern?

an enitity that supplies instances ( usually an interface)

more maintainability, ( only change code in one place)  
provides abstraction

## In what situations is it useful?
like when changing from JDBC to hibernate, helps simplify code changes and it gives just one place to call dao methods

when you need to abstract the creation of an object away from its actual implementation

## Why is coding to an interface a good practice?

There is flexibility and maintainability, especially when the code for interfaces may need to change constantly.

## Testing

## What is testing?

where you focus on investigation and discovery. During the testing phase, developers find out whether their code and 
programming work according to customer requirements.

## What is the testing pyramid?

unit testing
Integration testing
Functional testing

<!-- From bottom to top
Unit testing
integration testing
system testing
user acceptance testing -->


ROI
Expensive to write Time to Execute
Quantity of tests

## What is the difference between positive and negative testing?
Positive testing determines that your application works as expected. If an error is encountered during positive
 testing, the test fails. Negative testing ensures that your application can gracefully handle invalid input or unexpected user behavior.

## What is the difference between black box and white box testing?

black box you do not nkow the inner code/working of the app white tbox is when you know the inner workings/code

A positive test is based on validating what output should occur when a user is utilizing a feature "correctly", 
while a negative test is based on what output should occur when a user is utilizing a feature "incorrectly"

## What are the main differences between a test strategy and a test plan?

Test Plan ( is how testing more for team info how it is done) is a document that describes the scope, objective and weight on software testing task 

 Test Strategy (high level how to report it , more for manager)describes how testing needs to be done. Test Plan is used at the project level whereas Test Strategy is used at the organization level.

## What are test cases? Give some examples.
A test case is exactly what it sounds like: a test scenario measuring functionality across a set of actions or 
conditions to verify the expected result. They apply to any software application, can use manual testing or an automated test, and can make use of test case management tools.

## What is the defect lifecycle?
1 find a bug
2assign to a team/developer
3 test and fix
        if not fixed reassigned
4 verify fix
5 close the bug/ bug report

## What is a defect? Is this different from a bug?
behavior that doesnt work as expected- not necessarily bad

defects and bugs are similar

bugs have a severity, & priority
severity: functionality works or not
priority: the importance to product owner
## What is regression testing? Why is this valuable?

Evaluates the functionality of new programming code. Ensures that new coding doesn't interrupt existing
 coding features. Assures there are no defects or bugs after implementing software updates. Allows for retesting existing software after application changes.