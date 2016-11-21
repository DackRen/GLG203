
cygwin=false
case "`uname`" in
CYGWIN*) cygwin=true;;
esac

JAVA=$JAVA_HOME/bin/java
RMIREGISTRY=$JAVA_HOME/bin/rmiregistry

DEPLOY_DIR=../build
LIB_DIR=../lib

CLASSPATH=$DEPLOY_DIR/server.jar:$DEPLOY_DIR/common.jar:$MYSQL_HOME/lib/mysql-connector-java-5.1.37-bin.jar

if $cygwin; then
# CLASSPATH=`cygpath --path --windows "$CLASSPATH"`
CLASSPATH="$DEPLOY_DIR/server.jar;$DEPLOY_DIR/common.jar;$MYSQL_HOME/lib/mysql-connector-java-5.1.37-bin.jar"
fi


$RMIREGISTRY &

$JAVA -classpath "$CLASSPATH" com.yaps.petstore.server.RegisterServices

