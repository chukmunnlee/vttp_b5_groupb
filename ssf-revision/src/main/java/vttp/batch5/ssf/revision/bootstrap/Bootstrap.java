package vttp.batch5.ssf.revision.bootstrap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import vttp.batch5.ssf.revision.models.Customer;

@Component
public class Bootstrap implements CommandLineRunner {

   @Value("${my.api.pass-key}")
   private String apiKey;

   @Value("${csv.file.path}")
   private String csvFile;

   @Override
   public void run(String... args) {

      System.out.printf(">>>> CSV file path: %s\n", csvFile);

      List<Customer> customers;

      try {
         //customers = withLoop(file);
         customers = withStream(csvFile);

         System.out.printf(">>> customers: %s\n", customers);
      } catch (Exception ex) {
         ex.printStackTrace();
      }

      System.out.printf(">>> API KEY: %s\n", apiKey);
   }

   public List<Customer> withStream(String file) throws Exception {

      try (FileReader fr = new FileReader(file)) {
         BufferedReader br = new BufferedReader(fr);

         // Access the stream
         return br.lines()
            .skip(1)
            .limit(10)
            .map(line -> {
               return line.split(",");
            }) // string -> string[]
            .filter(fields -> "chile".equals(fields[6].trim().toLowerCase()))
            .map(fields -> {
               Customer customer = new Customer();
               customer.setCustomerId(fields[1]);
               customer.setFirstName(fields[2]);
               customer.setLastName(fields[3]);
               customer.setCompany(fields[4]);
               customer.setCity(fields[5]);
               customer.setCountry(fields[6]);
               return customer;
            }) // string[] -> customer
            .toList();
      }
   }

   public List<Customer> withLoop(String file) throws Exception {

      List<Customer> customers = new LinkedList<>();

      try (FileReader fr = new FileReader(file)) {
         BufferedReader br = new BufferedReader(fr);
         // discard the header line, skip a lne
         br.readLine();
         String line;

         while (null != (line = br.readLine())) {

            // map: string -> string[]
            String[] fields = line.split(",");

            System.out.printf(">> %s\n", fields[6]);
            // filtering
            if (!"hungary".equals(fields[6].trim().toLowerCase()))
               continue;

            // map: string[] -> Customer
            Customer customer = new Customer();
            customer.setCustomerId(fields[1]);
            customer.setFirstName(fields[2]);
            customer.setLastName(fields[3]);
            customer.setCompany(fields[4]);
            customer.setCity(fields[5]);
            customer.setCountry(fields[6]);

            // aggregating
            customers.add(customer);

         }

         return customers;
      }

   }
   
}
