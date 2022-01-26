package domain.com.homework_6.lesson;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Suite for verifying numbers")
public class SimpleTest {

    private String str = "Junit";

    @Test
    @DisplayName("Assert that 3 is less than 2")
    void test() {
        Assertions.assertTrue(3 < 2);
        str = null;
    }

    @Test
    @DisplayName("Assert that 3 is more than 2")
    void test1() {
        Assertions.assertTrue(3 > 2);
        str = null;
    }

    @Test
    @Disabled
    void test2() {
       str.toUpperCase();
    }
}


