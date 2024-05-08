package io.takima.springdatabbl;

import io.takima.springdatabbl.model.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestSetupUtil {

    public static final String VALENTIN = "Valentin";
    public static final String JP = "JP";

    public static final String MOJITO = "mojito";
    public static final String SEX_ON_THE_BEACH = "sex on the beach";
    public static final String DAIQUIRI = "daiquiri";
    public static final String PINA_COLADA = "pina colada";

    public static Barman getValentin() {
        return new Barman().setName(VALENTIN);
    }

    public static Barman getJp() {
        return new Barman().setName(JP);
    }

    public static Cocktail getMojito() {
        return new Cocktail()
                .setName(MOJITO)
                .setIngredients(List.of(Ingredient.MINT, Ingredient.WHITE_RHUM, Ingredient.LEMON, Ingredient.LEMONADE));
    }

    public static Cocktail getSexOnTheBeach() {
        return new Cocktail()
                .setName(SEX_ON_THE_BEACH)
                .setIngredients(List.of(Ingredient.VODKA, Ingredient.ORANGE_JUICE, Ingredient.CRANBERRY_JUICE));
    }

    public static Cocktail getDaiquiri() {
        return new Cocktail()
                .setName(DAIQUIRI)
                .setIngredients(List.of(Ingredient.WHITE_RHUM, Ingredient.LEMON_JUICE, Ingredient.SUGAR));
    }

    public static Cocktail getPinaColada() {
        return new Cocktail()
                .setName(PINA_COLADA)
                .setIngredients(List.of(Ingredient.WHITE_RHUM, Ingredient.AMBER_RHUM, Ingredient.PINEAPPLE_JUICE, Ingredient.COCONUT_MILK));
    }
}
