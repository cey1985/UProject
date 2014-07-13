This story is about validating the account number of creditcard.

Narrative:
In order to make sure only the valid account number of creditcard is able to get through
As a developor
I want to do some validations as below.


Scenario: Validate the length account number in which all the characters are digital.
Given the length of creditcard account number is 16
And character(s) that can be ignored: whitespace,hyphon
When an account number of creditcard '1234567812347698' is coming
Then this account number should be able to pass the verfication

Scenario: Validate the length account number which include some extra whitespace or hyphen characters. 
Given the length of creditcard account number is 16
And character(s) that can be ignored: whitespace,hyphon 
When an account number of creditcard ' - 12345-6781  2345-658   -' is coming
Then this account number should be able to pass the verfication

Scenario: Validate the length account number in which some of the characters are whitespace or hyphen.
Given the length of creditcard account number is 16
And character(s) that can be ignored: whitespace,hyphon
When an account number of creditcard ' 2- 5-78123  65 ' is coming
Then this account number should be able to fail the verfication: 1001-Invalid format

Scenario: Validate the length account number that all the characters are digital, but length is 8.
Given the length of creditcard account number is 16
And character(s) that can be ignored: whitespace,hyphon
When an account number of creditcard '12345678' is coming
Then this account number should be able to fail the verfication: 1001-Invalid format

Scenario: Validate the length account number that contains unignorable characters.
Given the length of creditcard account number is 16
And character(s) that can be ignored: whitespace,hyphon
When an account number of creditcard 'A234567T1234769Z' is coming
Then this account number should be able to fail the verfication: 1000-Invalid char

Scenario: Validate the length account number that contains unignorable characters.
Given the length of creditcard account number is 16
And character(s) that can be ignored: whitespace,hyphon
When an account number of creditcard '1234567812345678' is coming
Then this account number should be able to fail the verfication: 1002-Invalid number