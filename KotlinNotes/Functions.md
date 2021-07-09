# Functions

## Basic
- 调用无参函数时，需要空括号
- 参数
    + 建议使用尾迹逗号：在最后一个参数后面仍添加逗号
    + 默认参数： 赋值在类型后面
    ```
    fun read(b: ByteArray,off:Int=0,)
    ```
        - 重写函数时，如果父函数有默认参数，子函数必须省略默认参数值
        - 默认参数后面的参数如果没有默认值，函数调用时，必须输入参数名字
        - 如果默认参数后的最后一个参数是lambda函数，既可以作为命名参数，也可以放在函数外
        ```
        fun foo(
            bar:Int =0,
            baz:Int=1,
        qux:()->Unit,){/*...*/}

        foo(qux={println("hello")})  //命名参数形式
        foo{println("hello")}  //函数外形式
        ```
    + 不定长参数`vararg`
        * 只有一个参数能被定义为不定长参数
        * 当不定长参数不是最后一个参数时，其后面的参数传参时需要采用命名参数形式，如果不定长参数后的参数为函数，需要采用lambda函数形式放在括号外
        * 使用扩展操作符`*`将数组内的元素一个一个地作为参数传递
        ```
        fun <T> asList(vararg ts: T): List<T> {
            val result = ArrayList<T>()
            for (t in ts) // ts is an Array
                result.add(t)
            return result
        }
        val a = intArrayOf(1,2,3,4)
        val list = asList(-1,0,*a,4)
        ```
- 无返回类型函数：Unit，可省略
- 单行表达式函数，当函数体只有一句表达式时，可省略花括号，直接在函数名后赋值
`fun double(x:Int):Int = x*2`
- 函数体有多行表达式时，需要明确返回类型（无返回类型除外）
- infix notation
    + 含义：使用了`infix`关键字的函数，调用时不需要使用`.`和括号`()`
    + 什么函数可以使用`infix`
        * 成员函数或扩展函数（extension functions）
        * 只有一个参数
        * 没有默认参数值，也不是变长参数
    ```
    infix fun Int.shl(x:Int):Int{...}
    1 shl 2 //等价于1.shl(2)

    ```
    + infix 函数的优先级低于：数据操作符、类型转换和`rangeTo`操作符
    + infix 函数优先级高于：布尔操作符（`&&`和`||`），判断操作符`is`和`in`
    + infix 函数必须明确接收对象和参数
    ```
    // add is an infix function
    this add "abc"  // Correct
    add("abc")  // Correct 
    add "abc"  // InCorrect
    ```

- 函数范围
    + 本地函数：函数内的函数
    + 成员函数：类/object内的函数
    + Generic functions:`fun <T> singletonList(item: T): List<T> { /*...*/ }`
    + 尾迹回归函数
        * 使用关键字: `tailrec`
        * 在函数运行时，最后一个操作必须时调用其自身（回归调用）

## 高阶函数和 lambda 函数

### 高阶函数
- 含义： 以函数为参数，或返回值
- 函数类型
    + 所有的函数类型都有`参数列表`(A,B)和`返回值类型`(C)，eg:`(A,B)->C`
        * 参数列表可为空,`()->A`
        * 无返回类型(`Unit`)不可省略,`()->Unit`
    + 函数类型可以选择添加接收者：`A.(B)->C`
        * A：接收者
        * B：参数列表
        * C：返回值类型
    + suspending functions 是一种特殊的函数类型，使用关键字`suspend`，eg:`suspend ()->Unit`
    + 参数名可选，一般用于文档内解释参数的含义
    + 指定函数类型可为空时，格式：`((Int,Int)->Int)?`
    + 函数类型定义可通过括号进行组合，如：`(Int)-((Int)-Unit)`

- 实例化函数类型
    + 使用代码块
        * lambda表达式: `{a,b -> a+b}`
        * 匿名函数：`fun(s:String): Int { return s.toIntOrNull() ? 0}`
    + 使用现有定义的可调用引用
        * a top-level, local, member, or extension function: `::isOdd`, `String::toInt`
        * a top-level, member, or extension property: `List<Int>::size`
        * 构造函数：`::Regex`
    + 使用将函数类型定义为接口的自定义类的实例
    ```
    class IntTransformer: (Int) -> Int {
        override operator fun invoke(x: Int): Int = TODO()
    }

    val intFunction: (Int) -> Int = IntTransformer()
    val a = { i: Int -> i + 1 } // The inferred type is (Int) -> Int

    ```
        * 如果函数类型的参数值是`non-literal`的，那么有接受者和无接收者可以进行互换（接收者作为参数列表内第一个参数）
        `(A,B)->C equals A.(B)->C`
- 调用函数类型实例
    + 调用方法
        * 使用操作符：`invoke(...)`，eg:`f.invoke(x)`
        * 直接调用：`f(x)`
    + 如果有接收者类型，需要将接收者作为第一个参数
        * 也可以采用扩展函数类型，`1.foo(2)`

### lambda 函数
- lambda表达式和匿名函数都是字面量函数
- 字面量函数：没有被定义，但直接作为表达式使用。eg:`max(strings, {a,b->a.length<b.length})`

- Function types:
    + 格式： `(Int)->String`
        * ()内为参数类型，参数类型列表可为空
        * ->后为返回值类型，Unit返回值类型必须写
    + 格式： `A.(B) -> C`, 可以有格外的接收类型
    + 格式： `suspend () -> Unit` / `suspend A.(B) -> C`

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

- 匿名函数
    + 指定函数的返回值类型
    + 与一般的函数类似，只是省略了函数名
    + 如果可以通过上下文推测参数的类型，则可省略
    + 匿名函数的参数必须放在括号内（lambda函数除外）

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

## Inline 函数
- 高阶函数会增加运行时开销
    + 高阶函数访问的函数是独立的对象，对象自身形成闭包，访问闭包内的内容只能在自己内部进行。因此高阶函数需要调用函数时需要跳转至函数内部，调用之后再回到高阶函数本身，导致开销增加。
    + 使用内联lambda表达式可以消除这种开销。
- 关键字：`inline`，影响函数本身和传递给它的lambda函数。
- 使用内联函数时，避免内联大型的函数。

- noinline
    + 只想传递部分inline函数，不想inline的函数使用`noinline`关键字
    `eg: inline fun foo(inlined: () -> Unit, noinline notInlined: () -> Unit) { ... }`
    + inline 函数只能被包含它的函数调用，noinline的函数，可以被任意函数调用

- Reified type parameters
    + 限定词：`reified`
    + 只能在inline函数上使用
    + 使用`reified`修饰的type parameter在函数内部也可以被使用