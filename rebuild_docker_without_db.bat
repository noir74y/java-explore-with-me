docker container rm ewm-stat-svc-server-cnt
rem docker container rm ewm-stat-svc-db-cnt

docker image rm ewm-stat-svc-server-img
rem docker image rm postgres:13.7-alpine

mvn clean package

docker compose up
