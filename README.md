# AutumnBoot
SpringBoot项目模板及工具类

## Requirement

At least jdk17

## Tips

- [link Maven适配不同JDK版本](tips/maven.md)

--output docs/error_code.md -cp "target/lib/*;target/classes" -docletpath "target/classes;target/lib/commons-text-1.9.jar;target/lib/commons-lang3-3.12.0.jar;target/lib/markdowngenerator-1.3.1.1.jar;target/lib/reflections-0.10.2.jar;target/lib/slf4j-api-1.7.36.jar;target/lib/javassist-3.28.0-
GA.jar;target/lib/jackson-annotations-2.13.3.jar;target/lib/caffeine-3.1.1.jar" -doclet simplesoul.autumnboot.rest.common.docs.MarkUniverseDown -encoding utf-8 --docEncoding GBK --enable-preview --release 17 -sourcepath src/main/java -subpackages simplesoul