# Kotlin Bootcamp for programmers

[toc]

## 1. Get starts
[codelab](https://developer.android.com/codelabs/kotlin-bootcamp-introduction#8)  
Kotlin 专注于干净、易读和代码安全。  
kotlin的优势 

- 健壮的代码
- 成熟的平台
- 简洁、可读性高的代码
- 可与 Java 交互

在 IDEA 内 Select `Tools > Kotlin > Kotlin REPL `to open the REPL（交互式命令行），采用 `Control+Enter`执行代码。

## Kotlin Basic

### 操作和数据类型
在 kotlin 中，数字可被看作为对象，数字可直接调用方法。
如： `2.4.div(2) ==> 1.2`，在这个过程中，数字`2.4`被自动封装为对象，可调用`number`相关的方法。

kotlin 无法进行隐式的类型转换，因此，不能直接将一个 `short` 型值赋值给`long`型变量。但是可进行强制类型转换 使用 `.toxxx()方法`
```kotlin
val b: Byte = 2
val i: Int = b.toInt()
```

为了让长度较长的数字类型常量更可读，kotlin支持在数字中增加下划线。如`1_000_000`。
kotlin 是个强类型语言，编译器可以推测数据类型，因此定义变量是可不用进行显示的数据类型定义。
`val` 常量型变量，只能赋值一次。
`var` 可变型变量，可以进行多次赋值。

当变量被赋值后（手动赋值或编译器自动赋值），该变量的数据类型便不可再改变。

String 可在字符串模板内使用变量插入 `$variable`会被替换为变量的值，`${expression}`使用花括号之后可以在字符串内插入表达式的值。同其他语言的String一样，也可以使用`+`连接字符串。

### 比较和布尔值
布尔操作符与其他语言类似，在 `if`判断语句中可以使用范围`range`， 如`if (fish in 1..100)` 等价于 `if (fish <= 100 && fish >=1)`

kotlin 中没有`switch`，但`when`的功能类似于`switch`。同样，在`when`中也可以使用范围`range`
```kotlin
when (numberOfFish) {
    0  -> println("Empty tank")
    in 1..39 -> println("Got fish!")
    else -> println("That's a lot of fish!")
}
``` 

### 可空性（nullability）

默认情况下，所有的变量都是不可为空(null)的，通过问号`?`标记变量可为空。如： `var marbles: Int? = null`
如果`list`不可为空，则 `list`内的元素也必须不可为空。

- `?` 操作符和 `?:`操作符
	- `?` 判断变量是否为空，如果非空则执行后面的链式操作；
	- `?:` 判断变量是否为空，如果为空，则不执行链式操作，而执行 `?:`后面的操作。
如： 
`fishFoodTreats = fishFoodTreats?.dec() ?: 0` 等价于 如果 `fishFoodTreats` 非空，就用该变量执行方法`dec()`取得的结果作为值；否则就直接用`?:`后面的变量（0）作为值


- `!!` 强制非空：将任何变量转化为非空变量，如果变量为空则抛出异常
	+ 不建议使用这种方式，然而在处理java代码时可以使用。
	
### arrays/list/loops

- `list`分为可变`list`和不可变`list`
	- 不可变 `list`：通过`listOf()` 构造，构造之后该变量不可更改，即便是`var`型变量进行重新赋值，变量存的值仍为第一次赋值的值。
	- 可变 `list`：通过 `mutableListOf()`构造，构造之后可正常进行remove()、add()……即便变量类型为`val`

- `array`与`list`不同，`array`在创建后，大小就固定了，不能移除或是新增元素，但是可以修改
	+ 使用`arrayOf()`创建`array`, 使用 `java.util.Arrays.toString()`打印数组元素，否则直接打印的是数组变量的地址；
	+ `arrayOf()` 初始化的数组元素没有类型限制，可以同时输入多种类型的变量。如:`val mix = arrayOf("fish", 2)`；
	+ 也可以限制数组的元素类型，使用该类型的初始化函数，如`val numbers = intArrayOf(1,2,3)`， `numbers`的元素就只能为`int`类型；
	+ 使用 `+`操作符来连接多个数组
	如： 
	```kotlin
	val numbers = intArrayOf(1,2,3)
	val numbers2 = intArrayOf(4,5,6)
	val foo2 = numbers2 + numbers
	println(java.util.Arrays.toString(foo2))  
	==> [4, 5, 6, 1, 2, 3]
	```
	+ array 可通过代码初始化，而不是全初始化为 0
	```
	val array = Array(5) {it *2}
	```
		* `it` 指的是数组的脚标，{}内的代码为初始化代码。

- `list` 和 `array` 允许嵌套
	- `list` 的元素可以是 `array` 也可以是 `list`，同理，`array` 的元素可以是 `array` 也可以是 `list`

- `for` 循环
	+ 采用迭代器的方式
	+ 可用范围
		* 支持逆序 `downTo`
		* 支持间隔 `step`
	+ 可同时输出数组脚标和数组元素`for ((index, element) in array.withIndex()) {index:索引脚标，element对应的元素值}`
- `while` `do-while`与其他语言类似
-  `repeat` 重复 ， 如： `repeat(2) { // code }` 执行2遍

### kotlin 与其他语言的异同
- 不同点
	+ kotlin 的数据类型不能被隐式转换，必须强制类型转换
	+ 声明为val的变量不能被再次赋值
	+ kotlin 的变量默认为非空变量，使用 ? 可以使变量声明为可空
	+ 在for循环中可同时输出索引和元素值
	
- 相同点
	+ arrays 和 list 的元素可以有一种或多种数据类型
	+ Arrays 和 list 都可以被嵌套
	+ 创建循环的方式： for, while, do-while, repeat
	+ when 是kotlin 版本的 switch，但是更灵活

## functions

### main() 函数

函数格式：
`fun main(args: Array<String> {}`
在 kotlin 的版本大于等于1.3 之后，可以不用写参数  

main() 函数是程序的入口，需要写在文件根目录内，不能写在类内部  

### compact functions

compact functions == 表达式函数  
eg: `fun isHot(temperature: Int) = temperature > 30`

### Filter 函数
filter is eager，意味着每调用一次过滤函数就有一个 list 创建，是实时的  
想要让过滤函数进行懒加载，就需要使用`Sequence`集合，该集合一次只能查看一个元素  
> eager 可以理解为实时加载， lazy可理解为懒加载即需要的时候再加载

eg: 
```kotlin
    val decorations = listOf("rock", "pagoda", "plastic plant", "alligator", "flowerpot")
    val eager = decorations.filter { it[0] == 'p' }
    val filtered = decorations.asSequence().filter { it[0] == 'p' }
    val newList = filtered.toList()	

    println("eager: $eager")
    println("filtered: $filtered")
    println("new list: $newList")
    -------------------------------------------------------------------
    => output: 
    eager: [pagoda, plastic plant]
	filtered: kotlin.sequences.FilteringSequence@506c589e
	new list: [pagoda, plastic plant]
```
### lambda & higher-order functions

lambda 函数 == 匿名函数  
higher-order 函数 == 参数中有函数的函数

函数作为参数时，通过双冒号`::`标记，表明该参数为函数，传递的值为函数的地址，编译器处理时则不会调用该函数，而是获取该函数的地址  
最后一个参数为函数时，一般将函数写在参数括号的外面  

## 面向对象编程

### 类
在定义类时声明构造函数，可同时在声明构造函数时定义属性。
`class Aquarium(var length: Int = 100, var width: Int = 20, var height: Int = 40) {·····}`

构造函数内容需要更多的初始化代码，可以使用`init {}` 代码块，当构造函数被调用时，`init` 代码块就会被调用。  
第二构造函数必须先调用主构造函数 `this()` 或者直接调用其他的第二构造函数）。
```
class Aquarium(var length: Int = 100, var width: Int = 20, var height: Int = 40) {
	init {
		println("aquarium initializing")
	}
	constructer(numberOfFish: Int) : this() {
		val tank = numberOfFish * 2000 * 1.1
        height = (tank / (width * length)).toInt()
	}
}
```

可见性修饰符：kotlin 默认为 `public`

| 修饰符 | 含义 |
| --- | ---| 
| public | 类外都可以访问，默认为 public |
| internal | 模块内可以访问，如 library / application |
| private | 仅在类内可访问（或者文件内可见）|
| protected | 类内和子类可访问 |

### 子类和继承

只有被`open`修饰的类才能被继承，同理，只有被 `open` 修饰的类属性才能被重写。

### 抽象类和接口

鉴于 高封装、低耦合的原则，Kotlin 推荐使用接口多于抽象类。  
一个类可以实现多个接口、只能继承一个抽象类。  
在有多个方法和一两个默认实现的时候使用接口。  
任何时候，当不能完成一个类时，使用抽象类。  





