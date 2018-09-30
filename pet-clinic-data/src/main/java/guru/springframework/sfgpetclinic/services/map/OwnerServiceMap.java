package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.CrudService;
import guru.springframework.sfgpetclinic.services.map.AbstractMapService;

public class OwnerServiceMap extends AbstractMapService<Owner,Long> implements CrudService<Owner, Long> {
    @Override
    public Owner save(Owner obj) {
        return super.save(obj, obj.getId());
    }
}
