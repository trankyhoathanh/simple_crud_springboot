Go to folder main (have file readme.md)

docker compose up

Note : 
1. view JSON list users http://localhost:8080/users
2. add new user
curl --location --request POST 'http://localhost:8080/users' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "a",
    "email": "a@gmail.com"
}'
3. Check data from db :
- download TablePlus
- connect to URL port 5434 : 
postgresql://postgres:db-write@localhost:5434/example

How to debug from visual studio code ? 
1. Change connection string here : application.properties
from spring.datasource.url=jdbc:postgresql://db:5432/example
to spring.datasource.url=jdbc:postgresql://localhost:5434/example
2. Start postgres docker (port 5434)
3. Go to file Application.java => Run debug, add debug point break.


======
redis notes : 
1. curl --location --request POST 'http://localhost:8080/api/redis/publish' \
--header 'Content-Type: application/json' \
--data-raw '{
    "author":"thanh",
    "data":"112"
}'

2. Tracking notes at docker subcribers node
Message received : Message{data='112', author='thanh'}