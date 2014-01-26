This package contains a basic Java project, already setup with Gradle build script with a dummy test case and a starting point implementation.

We are required to implement a validator with test in a TDD fashion.

The module will provide an validator which validate an credit number
- Input should should contains numberic, whitespace, or 'hypen' only
- All 'whitespace' or 'hypen' will be ignored or bear no meaning
- The credit number check digit need to be validated according to Luah algorithm
- The validator will be used and deployed in different ways, for example
    - Used as Java library deployed with a Web-based application
    - Wrapped as a web service and provide validation to other termminal service
    - Used as library deployed as part of a Event Driven, or Message Driven application
- Consumer of the libary require to present meaning error message to end user
- You can redesign the validator interface freely (move, rename, extract, use excetion etc)
- Implement unit test with JUnit 4, Hamcrest and Mockito
- Implement libary level behavior test with JBehave where the test should be readable
- You can use whatever IDE you like (or plain text editor)
- Git should be used for tracking your changes 
- Gradle is used to configure / build the project
- Submit your solution after a project clean up (ie: no compilation output)


Solution should be functionally correct, provded by unit/behavior test cases.
In additions, solution will be evaluated with following criteria
- Basic engineering practice for example how do you commit the source code
- How the solution is implemented in a TDD way
- Does the implementation follow good software engineering practice
- Does the solution follow good software engineering design principles


Once an solution is accepted, technical interview will be covering following
- Discuss about the solution
- Enhance the solution or test coverage
- Refactor the solution
- Implement extra requirements
