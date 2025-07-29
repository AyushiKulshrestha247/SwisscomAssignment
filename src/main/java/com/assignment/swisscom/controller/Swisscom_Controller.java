package com.assignment.swisscom.controller;

import com.assignment.swisscom.model.ServiceObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.assignment.swisscom.service.Swisscom_Service;

@RestController
@RequestMapping("/api/service")
public class Swisscom_Controller {

    private final Swisscom_Service cache;


    public Swisscom_Controller(Swisscom_Service cache) {
        this.cache = cache;
    }

    @PostMapping
    public ResponseEntity<ServiceObject> create(@RequestBody ServiceObject service) {
        return ResponseEntity.ok(cache.save(service));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceObject> update(@PathVariable String id, @RequestBody ServiceObject service) {
        return ResponseEntity.ok(cache.update(id, service));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceObject> get(@PathVariable String id) {
        return cache.get(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        cache.delete(id);
        return ResponseEntity.noContent().build();
    }
}
