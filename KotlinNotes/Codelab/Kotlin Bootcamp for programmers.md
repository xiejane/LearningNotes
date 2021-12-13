# Kotlin Bootcamp for programmers

## 1. Get starts

Kotlin 专注于干净、易读和代码安全。
kotlin的优势
- 健壮的代码
- 成熟的平台
- 简洁、可读性高的代码
- 可与 Java 交互

在 IDEA 内 Select `Tools > Kotlin > Kotlin REPL `to open the REPL（交互式命令行），采用 `Control+Enter`执行代码。

## Kotlin Basic

在 kotlin 中，数字可被看作为对象，数字可直接调用方法。
如： `2.4.div(2) ==> 1.2`，在这个过程中，数字`2.4`被自动封装为对象，可调用`number`相关的方法。

kotlin 无法进行隐式的类型转换，因此，不能直接将一个 `short` 型值赋值给`long`型变量。但是可进行强制类型转换 使用 `.toxxx()方法`
```kotlin
val b: Byte = 2
val i: Int = b.toInt()
```