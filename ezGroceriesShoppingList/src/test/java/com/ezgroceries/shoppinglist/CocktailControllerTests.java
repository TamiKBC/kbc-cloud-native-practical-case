package com.ezgroceries.shoppinglist;

import com.ezgroceries.shoppinglist.Feign.CocktailDBClient;
import com.ezgroceries.shoppinglist.cocktail.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class CocktailControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CocktailDBClient cocktailDBClient;

    private CocktailControler controller;

    @Autowired
    private CocktailRepository cocktailRepository;

    @BeforeEach
    public void setUp() throws Exception {
        controller = new CocktailControler(new CocktailService(cocktailDBClient, cocktailRepository));
    }

    @Test
    public void testGetCocktails(){

        setupFakeRequest("http://localhost/cocktails");
        CocktailDBResponse cocktailDBResponse = CocktailDBResponse.builder().build();
        List<CocktailDBResponse.DrinkResource> drinkResources = new ArrayList<>();
        drinkResources.add(CocktailDBResponse.DrinkResource.builder().strDrink("test1").build());
        drinkResources.add(CocktailDBResponse.DrinkResource.builder().strDrink("test2").build());
        cocktailDBResponse.setDrinks(drinkResources); ;
        ;
        when(cocktailDBClient.searchCocktails("Russian")).thenReturn(cocktailDBResponse);
        ResponseEntity<List<CocktailResource>> response = controller.getCocktails("Russian");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertEquals(2,response.getBody().size());


    }

    /**
     * Add a mocked up HttpServletRequest to Spring's internal request-context
     * holder. Normally the DispatcherServlet does this, but we must do it
     * manually to run our test.
     *
     * @param url
     *            The URL we are creating the fake request for.
     */
    private void setupFakeRequest(String url) {
        String requestURI = url.substring(16); // Drop "http://localhost"

        // We can use Spring's convenient mock implementation. Defaults to
        // localhost in the URL. Since we only need the URL, we don't need
        // to setup anything else in the request.
        MockHttpServletRequest request = new MockHttpServletRequest("POST", requestURI);

        // Puts the fake request in the current thread for the
        // ServletUriComponentsBuilder to initialize itself from later.
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }
}
