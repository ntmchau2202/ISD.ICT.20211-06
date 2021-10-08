# OUTLINE FOR "DOCK SEARCH" USECASE OF ECOBIKE PROJECT

## Description
- A customer using the app can choose to search for a dock by name or by address

## Actors
- Customers

## Steps for searching dock
Suppose that user has entered the application successfully:
- User chooses to search for a dock
- User chooses to search by name or by address
- User enters the dock's name/address

## Flow of events
### Basic flow
Suppose that user has entered the application successfully:
- User chooses to search for a dock
- User chooses to search by name or by address
- User fully enters the dock's name/address
- EcoBike displays search result and information about the dock, including:
  - Name of the dock
  - Address of the dock
  - Dock area
  - Number of currently available bikes
  - Number of currently empty docking points
  - Distance from current location of user
  - Walking time from current user's location to the dock

### Alternative flow
#### Exception
- Invalid user input 
  - Notify invalid input
- Dock name/address not available
  - Notify dock name/address is not available
- Cannot locate current user's location
  - Asks user to turn on GPS/Notify users about location errors

#### Alternative
- User only enters part(s) of the dock's name/address
  - Display a list of matched results
