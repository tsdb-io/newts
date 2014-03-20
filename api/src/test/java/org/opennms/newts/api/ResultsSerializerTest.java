package org.opennms.newts.api;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.opennms.newts.api.MetricType.COUNTER;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ResultsSerializerTest {

    @Test
    public void testMeasurements() throws JsonProcessingException {

        Results<Measurement> testData = new Results<>();
        testData.addElement(new Measurement(Timestamp.fromEpochSeconds(900000000), "localhost", "ifInOctets", 5000));
        testData.addElement(new Measurement(Timestamp.fromEpochSeconds(900000000), "localhost", "ifOutOctets", 6000));
        testData.addElement(new Measurement(Timestamp.fromEpochSeconds(900000300), "localhost", "ifInOctets", 6000));
        testData.addElement(new Measurement(Timestamp.fromEpochSeconds(900000300), "localhost", "ifOutOctets", 7000));

        String json = "["
                + "  ["
                + "    {\"ifOutOctets\":"
                + "      {"
                + "        \"timestamp\":900000000,"
                + "        \"value\":6000.0"
                + "      }"
                + "    },"
                + "    {\"ifInOctets\":"
                + "      {"
                + "        \"timestamp\":900000000,"
                + "        \"value\":5000.0"
                + "      }"
                + "    }"
                + "  ],"
                + "  ["
                + "    {\"ifOutOctets\":"
                + "      {"
                + "        \"timestamp\":900000300,"
                + "        \"value\":7000.0"
                + "      }"
                + "    },"
                + "    {\"ifInOctets\":"
                + "      {"
                + "        \"timestamp\":900000300,"
                + "        \"value\":6000.0"
                + "      }"
                + "    }"
                + "  ]"
                + "]";

        ObjectMapper mapper = new ObjectMapper();

        assertThat(mapper.writeValueAsString(testData), is(normalize(json)));

    }

    @Test
    public void testSamples() throws JsonProcessingException {

        Results<Sample> testData = new Results<>();
        testData.addElement(new Sample(
                Timestamp.fromEpochSeconds(900000000),
                "localhost",
                "ifInOctets",
                COUNTER,
                ValueType.compose(5000, COUNTER)));
        testData.addElement(new Sample(
                Timestamp.fromEpochSeconds(900000000),
                "localhost",
                "ifOutOctets",
                COUNTER,
                ValueType.compose(6000, COUNTER)));
        testData.addElement(new Sample(
                Timestamp.fromEpochSeconds(900000300),
                "localhost",
                "ifInOctets",
                COUNTER,
                ValueType.compose(6000, COUNTER)));
        testData.addElement(new Sample(
                Timestamp.fromEpochSeconds(900000300),
                "localhost",
                "ifOutOctets",
                COUNTER,
                ValueType.compose(7000, COUNTER)));

        String json =  "["
                + "  ["
                + "    {\"ifOutOctets\":"
                + "      {"
                + "        \"timestamp\":900000000,"
                + "        \"type\":\"COUNTER\","
                + "        \"value\":6000"
                + "      }"
                + "    },"
                + "    {\"ifInOctets\":"
                + "      {"
                + "        \"timestamp\":900000000,"
                + "        \"type\":\"COUNTER\","
                + "        \"value\":5000"
                + "      }"
                + "    }"
                + "  ],"
                + "  ["
                + "    {\"ifOutOctets\":"
                + "      {"
                + "        \"timestamp\":900000300,"
                + "        \"type\":\"COUNTER\","
                + "        \"value\":7000"
                + "      }"
                + "    },"
                + "    {\"ifInOctets\":"
                + "      {"
                + "        \"timestamp\":900000300,"
                + "        \"type\":\"COUNTER\","
                + "        \"value\":6000"
                + "      }"
                + "    }"
                + "  ]"
                + "]";

        ObjectMapper mapper = new ObjectMapper();

        assertThat(mapper.writeValueAsString(testData), is(normalize(json)));

    }

    private String normalize(String input) {
        return input.replaceAll("\\n", "").replaceAll("\\s", "");
    }

}
