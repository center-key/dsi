#!/bin/bash
#
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

setupTools() {
   cd $projectHome
   echo "Tools..."
   source add-app-to-path.sh java
   source add-app-to-path.sh groovy
   groovyJar=$GROOVY_HOME/lib/$(basename $GROOVY_HOME).jar
   echo $groovyJar
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
   source dist/run.sh --version
   echo
   }

displayIntro
setupTools
compileDsi
bundleDsi
showDsiVersion
