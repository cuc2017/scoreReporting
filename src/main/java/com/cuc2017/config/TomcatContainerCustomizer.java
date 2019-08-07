package com.cuc2017.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.stereotype.Component;

import com.cuc2017.controller.ScoreGameController;

@Component
public class TomcatContainerCustomizer implements EmbeddedServletContainerCustomizer {

  private static final Logger log = LoggerFactory.getLogger(ScoreGameController.class);

  @Override
  public void customize(final ConfigurableEmbeddedServletContainer container) {
    if (container instanceof TomcatEmbeddedServletContainerFactory) {
      final TomcatEmbeddedServletContainerFactory tomcat = (TomcatEmbeddedServletContainerFactory) container;
      tomcat.addConnectorCustomizers(connector -> {
        connector.setScheme("https");
        connector.setProxyPort(443);
      });
      log.info("Enabled secure scheme (https).");
    } else {
      log.warn("Could not change protocol scheme because Tomcat is not used as servlet container.");
    }
  }
}
