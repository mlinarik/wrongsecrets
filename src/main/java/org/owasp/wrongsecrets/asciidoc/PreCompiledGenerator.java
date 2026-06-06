package org.owasp.wrongsecrets.asciidoc;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

/**
 * Default enabled template rendering class which uses the HTML files Note that Ascidoc files need
 * to be converted to HTML, use `mvn package` or `mvn install` to make sure they are generated.
 */
@Slf4j
public class PreCompiledGenerator implements TemplateGenerator {

  private static final String ALLOWED_TEMPLATE = "allowed-template.html";

  @Override
  public String generate(String name) throws IOException {
    if (!ALLOWED_TEMPLATE.equals(name + ".html")) {
      throw new IllegalArgumentException("Invalid template name");
    }

    try (var bos = new ByteArrayOutputStream()) {
      FileCopyUtils.copy(new ClassPathResource(ALLOWED_TEMPLATE).getInputStream(), bos);
      return bos.toString(UTF_8);
    }
  }
}
