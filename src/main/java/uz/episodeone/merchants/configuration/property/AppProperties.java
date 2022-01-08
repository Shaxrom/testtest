package uz.episodeone.merchants.configuration.property;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource("classpath:application-dev.yml")
@PropertySource("classpath:application-prod.yml")
public class AppProperties {

    private String basePath;
    private String login;
    private String password;
    private String accessToken;
    @Value("${app.cryptofile.path}")
    private Resource certFile;
}
