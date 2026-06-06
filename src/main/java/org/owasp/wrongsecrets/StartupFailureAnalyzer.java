package org.owasp.wrongsecrets;

import java.util.stream.Collectors;
import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

public class StartupFailureAnalyzer extends AbstractFailureAnalyzer<MissingEnvironmentException> {

  @Override
  protected FailureAnalysis analyze(Throwable rootFailure, MissingEnvironmentException cause) {
    return new FailureAnalysis(getDescription(cause), getAction(cause), cause);
  }

  private String getDescription(MissingEnvironmentException ex) {
    // Sanitize the output of the K8S_ENV environment variable
    String sanitizedEnv = sanitizeEnvironmentVariable(ex.getCurrentRuntimeEnvironment());
    return String.format(
        "K8S_ENV is set to: '%s' which is not correct", sanitizedEnv);
  }

  private String getAction(MissingEnvironmentException ex) {
    return String.format(
        "Consider updating the K8S_ENV environment variable to one of the expected values '%s'",
        ex.getEnvironments().stream().map(e -> e.name()).collect(Collectors.joining(", ")));
  }

  // Helper method to sanitize the environment variable
  private String sanitizeEnvironmentVariable(String env) {
    if (env == null) {
      return "unknown";
    }
    // Example sanitization: replace sensitive information with a placeholder
    return env.replaceAll("[a-zA-Z0-9]", "*");
  }
}
