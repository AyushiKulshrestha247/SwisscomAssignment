package model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("service")
public class ServiceObject {

    @Id
    private String id;
    private List<Resource> resources;
}
