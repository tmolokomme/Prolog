package com.wealth.domain.enums;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.wealth.domain.BaseDomainEntity;

@MappedSuperclass
public abstract class AbstractEnumEntity<T extends EntityEnum> extends BaseDomainEntity {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private int ordinal;

    @Column(nullable = false)
    private String description;

    public int getOrdinal() {
        return ordinal;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
