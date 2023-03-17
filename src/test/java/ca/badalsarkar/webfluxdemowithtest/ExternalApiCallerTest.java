package ca.badalsarkar.webfluxdemowithtest;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExternalApiCallerTest {
    private ExternalApiCaller caller= new ExternalApiCaller();

    @Test
    void test() throws IOException {
        var server = new MockWebServer();
        server.enqueue(new MockResponse().setResponseCode(200).setBody("test"));
        server.start();
        var url = server.url("/todos/1");
        Config config = new Config("http://" + server.getHostName() + ":" + server.getPort());
        var result = caller.call(config, String.class);
        assertEquals(result,"test");
        server.shutdown();
    }
}
