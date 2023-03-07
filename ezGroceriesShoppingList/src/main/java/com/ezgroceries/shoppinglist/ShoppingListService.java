package com.ezgroceries.shoppinglist;

import com.ezgroceries.shoppinglist.cocktail.CocktailDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class ShoppingListService {

private final ShoppingListRepository shoppingListRepository;

    public ShoppingListService(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    public UUID createNewList(ShoppingListNameDTO shoppingListNameDTO) {
        ShoppingListEntity shoppingListEntity = ShoppingListEntity.builder()
                .name(shoppingListNameDTO.getName())
                .build();
        ShoppingListEntity shoppingListEntityNew = shoppingListRepository.save(shoppingListEntity);
        UUID shoppingListId= shoppingListEntityNew.getId();
         return shoppingListId;
    }

    public UUID addIngredientsFromCocktail(UUID shoppingListId, CocktailDTO cocktailDTO) {
        return shoppingListId;
    }

    public ShoppingListDTO getShoppingList(UUID shoppingListId) {
        return ShoppingListDTO.builder()
                .shoppingListId(shoppingListId)
                .name("Stephanie's birthday")
                .ingredients(Arrays.asList("Tequila",
                        "Triple sec",
                        "Lime juice",
                        "Salt",
                        "Blue Curacao"))
                .build();
    }

    public List<ShoppingListDTO> getAllShoppingList() {
        List<ShoppingListDTO> shoppingListDTOS =  new ArrayList<>();
        shoppingListDTOS.add(ShoppingListDTO.builder()
                .shoppingListId(UUID.fromString("4ba92a46-1d1b-4e52-8e38-13cd56c7224c"))
                .name("Stephanie's birthday")
                .ingredients(Arrays.asList("Tequila",
                        "Triple sec",
                        "Lime juice",
                        "Salt",
                        "Blue Curacao"))
                .build());
        shoppingListDTOS.add(ShoppingListDTO.builder()
                .shoppingListId(UUID.fromString("6c7d09c2-8a25-4d54-a979-25ae779d2465"))
                .name("My Birthday")
                .ingredients(Arrays.asList("Tequila",
                        "Triple sec",
                        "Lime juice",
                        "Salt",
                        "Blue Curacao"))
                .build());
        return shoppingListDTOS;
    }
}
