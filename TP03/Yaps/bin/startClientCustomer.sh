cygwin=false
case "`uname`" in
CYGWIN*) cygwin=true;;
esac

JAVA=$JAVA_HOME/bin/java
DEPLOY_DIR=../build

CLASSPATH=$DEPLOY_DIR/clientCustomer.jar:$MYSQL_HOME%/lib/mysql-connector-java-5.1.21-bin.jar
if $cygwin; then
  # CLASSPATH=`cygpath --path --windows "$CLASSPATH"`
  CLASSPATH="$DEPLOY_DIR/clientCustomer.jar;$MYSQL_HOME/lib/mysql-connector-java-5.1.21-bin.jar"
fi

$JAVA -cp "$CLASSPATH" com.yaps.petstore.ui.MenuCustomer
