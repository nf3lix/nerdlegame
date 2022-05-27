package de.dhbw.nerdlegame.test_utils;

import de.dhbw.nerdlegame.test_utils.ValueBehavior.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class ValueObjectBehaviorTest<T> {

    private final List<ClassContainer<T>> containers;
    private final List<ValueBehavior<T>> tests;

    public static <T> void withDisjointList(final List<Supplier<T>> objects) {
        final ValueObjectBehaviorTest<T> test = new Builder<T>()
                .setDisjointList(objects)
                .addTest(new ValueEquality<>())
                .addTest(new ValueInequality<>())
                .addTest(new InstanceEquality<>())
                .addTest(new HashcodeEquality<>())
                .addTest(new HashcodeInequality<>()).build();
        test.performTests();
    }

    private ValueObjectBehaviorTest(final List<ClassContainer<T>> containers, final List<ValueBehavior<T>> tests) {
        this.containers = containers;
        this.tests = tests;
    }

    public void performTests() {
        for(final ValueBehavior<T> test : tests) {
            test.performTest(containers);
        }
    }

    public static class Builder<T> {
        private final List<ClassContainer<T>> containers = new ArrayList<>();
        private final List<ValueBehavior<T>> tests = new ArrayList<>();

        public CreatableValueObjectBehaviorTest setDisjointList(final List<Supplier<T>> objects) {
            for(Supplier<T> supplier : objects) {
                containers.add(new ClassContainer<>(supplier));
            }
            expectDisjointList();
            expectMinSize();
            return new CreatableValueObjectBehaviorTest();
        }

        private void expectDisjointList() {
            final Set<T> set = new HashSet<>();
            for(ClassContainer<T> container : containers) {
                set.add(container.createContents());
            }
            if(set.size() != containers.size()) {
                throw new IllegalStateException("Supplied objects should be different from each other");
            }
        }

        private void expectMinSize() {
            if(containers.size() < 2) {
                throw new IllegalStateException("At least two different objects should be supplied");
            }
        }

        public class CreatableValueObjectBehaviorTest extends Builder<T> {
            private CreatableValueObjectBehaviorTest() { }

            public CreatableValueObjectBehaviorTest addTest(final ValueBehavior<T> test) {
                Builder.this.tests.add(test);
                return this;
            }

            public ValueObjectBehaviorTest<T> build() {
                return Builder.this.build();
            }

        }

        private ValueObjectBehaviorTest<T> build() {
            return new ValueObjectBehaviorTest<>(this.containers, this.tests);
        }

    }

}
