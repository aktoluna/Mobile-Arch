package com.testinium.mobile.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.saha.slnarch.common.file.FileHelper;
import java.io.InputStream;

public class YamlHelper {

  private YamlHelper() {

  }

  public static String convertYamlToJsonByFile(String path) {
    String json = "";
    InputStream inputStream;
    try {
      inputStream = FileHelper.getInstance().getFileStream(path);
      ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
      Object obj = yamlReader.readValue(inputStream, Object.class);
      ObjectMapper jsonWriter = new ObjectMapper();
      json = jsonWriter.writeValueAsString(obj);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return json;
  }


}
