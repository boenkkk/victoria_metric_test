package dev.boenkkk.victoria_metric_test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
public class TesController {

    @GetMapping("/tes-get")
    public String testGet(){
        try {
            String url = "http://{host}:8428/api/v1/query?query=data_point&step=1h";

            HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            int responseCode = httpResponse.statusCode();
            String responseBody = httpResponse.body();
            System.out.println("responseCode|responseBody: "+responseCode+"|"+responseBody);

            return responseBody;
        } catch (Exception e) {
            System.err.println("ERROR: "+e.getMessage());
            e.printStackTrace();

            return "ERROR";
        }
    }

    @PostMapping("/tes-post")
    public String tesPost(){
        try {
            String url = "http://{host}:8428/api/v1/import/prometheus";
            String body = "tesboy{id=\"masukgaboy\"} 99999";

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            int responseCode = httpResponse.statusCode();
            String responseBody = httpResponse.body();
            System.out.println("responseCode|responseBody: "+responseCode+"|"+responseBody);

            return responseBody;
        } catch (Exception e) {
            System.err.println("ERROR: "+e.getMessage());
            e.printStackTrace();

            return "ERROR";
        }
    }
}
