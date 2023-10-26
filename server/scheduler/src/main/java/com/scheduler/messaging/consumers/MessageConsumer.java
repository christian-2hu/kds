package com.scheduler.messaging.consumers;

public interface  MessageConsumer<T> {    
    public void getMessage(T message);
}
