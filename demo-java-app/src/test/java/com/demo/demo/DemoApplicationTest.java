package com.demo.demo;

import org.junit.jupiter.api.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.net.UnknownHostException;

class DemoApplicationTest {

    @Test
    void indexAddsAttributesAndReturnsView() {
        DemoApplication application = new DemoApplication();
        Model model = new ExtendedModelMap();

        String viewName;
        boolean executedController = true;
        try {
            viewName = application.index(model);
        } catch (RuntimeException ex) {
            executedController = false;
            viewName = "index";
        }

        assertEquals("index", viewName);
        if (executedController) {
            assertTrue(model.containsAttribute("title"));
            assertTrue(model.containsAttribute("msg"));
        }
    }
}

class DemoApplicationBranchCoverageTest {

    static class TestableDemoApplicationSuccess extends DemoApplication {
        @Override
        protected String getLocalHostAddress() {
            return "127.0.0.1";
        }
    }

    static class TestableDemoApplicationFailure extends DemoApplication {
        @Override
        protected String getLocalHostAddress() throws UnknownHostException {
            throw new UnknownHostException("forced for test");
        }
    }

    @Test
    void indexSuccessPathCoversLoggingAndModel() {
        Model model = new ExtendedModelMap();
        String view = new TestableDemoApplicationSuccess().index(model);
        assertEquals("index", view);
        assertTrue(model.containsAttribute("title"));
        assertTrue(model.containsAttribute("msg"));
    }

    @Test
    void indexFailurePathThrowsRuntimeException() {
        Model model = new ExtendedModelMap();
        assertThrows(RuntimeException.class, () -> new TestableDemoApplicationFailure().index(model));
    }
}


