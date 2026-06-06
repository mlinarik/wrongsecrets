package org.owasp.wrongsecrets.challenges.cloud;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import lombok.extern.slf4j.Slf4j;
import org.owasp.wrongsecrets.challenges.FixedAnswerChallenge;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/** Cloud challenge that leverages the CSI secrets driver of the cloud you are running in. */
@Component
@Slf4j
public class Challenge10 extends FixedAnswerChallenge {

  private final String awsDefaultValue;
  private final String filePath;
  private final String fileName;

  public Challenge10(
      @Value("${secretmountpath}") String filePath,
      @Value("${default_aws_value_challenge_10}") String awsDefaultValue,
      @Value("${FILENAME_CHALLENGE10}") String fileName) {
    this.awsDefaultValue = awsDefaultValue;
    this.filePath = sanitizePath(filePath);
    this.fileName = sanitizeFileName(fileName);
  }

  private String sanitizePath(String path) {
    // Implement proper input validation or whitelisting here
    // For example, check if the path is within a specific directory
    return path;
  }

  private String sanitizeFileName(String fileName) {
    // Implement proper input validation or whitelisting here
    // For example, check if the file name contains only allowed characters
    return fileName;
  }

  @Override
  public String getAnswer() {
    return getCloudChallenge9and10Value(filePath, fileName);
  }

  @SuppressFBWarnings(
      value = "PATH_TRAVERSAL_IN",
      justification = "The location of the file is based on an Env Var")
  private String getCloudChallenge9and10Value(String filePath, String fileName) {
    try {
      return Files.readString(Paths.get(filePath, fileName), StandardCharsets.UTF_8);
    } catch (Exception e) {
      log.warn(
          "Exception during reading file ({}/{}}), defaulting to default without AWS",
          filePath,
          fileName);
      return awsDefaultValue;
    }
  }
}
