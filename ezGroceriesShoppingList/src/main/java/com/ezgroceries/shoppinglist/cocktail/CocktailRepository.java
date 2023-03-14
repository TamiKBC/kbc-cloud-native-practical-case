package com.ezgroceries.shoppinglist.cocktail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface CocktailRepository extends JpaRepository<CocktailEntity, UUID> {
    List<CocktailEntity> findByIdDrinkIn(List<String> ids);

    List<CocktailEntity> findByNameContainingIgnoreCase(String search);
}
