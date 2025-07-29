package com.assignment.swisscom.repository;

import com.assignment.swisscom.model.ServiceObject;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ServiceRepository extends MongoRepository<ServiceObject, String>{
}
