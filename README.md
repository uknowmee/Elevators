# Elevators - discrete simulation of elevator system management
 
 ---
 
 - This is a project in which i build small rest api without authentication and front side error handling to demonstrate elevator management system inside the building.
 - This isn't real world scenario because everything is based on discrete simulation. In reality the problem solution should be way more complex.
 - There are unfortunately no tests, so project isn't fully finished. There is still room for improvement with services responsibilities definition.
 
 ---
 
 - For the future development I'd like to focus on giving person possibility to decide either to enter elevator or not.
 - I'd also like to improve overall algorithm of adding goals to elevators.
 
 ---
 
From what i played with my app i can say it's working! - However there are unfortunately no tests yet so it might be not accurate :D
 
 - Start your simulation and then click + button on the left side.
 - You can add a person to specified floor and specify destination.
 - Then system will recognise your call and send you an elevator. (It uses only your direction, the specified desired floor is being used just when u step into elevator)
 - You can click on both elevator / floor to see its content.
 - Elevator has 5 states: STOPPED, MOVING, OPENING, CLOSING, ENTERING_EXITING.
 - There is 100% chance to enter random elevator that opens on your floor, however you may have to wait a bit until it delivers you to your place.
 
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
- run: com.uknowme.Main psvm method


## 2. Go to elevators-app:
- make sure you have node.js installed
- npm install
- npm run dev
