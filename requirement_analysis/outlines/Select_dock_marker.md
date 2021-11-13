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
### Preconditions
- List of docks to be selected has been displayed to users
### Basic flow
Step 1: User selects a mark of one dock on the map
Step 2: EcoBike displays information of the dock

### Inputs:
- Dock to be displayed with information

### Outputs:
- Information about the selected dock, including:
  - Name of the dock
  - Address of the dock
  - Dock area
  - Number of currently available bikes
  - Number of currently empty docking points
  - Distance from current location of user
  - Walking time from current user's location to the dock
  - Detailed information of available bike, including:
    - Bike name, type, color, license plate
    - Current battery percentage of e-bikes
    - Available running time estimation