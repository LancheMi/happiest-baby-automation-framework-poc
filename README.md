# Happiest Baby Technologies
## Test Automation Framework (Proof of Concept)

### How to execute the tests

Build the project before executing the tests:

`./gradlew clean build -x test`

In case there are issues with executing ./gradlew commands run the following:

`chmod +x gradlew`

Tests can be executed by right-clicking on the test method and selecting the run option from the context menu. 
Same execution method applies to test classes, packages and test suites.

To execute all the tests in the project from the terminal use:

`./gradlew clean test`

For running a specific test suite from the terminal use:

`./gradlew clean test -Dsuite=full`

After test execution the allure report can be generated with:

`./gradlew allureReport`

Report will be generated under build/reports/allure-report/allureReport folder, the index.html file can be opened from a browser 