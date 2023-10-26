package com.kdsAPI.messaging.consumers;

public interface  MessageConsumer<T> {    
    public void getMessage(T message);
}
