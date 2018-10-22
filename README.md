Factory Pattern used for driver creation during UI automation tests.

Executed with:
```
mvn clean test -Dbrowser=FIREFOX
```

Generate the allure report
```
 mvn allure:serve
```
See test setUp() for other options.


To add Categories to the allure report, create a file called ```categories.json``` inside ```target\allure-results``` prior to report generation that contains the following:
```
[
  {
    "name": "Ignored tests",
    "matchedStatuses": ["ignored"]
  },
  {
    "name": "Failed tests",
    "matchedStatuses": ["failed"]
  },
  {
    "name": "Test defects",
    "matchedStatuses": ["broken"]
  }
]
```