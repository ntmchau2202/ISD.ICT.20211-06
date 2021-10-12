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
### Preconditions
- User has entered the application successfully

### Basic flow
Step 1: User chooses to search by name or by address
Step 2: User enters the dock's name/address
Step 3: EcoBike displays search result(s)
  
### Alternative flow
- At step 3 => 3a: EcoBike cannot find any related bike
  - EcoBike displays notification about the failed search
- At step 3 => 3b: 
  - User selects a dock from the result list to display its information
  - EcoBike call "SELECT DOCK" usecase
- At step 3 => 3c: EcoBike cannot locate current location of user for calculating distance and walking time to the dock
  3c1 EcoBike displays notifications about estimation errors
  3c2 EcoBike provides options to locate position by self-input or via GPS
    - 3c2.1 User chooses to input location manually
      - 3c2.1.1. EcoBike shows a dialog box for users to type in location
      - 3c2.1.2 User enters his location into the dialog box
      - 3c2.1.3 EcoBike searches for the entered location on the map
        - Location found, EcoBike estimates distance and walking time to the selected dock
        - Location not found, go back to 3c1
    - 3c2.2 User chooses to use GPS to get current location
      - 3c2.2.1. EcoBike shows a dialog box to guide user to turn on GPS
      - 3c2.2.2. EcoBike waits for a time interval for GPS signals
        - GPS signal found, EcoBike locates current location of user, estimates distance and walking time to the selected dock
        - GPS signal not found, go back to 3c1

### Inputs
- Search option: by name or by address
- Search input: string, obligated

### Outputs
- List of matched docks with their information:  
  - Name of the dock
  - Address of the dock
  - Dock area
  - Number of currently available bikes
  - Number of currently empty docking points
  - Distance from current location of user
  - Walking time from current user's location to the dock
