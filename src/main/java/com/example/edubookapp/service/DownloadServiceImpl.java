package com.example.edubookapp.service;

import com.example.edubookapp.model.Download;
import com.example.edubookapp.repository.DownloadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DownloadServiceImpl implements DownloadService {
    @Autowired
    private DownloadRepository downloadRepository;

    @Override
    public Download save(Download download) {
        return downloadRepository.save(download);
    }
}
