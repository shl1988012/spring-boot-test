package com.spring.test.kafka.Serializer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

public class JsonNodeDeserializer implements Deserializer<JsonNode> {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public JsonNode deserialize(String topic, byte[] data) {
        try {
            return mapper.readTree(data);
        } catch (IOException e) {
            throw new SerializationException("error when deserializer data");
        }
    }

    @Override
    public void close() {

    }
}
