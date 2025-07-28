package repository;

import model.ServiceObject;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ServiceRepository extends MongoRepository<ServiceObject, String>{
}
