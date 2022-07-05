package simplesoul.autumnboot.rest.common.docs.errorcodes;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.ReflectionUtils;
import simplesoul.autumnboot.rest.common.exception.AbstractCustomException;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 扫描{@link AbstractCustomException}的实现类,汇总错误码
 *
 * @author AC
 */
public final class CustomExceptionScanner {

    private static final Logger LOG = LoggerFactory.getLogger(CustomExceptionScanner.class);

    /**
     * errorCode的默认字段名
     */
    private static final String DEFAULT_ERR_CODE_FIELD_NAME = "ERR_CODE";

    /**
     * {@link AbstractCustomException}的所有实现类
     */
    @Lazy
    public static final List<Class<? extends AbstractCustomException>> CUSTOM_EXCEPTION_IMPLS =
            List.copyOf(new Reflections(getRootPackage()).getSubTypesOf(AbstractCustomException.class));

    private static String getRootPackage() {
        return Arrays.stream(CustomExceptionScanner.class.getCanonicalName().split("\\.")).findFirst().orElse("");
    }

    /**
     * 获取错误码冲突的类
     */
    public static Set<ErrorCodeConflict> getErrorCodeConflicts() {
        return CUSTOM_EXCEPTION_IMPLS.stream().map(impl -> {
                    try {
                        var errorCodeField = ReflectionUtils.findField(impl, DEFAULT_ERR_CODE_FIELD_NAME);
                        assert errorCodeField != null;
                        errorCodeField.setAccessible(true);
                        int errorCode = (int) errorCodeField.get(null);
                        return new AbstractMap.SimpleImmutableEntry<>(errorCode, impl.getCanonicalName());
                    } catch (IllegalAccessException ex) {
                        LOG.error(String.format("无法获取%s的错误码", ex));
                        return new AbstractMap.SimpleImmutableEntry<>(0, impl.getCanonicalName());
                    }
                }).filter(entry -> entry.getKey() > 0)
                .collect(Collectors.groupingBy(AbstractMap.SimpleImmutableEntry::getKey, Collectors.toSet())).entrySet()
                .stream().map(entry ->
                        new ErrorCodeConflict(entry.getKey(), entry.getValue().stream()
                                .map(AbstractMap.SimpleImmutableEntry::getValue).collect(Collectors.toSet())))
                .filter(ecf -> ecf.conflicts().size() > 1)
                .collect(Collectors.toSet());
    }
}