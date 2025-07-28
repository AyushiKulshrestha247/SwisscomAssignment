import model.Owner;
import model.Resource;
import model.ServiceObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.ServiceRepository;
import service.Swisscom_Service;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SwisscomServiceTest {

    private ServiceRepository repository;
    private Swisscom_Service service;
    private ServiceObject serviceObject;

    @BeforeEach
    public void setUp(){
        repository = mock(ServiceRepository.class);
        service = new Swisscom_Service(repository);

        Owner owner1 = new Owner();
        owner1.setId("owner_id_1_1");
        owner1.setName("Owner 1");
        owner1.setAccountNumber("AC001");
        owner1.setLevel(1);

        Owner owner2 = new Owner();
        owner2.setId("owner_id_1_2");
        owner2.setName("Owner 2");
        owner2.setAccountNumber("AC002");
        owner2.setLevel(2);

        Resource resource = new Resource();
        resource.setId("resource_id_1");
        resource.setOwners(Arrays.asList(owner1, owner2));

        serviceObject = new ServiceObject();
        serviceObject.setId("service_id_1");
        serviceObject.setResources(Arrays.asList(resource));
    }

    @Test
    public void testSaveNewService() {
        when(repository.save(serviceObject)).thenReturn(serviceObject);

        ServiceObject saved = service.save(serviceObject);

        assertEquals("service_id_1", saved.getId());
        verify(repository, times(1)).save(serviceObject);
    }

    @Test
    public void testUpdateService() {
        when(repository.save(serviceObject)).thenReturn(serviceObject);
        String id = "1";

        ServiceObject updated = service.update(id, serviceObject);

        assertEquals("service_id_1", updated.getId());
        verify(repository, times(1)).save(serviceObject);
    }

    @Test
    public void testGetFromCache() {
        service.save(serviceObject); // Populate cache

        Optional<ServiceObject> result = service.get("service_id_1");

        assertTrue(result.isPresent());
        assertEquals("service_id_1", result.get().getId());
    }

    @Test
    public void testGetFromRepositoryWhenNotInCache() {
        when(repository.findById("service_id_1")).thenReturn(Optional.of(serviceObject));

        Optional<ServiceObject> result = service.get("service_id_1");

        assertTrue(result.isPresent());
        assertEquals("service_id_1", result.get().getId());
        verify(repository, times(1)).findById("service_id_1");
    }

    @Test
    public void testDeleteService() {
        service.save(serviceObject);
        doNothing().when(repository).deleteById("service_id_1");

        service.delete("service_id_1");

        Optional<ServiceObject> result = service.get("service_id_1");
        assertFalse(result.isPresent());
        verify(repository, times(1)).deleteById("service_id_1");
    }

}
