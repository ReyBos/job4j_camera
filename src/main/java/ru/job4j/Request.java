package ru.job4j;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;

public class Request implements Callable<String> {
    private final String url;

    public Request(String url) {
        this.url = url;
    }

    @Override
    public String call() throws Exception {
        StringBuilder sb = new StringBuilder();
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(is, StandardCharsets.UTF_8)
            );
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
        }
        return sb.toString();
    }
}
