package org.opennms.newts.api;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class MeasurementSerializer extends JsonSerializer<Measurement> {

    @Override
    public void serialize(Measurement value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        jgen.writeStartObject();
        jgen.writeNumberField("timestamp", value.getTimestamp().asSeconds());
        jgen.writeNumberField("value", value.getValue());
        jgen.writeEndObject();
    }

}
