package com.wealth.translation;

import java.util.List;

import com.wealth.client.domain.enums.BaseEnumTO;

public interface  BaseEnumTranslator<E extends Enum, T extends BaseEnumTO> {
  public T getTO(E entity) ;
  public List<T> getTOList(E[] entity) ;
}
