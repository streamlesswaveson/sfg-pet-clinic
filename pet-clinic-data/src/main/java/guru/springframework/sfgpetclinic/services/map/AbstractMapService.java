package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.BaseEntity;

import java.util.*;

public class AbstractMapService<T extends BaseEntity,ID extends Long> {

    Map<Long,T> data = new HashMap<>();


    public Set<T> findAll() {
        return new HashSet<>(data.values());
    }

    public T save(T obj) {
        if (Objects.nonNull(obj)) {
            if (Objects.isNull(obj.getId())) {
                obj.setId(getNextId());
            }
            data.put(obj.getId(), obj);
        } else {
            throw new RuntimeException("Save requires non-null object");
        }
        return obj;
    }

    public T findById(ID id) {
        return data.get(id);
    }

    public void delete(T obj) {
        data.entrySet().removeIf(e -> e.getValue().equals(obj));
    }

    public void deleteById(ID id) {
        data.remove(id);
    }

    private Long getNextId() {
        try {
            return Collections.max(data.keySet()) + 1l;
        }catch (NoSuchElementException e) {
            return 1l;
        }
    }
}
