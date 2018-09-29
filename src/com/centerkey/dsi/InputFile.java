////////////////////////////////////////////////////////////////////////////////
// DSI (Design-Side Includes)                                                 //
//                                                                            //
// Input File Reader                                                          //
//    Reads and parses the base (source) files used to create the final HTML  //
//    output. The default file name extension for base files is ".bhtml".     //
//                                                                            //
// MIT License - Copyright (c) individual contributors to DSI                 //
////////////////////////////////////////////////////////////////////////////////

package com.centerkey.dsi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.text.DateFormat;
import java.util.Calendar;

public class InputFile {

   PageVariables attributes = new PageVariables();
   boolean withinIfBlock = false;
   boolean doIf, trueIfFound;

   public void processFile(File srcFile, File destFile) {
      FileOutputStream fileOut;
      try {
         attributes.clearAll();
         attributes.setValue("DOCUMENT_NAME", srcFile.getName());
         //DateFormat df = SimpleDateFormat(DateFormat.LONG);
         //String dateStr = df.format(Calendar.getInstance().getTime()); // format date here
         //String myString = DateFormat.getDateInstance().format(myDate);
         //String x = Calendar.getInstance().toString();
         //SimpleDateFormat formatter;
         //attributes.setValue("LAST_MODIFIED",
         //   Calendar.getInstance().getTime().toLocaleString().substring(0, 12));
         attributes.setValue("LAST_MODIFIED",
            DateFormat.getDateInstance(DateFormat.LONG).format(
               Calendar.getInstance().getTime()));
         fileOut = new FileOutputStream(destFile);
         processFile(srcFile, new PrintStream(fileOut, false, "UTF-8"));
         fileOut.close();
         }
      catch (IOException e) {
         System.out.println(">>> ERROR #2 -- " + e.toString());
         }
      }

   public void processFile(File srcFile, PrintStream dataOut) {
      FileInputStream fileIn;
      BufferedReader  dataIn;
      String          textLine;
      try {
         fileIn = new FileInputStream(srcFile);
         dataIn = new BufferedReader(new InputStreamReader(fileIn, "UTF-8"));
         textLine = dataIn.readLine();
         while (textLine != null) {
            processLine(textLine, srcFile, dataOut);
            textLine = dataIn.readLine();  //read line
            }
         fileIn.close();
         }
      catch (IOException e) {
         String errMsg = ">>> ERROR #3 -- " + e.toString();
         System.out.println(errMsg);
         dataOut.println(errMsg);
         }
      }

   private void processLine(String textLine, File srcFile, PrintStream dataOut){
      String line = textLine;
      String q = "\"";
      String escapedQuotePlaceholder = "<##Q##>";
      String fileName, varName, varValue;
      final String macroEchovar = "<!--#echo var=";
      while (line.contains(macroEchovar)) {
         //  <!--#echo var="COLOR" -->
         int loc = line.indexOf(macroEchovar);
         varName = line.substring(loc + 15, line.indexOf('"', loc + 15));
         varValue = attributes.getValue(varName);
         line = line.replaceFirst(macroEchovar + q + varName + q + "-->", varValue);
         line = line.replaceFirst(macroEchovar + q + varName + q + " -->", varValue);
         loc = line.indexOf(macroEchovar);
         }
      if (line.contains("<!--#if")) {
         // <!--#if expr="$DOCUMENT_NAME=index.bhtml" -->
         withinIfBlock = true;
         doIf = line.contains("DOCUMENT_NAME="+attributes.getValue("DOCUMENT_NAME"));
         trueIfFound = doIf;
         }
      else if (line.contains("<!--#elif")) {
         // <!--#elif expr="$DOCUMENT_NAME=pilaf.bhtml" -->
         doIf = line.contains("DOCUMENT_NAME="+attributes.getValue("DOCUMENT_NAME"));
         trueIfFound = trueIfFound || doIf;
         }
      else if (line.contains("<!--#else")) {
         // <!--#else -->
         doIf = !trueIfFound;
         }
      else if (line.contains("<!--#endif")) {
         // <!--#endif -->
         withinIfBlock = false;
         }
      else if (!withinIfBlock || doIf) {
         if (line.contains("<!--#set var=")) {
            // <!--#set var="COLOR" value="blue" -->
            String[] cmdPieces =
               line.replaceAll("\\\\"+q, escapedQuotePlaceholder).split(q);
            attributes.setValue(cmdPieces[1],
               cmdPieces[3].replaceAll(escapedQuotePlaceholder, q));
            }
         else if (line.contains("<!--#include")) {
            // <!--#include file="[FileName]" plain -->
            int loc = line.indexOf("file=") + 6;
            fileName = line.substring(loc, line.indexOf('"', loc));
            new InputFile().processFile(new File(srcFile.getParentFile(),
                  fileName), dataOut);
            }
         else
            dataOut.println(line);
         }
      }

   }
