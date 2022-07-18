# Walkin Clinic 

This is the implementation of  the Walkin Clinic app.
## Team Members
| Name | Student Number | Tasks | 
| --- | --- | --- |
| Kayla Donovan | 300057156 | displays, CircleCI, displaying searches |
| Mahnam Nauman | 8281780 | search clinic by address, UML diagram | 
| Yutaro Miyata | 8912340 | search for clinic directly, check in functionality, book an appointment |
| Minh Ta Anh | 300078762 | search clinic by hours |
| Prashanth Sivoththaman | 8693247 | test classes, search clinic by services |
| Mohannad Shaheen | 8571137 | book an appointment, rate a clinic |

### DIRECTORIES
- Java files for the classes added can all be found within the directory:
	
    https://github.com/professor-forward/project-lab01-group7/tree/f/deliverable04/WalkinClinic/app/src/main/java/com/example/walkinclinicv01

- XML files for the newly added activities can be found in the directory:
	
    https://github.com/professor-forward/project-lab01-group7/tree/f/deliverable04/WalkinClinic/app/src/main/res/layout

- Test files relevant to the features of deliverable 4 can be found in the directory: 
	
    https://github.com/professor-forward/project-lab01-group7/tree/f/deliverable04/WalkinClinic/app/src/androidTest/java/com/example/walkinclinicv01 
	Note JUnit tests were used. 
	
### DATABASE
For our implementation, Firebase was used.
Our database: https://console.firebase.google.com/u/1/project/walkinclinicv01/overview

Please also note: the admin user is stored as 
> ~~firstName = 'John',~~
> ~~lastName = 'Doe',~~
> ~~role = 'Admin',~~
> ~~userName = 'admin'~~
>>role = 'Admin',
> userName = 'qwe@gmail.com' ,
> password = '123456'

### UML DIAGRAM
The UML diagram for our system can be found in the folder UML. Within the directory, there is a PNG file of the diagram as well as the corresponding umple code.
Note: Diagram does not show methods (due to complications with umple). Please refer to the umple code for these methods.

### CIRCLE CI

Build Status
[![Build
Status](https://circleci.com/gh/kayladonovan/project-lab01-group7-circleci.png?branch=f/deliverable04)](https://circleci.com/gh/kayladonovan/project-lab01-group7-circleci/tree/f%2Fdeliverable04)


### RUNTIME SCREENSHOTS

#### After a patient has already created an account, they can login:

![register](https://github.com/professor-forward/project-lab01-group7/blob/f/deliverable04/screenshots/patientLogin.png)



#### The welcome screen for a patient looks like:

![welcome](https://github.com/professor-forward/project-lab01-group7/blob/f/deliverable04/screenshots/welcomePatient.png)



#### After clicking 'Search for Clinic' the patient is then prompted to search by name, address, services, or hours. 
###### After a successful search of an clinic by name, the patient is directed to the PatientClinic Screen:
*A successful search is when the patients input matches a name in the database.*

![name](https://github.com/professor-forward/project-lab01-group7/blob/f/deliverable04/screenshots/searchDirect.png)



##### After a successful search of an clinic by address, the patient is directed to the PatientClinic Screen:
*A successful search is when the patients input matches an address in the database.*

![address](https://github.com/professor-forward/project-lab01-group7/blob/f/deliverable04/screenshots/searchAddress.png)


##### After searching by services provided, or working hours, the patient will be directed to a list of all clinics that match that criteria. 
*An example to show the list of all clinics in the DB at the time of the screenshot is shown below.* 

![list](https://github.com/professor-forward/project-lab01-group7/blob/f/deliverable04/screenshots/listOfAllClinics.png)



#### After finding a clinic, the patient is directed to PatientClinic screen where they can check in:

![initial](https://github.com/professor-forward/project-lab01-group7/blob/f/deliverable04/screenshots/patientClinicInitial.png)


- After checking in, the patient is redirected to ClinicSearch screen (to prevent multiple checkins by same patient)


![checkin](https://github.com/professor-forward/project-lab01-group7/blob/f/deliverable04/screenshots/checkInRedirect.png)


- Searching that same clinic again, you will notice the wait time increments by 15 minutes with each check in. 


![update](https://github.com/professor-forward/project-lab01-group7/blob/f/deliverable04/screenshots/rateUpdate.png)




#### A patient may also rate the selected clinic:

![rate](https://github.com/professor-forward/project-lab01-group7/blob/f/deliverable04/screenshots/rateScreen1.png)


- Ratings can be updated:

![rating](https://github.com/professor-forward/project-lab01-group7/blob/f/deliverable04/screenshots/rateScreen2.png)


- After rating, the patient is redirected to PatientClinic where they will see the updated rating:

![update](https://github.com/professor-forward/project-lab01-group7/blob/f/deliverable04/screenshots/rateUpdate.png)
