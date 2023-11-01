# To test run step by step from current directory
Build application:
```shell
../mvnw clean install
```
Run grpc server:
```shell
java -jar ./grpc_server/target/grpc_server-0.0.1-SNAPSHOT.jar
```
Run grpc client:
```shell
 java -jar ./grpc_client/target/grpc_client-0.0.1-SNAPSHOT.jar
```
Call with curl (grpc client has the rest controller to test)
```shell
curl -G http://localhost:8080/pingPong -d 'message=someMessage'
```