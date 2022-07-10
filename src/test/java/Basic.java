import org.junit.jupiter.api.Test;
import org.simplesoul.autumnboot.rest.common.docs.errorcodes.ErrorCode;

import java.util.regex.Pattern;

/**
 * @author AC
 */
public class Basic {

    @Test
    void test() {
        String content = "{\"e-mail\":\"472108922@qq.com\",BossId=3646&Pwd=1408787169&osVer=unknown&serial=-1&platform=4330303&deviceModel=unknown&appVer=2.4.3.51028&p2pVer=5.7.5.00136&guid=d20e957ee4f611ea90a46c92bfd79530&qq=0&wx=unknown&clientTime=1657174805&step=3&connectElapse=0&dnsElapse=0&domain=&elapse=0&er\"enrolltype\":\"自主考试录取\",\"print_graduate\":0,\"password\":\"Dc11023022\",\"xjzt\":0,\"xuezhi\":\"2.5\",\"classcode\":\"DC1171D1001\",\"season\":\"1\",\"depart\":\"北京市海淀区金航教育培训学校\",\"enrolltime\":\"20170301\",\"specialityname\":\"工商管理\",\"graduatetime\":\"20170110\",\"postcode\":\"100000\",\"username_new\":\"DC1171D1001\",\"precredit\":0,\"graduatespecia\":\"计算机科学与技术\",\"nationality\":\"汉族\",\"phone\":null,\"grade\":\"171\",\"graduatenumber\":\"0\",\"status\":\"0\",\"birthday\":\"19861002\",\"politics\":\"群众\",\"xuejistatus\":\"0\",\"checkresult\":\"0\",\"remark\":null,\"graduatedate\":null,\"examid\":\"17W131061017104751\",\"departmentcode\":\"D1\",\"company\":\"北大青鸟\",\"cardtype\":\"身份证\",\"specode\":\"C1\",\"graduateid\":\"100067201706009718\",\"email\":null,\"confirmdate\":\"2017-03-10\",\"resume\":\"01\",\"rowno\":1,\"address\":\"北京市海淀区西三环北路甲105号科原大厦B504\",\"sex\":\"女\",\"mobile\":\"18519195752\",\"tuition\":\"是\",\"en_c";
        String regex = """
                ([\\D]|^)1(3[0-9]|45|47|49|5[0-3]|5[5-9]|70|71|73|7[6-8]|8[0-9])\\d{8}([\\D]|$)""";
        Pattern pattern = Pattern.compile(regex);
        System.out.println(content.matches(regex));
        System.out.println(pattern.matcher(content).find());
    }

    @Test
    void test2() throws Exception {
        Class.forName("org.simplesoul.autumnboot.rest.common.response.ResponseEntity$Fatal");
    }

    private static abstract class A {

    }

    private static class B extends A {

    }

    abstract class C {

        void getErrorCode() {
            int value = getClass().getAnnotation(ErrorCode.class).value();
            System.out.println(value);
        }
    }

    @ErrorCode(12345)
    class D extends C{

    }
}
