package com.ezgroceries.shoppinglist.cocktail;

import com.ezgroceries.shoppinglist.ShoppingListEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "cocktail")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CocktailEntity {
    @Id
    @GeneratedValue
    private UUID id;
    private String idDrink;
    private String name;

}
