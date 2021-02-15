Some idea about this project : 
  -- This should be a session less thing, since this is a IoT device so we do not want to deal with logins or sessions
  -- This is why for authentication we are using tokens in the header
  
  
If the office is closed for a day :
  -- Then the admin should set a flag that will be checked before validation of employee
  -- If the flag says false then no one is allowed else we move to other validations
  
  -- We are maintaining a set of Restricted emplyees before Enter or Exit event
