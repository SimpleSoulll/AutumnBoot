package simplesoul.autumnboot.rest.common.validator.oneof;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 校验对象是否在指定int数组中
 *
 * @author AC
 */
public class OneOfLongsValidator extends AbstractOneOfValidator<OneOfLongs, Long> {

    @Override
    protected Set<Long> extractExpectations(OneOfLongs constraintAnnotation) {
        return Arrays.stream(constraintAnnotation.value()).boxed().collect(Collectors.toSet());
    }
}
