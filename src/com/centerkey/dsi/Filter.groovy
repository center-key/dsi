////////////////////////////////////////////////////////////////////////////////
// DSI (Design-Side Includes)                                                 //
//                                                                            //
// Input File Name Filter                                                     //
//    Determines if a file has the right name to be a base (source) file.     //
//                                                                            //
// MIT License - Copyright (c) individual contributors to DSI                 //
////////////////////////////////////////////////////////////////////////////////

package com.centerkey.dsi

class Filter implements FilenameFilter  {

   String filterExpression
   boolean wildcard = false

   Filter(String fileName) {
      this.filterExpression = fileName
      wildcard = fileName.length() > 0 && fileName.charAt(0) == "*"
      if (wildcard)
         this.filterExpression = fileName.substring(1)
      }

   boolean accept(File dir, String name) {
      File f = new File(name)
      String fileName = f.getName()
      return fileName.equals(filterExpression) || (wildcard && fileName.endsWith(filterExpression))
      }

   }
