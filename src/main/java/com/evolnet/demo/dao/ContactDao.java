package com.evolnet.demo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import com.evolnet.demo.contact.dto.Contact;

@Repository
public interface ContactDao extends CrudRepository<Contact,Integer>{

}
