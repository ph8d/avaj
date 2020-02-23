package com.avaj.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class Logger {

  private static final Logger _logger = new Logger();
  private static Writer writer = null;

  private Logger() {}

  public Logger getLogger() {
    return _logger;
  }

  public static void setLogFile(String fileName) throws IOException{
    if (writer != null) {
      writer.close();
      writer = null;
    }

    File logFile = new File(fileName);
    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(logFile), "UTF-8"));
  }

  public static void closeLogFile() {
    try {
      writer.flush();
      writer.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
      System.exit(1);
    }
  }

  public static void log(String str) {
    try {
      if (writer == null) {
        throw new IOException("Error, no log file was specified.");
      }

      writer.write(str + '\n');
      writer.flush();
    } catch (IOException e) {
      System.out.println(e.getMessage());
      System.exit(1);
    }
  }

}