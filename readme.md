#Restaurant Chooser
Design and implement a JSON API using Hibernate/Spring/SpringMVC (or Spring-Boot) **without frontend**.

The task is:

Build a voting system for deciding where to have lunch.

* 2 types of users: admin and regular users
* Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
* Menu changes each day (admins do the updates)
* Users can vote on which restaurant they want to have lunch at
* Only one vote counted per user
* If user votes again the same day:
— If it is before 11:00 we asume that he changed his mind.
— If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides new menu each day.

### Requirements
Before launching this API it is needed to create and fill in database.
App have a postgreSQL as DBMS, scripts for creating and filling database are in /resources/db folder.
As application server uses Tomcat8.

Some cURL commands:
####GET get All restaurants
`curl -s http://localhost:8080/rest/restaurants`

####GET get All restaurant`s menus
`curl -s http://localhost:8080/rest/restaurants/1/menus/`

####GET get restaurant`s menu on id 1
`curl -s http://localhost:8080/rest/restaurants/1/menus/1`

####POST add user vote
`curl -s -v -X POST -d '{"voteTime":"2016-09-22T13:00","user":{"id":1001,"name":"User","email":"user@yandex.ru","password":"user","enabled":true,"registered":1474461748058,"roles":["ROLE_USER"]},"restaurant":{"id":2,"name":"Cornello","description":"If you love eat, with us you can do it good","votes":0}}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/votes`

####PUT update user vote // set correct time of update and vote id instead {voteId}
`curl -s -v -X PUT -d '{"voteTime":"2016-09-23T10:10","user":{"id":1001,"name":"User","email":"user@yandex.ru","password":"user","enabled":true,"registered":1474461748058,"roles":["ROLE_USER"]},"restaurant":{"id":2,"name":"Cornello","description":"If you love eat, with us you can do it good","votes":0}}' -H 'Content-Type: application/json' http://localhost:8080/rest/votes/{voteId}`

####PUT update user vote after 11 a.m.
`curl -s -v -X PUT -d '{"voteTime":"2016-09-22T11:10","user":{"id":1001,"name":"User","email":"user@yandex.ru","password":"user","enabled":true,"registered":1474461748058,"roles":["ROLE_USER"]},"restaurant":{"id":2,"name":"Cornello","description":"If you love eat, with us you can do it good","votes":0}}' -H 'Content-Type: application/json' http://localhost:8080/rest/votes/30`

####POST add admin restaurant
`curl -s -v -X POST -d '{"name":"Some New Restaurant","description":"We have same food as others for cheaper price!","votes":0}}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/restaurants`

####POST add admin restaurant`s menu
`curl -s -v -X POST -d '{"day":"2016-09-22T00:01","dishes":[{"id":1,"name":"Hamburger","price":2.45},{"id":6,"name":"Fish soup","price":1.75},{"id":11,"name":"Pizza","price":3.0}],"restaurant":{"id":4,"name":"Some New Restaurant","description":"We have same food as others for cheaper price!","votes":0}}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/menus`

####DELETE delete restaurant
`curl -s -X DELETE http://localhost:8080/rest/restaurants/3`

##AUTHENTIFICATION
`http://localhost:8080/rest/login?email=admin@gmail.com`
##USER PROFILE
`http://localhost:8080/rest/profile`

