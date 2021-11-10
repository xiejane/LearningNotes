# Java 核心类

## StringBuilder

String: 支持直接拼接字符串，缺点是每次拼接时会创建新的字符串对象，然后扔掉旧的字符串。这样，绝大部分字符串都是临时对象，不但浪费内存，还会影响GC效率。
StringBuilder：
- 是一个可变对象，可预分配缓冲区，这样，往`StringBuilder`中新增字符时，不会创建新的临时对象。
- 可以进行链式操作。进行链式操作的关键是定义的`append()`方法会返回 `this`，这样，就可以不断调用自身的其他方法。

注意：对于普通的字符串`+`操作，并不需要我们将其改写为 `StringBuilder`，因为Java 编译器在编译时就自动把多个连续的 `+` 操作编码为 `StringContactFactory` 的操作。在运行期，`StringContactFactory` 会自动把字符串连接操作优化为数组复制或 `StringBuilder` 操作。

## StringJoiner

StringJoiner：用分隔符拼接数组。有两种构建方法，一种直接设置分隔号，另一种还支持指定“开头”和“结尾”
- `public StringJoiner(CharSequence delimiter)`
- `pulice StringJoiner(CharSequence delimiter, CharSequence prefix, Charsequence suffix)`

- `add(*)`返回`this`，也支持链式操作

String.join()：在不需要指定“开头”和“结尾”时更方便，该方法内部使用了 `StringJoiner` 来拼接字符串。
```java
String[] names = {"Bob", "Alice", "Grace"};
var s = String.join(", " names);// s = "Bob, Alice, Grace"
```

## 包装类型
Java 数据类型
- 基本类型：`byte`, `short`, `int`, `long`, `boolean`, `float`, `double`, `char`
- 引用类型：所有 `class` 和 `interface` 类型
引用类型可以赋值为 `null`，表示空，但基本类型不能赋值为 `null`。  
把基本类型视为对象（引用类型），则该对象称之为基本类型的包装类(Wrapper Class)，如 `Integer` 是 `int` 的包装类。

基本类型和包装类之间可以直接相互转换（编译器自动完成其转型）。
直接将基本类型变为包装类的赋值写法，称为自动装箱(Auto Boxing)；反过来，把包装类直接变为基本类型的赋值写法，称为自动拆箱(Auto Unboxing)
```java
Integer i = 100;
int x = n;
```

### 不变类
所有的包装类型都是不变类，即实例不能被修改的类。
对比包装类型的实例不能使用 `==` 比较，必须使用 `equals()` 比较。
