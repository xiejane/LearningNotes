# Concepts

## Types

- smart casts: 可使用对象
    + `val`本地变量：
    + `val`属性： private、internal属性，在同一个模块内进行了校验
    + `var`本地变量：在比较和使用之间没有被改变（包括没有被lambda表达改变，不是 local delegated property)
    + `var`属性 ：不能被其他代码改变

- delegated property:
    +  lazy properties
    +  Observable properties
    +  Storing properties in a map

- 强制类型转换 `as`
    + null 不能转换为其他不可为null的类型
    + `val x:String? = y as? String`


## Contrl flow

### Condition and loops
- if: 在 kotlin 中作为表达式，可以有返回值，此时，else 必须有
- when: 在 kotlin 中，可以作为表达式也可以作为 statement（类似于switch），同样作为表达式时，else 也必须有
    + 任意表达式
    ```
    when(x){
        0,1 ->print("x ==0 or x==1")
        else ->print("otherwise")
    }
    ```
    + `in` / `!in`
    + `is` / `!is`
    + 无参数时可作为 `if-else`的替代: 
    ```
    when{
        x.isOdd() -> print("x is old")
        y.isEven() -> print("y is even")
        else -> print("x+y is odd")
    }
    ```

- for loops: 提供迭代器，类似于`foreach`，语法：`for (item in collection) print(item)`
- for loop 提供迭代器的含义
    + 有函数或继承了函数`iterator()`，返回 `Iterator<>`
    + 有或者继承了`next()`
    + 有或者继承了`hasNext()`
```
for((index,value) in array.withIndex){
    println("the element at $index is $value")
}

for(i in array.indeices){
    println(array[i])
}
```

- while loops : 与其他语言的类似

### Returns and jumps

- 结构性跳转表达式
    + return： 默认从最直接包围它的函数或者匿名函数返回
    + break：终止最直接包围它的循环
    + continue：继续下一次最直接包围它的循环

- 标签（lable)
    + 格式：`lableName@`
    + 使用方法： 表达式前加标签
    + 用途
        * 使用标签限制 `break` 或 `continue`
        * 从标签处返回
        ```
        fun foo(){
            ints.foreach lit@ {
                if (it ==0 ) return@lit
                print(it)
            }
            // 隐式标签
            ints.foreach{
                if(it==0) return@foreach
                print(it)
            }
        }
        ```
        * 


## Functions

### Lambdas

- Higher-order functions: 把函数作为参数，或者返回函数的函数

- Function types:
    + 格式： `(Int)->String`
        * ()内为参数类型，参数类型列表可为空
        * ->后为返回值类型，Unit返回值类型必须写
    + 格式： `A.(B) ->C`, 可以有格外的接收类型
    + 格式： `suspend () ->Unit` / `suspend A.(B) -> C`

- lambda 表达式语法： `val sum:(Int,Int)->Int = {x:Int, y:Int -> x+y`
    + lambda 表达式被大括号包围
    + 参数类型声明在大括号内，且参数类型可选
    + 函数主体在`->`后
    + 如果函数的返回值类型不是`Unit`，那么，函数体的最后一行表达式作为返回值

- lambda 表示式仅留必需项：`val sum ={x:Int,y:Int -> x+y}`
- 如果一个函数的最后一个参数是函数，那么当传递的函数参数是lambda函数时，lambda函数可写在小括号外。这样的语法叫做`尾迹lambda`

- it: 单个参数的隐式名称
    + 参数使用隐式名称时，lambda表达式可省略参数声明和`->`
- 没有使用的参数，可用下划线`_`替代
- `map.forEach {_, value -> println("$value") }`


- lambda 函数和匿名函数的对比

|函数|作为参数|return|
|---|---|---|
|lambda 函数|最后一个参数，可写在函数外|返回至最外层的调用函数外|
|匿名函数|必须写在调用函数的括号内|返回至匿名函数外|

- Closure
    + `这是 Theoritical computer science 里出的术语：不引用外部变量的 lambda 表达式是「封闭的」（Closed），那么把「开放的」表达式「封闭住」的东西，就是 Closure 了`
    + Closure 指的是函数
    + case: 
    ```
    var sum =0 //sum is bound variable
    ints.filter {it>0}.forEach{
        sum+=it // sum is free variable
    }
    print(sum)
    ```

|variable|meaning|
|---|---|
|free variable|variable that used in closure but defined outside closure|
|bound variable|local variable/golable variable|

## Classes and objects

### Classes
- 类成员
    + 构造器和初始化
    + 方法
    + 属性
    + 嵌套类和内部类
    + 对象声明

### 属性
- 获取属性，直接使用属性名`className.propertyName`
- 声明属性的完整语法: 
```
// []： 表明可选项
var <propertyName>[:<propertyType] [=<property_initializer]
    [getter]
    [setter]
```
- `setter`的默认参数名为 value: 
```
var id:Int =9
    set(value){
        field = value //
    }
```
- 修改属性的可见性
```
var setterVisibility : String ="abc"
    private set
```
- 