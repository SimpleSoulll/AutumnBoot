package simplesoul.autumnboot.common.reflection;

/**
 * @author AC
 */
public class ReflectionUtils {

    /**
     * 通过全限定名获取Class对象
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> classOf(String canonicalName) throws ClassNotFoundException {
        try {
            return (Class<T>) Class.forName(canonicalName);
        } catch (ClassNotFoundException ignored) {
            return (Class<T>) Class.forName(getInnerStaticClassCanonicalName(canonicalName));
        }
    }

    /**
     * 获取静态内部类的全限定名
     */
    private static String getInnerStaticClassCanonicalName(String clazz) {
        int idx = clazz.lastIndexOf('.');
        var builder = new StringBuilder(clazz);
        builder.setCharAt(idx, '$');
        return builder.toString();
    }
}
