package io.takima.springdatabbl.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Ingredient {
    MINT(Unit.CL),
    LEMONADE(Unit.CL),
    LEMON(Unit.QUARTER),
    WHITE_RHUM(Unit.CL),
    AMBER_RHUM(Unit.CL),
    VODKA(Unit.CL),
    ORANGE_JUICE(Unit.CL),
    CRANBERRY_JUICE(Unit.CL),
    PINEAPPLE_JUICE(Unit.CL),
    COCONUT_MILK(Unit.CL),
    ;

    private final Unit unit;
}
