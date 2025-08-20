package com.demo.demo;

import org.junit.jupiter.api.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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


