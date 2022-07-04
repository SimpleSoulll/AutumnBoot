package simplesoul.autumnboot.rest.common.docs;

import org.reflections.Reflections;
import org.springframework.context.annotation.Lazy;
import simplesoul.autumnboot.rest.common.exception.AbstractCustomException;

import java.util.List;

/**
 * 扫描{@link AbstractCustomException}的实现类,汇总错误码
 *
 * @author AC
 */
public final class CustomExceptionScanner {

    /**
     * {@link AbstractCustomException}的所有实现类
     */
    @Lazy
    public static final List<Class<? extends AbstractCustomException>> CUSTOM_EXCEPTION_IMPLS =
            List.copyOf(new Reflections().getSubTypesOf(AbstractCustomException.class));
}
