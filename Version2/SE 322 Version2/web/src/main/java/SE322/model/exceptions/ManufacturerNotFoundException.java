package SE322.model.exceptions;

public class ManufacturerNotFoundException extends RuntimeException {

    public ManufacturerNotFoundException(Long manufacturerId) {
        super(String.format("Manufacturer with id %d does not exist.", manufacturerId));
    } // throwing this error when we can't find a manufacturer
}
