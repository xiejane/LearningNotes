# Class and Objects-Classes

## Classes
- 关键字：`class`
- 定义
    + 类名
    + class header：可选
        * type parameters
        * 主构造器
    + 类主体（大括号包含的内容）：可选
- 类成员
    + 构造器和初始化
    + 方法       
    + 属性
    + 嵌套类和内部类
    + 对象声明
- 构造器
    + 每个类都可以有一个主构造器和多个其他构造器
    eg: `class Person constructor(firstName: String) { /*...*/ }`
    + 主构造器
        * 在`class header`处定义
        * 没有注解限定词或者可见性限定词时，`constructor`可省略
        * 构造内的参数格式可采用尾迹逗号格式（最后一个参数也带分号）
    + 构造器内不能有代码，而是通过`init`结构内初始化实现
    + 其他构造器
        * 使用前缀标识 `constructor`
    + 空构造器
- 实例化
    + 不使用`new`


## 属性
- 声明属性的关键字
    + `val`：不可变变量
    + `var`：可变变量
- 获取属性，直接使用属性名`className.propertyName`
- 声明属性的完整语法: 
```
// []： 表明可选项
var <propertyName>[:<propertyType] [=<property_initializer]
    [getter]
    [setter]
```
- `setter`的默认参数名为 value，标识符`field` 只能在获取属性时使用
```
var id:Int =9
    set(value){
        field = value 
        // id = value //ERROR Stackflow: Using actual name "id" would make setter recursive 
    }
```
- 修改属性的可见性
```
var setterVisibility : String ="abc"
    private set
```

- 懒加载（延后设置属性变量的初始值）
    + 标识符：`lateinit`
    + 在懒加载变量赋初值之前被调用会报错
    + 判断懒加载变量是否已初始化：`.isInitialized`
    ```
    if (foo::bar.isInitialized){
        println(foo.bar)
    }
    ```


## Inheritance
- 在 kotlin 中，所有的类都有一个公共父类——`Any`，这个父类不需要声明
    - `Any`拥有的三个定义给所有类的方法
        + `equals()`
        + `hashCode()`
        + `toString()`
- 默认情况下，所有的类都是不可继承的，如果要使一个类可以被继承，则需要将这个类用`open`标识
- 继承类时，将继承的父类放在 class header 后，通过冒号`:`隔开，例如：
```
open class Base(p: Int)
class Derived(p: Int) : Base(p)
```
    - 如果被继承的类有主构造器，则子类在继承时必须要 class header 处用父类的参数进行初始化
    - 如果父类没有主构造器，那么其他构造器必须通过关键字`super`进行初始化
    ```
    class MyView : View {
        constructor(ctx: Context) : super(ctx)

        constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)
    }
    ```

###  重写函数
- 被重写的函数必须使用关键字`open`，重写的函数必须使用关键字`override`
```
open class Shape {
    open fun draw() { /*...*/ }
    fun fill() { /*...*/ }
}

class Circle() : Shape() {
    override fun draw() { /*...*/ }
}
```
- 使用`override`的函数默认是open的，如果不想被再次重写，可使用标识符`final`，则该已重写的函数不可再被重写

### 重写属性
> 与重写函数类似

- val 属性可被重写为 var 属性，反之不行
- 重写属性可直接在主构造器中使用`class Rectangle(override val vertexCount: Int = 4) : Shape `

### 派生类初始化顺序
- 父类的构造器先执行，再执行子类的构造器
- 父类需要使用`open`标识符，属性进行初始化或在`init`模块内进行初始化

### 调用父类的实现
- 使用关键字`super`

### 重写的规则
- 如果一个类从它的直接父类继承了同一个成员的多个实现，它必须覆盖这个成员并提供它自己的实现


## 接口
- 定义，使用关键字`interface`
- 一个类或者对象，可以实现一个或多个接口
- 属性：可定义为抽象属性或者是为访问器提供实现
- 接口可继承至其他接口
    + 需要实现父接口的属性/方法
    + 需要声明新的属性和方法
    + 实现子接口的类可以不用再实现已实现的属性/方法 