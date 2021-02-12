package ru.job4j;

import java.io.Serializable;
import java.util.Objects;

public class Camera implements Serializable {
    private int id;
    private String urlType;
    private String videoUrl;
    private String value;
    private int ttl;

    public Camera(int id, String urlType, String videoUrl, String value, int ttl) {
        this.id = id;
        this.urlType = urlType;
        this.videoUrl = videoUrl;
        this.value = value;
        this.ttl = ttl;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Camera camera = (Camera) o;
        return id == camera.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Camera{"
                + "id=" + id
                + ", urlType='" + urlType + '\''
                + ", videoUrl='" + videoUrl + '\''
                + ", value='" + value + '\''
                + ", ttl='" + ttl + '\''
                + "}";
    }
}
