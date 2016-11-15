@echo on

set JAVA=%JAVA_HOME%\bin\java
set RMIREGISTRY=%JAVA_HOME%\bin\rmiregistry 

set DEPLOY_DIR=..\build
set LIB_DIR=..\lib

set CLASSPATH=%DEPLOY_DIR%\server.jar;%DEPLOY_DIR%\common.jar;%MYSQL_HOME%\lib\mysql-connector-java-5.1.21-bin.jar

start %RMIREGISTRY%

%JAVA% -cp %CLASSPATH% -Djava.security.policy=D:\home\java\iagl2\src\GLG203\NouveauxTPs\TP05\Yaps\bin\x.policy com.yaps.petstore.server.RegisterServices

pause
