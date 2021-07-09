# Groovy 介绍
- Groovy 是一种动态语言，和Java一样，也运行在 Java 虚拟机中。
- 定义： Groovy 是在 Java 平台上的、具有像 python，ruby 和 smalltalk 语言特性的灵活动态语言，Groovy 保证了这些特性像 Java 语法一样被 Java 开发者使用
- Groovy 有时候又像一种脚本语言
- 由于 Groovy Code 在真正执行的时候，已经变成了 Java 字节码，所以 JVM 


## 预备知识
- Groovy 注释标记与Java一致：`//`或者`/**/`
- Groovy 语句可以不用分号结尾
- Groovy 支持动态类型，即<font color='orange'>定义变量的时候可以不指定其类型。</font>
- 变量定义时可使用关键字`def`，虽不是必须，但是为了代码清晰建议还是使用def关键字
- 函数定义时，参数的类型也可以不指定
- Groovy 中函数调用的时候可以不加括号，但Groovy经常把属性和函数调用混淆。建议：函数是Groovy API或者是Grandle API 中比较常见的（如，println)，就可以不带括号，否则还是带括号。


- 函数返回值
    + return xxx：xxx为函数返回值
    + 函数最后一句代码的执行结果

- 可不指定类型（无类型）
    + 变量
    + 函数参数
    + 函数返回值

- def关键字
    + 变量：可不用，但推荐使用（为了代码清晰）
    + 函数
        * 必须用：无类型的函数定义
        * 可不用：指定了函数返回类型

- 字符串
    + 单引号`'string $str'`:不支持$转义，输出`string $str`
    + 双引号: 与脚本语言的处理相似，有`$`时，先对`$表达式`求值
    ```groovy
    def str = "123",
    def out = "this is $str" // this is 123
    ```
    + 三引号：支持随意换行


## 数据类型
- 数据类型
    + Java 中的基本数据类型
    + Groovy 中的容器类
    + 闭包

### 基本数据类型
> Java 中的数据基本数据类型，在Groovy中对应他们的包装数据类型
- eg:
    + int: Integer
    + boolean： Boolean

### 容器类
- List: 链表，其底层对应 Java 中的 List 接口，一般用 ArrayList 作为真正的实现类
    + 变量定义： 由`[]`定义，其元素可以是任何对象
    + 变量存取： 可以直接通过索引存取，不用担心索引越界。如果索引超过当前链表长度，List 会自动往该索引添加元素
    ```
    def list = [5,"string",true]
    assert aList[1] == 'string'
    ```
- Map: 键值表，其底层对应 Java 中的 LinkedHashMap
    + 变量定义：由`[:]`定义
        * 冒号`:`左边是`key`，右边是`value`
            - `key`：必须是字符串，可以用`''（单引号）`或`""（双引号）`包起来，也可以不用引号包起来，会被默认处理为字符串
            - `value`：任何对象
    + 变量存取
        * 表达式方法: `println map.keyName`
        * `[]` key 值存取法: `println map['keyName']`
        * 添加新元素: `map.anotherKey = "i am a map"`
- Range: 范围，其实是 List 的一种拓展
    + 变量定义：由`begin 值+两个点+end值`表示，默认包含右值
    + eg: `def aRang = 1..5`
    + 不包含右值： `def aRangeWithoutEnd = 1..<5`

### 闭包
- 定义格式
    + `def xxx = {paramters -> code}` 箭头前是参数列表，其后是 code
    + `def xxx = {code}` // 无参数，纯 code
- 调用方式
    + 1：`闭包对象.call(参数)`
    + 2：`闭包对象(参数)`
- 如果闭包没有定义参数的话，则隐含有一个参数`it`，和 `this` 的作用类似。
- 闭包使用中的注意点
    + 省略圆括号
    + 如何确定闭包的参数
