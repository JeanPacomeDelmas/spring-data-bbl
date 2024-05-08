package io.takima.springdatabbl.model;

import org.hibernate.annotations.BatchSize;

@BatchSize(size = 2)
public enum Ingredient {
    MINT,
    LEMONADE,
    LEMON,
    WHITE_RHUM,
    AMBER_RHUM,
    VODKA,
    ORANGE_JUICE,
    CRANBERRY_JUICE,
    PINEAPPLE_JUICE,
    COCONUT_MILK,
    LEMON_JUICE,
    SUGAR
}
