# To test run step by step from root directory
Build application:
```
./mvnw clean install
```
Run grpc server:
```
java -jar ./module_2_task_1/grpc_server/target/grpc_server-0.0.1-SNAPSHOT.jar
```
Run grpc client:
```
 java -jar ./module_2_task_1/grpc_client/target/grpc_client-0.0.1-SNAPSHOT.jar
```
Call with curl (grpc client has the rest controller to test)
```
curl -G http://localhost:8080/pingPong -d 'message=someMessage'
```