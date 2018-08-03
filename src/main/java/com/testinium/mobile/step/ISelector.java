package com.testinium.mobile.step;

import com.testinium.mobile.model.SelectorInfo;
import com.testinium.mobile.selector.Selector;
import org.assertj.core.api.Assertions;

public interface ISelector {

  default SelectorInfo getSelectorInfo(String key) {
    SelectorInfo selectorInfo = getSelector().getSelectorInfo(key);
    Assertions.assertThat(selectorInfo).isNotNull();
    return selectorInfo;
  }

  Selector getSelector();
}
