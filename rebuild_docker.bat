docker container rm ewm-stat-svc-server-cnt
docker container rm ewm-main-svc-server-cnt
docker container rm ewm-stat-svc-db-cnt
docker container rm ewm-main-svc-db-cnt
docker image rm ewm-stat-svc-server-img
docker image rm ewm-main-svc-server-img
docker image rm postgres:13.7-alpine

mvn clean package

docker compose up
