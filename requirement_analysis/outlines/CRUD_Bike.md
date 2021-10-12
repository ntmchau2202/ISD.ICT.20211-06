# OUTLINE FOR "CRUD BIKE" USECASE OF ECOBIKE PROJECT

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

## Steps for reading bike information
Assuming the administrator has logged in successfully:
- Admin enters administrator page
- Admin chooses to list all the bikes in system

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
### Preconditions: 
These preconditions apply to all below usecases
- Administrator logs in the system successfully
- Administrator has entered the administrator page

### Creating bike
1. Basic flow for creating bike
Step 1: Admin chooses to add new bike
Step 2: EcoBike system displays form for entering new bike information
Step 3: Admin enters new bike information
Step 4: EcoBike validates newly entered information
Step 5: Admin approves to create new bike
Step 6: EcoBike creates new bike instance in the system

2. Alternative flow for creating bike
- At step 4: Admin enters invalid inputs: incompatible type 
  - EcoBike highlights invalid fields and requires admin to provide information that must have required type
  - Continue at step 2
- At step 4: Admin enters invalid inputs: duplicated information for unique fields
  - EcoBike highlights invalid fields and requires admin to provide unique information
  - Continue at step 2
- At step 6: EcoBike failed to create new instance in the system
  - EcoBike notifies errors (with error code) and requires admin to input again
  - Continue at step 2

1. Inputs:
- Bike information: 
  - Name: string, obligated
  - Type: string, obligated
  - License plate: string, obligated, unique
  - Color: string
  - Image of the bike: string (path to image file) 
- Bike barcode: string, obligated, unique
- Bike rental price: float, obligated

4. Outputs:
- A profile of bike in the system, including all inputs

### Reading bike information
1. Basic flow for reading bike information
Step 1: Admin select to list information of the bike
Step 2: EcoBike displays all bikes currently in the system

2. Alternative flow of reading bike information
- At step 2 => 2a: 
  - Administrator chooses one bike to read its detail information 
  - EcoBike displays board contains information of the bike and options for managing the bike
- At step 2 => 2b: Administrator searches for a specific bike
  - Administrator selects to search for bike by name, ID or license plate
  - Administrator enters information to search
  - EcoBike fetches data from the database
    - Displays results if there are any matched bike
      - Administrator choose to see information of one bike => go back to 2a
    - Displays notification when there is no matched bike

1. Inputs:
- 2a: Bike in the list: user click on the bike
- 2b:
  - Search type: search by name, ID or license plate
  - Information for search: string

4. Output
- List of the bikes in the system
- 2a: Bike information
  - Name: string
  - Image of the bike
  - Type of the bike: string
  - Licence plate of the bike: string
  - Color: string
  - Rental price: float
  - Status of the bike: rented/available
- 2b: List of the bikes that match the given criteria

### Updating bike information
*** Additional preconditions: 
- Administrator has chosen to list all the bike
- List of the bike has been displayed to admin
- Admin has chosen one bike to display information
- EcoBike has shown the board for displaying information and managing the bike

1. Basic flow for updating bike information
Step 1: Admin selects to update bike information
Step 2: EcoBike shows form for updating bike information
Step 3: Admin enters new information for the bike
Step 4: EcoBike validates newly entered information
Step 5: Admin approves to update bike information

2. Alternative flow for updating bike information
- At step 4: Admin enters invalid inputs: incompatible type 
  - EcoBike highlights invalid fields and requires admin to provide information that must have required type
  - Continue at step 2
- At step 4: Admin enters invalid inputs: duplicated information for unique fields
  - EcoBike highlights invalid fields and requires admin to provide unique information
  - Continue at step 2
- At step 6: EcoBike failed to update bike information in the system
  - EcoBike notifies errors (with error code) and requires admin to try again
  - Continue at step 2

1. Inputs:
- Bike information: 
  - Name: string, obligated
  - Type: string, obligated
  - License plate: string, obligated, unique
  - Color: string
  - Image of the bike: string (path to image file) 
- Bike barcode: string, obligated, unique
- Bike rental price: float, obligated

4. Outputs:
- A profile of bike in the system, including all updated information


### Basic flow for deleting bike
*** Additional preconditions: 
- Administrator has chosen to list all the bike
- List of the bike has been displayed to admin

1. Basic flow for deleting bike:
Step 1: Admin chooses one bike in the list to display information
Step 2: EcoBike shows the board of bike information and for managing the bike
Step 3: Admin choose to delete the bike from the system
Step 4: EcoBike asks for confirmation
Step 5: Admin approves to delete the bike 
Step 6: EcoBike clears the bike's profile from the system

2. Alternative flow for deleteing bike:
- At step 1 => 1a: Delete multiple bikes
  - Admin chooses to delete multiple bike from the system
  - EcoBike displays checkboxes for admin to selects multiple bikes
  - Admin chooses bikes for delete by select/unselect checkboxes
  - Continue at step 3

3. Inputs:
- Bike/list of bikes to be deleted

4. Outputs:
- Lists of all bikes in the system, after deleting selected bikes
