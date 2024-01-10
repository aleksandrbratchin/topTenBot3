package ru.bratchin.service.impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.bratchin.controller.UpdateController;
import ru.bratchin.service.AnswerConsumer;
import ru.bratchin.yaml.YamlPropertySourceFactory;

@Service
//не обязательно!
@PropertySource(value = "classpath:rabbitQueueType.yml", factory = YamlPropertySourceFactory.class)
public class AnswerConsumerImpl implements AnswerConsumer {

    private final UpdateController updateController;

    public AnswerConsumerImpl(UpdateController updateController) {
        this.updateController = updateController;
    }

    @Override
    @RabbitListener(queues = "${rabbitQueue.messages.answer}")
    public void consumer(SendMessage sendMessage) {
        updateController.setView(sendMessage);
    }
}
