package ru.bratchin.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.bratchin.configuration.telegram.BotConfig;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig config;

    private final UpdateController updateController;

    public TelegramBot(BotConfig config, UpdateController updateController) {
        super(config.getToken());

        this.config = config;
        this.updateController = updateController;

        // Создание меню
        /*List<BotCommand> commandList = new ArrayList<>();
        commandList.add(new BotCommand("/start", "Начальное меню"));
        commandList.add(new BotCommand("/new_game", "Cоздать новую игру"));
        commandList.add(new BotCommand("/find_game", "Найти игру"));
        try {
            this.execute(new SetMyCommands(commandList, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }*/

    }

    @Override
    public String getBotUsername() {
        return config.getName();
    }

    @PostConstruct
    private void init() {
        updateController.registerBot(this);
    }

    /***
     * Сюда падают все сообщения
     */
    @Override
    public void onUpdateReceived(Update update) {
        updateController.processUpdate(update);
    }

    /***
     * Отправить ответ в телегу
     */
    public void sendAnswerMessage(SendMessage sendMessage) {
        if (sendMessage != null) {
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
