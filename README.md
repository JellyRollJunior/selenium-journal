# selenium-test
Documenting my journey to learning selenium!

## Reflection:
I got some good debugging experience when doing CrossPlatformTests. 
 - Chrome was passing tests but Safari wasn't! It was a big headache getting element not visible / node not visible / stale element / element out of bounds exceptions until I figured out Safari loads slower than chrome so i needed to place some WebDriverWaits to account for Safaris snail loading. 

## Code Samples:
 - Driver setup
 - Web Elements
 - Locators
   - CssSelector
   - XPath
 - Implicit Waits
 - Explicit Waits
 - Alerts
 - Listeners
 - Exception Handling
 - Robot class
   - basically only use this when Action is insufficient 
   - ex: Action class uses WebDriver API to perform events so if an OS popup occurs, need to use Robot
 - JUnit 4 with Selenium!
 - TestNG with JUnit and Selenium!
 - Cross Browser Testing with TestNG xml file!!!
   - Automated Chrome + Safari testing 
 - Actions and Action class 
 - WebDriverWait
 - JavaScriptExecutor
