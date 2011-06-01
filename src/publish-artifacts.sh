# The purpose of this file is to publish valid Apache Hive 0.5.0-pentaho artifacts with the correctly versioned 
# required dependencies build from this project.  Two external dependencies are expected to already exist:
#
#   #) groupId:artifactId:version
#
#   1) org.apache.hadoop:hadoop-core:0.20.0
#   2) com.facebook.fb303:fb303:3.0.3

# These properties are supplied when run from Hudson (http://ci.pentaho.com)
# PENTAHO_OPEN_REPO=file:///tmp/test-mvn-repo
# WORKSPACE=`pwd`

VERSION=0.7.0-pentaho-SNAPSHOT
HADOOP_VERSION=0.20.2-cdh3u0
REPO_ID=pentaho
HIVE_DIST_LIB_DIR=$WORKSPACE/src/build/dist/lib

JDBC_FNAME=hive-jdbc-$VERSION.jar
JAR_DEPS="exec metastore service"

# Commands for publishing static libraries listed above:

#echo "Publishing Hadoop 0.20.0 Library..."
#mvn \
#deploy:deploy-file \
#-Durl=$PENTAHO_OPEN_REPO \
#-DrepositoryId=$REPO_ID \
#-Dfile=hive-tmp/branches/0.5.0/build/hadoopcore/hadoop-0.20.0/hadoop-0.20.0-core.jar \
#-DpomFile=hadoop-0.20.0-core-pom.xml
#
#echo "Publishing Facebook Library..."
#mvn deploy:deploy-file \
#    -Dfile=$HIVE_DIST_LIB_DIR/libfb303.jar \
#    -DrepositoryId=$REPO_ID \
#    -Durl=$PENTAHO_OPEN_REPO \
#    -DgroupId=com.facebook.fb303 \
#    -DartifactId=fb303 \
#    -Dversion=3.0.3 \
#    -Dpackaging=jar \
#    -DgeneratePom=true

echo "Publishing built artifacts related to the Hive JDBC driver at version $VERSION"

# Publish simple JAR Dependencies
echo "Publishing required JAR dependencies ..."
for dep in $JAR_DEPS
do
  echo "Publishing Jar for Hive Module: $dep ..."
mvn deploy:deploy-file \
    -Dfile=$HIVE_DIST_LIB_DIR/hive-$dep-$VERSION.jar \
    -DrepositoryId=$REPO_ID \
    -Durl=$PENTAHO_OPEN_REPO \
    -DgroupId=org.apache.hadoop.hive \
    -DartifactId=hive-$dep \
    -Dversion=$VERSION \
    -Dpackaging=jar \
    -DgeneratePom=true
done

# Generate the POM file for the Hive JDBC Driver project
JDBC_POM_TEMPLATE=$WORKSPACE/src/hive-jdbc-pom.template
JDBC_POM_FILE_OUTPUT=$WORKSPACE/src/hive-jdbc-pom.xml
echo "Generating $JDBC_POM_FILE_OUTPUT from $JDBC_POM_TEMPLATE ..."
sed "s/\${hive.version}/$VERSION/;s/\${hadoop.version}/$HADOOP_VERSION/" $JDBC_POM_TEMPLATE > $JDBC_POM_FILE_OUTPUT

# Publish the Hive JDBC driver
echo "Publishing Hive JDBC Driver ..."
mvn deploy:deploy-file \
  -Dfile=$HIVE_DIST_LIB_DIR/hive-jdbc-$VERSION.jar \
  -DrepositoryId=$REPO_ID \
  -Durl=$PENTAHO_OPEN_REPO \
  -DpomFile=$JDBC_POM_FILE_OUTPUT

