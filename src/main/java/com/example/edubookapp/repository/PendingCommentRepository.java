package com.example.edubookapp.repository;

import com.example.edubookapp.model.PendingComment;
import org.springframework.data.repository.CrudRepository;

public interface PendingCommentRepository extends CrudRepository<PendingComment, Integer> {
}
