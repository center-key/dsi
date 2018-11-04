#!/bin/bash
#
# Design-Side Includes (DSI)
#
# See full instructions:
#     https://github.com/center-key/dsi#readme
#
# Usage:
#     $ source ~/apps/dsi/run.sh --version
#     $ source ~/apps/dsi/run.sh [SrcFolder] [FileName] [NewExt]

addAppToPath() {
   appsFolder=~/apps
   binFolder=$(find $appsFolder/$appName -name bin -maxdepth 2 | sort | tail -1)
   binFolder=${binFolder:-$(find $appsFolder/$appName -name bin | sort | tail -1)}
   PATH=$PATH:$binFolder
   homeVarName=$(echo $appName | awk '{print toupper($0)}')_HOME
   printf -v $homeVarName $(dirname $binFolder)
   export $homeVarName
   which $appName || { echo "*** Folder 'bin' not found within: $appsFolder/$appName"; exit; }
   }

addApp()  {
   appName=$1
   which $appName || addAppToPath
   }

addApp java
addApp groovy
groovyJar=$GROOVY_HOME/lib/$(basename $GROOVY_HOME).jar
java -classpath $groovyJar:$(dirname $BASH_SOURCE)/dsi.jar --illegal-access=deny com.centerkey.dsi.Run $@
# --illegal-access=deny, see: https://issues.apache.org/jira/browse/GROOVY-8339
