# OUTLINE FOR "PAY FOR RENTAL BIKE" USECASE OF ECOBIKE PROJECT

## Descriptions
- Customers pay money after returing the rental bike

## Actor
- Customers

### Basic flow for paying for the rental bike
- Users confirm paying method again to make transaction
- Users have to provide card information and transaction content
- System calculates the money based on the rental period
- Users confirm transaction
- The system deduct the money from credit card
- App display and save transaction info in the system
- The system sends an email of transaction info to the customers

### Alternative flow
- Exceptions: not enough money in credit card, invalid input(Ex: card information)
