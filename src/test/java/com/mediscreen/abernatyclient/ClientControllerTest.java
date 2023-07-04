package com.mediscreen.abernatyclient;

import com.mediscreen.abernatyclient.controller.ClientController;
import com.mediscreen.abernatyclient.proxies.MicroservicePatientsProxy;
import com.mediscreen.abernatyclient.proxies.MicroservicePractitionersProxy;
import com.mediscreen.abernatyclient.proxies.MicroserviceRiskProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource("/application-test.properties")
public class ClientControllerTest {
    @Mock
    private MicroservicePatientsProxy patientsProxy;
    @Mock
    private MicroservicePractitionersProxy practitionersProxy;
    @Mock
    private MicroserviceRiskProxy riskProxy;

    ClientController clientController;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        clientController = new ClientController(patientsProxy, practitionersProxy, riskProxy);
    }

    @Test
    public void updateNoteTest(){
        clientController.updateNote()
    }



}
