# OUTLINE FOR "DEPOSIT" USECASE OF ECOBIKE PROJECT

## Descriptions
- Users have to deposit an amount of 40% of the value of the bike

## Actor
- Customers
- System
- Inter-bank

### Step for depositing
- Users confirm method of paying and the deposit
- Deposit money is deducted from credit card

### Basic flow for deposting
- Users choose a payment method to transaction (credit card by default)
- Users provide card information and transaction content
- System calculates the deposit
- Users confirm the deposit money
- System sends a request
- Inter-bank deducts money from user's credit card
- System sends an email of transaction info to the users

### Alternative flow
 - Exception: invalid input (EX: card information), not enough money in credit card
