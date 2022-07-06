package simplesoul.autumnboot.rest.common.validator;

import lombok.Getter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author AC
 */
public class InEnumTest {

    @Test
    @DisplayName("测试InEnum注解")
    void inEnumAnnotationTest() {

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
}

class Person {

    @InEnum(Names.class)
    private String name;
}

enum Names {


}
