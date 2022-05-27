package de.dhbw.nerdlegame.test_utils;

import java.util.function.Supplier;

class ClassContainer<E> {

    private final Supplier<E> supplier;

    ClassContainer(Supplier<E> supplier) {
        this.supplier = supplier;
    }

    E createContents() {
        return supplier.get();
    }

}
