package ru.bratchin.service.impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.bratchin.service.ConsumerService;
import ru.bratchin.service.ProducerService;
import ru.bratchin.yaml.YamlPropertySourceFactory;

@Service
@PropertySource(value = "classpath:rabbitQueueType.yml", factory = YamlPropertySourceFactory.class)
public class ConsumerServiceImpl implements ConsumerService {

    private final ProducerService producerService;

    public ConsumerServiceImpl(ProducerService producerService) {
        this.producerService = producerService;
    }


    @Override
    @RabbitListener(queues = "${rabbitQueue.messages.update.TEXT}")
    public void consumerTextMessageUpdates(Update update) {
        System.out.println("TextMessageUpdates");
        var message = update.getMessage();
        var sendMessage = new SendMessage();
        sendMessage.setText("Мы тут!");
        sendMessage.setChatId(message.getChatId().toString());
        producerService.producerAnswer(sendMessage);
    }

    @Override
    @RabbitListener(queues = "${rabbitQueue.messages.update.DOC}")
    public void consumerDocMessageUpdates(Update update) {
        System.out.println("DocMessageUpdates");
    }

    @Override
    @RabbitListener(queues = "${rabbitQueue.messages.update.PHOTO}")
    public void consumerPhotoMessageUpdates(Update update) {
        System.out.println("PhotoMessageUpdates");
    }
}
