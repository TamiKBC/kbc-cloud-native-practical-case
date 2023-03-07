package com.ezgroceries.shoppinglist.cocktail;

import com.ezgroceries.shoppinglist.Feign.CocktailDBClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class CocktailService {
    private CocktailDBClient cocktailDBClient;

    public CocktailService(CocktailDBClient cocktailDBClient) {
        this.cocktailDBClient = cocktailDBClient;
    }

    public List<CocktailDTO> getCocktails(String search) {
        List<CocktailDTO> cocktailDTOList = new ArrayList<>();
        CocktailDBResponse cocktailDBResponse = cocktailDBClient.searchCocktails(search);
        cocktailDTOList = mapResponseToDTO(cocktailDBResponse);
        return cocktailDTOList;
    }

    private List<CocktailDTO> mapResponseToDTO(CocktailDBResponse cocktailDBResponse) {
        List<CocktailDTO> cocktailDTOList = new ArrayList<>();
        for(CocktailDBResponse.DrinkResource drinkResource: cocktailDBResponse.getDrinks() ){
            List<String> ingredients= new ArrayList<>();
            if(drinkResource.strIngredient1 !=null) ingredients.add(drinkResource.strIngredient1);
            if(drinkResource.strIngredient2 !=null) ingredients.add(drinkResource.strIngredient2);
            if(drinkResource.strIngredient3 !=null) ingredients.add(drinkResource.strIngredient3);
            if(drinkResource.strIngredient4 !=null) ingredients.add(drinkResource.strIngredient4);
            if(drinkResource.strIngredient5 !=null) ingredients.add(drinkResource.strIngredient5);
            if(drinkResource.strIngredient6 !=null) ingredients.add(drinkResource.strIngredient6);
            if(drinkResource.strIngredient7 !=null) ingredients.add(drinkResource.strIngredient7);
            if(drinkResource.strIngredient8 !=null) ingredients.add(drinkResource.strIngredient8);
            if(drinkResource.strIngredient9 !=null) ingredients.add(drinkResource.strIngredient9);
            if(drinkResource.strIngredient10 !=null) ingredients.add(drinkResource.strIngredient10);
            if(drinkResource.strIngredient11 !=null) ingredients.add(drinkResource.strIngredient11);
            if(drinkResource.strIngredient12 !=null) ingredients.add(drinkResource.strIngredient12);
            if(drinkResource.strIngredient13 !=null) ingredients.add(drinkResource.strIngredient13);
            if(drinkResource.strIngredient14 !=null) ingredients.add(drinkResource.strIngredient14);
            if(drinkResource.strIngredient15 !=null) ingredients.add(drinkResource.strIngredient15);
            cocktailDTOList.add(CocktailDTO.builder()
                    .cocktailId(UUID.randomUUID())
                    .name(drinkResource.strDrink)
                    .glass(drinkResource.strGlass)
                    .instructions(drinkResource.strInstructions)
                    .image(drinkResource.strImageSource)
                    .ingredients(ingredients)
                    .build());
        }
        return cocktailDTOList;
    }
}
