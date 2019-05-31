package com.example.edubookapp.payload;

import javax.validation.constraints.NotBlank;

public class DownloadRequest {
    @NotBlank
    private int currentPage;

    public DownloadRequest(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
