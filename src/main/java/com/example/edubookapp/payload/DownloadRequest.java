package com.example.edubookapp.payload;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class DownloadRequest {
    @NotNull
    @Min(1)
    private Integer currentPage;

    public DownloadRequest(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public DownloadRequest() {
    }
}
