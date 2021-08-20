@echo off
D:
cd D:\code\spring\baron\Ecommerce-user-service
dir ./target
java -jar -Dserver.port=9004 ./target/user-service-0.0.1-SNAPSHOT.jar
pause