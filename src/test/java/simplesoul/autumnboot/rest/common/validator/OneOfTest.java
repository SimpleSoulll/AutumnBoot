package simplesoul.autumnboot.rest.common.validator;

import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.annotation.Validated;
import simplesoul.autumnboot.rest.common.validator.oneof.OneOfInts;
import simplesoul.autumnboot.rest.common.validator.oneof.OneOfStringValidator;
import simplesoul.autumnboot.rest.common.validator.oneof.OneOfStrings;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author AC
 */
@SpringBootTest
public class OneOfTest {

    @Autowired
    private Validator validator;

    /**
     * @see simplesoul.autumnboot.rest.common.validator.oneof.OneOfStrings
     */
    @Test
    @DisplayName("测试OneOfStrings注解")
    void inEnumAnnotationTest() {
        var person = new Person("icy", 28);
        var validateResult = validator.validate(person);
        Set<String> messages = validateResult.stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet());
        Set<String> expectedMessages = Set.of("icy不在{Francis,Icy}中", "28不在{30,20}中");
        Assertions.assertEquals(expectedMessages, messages);
    }

    private static class Weapon {
        @InEnum(Force.class)
        Force force;
    }

    private enum Force {

        StrongNuclearForce("强核力"),
        WeakNuclearForce("弱核力"),
        ElectromagneticForce("电磁力"),
        Gravity("引力");

        @Getter
        private final String name;

        private Force(String name) {
            this.name = name;
        }
    }

    private static record Person(
            @OneOfStrings(value = {"Icy", "Francis"}) String name,
            @OneOfInts({20, 30}) int age) {
    }
}
