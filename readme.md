#Restaurant Chooser
Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) **without frontend**.

The task is:

Build a voting system for deciding where to have lunch.

* 2 types of users: admin and regular users
* Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
* Menu changes each day (admins do the updates)
* Users can vote on which restaurant they want to have lunch at
* Only one vote counted per user
* If user votes again the same day:
— If it is before 11:00 we assume that he changed his mind.
— If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides new menu each day.

### Requirements
Before launching this API it is needed to create and fill in database.
App have a postgreSQL as DBMS, scripts for creating and filling database are in /resources/db folder.
As application server uses Tomcat8.

Some cURL commands:

###USER
###AUTHENTICATION
`curl -u user@yandex.ru http://localhost:8080/login` as regular user, password 'user'
`curl -u admin@gmail.com http://localhost:8080/login` as administrator, password 'admin'
###USER PROFILE for authorized user (authentication required)
`curl -u user@yandex.ru -s http://localhost:8080/profile`
`curl -s http://localhost:8080/profile`

####GET get All users (admin access required)
`curl -u admin@gmail.com -s http://localhost:8080/admin/users`
`curl -u user@yandex.ru -s http://localhost:8080/admin/users` //403 - Forbidden

####GET get user  (admin access required)
`curl -u admin@gmail.com -s http://localhost:8080/admin/users/1001`

####POST add user (admin access required)
`curl -u admin@gmail.com -s -v -X POST -d '{"name":"Max","email":"max@max.max","password":"MaX"}}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/admin/users`

####PUT update user (admin access required)
`curl -u admin@gmail.com -s -v -X PUT -d '{"name":"Maxwell"}' -H 'Content-Type: application/json' http://localhost:8080/admin/users/1000`
`curl -u admin@gmail.com -s -v -X PUT -d '{"password":"max"}' -H 'Content-Type: application/json' http://localhost:8080/admin/users/1000`

####DELETE delete user (admin access required)
`curl -u admin@gmail.com -s -X DELETE http://localhost:8080/admin/users/1001`


### RESTAURANTS
####GET get All restaurants
`curl -s http://localhost:8080/restaurants`

####GET get restaurant by id
`curl -u user@yandex.ru -s http://localhost:8080/restaurants/1000`

####GET get best restaurant of the selected/current day 
`curl -s http://localhost:8080/restaurants/best/2017-12-01`
`curl -s http://localhost:8080/restaurants/best`

####POST add restaurant (admin access required)
`curl -u admin@gmail.com -s -v -X POST -d '{"name":"Some New Restaurant","description":"We have same food as others for cheaper price!"}}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/restaurants`

####PUT update restaurant (admin access required)
`curl -u admin@gmail.com -s -v -X PUT -d '{"name":"Cool Updated Restaurant Name"}' -H 'Content-Type: application/json' http://localhost:8080/restaurants/1000`

####DELETE delete restaurant (admin access required)
`curl -u admin@gmail.com -s -X DELETE http://localhost:8080/restaurants/1001`


###MENUS
####GET get All menus for restaurant
`curl -s http://localhost:8080/restaurants/1001/menus`

####GET get actual menus
`curl -s http://localhost:8080/menus/actual`

####GET get menu by id
`curl -s http://localhost:8080/restaurants/1001/menus/1001`

####POST add menu (admin access required)
`curl -u admin@gmail.com -s -v -X POST -d '{"day":"2017-12-02"}}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/restaurants/1000/menus`
`curl -u admin@gmail.com -s -v -X POST -d '{"day":"2017-12-02", "dishes":[{"id":1,"name":"Hamburger","price":2.45},{"id":6,"name":"Fish soup","price":1.75},{"id":11,"name":"Pizza","price":3.0}]}}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/restaurants/1000/menus`

####PUT update menu (admin access required)
`curl -u admin@gmail.com -s -v -X PUT -d '{"day":"2016-12-02"}' -H 'Content-Type: application/json' http://localhost:8080/menus/1001`


###DISHES
####GET get All dishes for restaurant
`curl -s http://localhost:8080/dishes`

####GET get dish by id
`curl -s http://localhost:8080/dishes/1001`

####GET get dish for menu
`curl -s http://localhost:8080/menus/1000/dishes/1000`

####GET get all dishes from menu
`curl -s http://localhost:8080/menus/1000/dishes`

####POST add dish (admin access required)
`curl -u admin@gmail.com -s -v -X POST -d '{"name":"Pomp fries","price":1.5}}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/dishes`

####PUT update dish (admin access required)
`curl -u admin@gmail.com -s -v -X PUT -d '{"price":1.5}' -H 'Content-Type: application/json' http://localhost:8080/dishes/1000`

####DELETE delete dish (admin access required)
`curl -u admin@gmail.com -s -X DELETE http://localhost:8080/dishes/1001`


###VOTES
####GET get All votes
`curl -s http://localhost:8080/votes`

####GET get vote by id
`curl -s http://localhost:8080/votes/1000`

####GET get all votes for restaurant
`curl -s http://localhost:8080/restaurants/1001/votes`

####POST add vote to restaurant (authentication required)
`curl -u user@yandex.ru -s -v -X POST -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/restaurants/1001/votes`

####PUT update vote (authentication required) before 11 AM
`curl -u user@yandex.ru -s -v -X PUT -H 'Content-Type: application/json' http://localhost:8080/restaurants/1002/votes/1000`

####PUT update vote (authentication required) after 11 AM
`curl -u user@yandex.ru -s -v -X PUT -d '{"voteTime":"2016-11-22T11:10"}' -H 'Content-Type: application/json' http://localhost:8080/restaurants/1002/votes/1000`
