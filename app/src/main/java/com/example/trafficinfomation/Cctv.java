package com.example.trafficinfomation;

public class Cctv {
    private String name;
    private String rtspUri;

    public Cctv(String name, String rtspUri) {
        this.name = name;
        this.rtspUri = rtspUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRtspUri() {
        return rtspUri;
    }

    public void setRtspUri(String rtspUri) {
        this.rtspUri = rtspUri;
    }
}
