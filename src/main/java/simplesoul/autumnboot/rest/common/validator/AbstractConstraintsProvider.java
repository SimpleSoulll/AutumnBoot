package simplesoul.autumnboot.rest.common.validator;

/**
 * 向校验器提供约束的类
 *
 * @author AC
 */
public abstract class AbstractConstraintsProvider<T> {

    /**
     * 获取约束/期望值
     *
     * @param <T> 约束对象的类型
     * @return 约束值/期望值
     */
    abstract public T getConstraints();
}
