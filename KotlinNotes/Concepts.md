# Concepts-type&control_flow
[TOC]

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
