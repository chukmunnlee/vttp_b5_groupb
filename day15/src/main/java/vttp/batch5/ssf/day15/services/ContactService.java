package vttp.batch5.ssf.day15.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.batch5.ssf.day15.models.Contact;
import vttp.batch5.ssf.day15.repositories.ContactRepository;

@Service
public class ContactService {

   @Autowired
   private ContactRepository contactRepo;

   public String insert(Contact contact) {

      String id = UUID.randomUUID().toString().substring(0, 8);
      contact.setId(id);

      contactRepo.insertContact(contact);

      return id;
   }
   
}
