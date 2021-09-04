package org.zlycerqan.publicnetdisk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.zlycerqan.publicnetdisk.configuration.RepositoryConfiguration;

@EnableConfigurationProperties({RepositoryConfiguration.class})
@SpringBootApplication
public class PublicNetDiskApplication {

    public static void main(String[] args) {
        SpringApplication.run(PublicNetDiskApplication.class, args);
    }

}
