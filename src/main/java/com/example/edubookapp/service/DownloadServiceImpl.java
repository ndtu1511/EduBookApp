package com.example.edubookapp.service;

import com.example.edubookapp.model.Download;
import com.example.edubookapp.repository.DownloadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DownloadServiceImpl implements DownloadService {
    @Autowired
    private DownloadRepository downloadRepository;

    @Override
    public Download save(Download download) {
        return downloadRepository.save(download);
    }

    @Override
    public Optional<Download> findByBookIdAndUserId(Integer bookId, Integer userId) {
        return downloadRepository.findByBookIdAndUserId(bookId, userId);
    }

    @Override
    public Optional<Download> findById(Integer id) {
        return downloadRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        downloadRepository.deleteById(id);
    }
}
