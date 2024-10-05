package SE322.service.impl;

import SE322.model.Manufacturer;
import SE322.service.ManufacturerService;
import SE322.repository.jpa.ManufacturerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    //we use repositories because we want to manipulate with the data in the actual databases
    //and as previously stated the JpaRepository already has a lot of existing methods, so we don't need to
    //write our own code so much
    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        return this.manufacturerRepository.findById(id);
    }

    @Override
    public Optional<Manufacturer> save(String name, String address) {
        Manufacturer manufacturer = new Manufacturer(name, address);
        return Optional.of(this.manufacturerRepository.save(manufacturer));
    }

    @Override
    public void deleteById(Long id) {
         this.manufacturerRepository.deleteById(id);
    }

    @Override
    public boolean exists(Long id) {
        return this.manufacturerRepository.existsById(id);
    }

}
