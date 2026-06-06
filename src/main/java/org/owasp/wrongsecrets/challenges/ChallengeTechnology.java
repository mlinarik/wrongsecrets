package org.owasp.wrongsecrets.challenges;

import java.util.Arrays;
import java.util.Optional;

/** provides the technology used within a challenge. */
public class ChallengeTechnology {

  /** enum from which you can choose the tech to have consistent naming. */
  public enum Tech {
    GIT("Git"),
    DOCKER("Docker"),
    CONFIGMAPS("Configmaps"),
    SECRETS("Secrets"),
    VAULT("Vault"),
    LOGGING("Logging"),
    TERRAFORM("Terraform"),
    CSI("CSI-Driver"),
    CICD("CI/CD"),
    PASSWORD_MANAGER("Password Manager"),
    CRYPTOGRAPHY("Cryptography"),
    BINARY("Binary"),
    FRONTEND("Front-end"),
    IAM("IAM privilege escalation"),
    WEB3("Web3"),
    DOCUMENTATION("Documentation"),
    AI("AI");
    public final String id;

    Tech(String id) {
      this.id = id;
    }

    static ChallengeTechnology.Tech fromId(String id) {
      Optional<ChallengeTechnology.Tech> techOptional = Arrays.stream(ChallengeTechnology.Tech.values())
          .filter(e -> e.id.equalsIgnoreCase(id))
          .findAny();
      
      if (techOptional.isPresent()) {
        return techOptional.get();
      } else {
        throw new IllegalArgumentException("Invalid Tech ID: " + id);
      }
    }
  }
}
