#!/usr/bin/env bash
docker stop tomcat
docker rm tomcat

#docker stop postgres
#docker rm postgres

sudo docker build -t rest-chooser/postgres -f src/main/resources/docker/postgres.dockerfile .
sudo docker run  -e POSTGRES_PASSWORD=password -e POSTGRES_USER=user -e POSTGRES_DB=restaurant -d  --name=postgres --memory=512M --cpus=1 -v postgres:/var/lib/postgres rest-chooser/postgres

sudo docker build -t rest-chooser/tomcat -f src/main/resources/docker/tomcat.dockerfile .
sudo docker run -d --name=tomcat --link postgres:postgres -p 8080:8080 rest-chooser/tomcat