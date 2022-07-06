package simplesoul.autumnboot.rest.common.docs.respspec;

import com.sun.source.doctree.DocCommentTree;
import com.sun.source.doctree.DocTree;
import com.sun.source.doctree.UnknownBlockTagTree;
import com.sun.source.util.DocTreeScanner;
import com.sun.source.util.SimpleDocTreeVisitor;
import jdk.javadoc.doclet.Doclet;
import jdk.javadoc.doclet.DocletEnvironment;
import jdk.javadoc.doclet.Reporter;
import org.apache.commons.text.StringEscapeUtils;

import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.util.ElementScanner14;
import java.io.PrintStream;
import java.util.*;

/**
 * 扫描{@link simplesoul.autumnboot.rest.common.response.AbstractFailureResponse}的注释
 * 解决smart-doc不支持输出接口异常返回示例的问题
 *
 * @author AC
 */
public class FailureResponseEntityDoclet implements Doclet {

    private Reporter reporter;

    private static final boolean OK = true;
    private static final boolean FAILED = false;

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
        var docTrees = environment.getDocTrees();
        Map<String, String> classComments = new HashMap<>(8);
        environment.getIncludedElements().forEach(element -> {
            if (element.getKind().isClass()) {
                var canonicalName = element.toString();
                var comment = StringEscapeUtils.unescapeJava(docTrees.getDocCommentTree(element).getFullBody().get(0).toString());
                classComments.put(canonicalName, comment.trim());
            }
        });
        return OK;
    }

    private class ResponseEntityInfo {

        private String canonicalName;

        public ResponseEntityInfo(String canonicalName) {
            this.canonicalName = canonicalName;
        }

        private void extractInfo() {

        }
    }
}
