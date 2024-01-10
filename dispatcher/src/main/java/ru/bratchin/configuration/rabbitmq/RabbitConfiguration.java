package ru.bratchin.configuration.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.bratchin.yaml.YamlPropertySourceFactory;


@Configuration
@PropertySource(value = "classpath:rabbitQueueType.yml", factory = YamlPropertySourceFactory.class)
public class RabbitConfiguration {

    @Value("${rabbitQueue.messages.update.DOC}")
    private String doc;

    @Value("${rabbitQueue.messages.update.PHOTO}")
    private String photo;

    @Value("${rabbitQueue.messages.update.TEXT}")
    private String text;

    @Value("${rabbitQueue.messages.answer}")
    private String answer;


    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public Queue textMessageQueue() {
        return new Queue(text);
    }

    @Bean
    public Queue photoMessageQueue() {
        return new Queue(photo);
    }

    @Bean
    public Queue docMessageQueue() {
        return new Queue(doc);
    }

    @Bean
    public Queue answerMessageQueue() {
        return new Queue(answer);
    }

    public String getDoc() {
        return doc;
    }

    public String getPhoto() {
        return photo;
    }

    public String getText() {
        return text;
    }

    public String getAnswer() {
        return answer;
    }
}
