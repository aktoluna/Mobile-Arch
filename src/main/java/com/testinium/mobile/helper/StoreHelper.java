package com.testinium.mobile.helper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.testinium.mobile.model.ElementInfo;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum StoreHelper {
  INSTANCE;

  Map<String, Object> elementMapList;

  StoreHelper() {
    Gson gson = new Gson();
    List<ElementInfo> elementInfoList;
    elementInfoList = gson
        .fromJson(new InputStreamReader(this.getClass().getResourceAsStream("/elements.json")),
            new TypeToken<List<ElementInfo>>() {
            }.getType());
    elementMapList = new HashMap<>();
    for (ElementInfo elementInfo : elementInfoList) {
      elementMapList.put(elementInfo.getKey(), elementInfo);
    }
  }

  public ElementInfo findElementInfoByKey(String key) {
    return (ElementInfo) elementMapList.get(key);
  }

  public void saveValue(String key, String value) {
    elementMapList.put(key, value);
  }

  public String getValue(String key) {
    return elementMapList.get(key).toString();
  }

}
