cygwin=false
case "`uname`" in
CYGWIN*) cygwin=true;;
esac

JAVA=$JAVA_HOME/bin/java
DEPLOY_DIR=../build

CLASSPATH=$DEPLOY_DIR/client.jar:$DEPLOY_DIR/common.jar
if $cygwin; then
CLASSPATH="$DEPLOY_DIR/client.jar;$DEPLOY_DIR/common.jar"
fi

$JAVA -classpath "$CLASSPATH" com.yaps.petstore.client.ui.Menu
