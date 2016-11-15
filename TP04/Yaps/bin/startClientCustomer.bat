@echo on

set JAVA=%JAVA_HOME%\bin\java
set DEPLOY_DIR=..\build

set CLASSPATH=%DEPLOY_DIR%\clientCustomer.jar;%MYSQL_HOME%\lib\mysql-connector-java-5.1.21-bin.jar

rem start the application with a specific logger defined in mylogging.properties
%JAVA% -cp %CLASSPATH% -Djava.util.logging.config.file=mylogging.properties com.yaps.petstore.ui.text.MenuCustomer
