package com.engilyin.bestpractices.kafka.producers;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import com.engilyin.bestpractices.kafka.data.MyMessage;

public class BrokenSerializer implements Serializer<MyMessage>{

    @Override
    public byte[] serialize(String topic, MyMessage data) {
	throw new SerializationException("Testing serialization failure");
    }
}
