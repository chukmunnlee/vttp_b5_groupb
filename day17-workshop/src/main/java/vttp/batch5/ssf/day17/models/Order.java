package vttp.batch5.ssf.day17.models;

import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Order {

   private String name;
   private String address;
   private String phone;
   private Date deliveryDate;
   private List<LineItem> lineItems;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public Date getDeliveryDate() {
      return deliveryDate;
   }

   public void setDeliveryDate(Date deliveryDate) {
      this.deliveryDate = deliveryDate;
   }

   public List<LineItem> getLineItems() {
      return lineItems;
   }

   public void setLineItems(List<LineItem> lineItems) {
      this.lineItems = lineItems;
   }

   // Order -> JSON string
   public JsonObject toJson() {
      JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
      for (LineItem li: this.lineItems) {
         JsonObject j = Json.createObjectBuilder()
               .add("itemName", li.getItemName())
               .add("quantity", li.getQuantity())
               .build();
         arrBuilder.add(j);
      }

      DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      String dd = df.format(deliveryDate);

      return Json.createObjectBuilder()
            .add("name", this.name)
            .add("address", this.address)
            .add("phone", this.phone)
            .add("deliveryDate", dd)
            .add("items", arrBuilder.build())
            .build();
   }

   // JSON string -> Order
   public static Order toOrder(String json) {
      Order order = new Order();

      // Convert the string to JSON
      JsonReader reader = Json.createReader(new StringReader(json));
      JsonObject jsonObj = reader.readObject();

      order.setName(jsonObj.getString("name"));
      order.setAddress(jsonObj.getString("address"));
      order.setPhone(jsonObj.getString("phone"));

      Date deliveryDate = new Date();
      try {
         deliveryDate = new SimpleDateFormat("yyyy-MM-dd").parse(jsonObj.getString("deliveryDate"));
      } catch (Exception ex) {
         ex.printStackTrace();
      }
      order.setDeliveryDate(deliveryDate);

      List<LineItem> lineItems = new LinkedList<>();
      JsonArray arr = jsonObj.getJsonArray("items");
      for (int i = 0; i < arr.size(); i++) {
         LineItem li = new LineItem();
         JsonObject j = arr.getJsonObject(i);
         li.setItemName(j.getString("itemName"));
         li.setQuantity(j.getInt("quantity"));
         lineItems.add(li);
      }

      order.setLineItems(lineItems);

      return order;
   }

}
