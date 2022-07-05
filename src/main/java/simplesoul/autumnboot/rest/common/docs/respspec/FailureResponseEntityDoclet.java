package simplesoul.autumnboot.rest.common.docs.respspec;

import jdk.javadoc.doclet.Doclet;
import jdk.javadoc.doclet.DocletEnvironment;
import jdk.javadoc.doclet.Reporter;

import javax.lang.model.SourceVersion;
import java.util.Collections;
import java.util.Locale;
import java.util.Set;

/**
 * 扫描{@link simplesoul.autumnboot.rest.common.response.AbstractFailureResponse}的注释
 * 解决smart-doc不支持输出接口异常返回示例的问题
 *
 * @author AC
 */
public class FailureResponseEntityDoclet implements Doclet {

    private Reporter reporter;

    private static final boolean OK = true;

    @Override
    public void init(Locale locale, Reporter reporter) {
        this.reporter = reporter;
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public Set<? extends Option> getSupportedOptions() {
        return Collections.emptySet();
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latest();
    }

    @Override
    public boolean run(DocletEnvironment environment) {
        // TODO 提取ResponseEntity的注释
        System.out.println(environment.getSpecifiedElements().size());
        environment.getIncludedElements().forEach(ele -> {
            String comment = environment.getElementUtils().getDocComment(ele);
            System.out.println(comment);
        });
        return OK;
    }
}
