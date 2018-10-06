#!/bin/bash
#
# Design-Side Includes (DSI)

banner="DSI Specification Runner"
projectHome=$(cd $(dirname $0)/..; pwd)

displayIntro() {
   cd $projectHome/spec
   echo
   echo $banner
   echo $(echo $banner | sed s/./=/g)
   pwd
   source $projectHome/dist/run.sh --version
   echo
   }

specRunner() {
   cd $projectHome/spec/input
   echo "Running..."
   pwd
   ls -o
   source $projectHome/dist/run.sh
   rm ../output/*
   mv *.html ../output
   echo
   }

showResults() {
   cd $projectHome/spec/output
   echo "Results..."
   pwd
   echo
   ls -o ../output
   ls ../output/*.html | xargs open
   echo
   }

displayIntro
specRunner
showResults
