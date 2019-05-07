package com.example.edubookapp.repository;

import com.example.edubookapp.model.PendingBook;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PendingBookRepository extends CrudRepository<PendingBook, Integer> {
    List<PendingBook> findByRequestUsername(String username);

    PendingBook findByTitle(String title);

    PendingBook findByIdAndRequestUsername(Integer id, String requestUsername);

    @Query("select pb from PendingBook pb left join fetch pb.category c where pb.id = ?1")
    PendingBook findOneWithCategory(Integer id);
}
