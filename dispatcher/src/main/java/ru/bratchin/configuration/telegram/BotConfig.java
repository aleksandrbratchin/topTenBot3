package ru.bratchin.configuration.telegram;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.bratchin.yaml.YamlPropertySourceFactory;

@Data
@Configuration
@PropertySource(value = "classpath:telegram.yml", factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix = "bot") //https://www.baeldung.com/configuration-properties-in-spring-boot
public class BotConfig {
    private String name;
    private String token;
}
