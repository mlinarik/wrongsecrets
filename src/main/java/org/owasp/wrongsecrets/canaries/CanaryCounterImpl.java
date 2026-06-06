package org.owasp.wrongsecrets.canaries;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Service;

/** Implementation of CanaryCounter using an Atomic integer for actual implementation. */
@Service
public class CanaryCounterImpl implements CanaryCounter {

  private static final AtomicInteger numberofCanaryCalls = new AtomicInteger(0);

  private String lastToken;

  @Override
  public void upCallBackCounter() {
    numberofCanaryCalls.incrementAndGet();
  }

  @Override
  public int getTotalCount() {
    return numberofCanaryCalls.get();
  }

  @Override
  public void setLastCanaryToken(String tokenContent) {
    if (tokenContent != null && tokenContent.matches("^[a-zA-Z0-9]+$")) {
      lastToken = tokenContent;
    } else {
      throw new IllegalArgumentException("Invalid token content");
    }
  }

  @Override
  public String getLastToken() {
    return lastToken;
  }
}
