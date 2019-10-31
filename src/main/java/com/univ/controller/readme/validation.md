# 用法
1. 引入jar包（hibernate-validator与validator-api）；
2. 在controller方法需要验证的参数上使用@Valid或@Validated即可；

# @Valid与@Validated的区别
[参考资料](https://www.jianshu.com/p/0a9ce95f9586)
1. @Valid位于javax.validation包中，属于SR-303规范；@Validated是spring包，是对@Valid的增强，提供了分组验证的功能
2. 两者可作用的对象不同，METHOD, @Valid可用在字段, 构造函数, 参数以及方法上，@Validated可用在类型，方法以及参数上(不能用在字段上)

# 重要说明
1. 如果controller方法上校验使用了BindingResult，则错误信息会被传导至其上，否则校验失败时会抛出MethodArgumentNotValidException异常，此时可使用全局异常捕获(这也是常用的方法)
2. 如果要嵌套验证，则需要在被嵌套的字段上使用@Valid，否则没法嵌套验证；

