package de.dhbw.nerdlegame.test_utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public abstract class ValueBehavior<T> {

    abstract void performTest(List<ClassContainer<T>> containers);

    /**
     * Test equality of two objects by their values using equals method
     * @param <T> Value Object to test
     */
    public static class ValueEquality<T> extends ValueBehavior<T> {
        @Override
        void performTest(List<ClassContainer<T>> containers) {
            for(ClassContainer<T> container : containers) {
                assertEquals(container.createContents(), container.createContents());
            }
        }
    }

    /**
     * Test equality of two objects by their instance
     * @param <T> Value Object to test
     */
    public static class InstanceEquality<T> extends ValueBehavior<T> {
        @Override
        void performTest(List<ClassContainer<T>> containers) {
            for(ClassContainer<T> container : containers) {
                final T object = container.createContents();
                assertEquals(object, object);
            }
        }
    }

    /**
     * Test inequality of two objects by their values using equals method
     * @param <T> Value Object to test
     */
    public static class ValueInequality<T> extends ValueBehavior<T> {
        @Override
        void performTest(List<ClassContainer<T>> containers) {
            for(final ClassContainer<T> container : containers) {
                final Set<ClassContainer<T>> containerSet = new HashSet<>(containers);
                compareInequalityWithAll(container, containerSet);
            }
        }

        private void compareInequalityWithAll(ClassContainer<T> container, final Set<ClassContainer<T>> containerSet) {
            for(final ClassContainer<T> comparedContainer : containerSet) {
                if(container != comparedContainer) {
                    assertInequality(container.createContents(), comparedContainer.createContents());
                }
            }
        }

        private void assertInequality(T object1, T object2) {
            assertNotEquals(object1, object2);
            assertNotEquals(object1, new Object());
            assertNotEquals(object1, null);
        }
    }

    /**
     * Test equality of two objects by their hash codes
     * @param <T> Value Object to test
     */
    public static class HashcodeEquality<T> extends ValueBehavior<T> {
        @Override
        void performTest(List<ClassContainer<T>> containers) {
            for(ClassContainer<T> container : containers) {
                assertEquals(container.createContents().hashCode(), container.createContents().hashCode());
            }
        }
    }

    /**
     * Test inequality of two objects by their hash codes
     * @param <T> Value Object to test
     */
    public static class HashcodeInequality<T> extends ValueBehavior<T> {
        @Override
        void performTest(List<ClassContainer<T>> containers) {
            for(ClassContainer<T> container : containers) {
                final Set<ClassContainer<T>> containerSet = new HashSet<>(containers);
                compareHashCodeInEqualityWithAll(container, containerSet);
            }
        }

        private void compareHashCodeInEqualityWithAll(ClassContainer<T> container, final Set<ClassContainer<T>> containerSet) {
            for(ClassContainer<T> comparedContainer : containerSet) {
                if(container != comparedContainer) {
                    assertNotEquals(container.createContents().hashCode(), comparedContainer.createContents().hashCode());
                }
            }
        }
    }

}
