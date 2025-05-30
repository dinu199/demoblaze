# Demoblaze Test Automation

This project contains automated tests for the [demoblaze](https://www.demoblaze.com) website using Selenium and Cucumber.

## Browser Configuration

The tests read the `browser` property from `src/test/resources/application.properties` to decide which browser to use. Valid values are:

- `chrome`
- `edge`

If an unknown value is provided, the tests will log a warning and default to **Chrome**.
