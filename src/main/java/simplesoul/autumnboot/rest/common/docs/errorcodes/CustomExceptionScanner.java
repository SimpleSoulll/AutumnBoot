package simplesoul.autumnboot.rest.common.docs.errorcodes;

import jdk.javadoc.doclet.DocletEnvironment;
import net.steppschuh.markdowngenerator.MarkdownSerializationException;
import net.steppschuh.markdowngenerator.list.UnorderedList;
import net.steppschuh.markdowngenerator.table.Table;
import net.steppschuh.markdowngenerator.text.emphasis.BoldText;
import net.steppschuh.markdowngenerator.text.heading.Heading;
import org.apache.commons.text.StringEscapeUtils;
import org.reflections.Reflections;
import org.springframework.context.annotation.Lazy;
import simplesoul.autumnboot.rest.common.exception.AbstractCustomException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 扫描{@link AbstractCustomException}的实现类,汇总错误码
 *
 * @author AC
 */
public class CustomExceptionScanner {

    private final DocletEnvironment environment;

    public CustomExceptionScanner(DocletEnvironment environment) {
        this.environment = environment;
    }

    /**
     * 获取错误码的Markdown文本
     */
    public String markErrorCodesDown() throws MarkdownSerializationException {
        var errorCodes = getErrorCodeDescriptions();
        var doc = new StringBuilder();
        doc.append(new Heading("错误码说明", 3).serialize()).append("\n");
        Table.Builder errorCodeTable = new Table.Builder()
                .addRow("错误码", "说明");
        errorCodes.forEach(code -> errorCodeTable.addRow(code.getKey(), code.getValue()));
        return doc.append(errorCodeTable.build().serialize()).append("\n").toString();
    }

    /**
     * 获取冲突错误码Markdown文本
     */
    public String markErrorCodeConflictsDown() throws MarkdownSerializationException {
        var errorCodeConflicts = getErrorCodeConflicts();
        StringBuilder doc = new StringBuilder();
        if (!errorCodeConflicts.isEmpty()) {
            doc.append(new BoldText("冲突的错误码: ")).append("\n");
            var messages = errorCodeConflicts.stream().map(ErrorCodeConflict::getConflictMessage).collect(Collectors.toList());
            doc.append(new UnorderedList<>(messages).serialize()).append("\n");
        }
        return doc.toString();
    }

    /**
     * 获取继承了{@link AbstractCustomException}的异常类的错误码及其注释
     */
    private List<AbstractMap.SimpleImmutableEntry<Integer, String>> getErrorCodeDescriptions() {
        List<AbstractMap.SimpleImmutableEntry<Integer, String>> errorCodes = new LinkedList<>();
        var docTrees = environment.getDocTrees();
        var typeUtils = environment.getTypeUtils();
        environment.getIncludedElements().forEach(element -> {
            if (element.getKind().isClass()) {
                if (typeUtils.directSupertypes(element.asType()).stream().anyMatch(typeMirror ->
                        typeMirror.toString().equals(AbstractCustomException.class.getCanonicalName()))) {
                    var errorCodeAnnotation = element.getAnnotation(ErrorCode.class);
                    var errorCode = Objects.nonNull(errorCodeAnnotation) ? errorCodeAnnotation.value() : 0;
                    var comment = StringEscapeUtils.unescapeJava(docTrees.getDocCommentTree(element).getFullBody().get(0).toString());
                    errorCodes.add(new AbstractMap.SimpleImmutableEntry<>(errorCode, comment));
                }
            }
        });
        return errorCodes;
    }

    private int getErrorCodeFromAnnotation(Class<? extends AbstractCustomException> customException) {
        var errorCodeAnnotation = customException.getAnnotation(ErrorCode.class);
        return Objects.nonNull(errorCodeAnnotation) ? errorCodeAnnotation.value() : 0;
    }

    /**
     * {@link AbstractCustomException}的所有实现类
     */
    @Lazy
    private static final List<Class<? extends AbstractCustomException>> CUSTOM_EXCEPTIONS =
            List.copyOf(new Reflections(getRootPackage()).getSubTypesOf(AbstractCustomException.class));

    /**
     * 获取package的根目录
     *
     * @return 根目录
     */
    private static String getRootPackage() {
        return Arrays.stream(CustomExceptionScanner.class.getCanonicalName().split("\\.")).findFirst().orElse("");
    }

    /**
     * 获取错误码冲突的类
     *
     * @return 冲突的类及其错误码
     */
    public Set<ErrorCodeConflict> getErrorCodeConflicts() {
        return CUSTOM_EXCEPTIONS.stream().map(impl -> {
                    var errorCodeAnnotation = impl.getAnnotation(ErrorCode.class);
                    var errorCode = Objects.nonNull(errorCodeAnnotation) ? errorCodeAnnotation.value() : 0;
                    return new AbstractMap.SimpleImmutableEntry<>(errorCode, impl.getCanonicalName());
                }).filter(entry -> entry.getKey() > 0)
                .collect(Collectors.groupingBy(AbstractMap.SimpleImmutableEntry::getKey, Collectors.toSet())).entrySet()
                .stream().map(entry ->
                        new ErrorCodeConflict(entry.getKey(), entry.getValue().stream()
                                .map(AbstractMap.SimpleImmutableEntry::getValue).collect(Collectors.toSet())))
                .filter(ecf -> ecf.conflicts().size() > 1)
                .collect(Collectors.toSet());
    }
}