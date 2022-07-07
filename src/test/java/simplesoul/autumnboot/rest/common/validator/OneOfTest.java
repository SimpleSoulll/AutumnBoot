package simplesoul.autumnboot.rest.common.validator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import simplesoul.autumnboot.rest.common.validator.in.In;
import simplesoul.autumnboot.rest.common.validator.oneof.AbstractOneOfDynamicConstraintsProvider;
import simplesoul.autumnboot.rest.common.validator.oneof.OneOfIntegers;
import simplesoul.autumnboot.rest.common.validator.oneof.OneOfStrings;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
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
    @DisplayName("测试静态OneOf注解")
    void staticOneOfTest() {
        var person = new Person("icy", 28);
        var validateResult = validator.validate(person);
        Set<String> messages = validateResult.stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet());
        Set<String> expectedMessages = Set.of("icy不在{Francis,Icy}中", "28不在{30,20}中");
        Assertions.assertEquals(expectedMessages, messages);
    }

    @Test
    @DisplayName("测试动态OneOf注解")
    void dynamicOneOfTest() {
        var person = new DynamicPerson("icy", 31);
        var validateResult = validator.validate(person);
        Set<String> messages = validateResult.stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet());
        System.out.println(messages);
    }

    private static class Weapon {
        @In(Force.class)
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
            @OneOfIntegers({20, 30}) int age) {
    }

    private static record DynamicPerson(
            @OneOfStrings(dynamicsProvider = DynamicNameConstraintsProvider.class) String name,
            @OneOfIntegers(dynamicsProvider = DynamicAgeConstraintsProvider.class) int age) {
    }

    @NoArgsConstructor
    public static class DynamicNameConstraintsProvider extends AbstractOneOfDynamicConstraintsProvider<String> {
        @Override
        public Set<String> getDynamicExpectations() {
            return Set.of(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        }
    }

    @NoArgsConstructor
    public static class DynamicAgeConstraintsProvider extends AbstractOneOfDynamicConstraintsProvider<Integer> {
        @Override
        public Set<Integer> getDynamicExpectations() {
            Random random = new Random();
            return Set.of(random.nextInt(30), random.nextInt(30));
        }
    }
}
