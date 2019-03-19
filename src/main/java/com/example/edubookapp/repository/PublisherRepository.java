package com.example.edubookapp.repository;

import com.example.edubookapp.model.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher,Integer> {
    Publisher findByName(String name);
}
