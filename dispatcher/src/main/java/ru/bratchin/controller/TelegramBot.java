package ru.bratchin.controller;

import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.bratchin.config.BotConfig;

@Controller
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig config;

    public TelegramBot(BotConfig config) {
        super(config.getToken());
        this.config = config;

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
    public void onUpdateReceived(Update update) {
        var mes = update.getMessage();
        System.out.println(mes.getText());
    }

    @Override
    public String getBotUsername() {
        return config.getName();
    }
}
