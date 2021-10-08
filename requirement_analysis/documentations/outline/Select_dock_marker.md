# OUTLINE FOR "SELECT DOCK" USECASE OF ECOBIKE PROJECT

## Description
- A customer using the app can select a dock to get information of the dock

## Actors
- Customers

## Steps for selecting dock
Suppose that user has entered the application successfully:
- The application locates user's current location
- The application shows locations of nearby docks
- User selects a mark of one dock on the map

## Flow of events
### Basic flow
Suppose that user has entered the application successfully:
- The application locates user's current location
- The application shows locations of nearby docks
- User selects a mark of one dock on the map
- EcoBike displays information of the dock, including:
  - Name of the dock
  - Address of the dock
  - Dock area
  - Number of currently available bikes
  - Number of currently empty docking points
  - Distance from current location of user
  - Walking time from current user's location to the dock
  - Detailed information of available bike, including:
    - Bike name, type, color, license plate
    - Current battery percentage 
    - Available running time estimation

### Alternative flow

#### Alternative
- User select a dock via search result
  - EcoBike displays information of the dock, including:
    - Name of the dock
    - Address of the dock
    - Dock area
    - Number of currently available bikes
    - Number of currently empty docking points
    - Distance from current location of user
    - Walking time from current user's location to the dock
    - Detailed information of available bike, including:
      - Bike name, type, color, license plate
      - Current battery percentage 
      - Available running time estimation
