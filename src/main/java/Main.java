import dto.Person;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    @SneakyThrows
    public static void main(String[] args) {
        Class<?> personClass = Class.forName("dto.Person");

        //get field and method names
        var fields = Arrays.stream(personClass.getDeclaredFields())
                        .map(Field::getName)
                                .toList();
        var methods = Arrays.stream(personClass.getMethods())
                .map(Method::getName)
                .toList();
        LOGGER.info("Fields in class are {}", fields);
        LOGGER.info("Methods in class are {}", methods);

        //change person's name
        //violates the encapsulation principle!!!
        Field fieldName = personClass.getDeclaredField("name");
        fieldName.setAccessible(true);
        Person person = new Person("Jack", 15, "Chicago");
        fieldName.set(person, "Bob");
        LOGGER.info("Person's name has been changed to {}", person.getName());
    }
}