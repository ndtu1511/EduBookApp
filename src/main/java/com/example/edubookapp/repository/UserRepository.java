package com.example.edubookapp.repository;

import com.example.edubookapp.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Integer> {
    User findByUsername(String username);
    List<User> findByUsernameContaining(String s);

    @Query("from User u left join fetch u.roles where u.email = ?1")
    User findByEmail(String email);

}
