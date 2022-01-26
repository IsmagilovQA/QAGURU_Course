package domain.com.homework_6.lesson;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Junit {

    // Write our own Junit:
    public static void main(String[] args) throws NoSuchMethodException, InstantiationException, IllegalAccessException {
        for (Method declaredMethod : SimpleTest.class.getDeclaredMethods()) {
            Test test = declaredMethod.getAnnotation(Test.class);
            if (test != null) {
                try {
                    declaredMethod.invoke(
                            // every time we create SimpleTest object for each method inside SimpleTest class:
                            SimpleTest.class.getConstructor().newInstance());
                } catch (InvocationTargetException e) {
                    if (e.getCause() instanceof AssertionError) {
                        System.out.println("Test with name: [" + declaredMethod.getName() + "] " +
                                "-> FAILED with error message: " + e.getCause().getMessage());
                        continue;
                    } else {
                        System.out.println("Test with name: [" + declaredMethod.getName() + "] " +
                                "-> BROKEN with error message: " + e.getCause().getMessage());
                        continue;
                    }
                }
                System.out.println("Test with name: [" + declaredMethod.getName() + "] -> PASSED!");
            }
        }
    }
}
