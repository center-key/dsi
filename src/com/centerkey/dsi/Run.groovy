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
   //    > java Run [SrcFolder] [Filename] [NewExt] [DestFolder]

   private Run() {}

   static void main(String[] argv) {
      String srcFolderName, srcFilter, destFileExt, destFolderName
      println(SystemAttributes.headerMsg)
      srcFolderName =  (argv.length > 0) ? argv[0] : "."
      srcFilter =      (argv.length > 1) ? argv[1] : "*.bhtml"  //"b" for base
      destFileExt =    (argv.length > 2) ? argv[2] : ".html"
      destFolderName = (argv.length > 3) ? argv[3] : "."
      if (srcFolderName.equals("--version") || srcFolderName.equals("-v"))
         println(SystemAttributes.versionMsg)
      else
         processFiles(srcFolderName, srcFilter, destFileExt, destFolderName)
      println(SystemAttributes.footerMsg)
      }

   private static void processFiles(String srcFolderName, String srcFilter, String destFileExt, String destFolderName) {
      File srcFolder =  new File(srcFolderName)
      File destFolder = new File(destFolderName)
      File[] srcFiles = srcFolder.listFiles(new Filter(srcFilter))
      println("Searching '" + srcFolder.getCanonicalPath() + "' for '" + srcFilter + "'")
      if (srcFiles == null)
         throw new Exception("Cannot read folder")
      destFolder.mkdirs()
      for (File srcFile : srcFiles) {
         String destFilename = srcFile.getName().take(srcFile.getName().lastIndexOf('.')) + destFileExt
         File destFile =       new File(destFolder, destFilename)
         println("   " + srcFile.getName() + " --> " + destFile.getCanonicalPath())
         new InputFile().processFile(srcFile, destFile)
         }
      println("   Files: " + srcFiles.length)
      }

   }
