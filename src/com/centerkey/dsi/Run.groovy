////////////////////////////////////////////////////////////////////////////////
// DSI (Design-Side Includes)                                                 //
//                                                                            //
// Web Code Reuser Core                                                       //
//    The core (main) launcher and controller for DSI.                        //
//                                                                            //
// MIT License - Copyright (c) Individual contributors to DSI                 //
////////////////////////////////////////////////////////////////////////////////

package com.centerkey.dsi

final class Run {
   // Usage:
   //    > java Run <SrcFileName> <DestFileExt> <SrcDir>

   private Run() {}

   static void main(String[] argv) {
      File[] fileList
      String srcDirName, srcFileName , destDirName, destFileExt
      println(SystemAttributes.headerMsg)
      srcDirName =  (argv.length > 0) ? argv[0] : SystemAttributes.appWorkingDir
      srcFileName = (argv.length > 1) ? argv[1] : "*.bhtml" //"b" for base
      destDirName = (argv.length > 2) ? argv[2] : srcDirName
      destFileExt = (argv.length > 3) ? argv[3] : ".html"
      if (srcDirName.equals("") || srcDirName.equals("."))
         srcDirName = SystemAttributes.appWorkingDir
      if (srcDirName.equals("--version") || srcDirName.equals("-v"))
         println(SystemAttributes.versionMsg)
      else {
         println("Searching '" + srcDirName + "' for '" + srcFileName + "'")
         fileList = new File(srcDirName).listFiles(new Filter(srcFileName))
         if (fileList == null)
            println(">>> ERROR #1 -- Cannot Read Folder")
         else
            doIt(fileList, new File(destDirName), destFileExt)
         }
      println(SystemAttributes.footerMsg)
      }

   private static void doIt(File[] srcFileList, File destDir, String destFileExt) {
      println("Files Found: " + srcFileList.length)
      for (File srcFile : srcFileList) {
         String destFileName = srcFile.getName()
         destFileName = destFileName.substring(0, destFileName.lastIndexOf('.')) + destFileExt
         println(srcFile.getName() + " --> " + destDir.getAbsolutePath() +
            SystemAttributes.fileSeparator + destFileName)
         new InputFile().processFile(srcFile, new File(destDir, destFileName))
         }
      }

   }
