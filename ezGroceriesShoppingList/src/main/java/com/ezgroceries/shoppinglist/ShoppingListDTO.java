package com.ezgroceries.shoppinglist;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class ShoppingListDTO {
    private UUID shoppingListId;
    private String name;
    private List<String> ingredients;
}
