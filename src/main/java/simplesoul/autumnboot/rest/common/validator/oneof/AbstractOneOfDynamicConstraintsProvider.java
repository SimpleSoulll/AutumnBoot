package simplesoul.autumnboot.rest.common.validator.oneof;

import simplesoul.autumnboot.rest.common.validator.AbstractDynamicConstraintsProvider;

import java.util.Set;

/**
 * 提供OneOf动态约束集的抽象类
 *
 * @author AC
 */
@SuppressWarnings("rawtypes")
public abstract class AbstractOneOfDynamicConstraintsProvider<T> extends AbstractDynamicConstraintsProvider<Set<T>> {

    protected static final String PROVIDER = AbstractOneOfDynamicConstraintsProvider.class.getCanonicalName();
}
