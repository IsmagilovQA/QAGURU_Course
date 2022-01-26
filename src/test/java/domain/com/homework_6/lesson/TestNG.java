package domain.com.homework_6.lesson;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestNG {

    // Write our own TestNG:
    public static void main(String[] args) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        // Only once we create SimpleTest object for each method inside SimpleTest class:
        SimpleTest obj = SimpleTest.class.getConstructor().newInstance();
        for (Method declaredMethod : SimpleTest.class.getDeclaredMethods()) {
            Test test = declaredMethod.getAnnotation(Test.class);
            if (test != null) {
                try {
                    // Here we invoke obj only once:
                    declaredMethod.invoke(obj);
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
