package guru.springframework.sfgpetclinic.services.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AbstractMapService<T,ID> {

    Map<ID,T> data = new HashMap<>();


    public Set<T> findAll() {
        return new HashSet<>(data.values());
    }

    public T save(T obj, ID id) {
        data.put(id, obj);
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
}
