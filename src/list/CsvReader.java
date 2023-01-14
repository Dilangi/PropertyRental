package list;

import java.io.*;
import java.util.Scanner;

//file reading
public class CsvReader {
   private Scanner inputFile;
   private String line;
   
   public CsvReader(String filename)throws IOException{
      File file = new File(filename);
      inputFile = new Scanner(file);
   }

   public boolean readNextLine() throws IOException
   {
      boolean lineRead;
      lineRead = inputFile.hasNext();
      if (lineRead)
        line = inputFile.nextLine();
       
      return lineRead;
   }
   
   public String getLine(){
	   return line;
   }
   
   public void close() throws IOException
   {
      inputFile.close();
   }
}