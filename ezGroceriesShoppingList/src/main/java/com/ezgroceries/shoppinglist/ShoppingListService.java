package com.ezgroceries.shoppinglist;

import com.ezgroceries.shoppinglist.cocktail.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShoppingListService {

private final ShoppingListRepository shoppingListRepository;
    private final CocktailRepository cocktailRepository;
    private final CocktailService cocktailService;

    public ShoppingListService(ShoppingListRepository shoppingListRepository, CocktailRepository cocktailRepository, CocktailService cocktailService) {
        this.shoppingListRepository = shoppingListRepository;
        this.cocktailRepository = cocktailRepository;
        this.cocktailService = cocktailService;
    }

    public UUID createNewList(ShoppingListNameDTO shoppingListNameDTO) {
        ShoppingListEntity shoppingListEntity = ShoppingListEntity.builder()
                .name(shoppingListNameDTO.getName())
                .build();
        ShoppingListEntity shoppingListEntityNew = shoppingListRepository.save(shoppingListEntity);
        UUID shoppingListId= shoppingListEntityNew.getId();
         return shoppingListId;
    }

    public UUID addCocktail(UUID shoppingListId, CocktailDTO cocktailDTO) {
        Optional<ShoppingListEntity> shoppingListEntityOptional = shoppingListRepository.findById(shoppingListId);
        if(shoppingListEntityOptional.isPresent()){
            ShoppingListEntity shoppingList = shoppingListEntityOptional.get();
            cocktailRepository.findById(cocktailDTO.getCocktailId());
            if(cocktailRepository.findById(cocktailDTO.getCocktailId()).isPresent()) {

                Set<CocktailEntity> cocktailList = shoppingList.getCocktails();
                cocktailList.add(cocktailRepository.findById(cocktailDTO.getCocktailId()).get());
                shoppingList.setCocktails(cocktailList);
                shoppingListRepository.save(shoppingList);
            }

        }
        return shoppingListId;
    }

    public ShoppingListDTO getShoppingList(UUID shoppingListId) {
        ShoppingListDTO shoppingListDTO = ShoppingListDTO.builder()
                .build();
        Optional<ShoppingListEntity> shoppingListOptional = shoppingListRepository.findById(shoppingListId);
        if(shoppingListOptional.isPresent()){
            ShoppingListEntity shoppingList = shoppingListOptional.get();
            Set<String> ingredients = new HashSet<>();
            Set<CocktailEntity> cocktailList = shoppingList.getCocktails();
            for(CocktailEntity cocktailEntity : cocktailList){
                List<CocktailResource> cocktails = cocktailService.getCocktails(cocktailEntity.getName());
                for(CocktailResource cocktailResource : cocktails){
                    Set<String> cocktailIngredients = cocktailResource.getIngredients();
                    ingredients.addAll(cocktailIngredients);
                }
            }
            shoppingListDTO.setName(shoppingList.getName());
            shoppingListDTO.setShoppingListId(shoppingListId);
            shoppingListDTO.setIngredients(new ArrayList<>(ingredients));
        }

        return shoppingListDTO;
    }

    public List<ShoppingListDTO> getAllShoppingList() {
        List<ShoppingListDTO> shoppingListDTOS =  new ArrayList<>();
        List<ShoppingListEntity> allShoppingLists = shoppingListRepository.findAll();
        for (ShoppingListEntity shoppingList : allShoppingLists) {
            shoppingListDTOS.add(getShoppingList(shoppingList.getId()));
        }
        return shoppingListDTOS;
    }
}
