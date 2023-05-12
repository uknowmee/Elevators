# Elevators
 
 This is a project in which i build small rest api without authorisation and front side error handling to demonstrate elevator managment system inside the building.
 
 There are unfortunatelly no tests, so project isnt fully finished. There is still room for improvement with services responsibilities definition.
 
 From what i played with my app i can say its working!
 
 - Start your simulation and then click + button on the left side.
 - You can add a person to specified floor and specify destination.
 - Then system will recognise your call and send you an elevator. (It uses only your direction, the specified desired floor is being used just when u step into elevator)
 - You can click on both elevator / floor to see its content.
 - Elevator has 5 states: STOPPED, MOVING, OPENING, CLOSING, ENTERING_EXITING.
 - There is 100% chance to enter an elevator that opens on your floor, however you may have to wait a bit untill it delivers you to your place
 
 ---
 
 
# Tech stack:
- next.js, react
- java, spring boot, jpa, hibernate
- postgres
- docker
 
 
 ---
 
 
# How to set up?
 
## 1. Go to rest-server:
- make sure you have java 19 installed
- load maven project
- run: docker-compose.yml
- rum: com.uknowme.Main psvm main method


## 2. Go to elevators-app:
- make sure you have node.js installed
- npm install
- npm run dev
