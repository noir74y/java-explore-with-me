docker container rm stats-service
docker container rm main-service
docker container rm ewm-stat-svc-db-cnt
docker container rm ewm-main-svc-db-cnt
docker image rm ewm-stat-svc-server-img
docker image rm ewm-main-svc-server-img

mvn clean package

docker compose up
