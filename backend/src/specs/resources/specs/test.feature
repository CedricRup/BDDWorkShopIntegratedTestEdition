Feature: Time


Scenario: Tell the time

  Given the clock is set to 2017-08-17 15:42:00
  When the time is requested
  Then the system answer with the time 2017-08-17 15:42:00