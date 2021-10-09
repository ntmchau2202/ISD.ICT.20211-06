# OUTLINE FOR "CUD BIKE" USECASE OF ECOBIKE PROJECT

## Descriptions
- Customers return the bike they rent

## Actor
- Customers

## Steps for returning bikes
- Users push the bike into an empty docking point of a dock
- Users close the dock
- System return deposit if needed and deduct the amount of money


### Basic flow for deleting bike
- Users choose a dock to return
- User return the bike to the dock chosen
- System calculate the money and return deposit based on the rental period
- System deduct money from credit card

### Alternative flow
- Exceptions: users haven't rent a bike yet, the dock chosen is full, not enough money in credit card
