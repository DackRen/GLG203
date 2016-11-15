@echo off

set JAVA=%JAVA_HOME%\bin\java
set DEPLOY_DIR=..\build

set CLASSPATH=%DEPLOY_DIR%\clientCatalog.jar;%DEPLOY_DIR%\common.jar

%JAVA% -cp %CLASSPATH% com.yaps.petstore.client.ui.text.MenuCatalog