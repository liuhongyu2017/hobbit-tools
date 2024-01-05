## maven deploy 方法

```shell
mvn deploy -DaltDeploymentRepository=${distribute_id}::default::file:D:\GitHub
```

## maven 升级版本号

```shell
// 升级
mvn versions:set -DnewVersion=1.0.1-SNAPSHOT
// 回退
mvn versions:revert
// 确认
mvn versions:commit
```

------------------------------------------------------

1. checker 函数化参数检查
2. jackson
    1. desensitize 脱敏注解
    2. convert 字段转换
