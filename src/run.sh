#!/bin/bash
#
# Design-Side Includes (DSI)
#
# To run the dsi.jar, you need to include the Groovy JAR in the classpath.
#
# Example (assuming "dsi.jar" in the "~/apps/dsi/" folder):
#     $ groovyHome=$(dirname $(dirname $(which groovy)))
#     $ groovyJar=$groovyHome/lib/$(basename $groovyHome).jar
#     $ java -classpath $groovyJar:~/apps/dsi/dsi.jar com.centerkey.dsi.Run --version
#     $ java -classpath $groovyJar:~/apps/dsi/dsi.jar com.centerkey.dsi.Run
#
# This shell script ("run.sh") is just for convenience.  It setups up Groovy and runs the dsi.jar
# file.
#
# To make this file runnable:
#     $ chmod +x *.sh
#
# Install the Java SE Development Kit (JDK):
#     https://www.oracle.com/technetwork/java/javase/downloads
#
# Install Groovy or download binary:
#     http://groovy-lang.org/download.html --> ~/apps/groovy/
#
# Usage:
#     $ source ~/apps/dsi/run.sh --version
#     $ source ~/apps/dsi/run.sh [SrcFolder] [FileName] [NewExt]

addAppToPath() {
   # Pass in the name of the app, such as: "ant", "mongodb", or "groovy"
   # Example usage:
   #     addAppToPath groovy
   # Uses the ~/apps/ folder and assumes structure like: ~/apps/groovy/groovy-2.5.2/bin/groovy
   appName=$1
   addBin() {
      PATH=$PATH:$(find ~/apps/$appName/*/bin -type d | tail -1)
      which $appName || { echo "*** Folder 'bin' not found at: ~/apps/$appName"; exit; }
      }
   which $appName || addBin
   }

setupGroovy() {
   addAppToPath groovy
   groovyHome=$(dirname $(dirname $(which groovy)))
   groovyJar=$groovyHome/lib/$(basename $groovyHome).jar
   }

setupGroovy
java -classpath $groovyJar:$(dirname $BASH_SOURCE)/dsi.jar --illegal-access=deny com.centerkey.dsi.Run $@
# --illegal-access=deny, see: https://issues.apache.org/jira/browse/GROOVY-8339
