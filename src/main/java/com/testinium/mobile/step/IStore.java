package com.testinium.mobile.step;

import com.testinium.mobile.helper.StoreHelper;

public interface IStore {

  default String getValueByKey(String key) {
    return StoreHelper.INSTANCE.getValue(key);
  }

  default void saveValueByKey(String key, String value) {
    StoreHelper.INSTANCE.saveValue(key, value);
  }
}
