# LAB 01: USECASE DIAGRAMS
## Tasks:
- Design usecase diagram for EcoBike System, with the problem statment given

## Assignments:
1. Design usecase diagrams: 
- All team
2. Write outline for usecase diagrams:
- Chau: 
 - CUD Bike
 - Search for dock
 - Select dock marker 
- Long:
 - Rent a bike
 - Check bike information
 - Pause rental time
- Duong:
 - Return a bike
 - Deposit
 - Pay for bike rental
 - Return deposit
3. Notes

a. Meeting for designing usecase diagram: 8 PM 05 Oct. 2021

b. Guidelines for writing usecase outline:
- Write in .md file and push it to a specific branch for each usecase. For example, for the usecase "CUD Bike", push your work to `feature/usecase-cud-bike`
- Deadline: 5 PM 09 Oct. 2021
- Meeting on: 8 PM 09 Oct. 2021
- Finish refinement on: before 10 PM 10 Oct. 2021

-------------------- 

# LAB 02: SRS
## Tasks:
- Create specifications documentations for (at least) these usecases:
  - View bike in station and/or View Bike information
  - Rent bike
  - Return bike
- Complete the Software Requirement Specification SRS (following the given template)

## Assignments:
1. Create specifications documentations for usecases:
- Chau:
  - View bike in station and/or View Bike information
  - CRUD bike
- Long:
  - Rent a bike
- Duong:
  - Return a bike

2. Complete SRS Documentation
- Chau

3. Notes:

**For all assignments:** We should follow the templates [here](https://www.dropbox.com/sh/2llptvvm9atklen/AABNO5qyFtfECZ1lyDnXVguDa/Template/SRS-Template-EN.docx?dl=0)
  
a. For the (1) assignment

- Pull the branch `topic/specs` for starting your work easily
- Write your `specifications` in a .docx file and put them in folder `/requirement_analysis/usecase_specifications`
- Your `usecase diagrams` should be put in folder `/requirement_analysis/diagrams/usecase`. 
- Your `activity diagrams` should be put in folder `/requirement_analysis/diagrams/activity`
- Others diagrams (if there are) can be put in folder `/requirement_analysis/diagrams`
- Remember put your `.asta` files in the folder `/requirement_analysis/.asta` also
- Push your work to a specific branch for each usecase, with the convention `topic/specs-usecase-name`. For example, for the usecase "CRUD Bike", push your work to `topic/specs-crud-bike`

b. For the (2) assignment

- Write your `SRS` in a .docx file and put it in folder `/requirement_analysis/usecase_specifications`
- Push your work to the branch `topic/srs`

c. Timeline for working
- 1st deadline: 5 PM 14 Oct. 2021
- 1st meeting for review & questions: 8 PM 14 Oct. 2021
- 2nd deadline: 5 PM 16 Oct. 2021
- 2nd meeting for review, questions and finalize: 8 PM 16 Oct. 2021
- Final deadline before merging: 5 PM 17 Oct. 2021
- Deadline submission: before 10 PM 17 Oct. 2021

-------------------- 

# LAB 03: Interaction diagrams
## Tasks:
- Complete interaction diagrams (including sequence diagram and communication diagram) for these usecases:
  - View station information and/or View bike information
  - Rent bike
  - Return bike

## Assignments:
1. Assignments
- Chau: Interaction diagrams for View bike information usecase
- Long: Interaction diagrams for Rent bike usecase
- Duong: Interaction diagrams for Return bike usecase

2. Notes

a. Submissions:
- Put all your `.astah` files to folder `/architecture_design/.astah`
- Put all your exported diagrams to folder `/architecture_design/diagrams`
- Push your work to a specific branch with the convention `topic/ad-usecase-name`. For example, if you're working with the Rent bike usecase, your branch name will be `topic/ad-rent-bike`

3. Timeline for working
- 1st deadline: 5 PM 23 Oct. 2021
- 1st meeting for review: 8 PM 23 Oct. 2021
- 2nd deadline after review: 5 PM 24 Oct. 2021
- Deadline submission: before 10 PM 24 Oct. 2021

-------------------- 

# LAB 05: Interface design
## Tasks:
- Design graphical user interface: Screen configuration standarlization, mockup designs, screen transition diagrams, detailed screen specifications
- Design interface with another system/device: using subsystem

## Assignments:

1. Assignments:

- Long: design UI and write specifications for Rent bike usecase
- Duong: design UI and write specifications for Return bike usecase
- Chau: design UI and write specifications for the main UI and View bike usecase; propose Interbank subsystem

2. Notes:

a. Submissions:

- Put all your `.astah` file to folder `/detailed_design/interface_design/.astah`
- Put all your exported diagrams to folder `/detailed_design/interface_design/diagrams`
- Put all your UI design (i.e mockups) to folder `/detailed_design/interface_design/design`
- Put all your preview images (if you have) to folder `/detailed_design/interface_design/preview` 
- Put all your images used to conduct the UI to folder `/detailed_design/interface_design/images`. The brand logo or button icons... should be put in `./icon` folder, other images can be put in `./data` folder
- Put all your specfications to folder `/detailed_design/interface_design/interface_specs`
- Push your GUI design to a specific brach with the name convention `topic/gui-usecase-name`, for example, `topic/gui-view-bike`
- Push the subsystem design to a specific branch `topic/subsystem-subsystemname`, for example, `topic/subsystem-interbank`

b. Timeline for working

- 1st meeting for discussing the configuration standarlization and subsystem design: 10 PM 27 Oct. 2021
- 1st deadline: 5 PM 28 Oct. 2021
- 2nd meeting for reviewing work: 8 PM 30 Oct. 2021
- 2nd deadline for refine work: 5 PM 31 Oct. 2021
- Deadline submission: before 10 PM 31 Oct. 2021

-------------------- 

# LAB 05: Analyze class diagram
## Tasks:
- Design class diagrams for the usecases: 
  - View station, and/or View bike information
  - Rent bike
  - Return bike
with all relevant usecases

## Assignments:

1. Assignments:

- Long: Rent bike usecase
- Duong: Return bike usecase
- Chau: View bike station and/or view bike information

1. Notes:

a. Submissions:

- Modify your previous works in your `.astah` files and export them to `/architectural_design/diagrams` folder
- Push your work to a specific branch with naming convention `topic/cd-your-usecase`, for example, `topic/cd-view-bike-info`

b. Timeline for working

- 1st deadline: 5 PM 04 Nov. 2021
- 1st meeting for review: 10 PM 04 Nov. 2021
- 2nd deadline for refining work: 5 PM 06 Nov. 2021
- 2nd meeting for review (if needed): 10 PM 04 Nov. 2021
- Deadline submission: before 10 PM 05 Oct. 2021

-------------------- 

# LAB 06: Class implementation
## Tasks: 
Design class functions and write docs for the classes

## Assignments:
1. Assignments
- Chau: Bike information subsystem and interbank subsystem
- Long & Duong: Rent bike subsystem

2. Notes:

a. Guidelines for submission

- Pull the branch `topic/class-design` and add packages/classes/interfaces according to your design to the source code folder `/src`
- If you need to update the subsystem classes and methods of classes, make a clone of `EcoBike-Full-Class-Diagrams.asta` in folder `architecture_design/.astah`, add your name to the end and modify on this file. Put it in the above `/.astah` folder for future merge
- Write detail doc for classes in comment form, so that it can be exported into JavaDocs later
- Push your work to a specific branch with the naming convention `topic/cd-docs-yourpartname`, for example, `topic/cd-docs-rentbike`

b. Timeline for working

- 5 PM 13 Nov. 2021
- Deadline submission: before 10 PM 13 Nov. 2021

---------------------------

# LAB 07: Data modeling
## Tasks: 
- Implement database for EcoBike system. Write detail specifications for the database

## Assignments:
1. Assignments
- Chau: Bike, BikeInDock, Dock, Administrator database
- Duong & Long: CreditCard, Transaction, Invoice, BikeRent database

2. Notes:

a. Guidelines for submission
- Write your database specification in a .docx file with given template and put it into `/detailed_design/data_modeling`, push it to a specific brand with the convention `topic/data-yourpartofdatabase`

b. Timeline for working
- Team meeting to discuss about database design: 16 Nov. 2021
- Deadline for 1st submission: 8 PM 19 Nov. 2021
- Deadline for submission: 10 PM 20 Nov. 2021
  
----------------------------

# LAB 08: Unit Test
## Tasks: 
- Desig test cases for the application, write corresponding code for these test cases

## Assignments:
1. Assignments
- Chau: Test cases related to adding and viewing bike information
- Duong & Long: Test cases related to rent bike & performing transaction

2. Notes:

a. Guidelines for submission
- Write your testcase specification for each class in a different sheet with name of the class into `unit_test/ecobike_testcase_specs.xlsx` excel file (just for separation and better tracking :) )
- Write your test classes into `src/test` package
- Push your work to a specific branch, namely `topic/test-yourusecase`

b. Timeline for working
- Deadline for 1st submission: 5 PM 3 Dec. 2021
- Deadline for submission: 10 PM 4 Nov. 2021