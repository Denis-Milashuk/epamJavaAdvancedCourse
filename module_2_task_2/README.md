# To test run step by step from root directory
Build application:
```
./mvnw clean install
```
Run docker compose with kafka and schema registry:
```
docker compose -f ./module_2_task_2/docker/docker-compose.yml up
```
Run application:
```
java -jar ./module_2_task_2/target/module_2_task_2-0.0.1-SNAPSHOT.jar
```
Call with curl:
```
curl -X POST http://localhost:8080/employees -H "Content-Type: application/json" -d '{"id": 123, "firstName": "Dima", "lastName": "Milashuk"}'  
```
Check log from consumer:
```
Consumed message:
{"id": 123, "firstName": "Dima", "lastName": "Milashuk"}
```