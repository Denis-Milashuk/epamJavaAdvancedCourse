# To test run step by step from current directory
Build application:
```shell
../mvnw clean install
```
Run docker compose with kafka and schema registry:
```shell
docker-compose -f ./docker/docker-compose.yml up
```
Run application:
```shell
java -jar ./target/module_2_task_2-0.0.1-SNAPSHOT.jar
```
Call with curl:
```shell
curl -X POST http://localhost:8080/employees -H "Content-Type: application/json" -d '{"id": 123, "firstName": "Dima", "lastName": "Milashuk"}'  
```
Check log from consumer:
```
Consumed message:
{"id": 123, "firstName": "Dima", "lastName": "Milashuk"}
```