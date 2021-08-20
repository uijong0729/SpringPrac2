@echo off
set mvn="D:\InteliJ\IntelliJ IDEA 2021.1.3\plugins\maven\lib\maven3\bin\mvn"
D:
cd D:\code\spring\baron\Ecommerce-user-service
%mvn% compile package
dir ./target
java -jar -Dserver.port=9004 ./target/user
pause