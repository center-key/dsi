////////////////////////////////////////////////////////////////////////////////
// DSI (Design-Side Includes)                                                 //
//                                                                            //
// System Attributes                                                          //
//    Defines constants and messages used by the application.                 //
//                                                                            //
// MIT License - Copyright (c) individual contributors to DSI                 //
////////////////////////////////////////////////////////////////////////////////

package com.centerkey.dsi;

import java.util.Properties;

public class SystemAttributes {

   //Application information
   public static final String appName =    "DSI";
   public static final String appVersion = "2.1";

   //System information retrieved from JVM
   private static final Properties sysInfo =  System.getProperties();
   public static final String fileSeparator = sysInfo.getProperty("file.separator");
   public static final String userHomeDir =   sysInfo.getProperty("user.home");
   public static final String appWorkingDir = sysInfo.getProperty("user.dir");
   public static final String osInfo =
      sysInfo.getProperty("os.name") + fileSeparator +
      sysInfo.getProperty("os.version") + " (" +
      sysInfo.getProperty("os.arch") + ")";
   public static final String javaVersion = "Java " +
      sysInfo.getProperty("java.version") + ", " +
      sysInfo.getProperty("java.vendor");
   public static final String javaHomeDir = sysInfo.getProperty("java.home");
   public static final String javaVMInfo =
      sysInfo.getProperty("java.vm.name") + ", " +
      sysInfo.getProperty("java.vm.version");

   //Message text
   public final static String headerMsg = "\n" +
      "Design-Side Includes (DSI)\n" +
      "--------------------------";
   public final static String footerMsg =
      "--------------------------";
   public static final String versionMsg =
      "Version " + appVersion + "\n" + osInfo + "\n" + javaVersion + "\n" + javaVMInfo;

   }
