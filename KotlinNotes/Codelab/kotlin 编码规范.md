# kotlin 编码规范

## 源码组织
kotlin 的代码要放在 `org.example.kotlin`包下。  
文件名要与文件内容匹配，如果文件内只有一个类，则文件名=类名。  

组织文件内容的顺序：

- 属性声明和初始化代码块
- 第二构造函数
- 方法声明、定义
- Companion 对象

不要把函数按照字母或者可见性排序，而是根据业务逻辑分块，并且嵌套使用的类要和使用它的类挨着。  
外部引用的类，没有被本文件使用的类放在最后（Companion 对象之后）  
实现的接口的参数顺序与原接口顺序一致。  

## 命名规则

- 包名： 全小写，不要有下划线，多词组采用小驼峰式命名
- 类名/对象名：大驼峰命名（首字母大写），无下划线
- 函数名：小驼峰，无下划线
	+ 构造函数名：与类名一致
- 变量名 
	+ 常量：全大写，用下划线分割单词
	+ 普通变量： 小驼峰
	+ 引用单例对象的变量：大驼峰
	+ 枚举变量：全大写+下划线，或者大驼峰

类名：名词或名词短语  
方法名：动词或动词短语  
含有缩写词：只有两个单词，缩写词全大写；含有多个单词，缩写词首字母大写


## 格式化规则

使用4个空格替代Tab键
花括号格式：
```
name {
	// code
}
```
二元操作符两边留空格（范围操作符`0..1`除外）  
一元操作符之间不留空格  
控制操作符和左括号之间留一个空格， 如 `if (xx) {`  
**主构造函数、方法定义、方法调用**时，与左括号之间不留空格。  
左括号`(/[`之后不留空格，右括号之前不留空格  
`?` `?:` 后不留空格，单行注释后留一个空格  
尖括号`<>`前后不留空格  
双冒号`::`前后不留空格  
`?`标记可空数据类型时，前面不留空格  

冒号前留空格：

- 分割类型和父类型（继承关系中）
- 委托给超类构造函数或不同的构造函数
- 关键字`object`之后

当冒号用于变量和数据类型之间时，前面不留空格。

类的主构造函数只有少量参数时，写一行；有较多参数时，每个参数单独一行，右括号与继承类/接口单独一行；有多个父类/接口，每个父类/接口单独一行，内部代码前留一行空行。  
多个限定词的书写顺序：
```
public / protected / private / internal
expect / actual
final / open / abstract / sealed / const
external
override
lateinit
tailrec
vararg
suspend
inner
enum / annotation / fun // as a modifier in `fun interface`
companion
inline / value
infix
operator
data
```

`public` 在不必要的时候省略不写  

注解：在定义之前单独写一行；没有参数的多个注解写在同一行；没有参数的一个注解与定义写在同一行  
文件注解：在注释之后、包标注之前；并与包标注之前空一行  

函数参数太多一行放不下的时候，每个参数单独占一行（并且距离行首 4 个空格），与构造函数格式类似  
函数体只有一行代码时，用表达式替代函数体。
如：
```kotlin
fun foo() {
	return 1   		==>   fun foo() = 1
}
```

表达式过长不能一行放下时，表达式右值放在下一行（并且距离行首 4 个空格） 
```
fun f(x: String, y: String, z: String) =
    veryLongFunctionCallWithManyWords(andLongParametersToo(), x, y, z)
```

属性变量：

- 对于简单的、只读的变量，考虑使用一行格式：`val isEmpty: Boolean get() = size == 0
` 
- 对于复杂的属性，使用 `put()`和 `get()`方法，单独占行（距离行首 4 个空格）
```
val foo: String
    get() { /*...*/ }
```

如果 `if` `when`的条件是多行的，那么代码块内不管有几行都需要使用大括号  
如果 `when` 的分支有多个，单行分支后用空行与其他分支分隔  
`when`的单行分支 的代码块不需要使用大括号  

方法调用：参数过多则换行表示，默认参数值时，`=`等号两边要留空格  
链式调用：`.`和`?.`需要放在行首，换行调用，同样距离行首 4 个空格  

 lambda 函数：花括号两边都需要留空格；如果有标签，标签和花括号之间不留空格
 ```
 list.filter { it > 10 }
-------------------
fun foo() {
    ints.forEach lit@{
        // ...
    }
}
 ```

<font color = red> 尾迹逗号：一串元素的最后一个元素后也正常添加逗号`,`  </font>   
好处：

- 让版本控制查看变化时更方便——专注于改变的值
- 重排参数列表更方便——不需要增删逗号
- 简化代码生成

使用场景：

- 枚举
- 赋值列表
- 类属性和参数
- 函数参数
- ······

## 文档注释

文本注释：`/**`开头，其余每行都以`*`开头，注释中尽量不要使用`@parm`和`@return`
```
// Avoid doing this:

/**
 * Returns the absolute value of the given number.
 * @param number The number to return the absolute value for.
 * @return The absolute value.
 */
fun abs(number: Int) { /*...*/ }

// Do this instead:

/**
 * Returns the absolute value of the given [number].
 */
fun abs(number: Int) { /*...*/ }
```

## 避免冗余结构

省略`Uint`返回类型  
省略行尾分号  
字符串模板，单个变量时不用花括号，只在长表达式时使用  

## 习惯用法
1. 尽量使用不可变变量，如果变量在初始化之后就不会再改变，则定义为 `val`
2. 尽量使用不可变集合引用，如：`Collection / List / Set / Map`；当使用工厂函数生成集合实例时，使用返回不可变集合类型的函数
```
// Bad: use of mutable collection type for value which will not be mutated
fun validateValue(actualValue: String, allowedValues: HashSet<String>) { ... }

// Good: immutable collection type used instead
fun validateValue(actualValue: String, allowedValues: Set<String>) { ... }

// Bad: arrayListOf() returns ArrayList<T>, which is a mutable collection type
val allowedValues = arrayListOf("a", "b", "c")

// Good: listOf() returns List<T>
val allowedValues = listOf("a", "b", "c")
```
3. 使用默认参数值替换重载函数
4. 类型别名：为经常使用的函数类型或有参数的类型定义一个别名，如：
```
typealias MouseClickHandler = (Any, MouseEvent) -> Unit
typealias PersonIndex = Map<String, Person>
```
5. 如果 lambda 函数比较短、或者没有嵌套使用，则建议使用 `it` 代替命名参数  
6. lambda 函数内避免多个退出标志，尽量保证只有一个出口，不用功将标志出口放在最后一行
7. 变量命名遵循有含义的原则，尽量不使用无意义变量名
8. 对 `try / if / when `有返回时，建议使用表达式
```
return if (x) foo() else bar()
return when(x) {
    0 -> "zero"
    else -> "nonzero"
}
```
9. ·`if` `when`的选取：二元条件使用`if`，其他大于二元的情况都建议使用`when`
10. 使用可空的布尔变量时，需要对布尔变量进行判断，使用`if (value == true) or if (value == false)`
11. 建议使用高阶函数而不是循环（但不建议使用`forEach`，而是使用普通的循环）
12. 在使用范围的时候，建议使用开区间的 `until`
```
for (i in 0..n - 1) { /*...*/ }  // bad
for (i in 0 until n) { /*...*/ }  // good
```
13. 字符串建议使用字符串模板进行连接
14. 多行字符串不建议使用`/n`进行分割，而是使用多行字符串格式`""" mutilline string """`
15. 不需要行缩进时，调用 `trimIndent()`，保留行缩进`trimMargin()`
```
println("""
    Not
    trimmed
    text
    """
       )

println("""
    Trimmed
    text
    """.trimIndent()
       )

println()

val a = """Trimmed to margin text:
          |if(a > 1) {
          |    return a
          |}""".trimMargin()

println(a)
---------------------
===>
    Not
    trimmed
    text
    
Trimmed
text

Trimmed to margin text:
if(a > 1) {
    return a
}

```
16. 
## lib 的规范

- 精确说明可见度
- 精确定义函数返回类型
- 提供文档注释对所有的公开变量，用于支持生成库文档