////////////////////////////////////////////////////////////////////////////////
// DSI (Design-Side Includes)                                                 //
//                                                                            //
// Web Page Variables                                                         //
//    Controls user defined and predefined variables utilized for inserting   //
//    text values into web pages.                                             //
//                                                                            //
// MIT License - Copyright (c) individual contributors to DSI                 //
////////////////////////////////////////////////////////////////////////////////

package com.centerkey.dsi

class PageVariables {

   static HashMap<String, String> values = new HashMap<String, String>()

   void clearAll() {
      values.clear()
      }

   String getValue(String varName) {
      String varValue = values.get(varName)
      if (varValue == null) {
         varValue = ">>> ERROR #4 -- Variable '" + varName + "' not defined."
         println(varValue)
         }
      return varValue
      }

   void setValue(String varName, String varValue) {
      values.put(varName, varValue)
      }

   }
