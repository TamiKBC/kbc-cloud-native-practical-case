package com.ezgroceries.shoppinglist.cocktail;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CocktailDBResponse {
    private List<DrinkResource> drinks;

    public List<DrinkResource> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<DrinkResource> drinks) {
        this.drinks = drinks;
    }

    @Builder
    public static class DrinkResource {
        public String idDrink;
        public String strDrink;
        public Object strDrinkAlternate;
        public String strTags;
        public Object strVideo;
        public String strCategory;
        public String strIBA;
        public String strAlcoholic;
        public String strGlass;
        public String strInstructions;
        public Object strInstructionsES;
        public String strInstructionsDE;
        public Object strInstructionsFR;
        public String strInstructionsIT;
        public String strDrinkThumb;
        public String strIngredient1;
        public String strIngredient2;
        public String strIngredient3;
        public String strIngredient4;
        public String strIngredient5;
        public String strIngredient6;
        public String strIngredient7;
        public String strIngredient8;
        public String strIngredient9;
        public String strIngredient10;
        public String strIngredient11;
        public String strIngredient12;
        public String strIngredient13;
        public String strIngredient14;
        public String strIngredient15;
        public String strMeasure1;
        public String strMeasure2;
        public String strMeasure3;
        public Object strMeasure4;
        public Object strMeasure5;
        public Object strMeasure6;
        public Object strMeasure7;
        public Object strMeasure8;
        public Object strMeasure9;
        public Object strMeasure10;
        public Object strMeasure11;
        public Object strMeasure12;
        public Object strMeasure13;
        public Object strMeasure14;
        public Object strMeasure15;
        public String strImageSource;
        public String strImageAttribution;
        public String strCreativeCommonsConfirmed;
        public String dateModified;
    }
}
