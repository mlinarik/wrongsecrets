package org.owasp.wrongsecrets.challenges.docker;

import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.util.encoders.Hex;
import org.owasp.wrongsecrets.challenges.FixedAnswerChallenge;
import org.springframework.stereotype.Component;

/** This challenge is about using a publicly specified key to safeguard data. */
@Slf4j
@Component
public class Challenge24 extends FixedAnswerChallenge {

  private String getActualData() {
    // Replace hardcoded secret with environment variable or secrets management service
    String encodedData = System.getenv("CHALLENGE_24_DATA");
    if (encodedData == null) {
      throw new RuntimeException("Environment variable CHALLENGE_24_DATA is not set");
    }
    return new String(
        Hex.decode(encodedData.getBytes(StandardCharsets.UTF_8)),
        StandardCharsets.UTF_8);
  }

  @Override
  public String getAnswer() {
    return getActualData();
  }
}
