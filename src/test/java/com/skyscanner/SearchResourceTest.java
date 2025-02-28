package com.skyscanner;

import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@org.junit.jupiter.api.extension.ExtendWith(DropwizardExtensionsSupport.class)
public class SearchResourceTest {

    private static final SearchResource SEARCH_RESOURCE = new SearchResource();

    private static final ResourceExtension RESOURCES = ResourceExtension.builder()
            .addResource(SEARCH_RESOURCE)
            .build();

    @BeforeEach
    void setUp() {
      
    }

    @Test
    void testSearchHotelAndRentalCar() {

        Search searchRequest = new Search("petalborough");

        
        Response response = RESOURCES.target("/search")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(searchRequest, MediaType.APPLICATION_JSON));

        assertNotNull(response);
        assertEquals(200, response.getStatus());

        List<SearchResult> results = response.readEntity(List.class);
        assertNotNull(results);
        assertEquals(2, results.size());

  
    }
}
