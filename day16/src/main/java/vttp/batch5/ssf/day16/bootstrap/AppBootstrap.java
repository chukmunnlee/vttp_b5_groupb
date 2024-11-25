package vttp.batch5.ssf.day16.bootstrap;

import java.io.Reader;
import java.io.StringReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import vttp.batch5.ssf.day16.services.HttpbinService;

@Component
public class AppBootstrap implements CommandLineRunner {

   @Autowired
   private HttpbinService httpBinSvc;

   @Override
   public void run(String... args) {

      //httpBinSvc.get();
      //httpBinSvc.getJokes();
      //httpBinSvc.getWithQueryParams();
      httpBinSvc.postForm();
      httpBinSvc.postJson();

      // Get an object builder
      /*
      JsonObjectBuilder objBuilder = Json.createObjectBuilder();
      objBuilder = objBuilder
            .add("name", "fred")
            .add("email", "fred@gmail.com")
            .add("age", 50)
            .add("married", true);

      JsonObject addr = Json.createObjectBuilder()
            .add("street", "1 bedrock ave")
            .add("postcode", "abcd1234")
            .build();

      JsonArray hobbies = Json.createArrayBuilder()
            .add("skiing")
            .add("jogging")
            .add("travelling")
            .build();

      JsonObject fred = objBuilder
            .add("address", addr)
            .add("hobbies", hobbies)
            .build();

      // JsonObject -> String
      System.out.printf("Fred as JSON: \n%s\n", fred.toString());

      System.out.printf("Name: %s\n", fred.getString("name"));
      System.out.printf("Age: %d\n", fred.getInt("age"));
      JsonObject tmpObj = fred.getJsonObject("address");
      System.out.printf("Street: %s\n", tmpObj.getString("street"));
      JsonArray tmpArr = fred.getJsonArray("hobbies");
      System.out.printf("Hobbies[1]: %s\n", tmpArr.getString(1));

      // String -> JsonObject
      String data = """
         {
            "name": "barney",
            "email": "barney@gmail.com",
            "age": 50, 
            "married": true,
            "address": {
               "street": "1 bedrock ave",
               "postcode": "abc123"
            },
            "hobbies": [ "skiing", "reading", "jogging" ]
         }
      """;

      Reader reader = new StringReader(data);
      JsonReader jsonReader = Json.createReader(reader);
      JsonObject barney = jsonReader.readObject();

      System.out.printf(">>> barney\n\t:%s\n", barney.toString());

      String arrData = "[ 123, 245, 789 ]";
      reader = new StringReader(arrData);
      jsonReader = Json.createReader(reader);
      
      JsonArray numList = jsonReader.readArray();
      for (int i = 0; i < numList.size(); i++)
         System.out.printf("%d: %d\n", i, numList.getInt(i));

      */

   }
   
}
