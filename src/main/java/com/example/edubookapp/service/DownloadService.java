package com.example.edubookapp.service;

import com.example.edubookapp.model.Download;

import java.util.Optional;

public interface DownloadService {
    Download save(Download download);

    Optional<Download> findByBookIdAndUserId(Integer bookId, Integer userId);

    Optional<Download> findById(Integer id);

    void delete(Integer id);
}
