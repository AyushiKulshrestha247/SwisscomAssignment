package com.assignment.swisscom.service;

import com.assignment.swisscom.model.ServiceObject;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import com.assignment.swisscom.repository.ServiceRepository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Profile("dev")
public class Swisscom_Service {

    private final Map<String, ServiceObject> cache = new ConcurrentHashMap<>();
    private final ServiceRepository repository;

    public Swisscom_Service(ServiceRepository repository) {
        this.repository = repository;
    }

    @CachePut(value = "services", key = "#service.id") // Automatically updates the cache
    public ServiceObject save(ServiceObject service) {
        return repository.save(service); // Save to DB first
    }

    @CachePut(value = "services", key = "#service.id") // Cache is updated after DB update
    public ServiceObject update(String id, ServiceObject service) {
        service.setId(id); // Ensure the service ID is set before saving
        return repository.save(service); // Save updated object in DB
    }


    @Cacheable(value = "services", key = "#id")
    public Optional<ServiceObject> get(String id) {
        return repository.findById(id); // Fallback to DB if not found in cache
    }

    @CacheEvict(value = "services", key = "#id") // Automatically remove the cache entry when deleted
    public void delete(String id) {
        repository.deleteById(id); // Delete from repository
    }
}
