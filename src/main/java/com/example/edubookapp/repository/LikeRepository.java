package com.example.edubookapp.repository;

import com.example.edubookapp.model.Like;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LikeRepository extends CrudRepository<Like, Integer> {

    @Query("select l from Like l" +
            " left join fetch l.book b" +
            " left join fetch l.user u" +
            " where b.id = ?1" +
            " and u.id= ?2")
    Optional<Like> findByBookIdAndUserId(Integer bookId, Integer userId);
}
