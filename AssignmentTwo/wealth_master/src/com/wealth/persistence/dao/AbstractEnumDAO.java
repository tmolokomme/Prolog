package com.wealth.persistence.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.wealth.domain.BaseDomainEntity;
import com.wealth.util.StringUtil;

public abstract class AbstractEnumDAO<T extends BaseDomainEntity> extends AbstractDAO<T> {

    public T findByOrdinal(Integer ordinal) {
        Criteria criteria = createCriteria();
        criteria.add(Restrictions.eq("ordinal", ordinal));
        List<T> results = searchByCriteria(criteria);
        if (results == null || results.isEmpty()) {
            return null;
        }
        return results.get(0);
    }

    public T findByOrdinal(String ordinalStr) {
        Integer ordinal = null;
        if (!StringUtil.isEmpty(ordinalStr)) {
            try {
                ordinal = Integer.parseInt(ordinalStr);
            }
            catch (Exception ke) {
                ordinal = null;
            }
        }

        if (ordinal == null) {
            return null;
        }
        else {
            return findByOrdinal(ordinal);
        }
    }
}
