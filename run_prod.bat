call docker compose stop
call docker container rm materialloc_api
call docker container rm materialloc_db
call docker image rm akimtspro/materialloc_api:0.0.1
call mvn clean package -P prod -DskipTests -f pom.xml
call docker build -t akimtspro/materialloc_api:0.0.1 .
call docker compose up