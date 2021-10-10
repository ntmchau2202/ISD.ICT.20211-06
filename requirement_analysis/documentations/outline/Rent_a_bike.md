# OUTLINE FOR "RENT A BIKE" USECASE OF ECOBIKE PROJECT

## Description
- A customer using the app to rent a bike

## Actors
- Customers

## Steps for renting a bike
Suppose that user has entered the application successfully:
- User scans the barcode on the lock using EcoBikeRental app
- System shows bike information
- System call "DEPOSIT" usecase

## Flow of events
### Basic flow
Suppose that user has entered the application successfully:
- User scans the barcode on the lock using EcobikeRental app
- System shows bike information, including:
  - Bike type
  - License plate
  - Current battery
- The user choose to deposit
- System call "DEPOSIT" usecase
- The lock is automatically opened, allowing user to use the bike


### Alternative flow
