package com.ezgroceries.shoppinglist;

import com.ezgroceries.shoppinglist.cocktail.CocktailDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
public class ShoppingListControler {
private final ShoppingListService shoppingListService;

    public ShoppingListControler(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @PostMapping(value="/shopping-lists")
    public ResponseEntity<Void> createShoppingList(@RequestBody ShoppingListNameDTO shoppingListNameDTO){
        UUID newShoppingListId = shoppingListService.createNewList(shoppingListNameDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{shoppingListId}")
                .buildAndExpand(newShoppingListId).toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping(value="/shopping-lists/{shoppingListId}/cocktails")
    public ResponseEntity<Void> addCocktailToList(@PathVariable UUID shoppingListId, @RequestBody CocktailDTO cocktailDTO){

        UUID createdShoppingListId = shoppingListService.addCocktail(shoppingListId, cocktailDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/shopping-lists/{shoppingListId}")
                .buildAndExpand(createdShoppingListId).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(value="/shopping-lists/{shoppingListId}")
    public ResponseEntity<ShoppingListDTO> getShoppingList(@PathVariable UUID shoppingListId){
        ShoppingListDTO shoppingListDTO = shoppingListService.getShoppingList(shoppingListId);
        return ResponseEntity.ok(shoppingListDTO);
    }
    @GetMapping(value="/shopping-lists")
    public ResponseEntity<List<ShoppingListDTO>> getAllShoppingList(){
        List<ShoppingListDTO> shoppingListDTOS = shoppingListService.getAllShoppingList();
        return ResponseEntity.ok(shoppingListDTOS);
    }

}
