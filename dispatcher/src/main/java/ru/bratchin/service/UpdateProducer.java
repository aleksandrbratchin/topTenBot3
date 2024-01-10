package ru.bratchin.service;

import org.telegram.telegrambots.meta.api.objects.Update;

/***
 * Для передачи update в rabbitmq
 */
public interface UpdateProducer {
    void produce(String rabbitQueue, Update update);
}
