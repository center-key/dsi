////////////////////////////////////////////////////////////////////////////////
// DSI (Design-Side Includes)                                                 //
//                                                                            //
// System Attributes                                                          //
//    Defines constants and messages used by the application.                 //
//                                                                            //
// MIT License - Copyright (c) individual contributors to DSI                 //
////////////////////////////////////////////////////////////////////////////////

package com.centerkey.dsi

class SystemAttributes {

   //Application information
   static final String appName =    "DSI";
   static final String appVersion = "2.3.0";

   //System information retrieved from JVM
   private static final Properties sysInfo =  System.getProperties();
   static final String fileSeparator = sysInfo.getProperty("file.separator");
   static final String userHomeDir =   sysInfo.getProperty("user.home");
   static final String appWorkingDir = sysInfo.getProperty("user.dir");
   static final String osInfo =
      sysInfo.getProperty("os.name") + fileSeparator +
      sysInfo.getProperty("os.version") + " (" +
      sysInfo.getProperty("os.arch") + ")";
   static final String javaVersion = "Java " +
      sysInfo.getProperty("java.version") + ", " +
      sysInfo.getProperty("java.vendor");
   static final String javaHomeDir = sysInfo.getProperty("java.home");
   static final String javaVMInfo =
      sysInfo.getProperty("java.vm.name") + ", " +
      sysInfo.getProperty("java.vm.version");

   //Message text
   final static String headerMsg = "\n" +
      "Design-Side Includes (DSI)\n" +
      "--------------------------";
   final static String footerMsg =
      "--------------------------";
   static final String versionMsg =
      "Version " + appVersion + "\n" + osInfo + "\n" + javaVersion + "\n" + javaVMInfo;

   }
