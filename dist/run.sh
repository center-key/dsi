#!/bin/bash

# Design-Side Includes (DSI)
#
# See full instructions:
#     https://github.com/center-key/dsi#readme
#
# Usage:
#     $ ~/apps/dsi/run.sh --version
#     $ ~/apps/dsi/run.sh [SrcFolder] [FileName] [NewExt]

installMessage() {
   command=$1
   echo
   echo "Install missing program:"
   echo "   \$ $command"
   echo
   exit
   }

findGroovyJar() {
   groovyHome=$(cd $(dirname $(which groovy))/$(dirname $(readlink $(which groovy)))/../libexec; pwd)
   groovyVersion=$(groovy --version | awk '{ print $3 }')
   groovyJar=$groovyHome/lib/groovy-$groovyVersion.jar
   }

which java ||   installMessage "brew install openjdk"
which groovy || installMessage "brew install groovy"
findGroovyJar
dsiFolder=$(cd $(dirname $0); pwd)
java -classpath $groovyJar:$dsiFolder/dsi.jar com.centerkey.dsi.Run $@
