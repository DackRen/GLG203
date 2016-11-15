@echo off

set JAVA=%JAVA_HOME%\bin\java
set DEPLOY_DIR=..\build

set CLASSPATH=%DEPLOY_DIR%\clientCustomer.jar;%MYSQL_HOME%\lib\mysql-connector-java-5.1.21-bin.jar

%JAVA% -cp %CLASSPATH% com.yaps.petstore.ui.MenuCustomer