////////////////////////////////////////////////////////////////////////////////
// DSI (Design-Side Includes)                                                 //
//                                                                            //
// Web Page Variables                                                         //
//    Controls user defined and predefined variables utilized for inserting   //
//    text values into web pages.                                             //
//                                                                            //
// MIT License - Copyright (c) individual contributors to DSI                 //
////////////////////////////////////////////////////////////////////////////////

package com.centerkey.dsi;

import java.util.HashMap;

public class PageVariables {

   static HashMap<String, String> values = new HashMap<String, String>();

   public void clearAll() {
      values.clear();
      }

   public String getValue(String varName) {
      String varValue = values.get(varName);
      if (varValue == null) {
         varValue = ">>> ERROR #4 -- Variable '" + varName + "' not defined.";
         System.out.println(varValue);
         }
      return varValue;
      }

   public void setValue(String varName, String varValue) {
      values.put(varName, varValue);
      }

   }
