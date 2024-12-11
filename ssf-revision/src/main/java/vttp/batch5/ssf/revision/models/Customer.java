package vttp.batch5.ssf.revision.models;

public class Customer {

   private String customerId; // 1
   private String firstName; // 2
   private String lastName; // 3
   private String company; // 4
   private String city; // 5
   private String country; // 6

   public String getCustomerId() { return customerId; }
   public void setCustomerId(String customerId) { this.customerId = customerId; }

   public String getFirstName() { return firstName; }
   public void setFirstName(String firstName) { this.firstName = firstName; }

   public String getLastName() { return lastName; }
   public void setLastName(String lastName) { this.lastName = lastName; }

   public String getCompany() { return company; }
   public void setCompany(String company) { this.company = company; }

   public String getCity() { return city; }
   public void setCity(String city) { this.city = city; }

   public String getCountry() { return country; }
   public void setCountry(String country) { this.country = country; }

   @Override
   public String toString() {
      return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName + ", company="
            + company + ", city=" + city + ", country=" + country + "]";
   }
   
}