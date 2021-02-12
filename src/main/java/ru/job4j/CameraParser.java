package ru.job4j;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

public class CameraParser implements Callable<List<Camera>> {
    private ExecutorService pool;
    private String url;
    private ConcurrentHashMap<Integer, Future<Optional<Camera>>> futureCameras;
    private List<Camera> cameras;

    public CameraParser(ExecutorService pool, String url) {
        this.url = url;
        this.pool = pool;
        futureCameras = new ConcurrentHashMap<>();
        cameras = new ArrayList<>();
    }

    @Override
    public List<Camera> call() throws Exception {
        try {
            Future<String> camerasStr = pool.submit(new Request(url));
            JSONArray camerasJson = new JSONArray(camerasStr.get());
            JSONObject cameraInfo;
            for (int i = 0; i < camerasJson.length(); i++) {
                cameraInfo = camerasJson.getJSONObject(i);
                futureCameras.put(
                        cameraInfo.getInt("id"),
                        pool.submit(new CameraInfoParser(pool, cameraInfo))
                );
            }
            for (Future<Optional<Camera>> futureCamera : futureCameras.values()) {
                Optional<Camera> cameraOptional = futureCamera.get();
                cameraOptional.ifPresent(cameras::add);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return cameras;
    }
}
