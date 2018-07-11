package com.wealth.translation;

import com.wealth.client.domain.BaseDomainEntityTO;
import com.wealth.domain.BaseDomainEntity;

public class BaseDomainTranslator {

    public static void populateBaseDomainEntityTO(BaseDomainEntityTO to, BaseDomainEntity entity) {
        if (entity != null) {
            to.setId(entity.getId());
        }
    }
    
    public static void populateBaseDomainEntity(BaseDomainEntity entity, BaseDomainEntityTO to) {
        if (to != null) {
            entity.setId(to.getId());
        }
    }
}