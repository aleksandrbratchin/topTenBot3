package ru.bratchin.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.bratchin.dao.TasksRepository;
import ru.bratchin.entity.task.Tasks;
import ru.bratchin.service.MainService;
import ru.bratchin.service.ProducerService;

@Service
public class MainServiceImpl implements MainService {

    private final TasksRepository repository;
    private final ProducerService service;

    public MainServiceImpl(TasksRepository repository, @Qualifier("producerServiceImpl") ProducerService service) {
        this.repository = repository;
        this.service = service;
    }

    @Override
    public void processTextMessage(Update update) {
        saveTask(update);
    }

    private void saveTask(Update update) {
        Tasks tasks = Tasks.builder()
                .json(update)
                .build();
        repository.save(tasks);
    }

}
