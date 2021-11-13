package ru.krasilova.otus.microservices.service;

import ru.krasilova.otus.microservices.database.entity.Message;


public interface StockAdapterService {

    Message createMessage(Message message);

}
