package org.simplesoul.autumnboot.common.cache;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 使用{@link java.util.concurrent.ConcurrentHashMap}实现的朴素缓存
 * 用于缓存单例对象等,禁止缓存数据
 * @author AC
 */
@NoArgsConstructor
@Slf4j
public class PrimeCache<K, V> {

    private final Map<K, WeakReference<V>> cache = new ConcurrentHashMap<>();

    private Function<K, V> supplier = null;

    public PrimeCache(Function<K, V> supplier) {
        this.supplier = supplier;
    }

    public V getOrElseUpsert(K key) {
        if (!cache.containsKey(key)) {
            synchronized (cache) {
                if (!cache.containsKey(key)) {
                    if (Objects.nonNull(supplier)) {
                        cache.put(key, new WeakReference<>(supplier.apply(key)));
                    } else {
                        throw new RuntimeException("无法构建对象");
                    }
                } else {
                    WeakReference<V> weakValue = cache.get(key);
                    if (Objects.isNull(weakValue) || Objects.isNull(weakValue.get())) {
                        cache.put(key, new WeakReference<>(supplier.apply(key)));
                    }
                }
            }
        }
        return cache.get(key).get();
    }
}
