package com.openclassrooms.etudiant.configuration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppConfigTest {

    @Test
    void testAppConfigLoads() {
        AppConfig config = new AppConfig();
        assertNotNull(config);
    }
}
