package ru.job4j;

import org.json.JSONObject;

import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class CameraInfoParser implements Callable<Optional<Camera>> {
    private ExecutorService pool;
    private JSONObject cameraInfo;

    public CameraInfoParser(ExecutorService pool, JSONObject cameraInfo) {
        this.pool = pool;
        this.cameraInfo = cameraInfo;
    }

    @Override
    public Optional<Camera> call() throws Exception {
        try {
            Future<String> sourceDataStr = pool.submit(
                    new Request(cameraInfo.getString("sourceDataUrl"))
            );
            Future<String> tokenDataStr = pool.submit(
                    new Request(cameraInfo.getString("tokenDataUrl"))
            );
            JSONObject sourceData = new JSONObject(sourceDataStr.get());
            JSONObject tokenData = new JSONObject(tokenDataStr.get());
            Camera camera = new Camera(
                    cameraInfo.getInt("id"),
                    sourceData.getString("urlType"),
                    sourceData.getString("videoUrl"),
                    tokenData.getString("value"),
                    tokenData.getInt("ttl")
            );
            return Optional.of(camera);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
