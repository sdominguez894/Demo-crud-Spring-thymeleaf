package com.demo.baseproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc
@SpringBootTest
class BaseProjectApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    void listPageShowsDemoRegisters() throws Exception {
        mockMvc.perform(get("/records"))
                .andExpect(status().isOk())
                .andExpect(view().name("layout"))
                .andExpect(content().string(containsString("<head>")))
                .andExpect(content().string(containsString("<link rel=\"stylesheet\" href=\"/css/bulma.min.css\">")))
                .andExpect(content().string(containsString("<link rel=\"stylesheet\" href=\"/css/styles.css?v=grid-pagination\">")))
                .andExpect(content().string(containsString("<script src=\"/js/htmx.min.js\" defer></script>")))
                .andExpect(content().string(containsString("data-m-grid-details-toggle-all")))
                .andExpect(content().string(containsString("title=\"Expand all row details\"")))
                .andExpect(content().string(containsString("name=\"pageSize\"")))
                .andExpect(content().string(containsString("Registers per page")))
                .andExpect(content().string(containsString(">100</option>")))
                .andExpect(content().string(containsString("Export PDF")))
                .andExpect(content().string(containsString("Export Excel")))
                .andExpect(content().string(containsString("aria-label=\"Pagination\"")))
                .andExpect(content().string(containsString("pagination-previous is-disabled")))
                .andExpect(content().string(containsString("pagination-next")))
                .andExpect(content().string(containsString("href=\"/records?search=&amp;page=2&amp;pageSize=10\"")))
                .andExpect(content().string(containsString("pagination-link is-current")))
                .andExpect(content().string(containsString(">3</a>")))
                .andExpect(content().string(not(containsString("data-m-grid-details-open-all"))))
                .andExpect(content().string(not(containsString("data-m-grid-details-close-all"))))
                .andExpect(content().string(not(containsString(">Expand all<"))))
                .andExpect(content().string(not(containsString(">Collapse all<"))))
                .andExpect(content().string(containsString("Alpha Services")))
                .andExpect(content().string(containsString("Creation date")));
    }

    @Test
    void secondPageShowsAdditionalDemoRegisters() throws Exception {
        mockMvc.perform(get("/records").param("page", "2").param("pageSize", "10"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Registers 11 to 20 of 25")))
                .andExpect(content().string(containsString("Keystone Medical")))
                .andExpect(content().string(containsString("href=\"/records?search=&amp;page=1&amp;pageSize=10\"")))
                .andExpect(content().string(containsString("href=\"/records?search=&amp;page=3&amp;pageSize=10\"")));
    }

    @Test
    void searchFiltersRegistersByName() throws Exception {
        mockMvc.perform(get("/records").param("search", "finance"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("search", "finance"))
                .andExpect(content().string(containsString("Central Finance")));
    }

    @Test
    void pageSizeSelectPreservesRequestedSize() throws Exception {
        mockMvc.perform(get("/records").param("pageSize", "30"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<option value=\"30\"")))
                .andExpect(content().string(containsString("selected=\"selected\">30</option>")));
    }

    @Test
    void pageSizeSelectRoundsTooSmallValuesUpToTen() throws Exception {
        mockMvc.perform(get("/records").param("pageSize", "7"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<option value=\"10\"")))
                .andExpect(content().string(containsString("selected=\"selected\">10</option>")))
                .andExpect(content().string(containsString("<option value=\"100\">100</option>")));
    }

    @Test
    void createPageShowsRegisterForm() throws Exception {
        mockMvc.perform(get("/records/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("layout"))
                .andExpect(content().string(containsString("Add register")))
                .andExpect(content().string(containsString("Creation date")));
    }

    @Test
    void htmxListRequestReturnsPageContentFragment() throws Exception {
        mockMvc.perform(get("/records").header("HX-Request", "true"))
                .andExpect(status().isOk())
                .andExpect(view().name("pages/list :: pageContent"))
                .andExpect(content().string(containsString("id=\"page-content\"")))
                .andExpect(content().string(containsString("Alpha Services")));
    }

    @Test
    void htmxCreateRequestReturnsPageContentFragment() throws Exception {
        mockMvc.perform(get("/records/new").header("HX-Request", "true"))
                .andExpect(status().isOk())
                .andExpect(view().name("pages/form :: pageContent"))
                .andExpect(content().string(containsString("id=\"page-content\"")))
                .andExpect(content().string(containsString("Add register")));
    }
}
