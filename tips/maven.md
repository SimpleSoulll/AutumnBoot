# Maven适配不同的JDK版本

## 问题描述
执行`mvn -version`,可以看到当Maven支持的JDK版本为`1.8`.

```
> mvn -version
Apache Maven 3.5.2 (138edd61fd100ec658bfa2d307c43b76940a5d7d; 2017-10-18T15:58:13+08:00)
Maven home: E:\documents\Maven\apache-maven-3.5.2\bin\..
Java version: 1.8.0_144, vendor: Oracle Corporation
Java home: D:\JAVA\jre
Default locale: zh_CN, platform encoding: GBK
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
```

执行`mvn compile`抛出错误：
```
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.10.1:compile (default-compile) on project autumn-boot: Fatal error compiling: 无效的目标发行版: 17
```
问题在于当前MAVEN绑定JDK版本`1.8`,但尝试编译`JDK17`的代码。

## 解决方案

以在windows环境下适配JDK8和JDK17为例。

找到`MAVEN_HOME`,将`bin`目录下的`mvn.cmd`复制两份,分别命名为`mvn8.cmd`和`mvn17.cmd`,打开文件分别设置不同的`JAVA_HOME`：

- mvn8
```
...
@setlocal

set ERROR_CODE=0
set JAVA_HOME=/path/to/jdk/8
...
```

- mvn17
```
...
@setlocal

set ERROR_CODE=0
set JAVA_HOME=/path/to/jdk/17
...
```

**Linux环境使用`export`**

### 验证

```
> mvn8 -version
Apache Maven 3.5.2 (138edd61fd100ec658bfa2d307c43b76940a5d7d; 2017-10-18T15:58:13+08:00)
Maven home: E:\documents\Maven\apache-maven-3.5.2\bin\..
Java version: 1.8.0_144, vendor: Oracle Corporation
Java home: D:\JAVA\jre
Default locale: zh_CN, platform encoding: GBK
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
```

```
> mvn17 -version
Apache Maven 3.5.2 (138edd61fd100ec658bfa2d307c43b76940a5d7d; 2017-10-18T15:58:13+08:00)
Maven home: E:\documents\Maven\apache-maven-3.5.2\bin\..
Java version: 17.0.3.1, vendor: Oracle Corporation
Java home: D:\JAVA\jdk17\jdk-17.0.3.1
Default locale: zh_CN, platform encoding: GBK
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
```

```
> mvn17 clean package -DVERSION="v1.0"
...
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 4.421 s
[INFO] Final Memory: 27M/94M
[INFO] ------------------------------------------------------------------------
```
