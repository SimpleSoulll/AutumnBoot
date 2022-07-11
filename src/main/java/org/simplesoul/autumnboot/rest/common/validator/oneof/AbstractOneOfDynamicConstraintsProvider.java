package org.simplesoul.autumnboot.rest.common.validator.oneof;

import org.simplesoul.autumnboot.common.reflection.Singleton;
import org.simplesoul.autumnboot.rest.common.validator.AbstractConstraintsProvider;

import java.util.Set;

/**
 * 提供OneOf动态约束集的抽象类
 *
 * @author AC
 */
@Singleton
public abstract class AbstractOneOfDynamicConstraintsProvider<T> extends AbstractConstraintsProvider<Set<T>> {

    protected static final String PROVIDER = AbstractOneOfDynamicConstraintsProvider.class.getCanonicalName();
}
