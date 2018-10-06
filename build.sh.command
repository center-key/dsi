#!/bin/bash
#
# Design-Side Includes (DSI)
#
# To make this file runnable:
#     $ chmod +x *.sh.command
#
# Install the Java SE Development Kit (JDK):
#     https://www.oracle.com/technetwork/java/javase/downloads
#
# Install Groovy or download binary:
#     http://groovy-lang.org/download.html --> ~/apps/groovy/

banner="Design-Side Includes (DSI)"
projectHome=$(cd $(dirname $0); pwd)

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

displayIntro() {
   cd $projectHome
   echo
   echo $banner
   echo $(echo $banner | sed s/./=/g)
   pwd
   java -version
   echo
   }

compileDsi() {
   cd $projectHome/src
   echo "Compiling..."
   addAppToPath groovy
   pwd
   rm -rf ../build
   groovyc -d ../build com/centerkey/dsi/*.groovy
   ls ../build/com/centerkey/dsi/*.class
   echo
   }

bundleDsi() {
   cd $projectHome/build
   echo "Bundling..."
   pwd
   rm ../dist/*
   jar cfv ../dist/dsi.jar *
   cp ../src/run.sh ../dist
   ls -o ../dist
   echo
   }

showDsiVersion() {
   cd $projectHome
   echo "Version..."
   pwd
   ls -1 dist/*
   source dist/run.sh --version
   echo
   }

displayIntro
compileDsi
bundleDsi
showDsiVersion
