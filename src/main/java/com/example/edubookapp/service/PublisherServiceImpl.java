package com.example.edubookapp.service;

import com.example.edubookapp.model.Publisher;
import com.example.edubookapp.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PublisherServiceImpl implements PublisherService {
    @Autowired
    private PublisherRepository publisherRepository;
    @Override
    @Transactional
    public Publisher findByName(String name) {
        return publisherRepository.findByName(name);
    }

    @Override
    @Transactional
    public Publisher save(Publisher publisher) {
        return publisherRepository.save(publisher);
    }
}
