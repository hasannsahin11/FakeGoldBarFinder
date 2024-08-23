# FakeGoldBarFinder

## Overview
This is an automation project to solve the Fake Gold Bar problem using Java, Selenium, and TestNG. The program automates the weighing process on the Fetch SDET Challenge website to find the fake gold bar using a balance scale.

## Prerequisites
- Java 19
- Maven
- Chrome Browser
- ChromeDriver

## Setup
1. Clone the repository.
2. Navigate to the project directory.
3. Make sure `chromedriver` is available in your system's PATH or configure the driver path in `BaseTest.java`.

## Running the Tests
1. Open the project in IntelliJ (or any IDE).
2. Run `mvn clean test` to execute the tests via TestNG.
3. Alternatively, right-click the `goldBarTestSuite.xml` file and run it in your IDE.

## Expected Output
The program will automatically find the fake gold bar and display the result in the console.
