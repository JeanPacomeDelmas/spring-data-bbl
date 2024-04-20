package io.takima.springdatabbl.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Ingredient {
    MINT(Unit.CL),
    LEMONADE(Unit.CL),
    LEMON(Unit.QUARTER),
    WHITE_RHUM(Unit.CL),
    ;

    private final Unit unit;
}
