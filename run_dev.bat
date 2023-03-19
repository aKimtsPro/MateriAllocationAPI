call docker container stop materialloc_dev_db
call docker container rm materialloc_dev_db
call docker run -p "5432:5432" -e POSTGRES_DB=materialloc_db -d -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=pass --name materialloc_dev_db postgres
call mvn clean package -f pom.xml
call java -jar target/materi-alloc-0.0.1-SNAPSHOT.jar
