////////////////////////////////////////////////////////////////////////////////
// DSI (Design-Side Includes)                                                 //
//                                                                            //
// Input File Reader                                                          //
//    Reads and parses the base (source) files used to create the final HTML  //
//    output. The default file name extension for base files is ".bhtml".     //
//                                                                            //
// MIT License - Copyright (c) Individual contributors to DSI                 //
////////////////////////////////////////////////////////////////////////////////

package com.centerkey.dsi

import java.text.DateFormat

class InputFile {

   PageVariables attributes = new PageVariables()
   boolean withinIfBlock = false
   boolean doIf, trueIfFound

   void processFile(File srcFile, File destFile) {
      FileOutputStream fileOut
      try {
         attributes.clearAll()
         attributes.setValue("DOCUMENT_NAME", srcFile.getName())
         attributes.setValue("LAST_MODIFIED",
            DateFormat.getDateInstance(DateFormat.LONG).format(Calendar.getInstance().getTime()))
         // attributes.setValue("LAST_MODIFIED", new Date().format("MMMM d, yyyy"))
         fileOut = new FileOutputStream(destFile)
         processFile(srcFile, new PrintStream(fileOut, false, "UTF-8"))
         fileOut.close()
         }
      catch (IOException e) {
         println("*** ERROR #2 -- " + e.toString())
         }
      }

   void processFile(File srcFile, PrintStream dataOut) {
      FileInputStream fileIn
      BufferedReader  dataIn
      String          textLine
      try {
         fileIn = new FileInputStream(srcFile)
         dataIn = new BufferedReader(new InputStreamReader(fileIn, "UTF-8"))
         textLine = dataIn.readLine()
         while (textLine != null) {
            processLine(textLine, srcFile, dataOut)
            textLine = dataIn.readLine()  //read line
            }
         fileIn.close()
         }
      catch (IOException e) {
         String errMsg = "*** ERROR #3 -- " + e.toString()
         println(errMsg)
         dataOut.println(errMsg)
         }
      }

   private void processLine(String textLine, File srcFile, PrintStream dataOut){
      String line = textLine
      String q = '"'
      String escapedQuotePlaceholder = "<##Q##>"
      String fileName, varName, varValue
      final String macroEchovar = "<!--#echo var="
      while (line.contains(macroEchovar)) {
         //  <!--#echo var="COLOR" -->
         int loc = line.indexOf(macroEchovar)
         varName = line.substring(loc + 15, line.indexOf('"', loc + 15))
         varValue = attributes.getValue(varName)
         line = line.replaceFirst(macroEchovar + q + varName + q + "-->", varValue)
         line = line.replaceFirst(macroEchovar + q + varName + q + " -->", varValue)
         loc = line.indexOf(macroEchovar)
         }
      if (line.contains("<!--#if")) {
         // <!--#if expr="$DOCUMENT_NAME=index.bhtml" -->
         withinIfBlock = true
         doIf = line.contains("DOCUMENT_NAME="+attributes.getValue("DOCUMENT_NAME"))
         trueIfFound = doIf
         }
      else if (line.contains("<!--#elif")) {
         // <!--#elif expr="$DOCUMENT_NAME=pilaf.bhtml" -->
         doIf = line.contains("DOCUMENT_NAME="+attributes.getValue("DOCUMENT_NAME"))
         trueIfFound = trueIfFound || doIf
         }
      else if (line.contains("<!--#else")) {
         // <!--#else -->
         doIf = !trueIfFound
         }
      else if (line.contains("<!--#endif")) {
         // <!--#endif -->
         withinIfBlock = false
         }
      else if (!withinIfBlock || doIf) {
         if (line.contains("<!--#set var=")) {
            // <!--#set var="COLOR" value="blue" -->
            String[] cmdPieces = line.replaceAll("\\\\"+q, escapedQuotePlaceholder).split(q)
            attributes.setValue(cmdPieces[1], cmdPieces[3].replaceAll(escapedQuotePlaceholder, q))
            }
         else if (line.contains("<!--#include")) {
            // <!--#include file="[Filename]" plain -->
            int loc = line.indexOf("file=") + 6
            fileName = line.substring(loc, line.indexOf('"', loc))
            new InputFile().processFile(new File(srcFile.getParentFile(), fileName), dataOut)
            }
         else
            dataOut.println(line)
         }
      }

   }
