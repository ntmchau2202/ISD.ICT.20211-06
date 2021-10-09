# OUTLINE FOR "DEPOSIT" USECASE OF ECOBIKE PROJECT

## Descriptions
- Users have to deposit an amount of 40% of the value of the bike

## Actor
- Customers

### Basic flow for deposting
- Users choose a payment method to transaction (credit card by default)
- Users provide card information and transaction content
- System sends an email of transaction info to the users
- System calculates the deposit
- Users confirm the deposit money
- System deduct money from credit card

### Alternative flow
 - Exception: invalid input (EX: card information), not enough money in credit card
