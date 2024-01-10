package ru.bratchin.controller;

import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.bratchin.configuration.rabbitmq.RabbitConfiguration;
import ru.bratchin.service.UpdateProducer;
import ru.bratchin.utils.MessageUtils;

@Controller
public class UpdateController {
    private TelegramBot telegramBot;

    private final MessageUtils messageUtils;
    private final UpdateProducer updateProducer;
    private final RabbitConfiguration rabbitConfiguration;


    public UpdateController(MessageUtils messageUtils, UpdateProducer updateProducer, RabbitConfiguration rabbitConfiguration) {
        this.messageUtils = messageUtils;
        this.updateProducer = updateProducer;
        this.rabbitConfiguration = rabbitConfiguration;
    }

    public void registerBot(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void processUpdate(Update update) {
        if (update == null) {
            System.out.println("Error: update");
            return;
        }
        if (update.hasMessage()) {
            System.out.println("Error update.getMessage()");
            return;
        }
        distributeMessageByType(update);
    }

    //распределить сообщение по типу
    private void distributeMessageByType(Update update) {
        var message = update.getMessage();
        if (message.hasText()) {
            processTextMessage(update);
        } else if (message.hasPhoto()) {

        }else if (message.hasDocument()){

        } else {
            System.out.println("Неизвестный тип сообщения");
        }
    }

    public void setView (SendMessage sendMessage) {
        telegramBot.sendAnswerMessage(sendMessage);
    }

    private void processTextMessage(Update update) {
        updateProducer.produce(rabbitConfiguration.getText(), update);
    }

    private void processPhotoMessage(Update update) {
        updateProducer.produce(rabbitConfiguration.getPhoto(), update);
    }

    private void processDocumentMessage(Update update) {
        updateProducer.produce(rabbitConfiguration.getDoc(), update);
    }

    private void setFileIsReceivedView(Update update) {
        var sendMessage = messageUtils.generateSendMessageWithText(update,
                "Файл обрабатывается...");
        setView(sendMessage);
    }

    private void sendUnsupportedMessage(Update update) {
        var sendMessage = messageUtils.generateSendMessageWithText(update,
                "Неподдерживаемый тип сообщения");
        setView(sendMessage);
    }
}
