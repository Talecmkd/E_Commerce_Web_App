package SE322.service;

import SE322.model.Manufacturer;

import java.util.List;
import java.util.Optional;
//we use service interfaces to declare the methods we want done in the service implementations

public interface ManufacturerService {
    List<Manufacturer> findAll();

    Optional<Manufacturer> findById(Long id);

    Optional<Manufacturer> save(String name, String address);

    void deleteById(Long id);

    boolean exists(Long id);

}