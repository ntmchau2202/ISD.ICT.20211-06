# OUTLINE FOR "RETURN A BIKE" USECASE OF ECOBIKE PROJECT

## Descriptions
- Customers return the bike they rent

## Actor
- Customers

## Steps for returning bikes
- Users push the bike into an empty docking point of a dock
- Users close the dock
- System return deposit if needed and deduct the amount of money


### Basic flow for returning bike
- Users choose a dock to return
- Users return the bike to the dock chosen
- Users are asked to confirm the card information and transaction content
- System calculates the money and return deposit based on the rental period
- System deducts money from credit card
- System saves transaction info and sends an email to users

### Alternative flow
- Exceptions: users haven't rent a bike yet, the dock chosen is full, not enough money in credit card
