package com.evolnet.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evolnet.demo.contact.dto.Contact;
import com.evolnet.demo.exception.RecordsNotFoundException;
import com.evolnet.demo.service.ContactServiceImpl;

@RestController
@RequestMapping("/contact")
@CrossOrigin
public class ContactController {

	
	@Autowired
	ContactServiceImpl contactService;
	
	//For getting the list of contacts
	
	@GetMapping("/all")
	public ResponseEntity<List<Contact>> getContacts()
	{
		List<Contact> contactList = contactService.getAllContacts();
		if(contactList == null)
			throw new RecordsNotFoundException("No record Found ");
		
		return new ResponseEntity(contactList,HttpStatus.OK);
	}
	// For Adding the contact
	
	@PostMapping("/add")
	public ResponseEntity<String> addContact(@RequestBody Contact contact)
	{
		System.out.println("Addint new entry");
		if( !contactService.addContact(contact) )
				throw new RecordsNotFoundException("Unable to add Contact ");
		
		return new ResponseEntity("Contact inserted Successfully ",HttpStatus.OK);
		
	}
	
	// For Editing the contact
	@PutMapping("/update/{id}")
	public ResponseEntity<String> editContact(@RequestBody Contact contact,@PathVariable int id)
	{
		if( !contactService.updateContact(id, contact) )
			throw new RecordsNotFoundException("Error while editing ");

		return new ResponseEntity("Contact updated Successfully ",HttpStatus.OK);
	}
	
	//For Deleting the contact
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> removeContact(@PathVariable int id)
	{
		
		contactService.removeContact(id);
		return new ResponseEntity("Contact deleted Successfully ",HttpStatus.OK);
	}
	
	//For checking the status
	@PutMapping("/deactivate/{id}")
	public ResponseEntity<String> deactivateContact(@PathVariable int id)
	{
	   if(	!contactService.deactivateContact(id) )
			 throw new RecordsNotFoundException("Error while deactivatings");
	   return new ResponseEntity("Contact deactivated. " ,HttpStatus.OK);	 
	}
	@PutMapping("/activate/{id}")
	public ResponseEntity<String> activateContact(@PathVariable int id) {
		if( !contactService.activateContact(id))
			throw new RecordsNotFoundException("Error while activating");
		return new ResponseEntity("Contact Activated successfully",HttpStatus.OK);
		
	}
}
