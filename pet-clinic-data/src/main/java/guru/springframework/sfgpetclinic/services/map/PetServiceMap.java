package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.services.CrudService;

public class PetServiceMap extends AbstractMapService<Pet,Long> implements CrudService<Pet,Long>{
    @Override
    public Pet save(Pet obj) {
        return super.save(obj, obj.getId());
    }
}
