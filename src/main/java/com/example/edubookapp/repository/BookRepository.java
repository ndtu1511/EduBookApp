package com.example.edubookapp.repository;

import com.example.edubookapp.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book,Integer> {
    Book findByIsbn(String isbn);
    Book findByTitle(String title);
    List<Book> findByTitleContaining(String s);
    @Query("select b from Book b left join fetch b.category c where c.id = ?1")
    List<Book> findByCategoryId(Integer categoryId);
    @Query("select b from Book b left join fetch b.category c where b.id = ?1")
    Book findOneWithCategory(Integer id);
    List<Book> findTop10ByOrderByIdDesc();
}
