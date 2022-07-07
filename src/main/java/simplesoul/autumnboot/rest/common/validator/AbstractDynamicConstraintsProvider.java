package simplesoul.autumnboot.rest.common.validator;

/**
 * 动态约束
 *
 * @author AC
 */
public abstract class AbstractDynamicConstraintsProvider<T> {

    /**
     * 获取动态约束/期望值
     *
     * @param <T> 约束对象的类型
     * @return 约束值/期望值
     */
    abstract public T getDynamicExpectations();
}
