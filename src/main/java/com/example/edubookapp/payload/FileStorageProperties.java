package com.example.edubookapp.payload;

public class FileStorageProperties {
    private String pathfile;

    public String getPathfile() {
        return pathfile;
    }

    public void setPathfile(String pathfile) {
        this.pathfile = pathfile;
    }

    public FileStorageProperties(String pathfile) {
        this.pathfile = pathfile;
    }
}
