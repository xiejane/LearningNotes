# Java 核心类

## JavaBean
> 是一种规范，如果class的定义符合以下规范，则称该类为JavaBean  
> 1： 若干`private`实例字段  
> 2： 通过`public`方法来读写实例字段

JavaBean主要用来传递数据，即把一组数据组合成一个JavaBean便于传输。此外，JavaBean可以方便地被IDE工具分析，生成读写属性的代码，主要用在图形界面的可视化设计中。

JavaBean是一种符合命名规范的class，它通过getter和setter来定义属性

属性是一种通用的叫法，并非Java语法规定

可以利用IDE快速生成getter和setter

使用Introspector.getBeanInfo()可以获取属性列表

## 枚举类
- 特点
    - 定义的enum类型总是继承自java.lang.Enum，且无法被继承
    - 只能定义出enum的实例，而无法通过new操作符创建enum的实例
    - 定义的每个实例都是引用类型的唯一实例
    - 可以将enum类型用于switch语句
- 判断枚举常量的名字，要始终使用name()方法，绝不能调用toString()
- 可以为enum编写构造方法、字段和方法
- enum的构造方法要声明为private，字段强烈建议声明为final

## 反射
通过Class实例获取class信息的方法称为反射（Reflection）
