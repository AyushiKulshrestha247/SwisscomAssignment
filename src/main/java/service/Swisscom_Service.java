package service;

import model.ServiceObject;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import repository.ServiceRepository;

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

    public ServiceObject save(ServiceObject service) {
            cache.putIfAbsent(service.getId(), service);
            return repository.save(service);
    }

    public ServiceObject update(String id, ServiceObject service) {
        cache.put(service.getId(), service);
        return repository.save(service);
    }

    public Optional<ServiceObject> get(String id) {
        return Optional.ofNullable(cache.getOrDefault(id, repository.findById(id).orElse(null)));
    }

    public void delete(String id) {
        repository.deleteById(id);
        cache.remove(id);
    }
}
