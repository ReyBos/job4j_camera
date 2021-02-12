package ru.job4j;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.Assert.*;

public class CameraParserTest {
    @Test
    public void test() throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newCachedThreadPool();
        Future<List<Camera>> futureCameras = pool.submit(
                new CameraParser(pool, "http://www.mocky.io/v2/5c51b9dd3400003252129fb5")
        );
        List<Camera> cameras = futureCameras.get();
        cameras.sort(Comparator.comparingInt(Camera::getId));
        pool.shutdown();
        List<Camera> expected = List.of(
                new Camera(
                        1, "LIVE", "rtsp://127.0.0.1/1",
                        "fa4b588e-249b-11e9-ab14-d663bd873d93", 120
                ),
                new Camera(
                        2, "LIVE", "rtsp://127.0.0.1/20",
                        "fa4b5b22-249b-11e9-ab14-d663bd873d93", 180
                ),
                new Camera(
                        3, "ARCHIVE", "rtsp://127.0.0.1/3",
                        "fa4b5b22-249b-11e9-ab14-d663bd873d93", 120
                ),
                new Camera(
                        20, "ARCHIVE", "rtsp://127.0.0.1/2",
                        "fa4b5b22-249b-11e9-ab14-d663bd873d93", 60
                )
        );
        assertEquals(cameras, expected);
    }
}