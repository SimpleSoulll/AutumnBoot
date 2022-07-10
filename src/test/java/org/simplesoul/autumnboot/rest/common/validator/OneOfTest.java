package org.simplesoul.autumnboot.rest.common.validator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.simplesoul.autumnboot.rest.common.validator.oneof.AbstractOneOfDynamicConstraintsProvider;
import org.simplesoul.autumnboot.rest.common.validator.oneof.OneOf;
import org.simplesoul.autumnboot.rest.common.validator.oneof.OneOfIntegers;
import org.simplesoul.autumnboot.rest.common.validator.oneof.OneOfStrings;

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
     * @see OneOfStrings
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
        Assertions.assertEquals(2, messages.size());
    }

    @Test
    @DisplayName("测试OneOf注解")
    void oneOfTest() {
        Programmer programmer = new Programmer("linux", new Language("ASM"));
        var validateResult = validator.validate(programmer);
        Set<String> messages = validateResult.stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet());
    }

    private static record Programmer(String name, @OneOf(constraintsProvider = MajorLanguage.class) Language language) {
    }

    @NoArgsConstructor
    private static class MajorLanguage extends AbstractOneOfDynamicConstraintsProvider<Language> {

        @Override
        public Set<Language> getConstraints() {
            return Set.of(new Language("C++"), new Language("Java"), new Language("C#"));
        }
    }

    @Data
    @AllArgsConstructor
    private static class Language {

        private String name;
    }

    private static record Person(
            @OneOfStrings(value = {"Icy", "Francis"}) String name,
            @OneOfIntegers({20, 30}) int age) {
    }

    private static record DynamicPerson(
            @OneOfStrings(constraintsProvider = DynamicNameConstraintsProvider.class) String name,
            @OneOfIntegers(constraintsProvider = DynamicAgeConstraintsProvider.class) int age) {
    }

    @NoArgsConstructor
    public static class DynamicNameConstraintsProvider extends AbstractOneOfDynamicConstraintsProvider<String> {
        @Override
        public Set<String> getConstraints() {
            return Set.of(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        }
    }

    @NoArgsConstructor
    public static class DynamicAgeConstraintsProvider extends AbstractOneOfDynamicConstraintsProvider<Integer> {
        @Override
        public Set<Integer> getConstraints() {
            Random random = new Random();
            return Set.of(random.nextInt(20), random.nextInt(10) + 20);
        }
    }
}
