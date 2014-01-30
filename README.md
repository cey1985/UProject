Abouve This Package
===================
This package contains a basic Java project, already setup with Gradle build
script with a dummy test case and a starting point implementation.


Problem Description and Requirements
====================================
We are required to implement a validator with test in a TDD fashion.

The module will provide an validator which validate an credit number
- Input can contains numberic, whitespace, or 'hypen' only
- All 'whitespace' or 'hypen' will be ignored or bear no meaning
- The credit number check digit need to be validated according to Luhn algorithm
- Consumer of the libary require to present meaningful error message to end user

Design Consideration
--------------------
- The validator will be used and deployed in different ways, for example
    - Used as Java library deployed with a Web-based application
    - Wrapped as a web service and provide validation to other termminal service
    - Used as library deployed as part of a Event Driven, or Message Driven application
- You can redesign the validator interface freely (move, rename, extract, use excetion etc)

Testability Requirement
-----------------------
- Implement unit test with JUnit 4, Hamcrest and Mockito
- Implement libary level behavior test with jbehave where the test should be readable

Other Requirements
------------------
- Git should be used for tracking your changes 
- Gradle is used to configure / build the project
- Submit your solution after a project clean up (ie: no compilation output)


Expectation
===========
You can implement the solution in whatever IDE you like (or plain text editor)
The submission should be:
- In a tarball / zip file
- Meet all Requirements (include Functional/Design/Testability/Others)
- In additions, solution will be evaluated with following criteria
    - Basic engineering practice for example how do you commit the source code
    - How the solution is implemented in a TDD way
    - Does the implementation follow good software engineering principles


What's Next
===========
Once solution accepted, technical interview will be cover following
- Discuss about the solution
- Enhance the solution or test coverage
- Refactor the solution
- Implement extra requirements
