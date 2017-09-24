package org.project.messages;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.HashMap;

public class BenchmarkMessage {
    private final HashMap fields;

    private BenchmarkMessage(Builder builder){
        this.fields = builder.fields;
    }

    public String toJson() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this.fields);
    }

        public static class Builder {
            private HashMap fields = new HashMap();

            // mandatory fields
            public Builder(String sourceId) {
                this.fields.put("sourceId", sourceId);
            }
            // optional fields
            public Builder orderId(String orderId) {
                this.fields.put("orderId", orderId); return this;}
            public Builder orderTs(long ts) {
                this.fields.put("orderTs", ts); return this;}
            public Builder feedId(int feedId) {
                this.fields.put("feedId", feedId); return this;}
            public Builder feedTs(long ts) {
                this.fields.put("feedTs", ts); return this;}

            public BenchmarkMessage build() {
                return new BenchmarkMessage(this);
            }
        }
}
