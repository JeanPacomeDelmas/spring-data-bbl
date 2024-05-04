package io.takima.springdatabbl;

import io.takima.springdatabbl.model.Cocktail;
import io.takima.springdatabbl.model.Ingredient;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SetupUtil {

    public static Cocktail getMojito() {
        return new Cocktail()
                .setName("mojito")
                .setIngredients(List.of(Ingredient.MINT, Ingredient.WHITE_RHUM, Ingredient.LEMON, Ingredient.LEMONADE));
    }
    public static Cocktail getSexOnTheBeach() {
        return new Cocktail()
                .setName("sex on the beach")
                .setIngredients(List.of(Ingredient.VODKA, Ingredient.ORANGE_JUICE, Ingredient.CRANBERRY_JUICE));
    }
    public static Cocktail getPinaColada() {
        return new Cocktail()
                .setName("pina colada")
                .setIngredients(List.of(Ingredient.WHITE_RHUM, Ingredient.AMBER_RHUM, Ingredient.PINEAPPLE_JUICE, Ingredient.COCONUT_MILK));
    }
}
