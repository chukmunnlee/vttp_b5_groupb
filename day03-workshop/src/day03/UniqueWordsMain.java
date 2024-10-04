package day03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.HashSet;
import java.util.Set;

// https://gist.github.com/sebleier/554280

public class UniqueWordsMain {

   public static void main(String[] args) throws FileNotFoundException, IOException {

      String inputFile = args[0];

      // Open inputFile for reading
      Reader reader = new FileReader(inputFile);
      BufferedReader bufferedReader = new BufferedReader(reader);

      // Create a set of string
      // <> - generics
      Set<String> uniqueWords = new HashSet<>();

      String line = "x";
      while (null != line) {
         // Read a line
         line = bufferedReader.readLine();
         
         // If line is null, we have reach the EOF
         if (null == line)
            break;

         //System.out.printf(">>>> line: %s\n", line.toUpperCase());
         String transformed = line.replaceAll("\\p{Punct}", "").toLowerCase().trim();
         //System.out.printf(">> %s\n", transformed);

         for (String word: transformed.split(" "))
            uniqueWords.add(word);
      }

      // Close the files
      reader.close();

      System.out.printf("Unique words in %s: %d\n", inputFile, uniqueWords.size());

      for (String word: uniqueWords)
         System.out.printf("%s, ", word);

      System.out.println();
   }

}