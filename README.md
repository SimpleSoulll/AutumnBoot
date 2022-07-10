# AutumnBoot
SpringBoot项目模板及工具类

## Requirement

At least jdk17

## Tips

- [link Maven适配不同JDK版本](tips/maven.md)

D:\JAVA\jdk17\jdk-17.0.3.1\bin\javadoc --output docs/error_code.md -cp "target/lib/*;target/classes" -docletpath build/distributions/customName.zip -doclet org.simplesoul.autumnboot.rest.common.docs.MarkUniverseDown -encoding utf-8 --docEncoding GBK --enable-preview --release 17 -sourcepath src/main/java -subpackages org.simplesoul