package com.ezgroceries.shoppinglist;

import com.ezgroceries.shoppinglist.Feign.CocktailDBClient;
import com.ezgroceries.shoppinglist.cocktail.CocktailDTO;
import com.ezgroceries.shoppinglist.cocktail.CocktailRepository;
import com.ezgroceries.shoppinglist.cocktail.CocktailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class ShoppingListControllerTests {
    private ShoppingListControler controller;


    @MockBean
    private ShoppingListService shoppingListService;

    @BeforeEach
    public void setUp() throws Exception {
        controller = new ShoppingListControler(shoppingListService);
    }

    @Test
    public void testCreateShoppingList(){
        setupFakeRequest("http://localhost/shopping-lists");
        ShoppingListNameDTO shoppingListName = ShoppingListNameDTO.builder().name("Stephanie's birthday").build();
        when(shoppingListService.createNewList(shoppingListName)).thenReturn(UUID.fromString("90689338-499a-4c49-af90-f1e73068ad4f"));
        ResponseEntity<Void> response = controller.createShoppingList(shoppingListName);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertEquals("http://localhost/shopping-lists/90689338-499a-4c49-af90-f1e73068ad4f",response.getHeaders().getLocation().toString());
    }

    @Test
    public void addCocktailToShoppingList(){
        UUID shoppingList = UUID.fromString("90689338-499a-4c49-af90-f1e73068ad4f");
        CocktailDTO cocktailDTO =CocktailDTO.builder().cocktailId(UUID.fromString("23b3d85a-3928-41c0-a533-6538a71e17c4")).build();
        setupFakeRequest("http://localhost/shopping-lists/"+shoppingList+"/cocktails");
        when(shoppingListService.addCocktail(shoppingList,cocktailDTO)).thenReturn(shoppingList);
        ResponseEntity<Void> response = controller.addCocktailToList(shoppingList,cocktailDTO);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertEquals("http://localhost/shopping-lists/"+shoppingList,response.getHeaders().getLocation().toString());
    }
    @Test
    public void getShoppingList(){
        UUID shoppingList = UUID.fromString("90689338-499a-4c49-af90-f1e73068ad4f");
        when(shoppingListService.getShoppingList(shoppingList)).thenReturn(ShoppingListDTO.builder().shoppingListId(shoppingList).name("Stephanie's birthday").build());
        setupFakeRequest("http://localhost/shopping-lists/"+shoppingList);
        ResponseEntity<ShoppingListDTO> response = controller.getShoppingList(shoppingList);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertEquals(shoppingList,response.getBody().getShoppingListId());
        assertEquals("Stephanie's birthday",response.getBody().getName());
    }

    @Test
    public void getAllShoppingList(){
        setupFakeRequest("http://localhost/shopping-lists");
        List<ShoppingListDTO> responseDTO = new ArrayList<>();
        responseDTO.add(ShoppingListDTO.builder().build());
        responseDTO.add(ShoppingListDTO.builder().build());
        when(shoppingListService.getAllShoppingList()).thenReturn(responseDTO );
        ResponseEntity<List<ShoppingListDTO>> response = controller.getAllShoppingList();
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
