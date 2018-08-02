package com.testinium.mobile.helper;

import com.saha.slnarch.common.file.parser.JsonParser;
import com.testinium.mobile.model.Configuration;
import java.io.IOException;

public enum ConfigurationHelper {
  INSTANCE;

  Configuration configuration;

  ConfigurationHelper() {
    try {
      configuration = new JsonParser()
          .parseFile(YamlHelper.convertYamlToJsonByFile("configuration.yaml"),
              Configuration.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Configuration getConfiguration() {
    return configuration;
  }

}
