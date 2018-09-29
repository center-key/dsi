#!/bin/bash
#
# Design-Side Includes (DSI)
#
# To make this file runnable:
#     $ chmod +x *.sh.command
#
# Install the Java SE Development Kit (JDK):
#    https://www.oracle.com/technetwork/java/javase/downloads

banner="Design-Side Includes (DSI)"
projectHome=$(cd $(dirname $0); pwd)

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
   pwd
   rm -rf ../build
   mkdir ../build
   javac -Xlint -d ../build com/centerkey/dsi/WebCodeReuser.java
   find ../build -name *.class
   echo
   }

bundleDsi() {
   cd $projectHome/build
   echo "Bundling..."
   pwd
   cp -v ../src/MainClass.txt .
   jar cmfv MainClass.txt ../dist/dsi.jar *
   cd ../dist
   pwd
   ls -o
   echo
   }

showDsiVersion() {
   cd $projectHome/dist
   echo "Version..."
   pwd
   echo "java -jar dsi.jar --version"
   java -jar dsi.jar --version
   echo
   }

displayIntro
compileDsi
bundleDsi
showDsiVersion
