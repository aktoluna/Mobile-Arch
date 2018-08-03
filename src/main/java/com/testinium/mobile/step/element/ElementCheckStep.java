package com.testinium.mobile.step.element;

public interface ElementCheckStep {
  void isExistByKey(String key);

  void isExistByKey(String key,int index);

  void equalsTextByKey(String key,String equalText);

  void equalsTextByKey(String key,String equalText,int index);

  void equalsTextByKeyWithSavedKey(String key,String savedKey);

  void equalsTextByKeyWithSavedKey(String key,String savedKey,int index);

  void containsTextByKey(String key,String containText);

  void containsTextByKey(String key,String containText,int index);

  void containsTextByKeyWithSavedKey(String key,String savedKey);

  void containsTextByKeyWithSavedKey(String key,String savedKey,int index);
}
