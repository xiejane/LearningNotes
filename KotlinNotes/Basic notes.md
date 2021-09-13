# Basic notes

[TOC]

Kotlin 是一种静态类型的语言，意味着，类型将在编译时解析且从不改变。

## 基本数据类型

+ 基本数值类型
    + Byte
    + Short
    + Int
    + Long
    + Float
    + Double

+ 比较两个数字
    + Kotlin 中没有基础数据类型，只有封装的数字类型。
    + 每定义的一个变量，是 Kotlin 封装的一个对象，以此保证不会出现空指针
    + 三个等号`===`比较对象地址
    + 两个等号`==`比较两个值大小 

+ 类型转换
    + 较大类型可以隐式转换为较小类型
    + 较小类型不能隐式转换为较大类型
        * toByte(): Byte
        * toShort(): Short
        * toInt(): Int
        * toLong(): Long
        * toDouble(): Double
        * toChar(): Char
    + eg: 
        ```
          val b:Byte = 1
          val i:Int = b.toInt()
        ```
    - 位操作符
        - shl(bits)-左移
        - shr(bits)-右移
        - ushr(bits)-无符号右移
        - and(bits)-与
        - or(bits)-或
        - xor(bits)-亦或
        - inv()-反向

    - 字符
        - Char 不能直接和数字操作，必须是单引号包含起来的
        - 特殊字符可支持反斜杠转义

## 条件表达式
- if
    - if 作为表达式，必须有配套的else语句
```
val answerString: String = if(count == 42){
  "I have the answer"
}else{
  "The answer is close"
}
```

- when，与 switch 语句类似
```
val answerString: String = when{
  count == 42 -> "I have the answer"
  count > 35 -> "The answer is close"
}
```

## 智能类型转换
使用条件语句来检查变量是否包含对 null 值的引用
```
val languageName: String?=null
if(languageName != null){
  // No need to write languangeName?.toUpperCase()
  println(languageName.toUpperCase())
}
```
智能类型转换适用于：null 检查，类型检查，符合合约(contracts)的任何条件

## 函数
声明：`fun functionName(params:type): returnType{body}`
无返回值： Unit 可以省略
`fun functionNsme(parms:type)：Unit{}`

函数主体可以是表达式：
```
fun sum(a:Int, b:Int)=a+b
```

## 基本语法
- 程序入口
    + `fun main(){println("Hello world!")}`
    + 
    ```
    fun main(args: Array<String>){
      println(args.contentToString())
    }
    ```

- 变量
    + 不可变变量： val
    + 可变变量： var

- 类
    + 类属性，可声明在类主体结构内，也可以声明在类的声明里
    + 
    ```
    class Rectangle(var height:Double, var length: Double){
      var perimeter = (height+length)*2
    }
    ```
    + 适用 `open` 标记类可被继承：`open class Shape`
    + 继承： `(:)`
    ```
    class Rectangle(var height:Double, var length: Double): Shape(){
      // body
    }
    ```

- 字符串模板
    + ${variable}
    ```
    var a =1
    var s1="a is $a"
    a = 2
    var s2 = "${s1.replace("is","was")}, but now is $a"
    ```

- 循环
    + for 循环
    ```
    val items = listOf("apple","banana","kiwifruit")
    for (item in items){
      println(item)
    }
    ```
    + while 循环
    ```
    val items = listOf("apple","banana","kiwifruit")
    var index =0
    while(index<items.size()){
      println("item at $index is ${items[index]}")
      index++
    }
    ```
    + range 范围`in`
    ```
    val x = 10
    val y = 9
    if(x in 1..y+1){
      println("fits in range")
    }
    // 间隔
    for(x in 1..10 step 2){
      print(x)  //1,3,5,7,9
    }

    // 逆序
    for(x in 9 downTo 0 step 3){
      print(x) //9,6,3,0
    }
    ```

- 集合
    + 迭代
    ```
    for (item in items){
      println(item)
    }
    ```
    + 是否包含于集合`in`
    ```
    when{
      "orange" in items -> println("juicy")
      "apple" in items -> println(" apple is fine too")
    }
    ```
    + 用lamda表达式过滤和map集合
    ```
    val fruits =listOf("banana","avocado","apple","kiwifruit")
    fruits.filter{it.startWith("a")}
          .sortedBy{it}
          .map {it.uppercase}
          .forEach(println(it))
    // APPLE
    // AVOVADO
    ```

- 可为null的值和null校验
    + 可为null的值，在变量类型后有`?`符号
    + 校验：使用判断

- 类型判断和自动转换
    + `is` 是
    + `!is` 不是

