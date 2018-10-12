package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

    OwnerServiceMap ownerServiceMap;

    final Long ownerId = 1l;
    final String ownerlastName = "Smith";

    @BeforeEach
    public void before() {

        ownerServiceMap = new OwnerServiceMap(new PetServiceMap(), new PetTypeServiceMap());

        ownerServiceMap.save(Owner.builder().id(ownerId).lastName(ownerlastName).build());

    }

    @Test
    public void shouldFindAll() {
        Set<Owner> all = ownerServiceMap.findAll();
        assertEquals(1, all.size());

    }

    @Test
    public void shouldFindOne() {
        Owner byId = ownerServiceMap.findById(ownerId);
        assertNotNull(byId);
        assertEquals(byId.getId(), ownerId);
    }

    @Test
    public void shouldSaveNoId() {
        Owner saved = ownerServiceMap.save(Owner.builder().build());
        assertNotNull(saved);
        assertNotNull(saved.getId());
    }

    @Test
    public void shouldDelete() {
        ownerServiceMap.delete(ownerServiceMap.findById(ownerId));
        assertEquals(0, ownerServiceMap.findAll().size());
    }
    @Test
    void findByLastName() {
        Owner smith = ownerServiceMap.findByLastName(ownerlastName);
        assertNotNull(smith);
        assertEquals(ownerlastName, smith.getLastName());
    }

    @Test
    void findByLastNameNotFound() {
        Owner cooper= ownerServiceMap.findByLastName("Cooper");
        assertNull(cooper);
    }
}