package ru.job4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class SourceDataParser implements Runnable {
    private ExecutorService pool;
    private String url;
    private int cameraId;

    public SourceDataParser(ExecutorService pool, String url, int cameraId) {
        this.pool = pool;
        this.url = url;
        this.cameraId = cameraId;
    }

    @Override
    public void run() {
        try {
            Future<String> sourceStr = pool.submit(
                    new Request(url)
            );
            System.out.println(sourceStr.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}