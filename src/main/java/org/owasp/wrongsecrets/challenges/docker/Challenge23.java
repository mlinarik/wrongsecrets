package org.owasp.wrongsecrets.challenges.docker;

import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;
import org.owasp.wrongsecrets.challenges.FixedAnswerChallenge;
import org.springframework.stereotype.Component;

/** This challenge is about finding a secret hardcoded in comments in a front-end. */
@Slf4j
@Component
public class Challenge23 extends FixedAnswerChallenge {

  @Override
  public String getAnswer() {
    return getActualData();
  }

  private String getActualData() {
    // Replace the hardcoded secret with an environment variable or secrets management service
    String encodedSecret = System.getenv("CHALLENGE_23_SECRET");
    if (encodedSecret == null) {
      throw new RuntimeException("Environment variable CHALLENGE_23_SECRET is not set");
    }
    return new String(
        Base64.decode(
            Hex.decode(
                Base64.decode(
                    encodedSecret))),
        StandardCharsets.UTF_8);
  }
}
