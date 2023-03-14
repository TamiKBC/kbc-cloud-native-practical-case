package com.ezgroceries.shoppinglist;

import com.ezgroceries.shoppinglist.cocktail.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class ShoppingListServiceTest {

    private ShoppingListService service;

    @MockBean
    private ShoppingListRepository shoppingListRepository;
    @MockBean
    private CocktailRepository cocktailRepository;
    @MockBean
    private CocktailService cocktailService;

    @BeforeEach
    void setUp() {
        service = new ShoppingListService(shoppingListRepository,cocktailRepository,cocktailService);
    }

    @Test
    void createNewList() {
        UUID id = UUID.randomUUID();
        ShoppingListNameDTO shoppingListNameDTO = ShoppingListNameDTO.builder().name("test").build();
        when(shoppingListRepository.save(any())).thenReturn(ShoppingListEntity.builder().id(id).build());
        UUID response = service.createNewList(shoppingListNameDTO);
        assertEquals(id,response);
    }

    @Test
    @Disabled
    void addCocktail() {
        /*
        UUID shoppingListId= UUID.randomUUID();
        CocktailDTO cocktailDTO= CocktailDTO.builder().build();
        when(shoppingListRepository.findById(any())).thenReturn();
        when(cocktailRepository.findById(any())).thenReturn();
        */
    }

    @Test
    void getShoppingList() {
        UUID shoppingListId = UUID.randomUUID();
        List<String> ingredientList = Arrays.asList("ingr1", "ingr2");
        Set<CocktailEntity> cocktailEntities = new HashSet<>();
        cocktailEntities.add(CocktailEntity.builder().name("testCocktail").build());
        List<CocktailResource> cocktails = new ArrayList<>();
        cocktails.add(CocktailResource.builder().ingredients(new HashSet<>(Arrays.asList("ingr1", "ingr2"))).build());
        ShoppingListEntity shoppingListEntity = ShoppingListEntity.builder()
                .id(shoppingListId)
                .name("test")
                .cocktails(cocktailEntities).build();
        when(shoppingListRepository.findById(shoppingListId)).thenReturn(java.util.Optional.ofNullable(shoppingListEntity));
        when(cocktailService.getCocktails(any())).thenReturn(cocktails);
        ShoppingListDTO shoppingListDTO= service.getShoppingList(shoppingListId);
        assertEquals(shoppingListId,shoppingListDTO.getShoppingListId());
        assertEquals("test",shoppingListDTO.getName());
        assertEquals(ingredientList.size(),shoppingListDTO.getIngredients().size());
        assertTrue(ingredientList.containsAll(shoppingListDTO.getIngredients())&&shoppingListDTO.getIngredients().containsAll(ingredientList));
    }

    @Test
    void getAllShoppingList() {
    }
}