package com.wealth.staticdata.client.interfaces;

import com.wealth.client.ServerException;
import com.wealth.staticdata.client.transferobjects.PropertyTypeTO;

public interface PropertyTypeService {
    
    public PropertyTypeTO[] getPropertyTypes() throws ServerException;
    public PropertyTypeTO[] getActivePropertyTypes() throws ServerException;
    public void createPropertyType(PropertyTypeTO propertyTypeTO) throws ServerException;
    public PropertyTypeTO getPropertyTypeById(int id) throws ServerException;

}