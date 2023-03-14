package com.ezgroceries.shoppinglist.cocktail;

import com.ezgroceries.shoppinglist.ShoppingListEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CocktailResource {

    @Id
    @GeneratedValue

    private UUID id;
    private String name;
    private String strGlass;
    private String  strInstructions;
    private String strDrinkThumb;
    @Convert(converter = StringSetConverter.class)
    private Set<String> ingredients;


}
