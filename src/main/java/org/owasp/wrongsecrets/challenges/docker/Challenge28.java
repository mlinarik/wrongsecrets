package org.owasp.wrongsecrets.challenges.docker;

import static java.nio.charset.StandardCharsets.UTF_8;

import org.bouncycastle.util.encoders.Base64;
import org.owasp.wrongsecrets.challenges.FixedAnswerChallenge;
import org.springframework.stereotype.Component;

/** This challenge is about finding a secret in a Github issue. */
@Component
public class Challenge28 extends FixedAnswerChallenge {

  private String getSecretKey() {
    // Use environment variable to store the secret key
    return new String(
        Base64.decode(
            System.getenv("SECRET_KEY"), UTF_8),
        UTF_8);
  }

  @Override
  public String getAnswer() {
    return getSecretKey();
  }
}
