#!/bin/bash

# Design-Side Includes (DSI)
#
# To make this file runnable:
#     $ chmod +x *.sh.command

banner="Design-Side Includes (DSI)"
projectHome=$(cd $(dirname $0); pwd)

displayIntro() {
   cd $projectHome
   echo
   echo $banner
   echo $(echo $banner | sed s/./=/g)
   pwd
   echo
   }

installMessage() {
   command=$1
   echo
   echo "Install missing program:"
   echo "   \$ $command"
   echo
   exit
   }

findGroovyJar() {
   dsiFolder=$(cd $(dirname $0); pwd)
   groovyHome=$(cd $(dirname $(which groovy))/$(dirname $(readlink $(which groovy)))/../libexec; pwd)
   groovyVersion=$(groovy -version | awk '{ print $3 }')
   groovyJar=$groovyHome/lib/groovy-$groovyVersion.jar
   }

setupTools() {
   cd $projectHome
   echo "Tools..."
   which java || installMessage "brew cask install java"
   java -version
   which groovy || installMessage "brew install groovy"
   groovy -version
   findGroovyJar
   echo "Groovy JAR:"
   ls $groovyJar
   echo
   }

compileDsi() {
   cd $projectHome/src
   echo "Compiling..."
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
   test -w ~/apps && mkdir -p ~/apps/dsi && cp -v ../dist/* ~/apps/dsi
   echo
   }

showDsiVersion() {
   cd $projectHome
   echo "Version..."
   pwd
   ls -1 dist/*
   chmod +x dist/run.sh
   dist/run.sh --version
   echo
   }

displayIntro
setupTools
compileDsi
bundleDsi
showDsiVersion
