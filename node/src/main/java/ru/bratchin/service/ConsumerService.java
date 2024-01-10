package ru.bratchin.service;

import org.telegram.telegrambots.meta.api.objects.Update;

/***
 * Для считывания сообщений из брокера
 */
public interface ConsumerService {

    void consumerTextMessageUpdates(Update update);
    void consumerDocMessageUpdates(Update update);
    void consumerPhotoMessageUpdates(Update update);
}
