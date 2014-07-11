LuhnGame Problem
==================

About this File
===============
Please read this file carefully. This will set out
- What this 'Coding Task' is about
- What is the Expectation?
    - What we you going to do
    - What we you going to return


Exercise Objectives
--------------------------
This is a part of the interview process. We expect candidate completes the exercise and follows the rules set out below.
When we receive the result, we will evaluate on the key points defined on 'Expectation Section'


What will be next after this round?
----------------------------------------------
We will call the candidate to proceed a follow-up session
- To discuss the details of the code which candidate has submitted?
- To extend / change / refactor the program code with a pair programming session
- Implement additional requirements
- Discuss any other topics (can be related, or not related to the exercise)

What we Value?
---------------------
We value professionals on the software engineering disciple, who
- Love to write clean code, clean design
- Strong on technical skills
- Can think and solve problems in a clear and logical way

These key points above are the key-criteria when the work result is under evaluation.


About This Package
===================
This package (the zip file) contains a basic Java project, already setup with Gradle build
Script with a dummy test case and an empty implementation.


Coding Problem Description and Requirements
---------------------------------------------------------------
You are required to implement a small Java module which provides common business identifier validation.
Note: There are different types of account number, and for each type, different validation algorithms are available.

This exercise only expects one type of validation: Credit Card Number validation and one specific algorithm: Luhn algorithm

The module will provide an validator which validate an credit card number
- Input contains numeric, whitespace, or 'hyphen' only
- All 'whitespace (include leading/trailing)' or 'hyphen' will be ignored or bear no meaning
- Card number must be 16 digits length
- The credit card number's check-digit needs to be validated according to Luhn algorithm
    - Luhn algorithm can be referred below.
    - You are not required to follow exact same steps as long as the result is correct
    - http://en.wikipedia.org/wiki/Luhn_algorithm
    - http://www.brainjar.com/js/validation/default2.asp
    - http://rosettacode.org/wiki/Luhn_test_of_credit_card_numbers

- The module expect to provide a single and simple method for credit card number validation
    - However, consumer of the library require to present meaningful error message to end user if any
    - Therefore, user can tell the reject is due to invalid char, invalid format, or invalid number (because of check digit check)

- The validator module will be used by other applications
    - Application can be 'Web Base', 'REST service', and even Event Driven / Message Driven application 

- The validator should be Thread safe

Testability Requirement
-----------------------
- The implementation should be done with TDD
- Implement unit test with JUnit 4, Hamcrest and Mockito
- Implement library level behavior test with jbehave where the test should be readable

Other Requirements
------------------
- Git should be used for tracking your changes 
    - You can either do it on master or create a separate branch
    - The Git commit history is important and used for evaluation as well
    - The commit should be small and commit message should be clear
	
- Gradle must be used to configure / build the project
    - With the result, the "test" tasks on Gradle need to execute all tests and all should pass
- Submit your solution after a project clean up (i.e.: no compilation output)
- Make sure the design and code is clean (your best effort), and follow good software engineering principles
