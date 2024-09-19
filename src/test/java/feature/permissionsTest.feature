Feature: Browser Permissions Test feature

  @PermissionTest
  Scenario Outline: Tests to show the permissions pop-ups are appearing
    When Launch the app "<appName>"
    And Wait for "10000" seconds permissions pop ups to appear


    Examples:
    |appName |
    |clearTripUrl|
    |whatMyLocationUrl|
    |micTestUrl|
    |webCamTestUrl|
