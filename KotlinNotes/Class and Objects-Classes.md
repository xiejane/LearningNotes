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
    + 构造器内不能有代码，而是通过`init`结构内初始化实现
    + 

## 属性
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
