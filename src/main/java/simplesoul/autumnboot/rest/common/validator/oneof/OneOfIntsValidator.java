package simplesoul.autumnboot.rest.common.validator.oneof;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * 校验对象是否在指定int数组中
 *
 * @author AC
 */
public class OneOfIntsValidator extends AbstractOneOfValidator<OneOfInts, Integer> {

    @Override
    protected Set<Integer> extractExpectations(OneOfInts constraintAnnotation) {
        return Arrays.stream(constraintAnnotation.value()).boxed().collect(Collectors.toSet());
    }
}
