# To test run step by step from current directory
Build application:
```shell
../mvnw clean install
```
Run docker compose with db:
```shell
docker compose -f ./docker/postgres-db.yaml up
```
Run application:
```shell
java -jar ./target/module_4_task_1-0.0.1-SNAPSHOT.jar
```