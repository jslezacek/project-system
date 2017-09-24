package org.project;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import junit.framework.TestCase;
import org.project.messages.BenchmarkMessage;

public class TestBenchmarkMessage extends TestCase {

    public void testCreateBenchmarkMsg() {
        String testMsg = "{\"sourceId\":\"unitTest\",\"feedTs\":10000,\"feedId\":123}";
        BenchmarkMessage msg = new BenchmarkMessage.Builder("unitTest").
                feedId(123).
                feedTs(10000).
                build();

        String expectedMsg = new JsonParser().parse(testMsg).getAsJsonObject().toString();
        String parsedMsg = msg.toJson();
        assertTrue(expectedMsg.equals(parsedMsg));
    }
}
