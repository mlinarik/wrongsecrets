package org.owasp.wrongsecrets.challenges.docker;

import org.owasp.wrongsecrets.challenges.FixedAnswerChallenge;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * This challenge can be run in CTF mode and is limited to using Docker as a runtime environment.
 */
@Component
public class Challenge3 extends FixedAnswerChallenge {

  private final String envPassword;

  public Challenge3(@Value("${DOCKER_ENV_PASSWORD}") String envPassword) {
    this.envPassword = envPassword;
  }

  @Override
  public String getAnswer() {
    return envPassword;
  }
}
