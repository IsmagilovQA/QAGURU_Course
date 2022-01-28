package domain.com.homework_8.lesson.properties;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class SystemPropertiesTests {

//    System.getProperty("key");
//    System.setProperty("key", "value");

    @Test
    void systemPropertyTest_1() {
        String someValue = System.getProperty("someKey");
        System.out.println(someValue); //null
    }

    @Test
    void systemPropertyTest_2() {
        System.setProperty("someKey", "testValue");
        String someValue = System.getProperty("someKey");
        System.out.println(someValue); // testValue
    }

    @Test
    void systemPropertyTest_3() {
        String someValue = System.getProperty("someKey", "defaultValue");
        System.out.println(someValue); // defaultValue
    }

    @Test
    void systemPropertyTest_4() {
        Boolean someValue = Boolean.parseBoolean(System.getProperty("someKey", "true"));
        System.out.println(someValue); // true as a boolean type
    }

    @Test
    @Tag("properties")
    void systemPropertyTest_5() {
        String browser = System.getProperty("browser");
        System.out.println(browser);
    }
}
