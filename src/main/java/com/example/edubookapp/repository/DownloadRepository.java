package com.example.edubookapp.repository;

import com.example.edubookapp.model.Download;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DownloadRepository extends CrudRepository<Download, Integer> {
    Optional<Download> findByBookIdAndUserId(Integer bookId, Integer userId);
}
