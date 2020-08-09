package com.evolnet.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.evolnet.demo.contact.dto.Contact;
import com.evolnet.demo.dao.ContactDao;
import com.evolnet.demo.util.Status;

@Service
public class ContactServiceImpl {

	@Autowired
	ContactDao contactDao;
	
	public List<Contact> getAllContacts()
	{
		Iterable<Contact> itr = contactDao.findAll();
		List<Contact> contacts = new ArrayList<Contact>();
		
		itr.forEach((contact) -> {
			contacts.add(contact);
		});
		return contacts;
	}
	
	public boolean addContact(Contact contact)
	{
	  contact.setStatus(Status.ACTIVE);	
	  if(contactDao.save(contact) != null)
		  return true;
	  return false;
	}
	
	public boolean updateContact(int id,Contact contact)
	{
          Optional<Contact> record  = contactDao.findById(id);
          Contact newContact = record.get();
          String newEmail = contact.getEmailId() != null ? contact.getEmailId() : newContact.getEmailId();
          String newMobileNumber = contact.getMobileNumber() != null ? contact.getMobileNumber() : newContact.getMobileNumber();
          String newFirstName = contact.getFirstName() != null ? contact.getFirstName() : newContact.getFirstName();
          String newLastName = contact.getLastName() != null ? contact.getLastName() : newContact.getLastName();
          newContact.setEmailId(newEmail);
          newContact.setFirstName(newFirstName);
          newContact.setLastName(newLastName);
          newContact.setMobileNumber(newMobileNumber);
          
          if( contactDao.save(newContact) != null)
        	   return true;
          
          return false;
	}
	
	public void removeContact(int id)
	{
		 contactDao.deleteById(id);
	}
	
	public boolean deactivateContact(int id)
	{
		Optional<Contact> record  = contactDao.findById(id);
        Contact newContact = record.get();
        newContact.setStatus(Status.INACTIVE);
        if ( contactDao.save(newContact) != null )
        	return true;
        return false;
	}
	public boolean activateContact(int id)
	{
		Optional<Contact> record  = contactDao.findById(id);
        Contact newContact = record.get();
        newContact.setStatus(Status.ACTIVE);
        if ( contactDao.save(newContact) != null )
        	return true;
        return false;
	}
	
}
