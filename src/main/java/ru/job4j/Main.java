package ru.job4j;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONException;

import java.io.*;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args)
            throws IOException, JSONException, ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newCachedThreadPool();
        Future<List<Camera>> futureCameras = pool.submit(
                new CameraParser(pool, "http://www.mocky.io/v2/5c51b9dd3400003252129fb5")
        );
        List<Camera> cameras = futureCameras.get();
        pool.shutdown();
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(cameras));
    }
}
