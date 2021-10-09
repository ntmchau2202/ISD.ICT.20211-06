# OUTLINE FOR "CUD BIKE" USECASE OF ECOBIKE PROJECT

## Descriptions
- Administrator can create, update and delete bike from list of available bike for renting

## Actor
- Administrator

## Steps for creating bike
Assuming the administrator has logged in successfully:
- Admin enters administrator page
- Admin chooses to add new bike
- Admin enters new bike information
  - Bike information: name, type, license plate, color, image
  - Bike barcode
  - Bike rental price
- Admin accepts to create new bike

## Steps for updating bike
Assuming the administrator has logged in successfully:
- Admin enters administrator page
- Admin chooses to update bike information
- Admin chooses bike to update information
- Admin enters new information for the bike
  - Bike information: name, type, license plate, color, image
  - Bike barcode
  - Bike rental price
- Admin accepts to update bike information

## Steps for deleting bike
Assuming the administrator has logged in successfully:
- Admin enters administrator page
- Admin enters update bike information page
- Admin choose bike to delete
- Admin approves to delete bike

## Flow of events
### Basic flow for creating bike
Assuming the administrator has logged in successfully:
- Admin enters administrator page
- Admin chooses to add new bike
- Admin enters new bike information
  - Bike information: name, type, license plate, color, image
  - Bike barcode
  - Bike rental price
- EcoBike validates newly entered information
- Admin approves to create new bike

### Basic flow for updating bike
Assuming the administrator has logged in successfully:
- Admin enters administrator page
- Admin chooses to update bike information
- EcoBike shows lists of bike to update
- Admin chooses bike to update
- Admin enters new information for the bike
  - Bike information: name, type, license plate, color, image
  - Bike barcode
  - Bike rental price
- EcoBike validates newly entered information
- Admin approves to update bike information

### Basic flow for deleting bike
Assuming the administrator has logged in successfully:
- Admin enters administrator page
- Admin choose to update bike information
- EcoBike shows lists of bike to update
- Admin chooses bike to delete
- EcoBike asks for confirmation
- Admin approves to delete bike 

### Alternative flow
#### Exception
- Invalid bike input: license plate duplicated, barcode duplicated
