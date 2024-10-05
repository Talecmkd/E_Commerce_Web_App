package SE322.repository.jpa;

import SE322.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
//Every Repository that extends the JpaRepository has embedded methods in itself
public interface ManufacturerRepository extends JpaRepository<Manufacturer,Long> {
}
