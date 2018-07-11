package com.wealth.staticdata.client.interfaces;

import com.wealth.client.ServerException;
import com.wealth.staticdata.client.transferobjects.AccountTypeTO;

public interface AccountTypeService {
    
    public AccountTypeTO[] getAccountTypes() throws ServerException;
    public AccountTypeTO[] getActiveAccountTypes() throws ServerException;
    public void createAccountTypes(AccountTypeTO accountTypesTO) throws ServerException;
    public AccountTypeTO getAccountTypesById(int id) throws ServerException;

}