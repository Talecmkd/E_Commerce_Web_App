package SE322.web.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import SE322.service.ManufacturerService;
import SE322.model.Manufacturer;
import java.util.ArrayList;
import java.util.List;

public class getManufacturersPageTest {

    private ManufacturerService mockManufacturerService;
    private ManufacturerController controller;
    private Model model;

    @Before
    public void setUp() {
        // Mocking the ManufacturerService
        mockManufacturerService = mock(ManufacturerService.class);
        controller = new ManufacturerController(mockManufacturerService);
        model = new ConcurrentModel();
    }

    @Test
    public void testGetManufacturersPage_Positive() {
        // Creating some sample manufacturers
        List<Manufacturer> sampleManufacturers = new ArrayList<>();
        sampleManufacturers.add(new Manufacturer("Manufacturer A", "UK"));
        sampleManufacturers.add(new Manufacturer("Manufacturer B", "USA"));

        // Stubbing the findAll method to return the sample manufacturers
        when(mockManufacturerService.findAll()).thenReturn(sampleManufacturers);

        // Calling the method under test
        String viewName = controller.getManufacturersPage(model);

        // Asserting that the model contains the manufacturers attribute
        assertTrue(model.containsAttribute("manufacturers"));

        // Asserting that the view name returned is correct
        assertEquals("master-template", viewName);
    }

    @Test
    public void testGetManufacturersPage_Negative() {
        // Stubbing the findAll method to return null (simulating an empty result)
        when(mockManufacturerService.findAll()).thenReturn(null);

        // Calling the method under test
        String viewName = controller.getManufacturersPage(model);

        // Asserting that the model does not contain the manufacturers attribute
        assertFalse(model.containsAttribute("manufacturers"));

        // Asserting that the view name returned is correct
        assertNotEquals("invalid-template", viewName);
    }
}