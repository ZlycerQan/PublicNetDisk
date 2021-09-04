package org.zlycerqan.publicnetdisk.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "spring.repository")
public class RepositoryConfiguration {

    private String path;

}
