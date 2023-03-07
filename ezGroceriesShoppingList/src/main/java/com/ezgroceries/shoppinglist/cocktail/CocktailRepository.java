package com.ezgroceries.shoppinglist.cocktail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CocktailRepository extends JpaRepository<CocktailEntity, UUID> {
}
