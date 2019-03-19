package com.example.edubookapp.service;

import com.example.edubookapp.model.Publisher;

public interface PublisherService {
    Publisher findByName(String name);
    Publisher save(Publisher publisher);
}
