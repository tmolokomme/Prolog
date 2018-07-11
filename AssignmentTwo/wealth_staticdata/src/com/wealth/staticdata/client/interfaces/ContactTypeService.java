package com.wealth.staticdata.client.interfaces;

import com.wealth.client.ServerException;
import com.wealth.staticdata.client.transferobjects.ContactTypeTO;

public interface ContactTypeService {
    
    public ContactTypeTO[] getContactTypes() throws ServerException;
    public ContactTypeTO[] getActiveContactTypes() throws ServerException;
    public void createContactType(ContactTypeTO contactTypeTO) throws ServerException;
    public ContactTypeTO getContactTypeById(int id) throws ServerException;

}