# Fragment

## Create

- Environment
    ```
    // Groovy
    dependencies {
    def fragment_version = "1.3.6"

    // Java language implementation
    implementation "androidx.fragment:fragment:$fragment_version"
    // Kotlin
    implementation "androidx.fragment:fragment-ktx:$fragment_version"
}
    ```


## FragmentManager
> FragmentManager 负责执行 fragment 的操作，包括 新增、移除、替换或者放在后台栈中

### 获取 FragmentManager

- 通过 Activity 获取
    + `getSupportFragmentManager()`
- 通过 Fragment 获取
    + `getChildFFragmentManager()`  管理子Fragment(fragment's children)
    + `getParentFragmentManager()`  管理主Fragment
    ![fragment的关系](../../resources/fragments.png)
- 子 Fragment
    + 使用场景：同时显示多个 Fragment，例如 split-view、 dashboard
        * 举例
            - Screen slides
            - Sub-navigation with in a set of related screens
            - Jetpack Navigation

### 使用 FragmentManager

被同时提交的一系列的操作（一个操作集）被认为是一个操作单元，叫做`FragmentTransaction`

`addToBackStack()` 被调用后，transaction 被压入 `back stack`中，当用户点击返回时，会被出栈，操作是可逆的。如果 transaction 没有调用该方法，则这些操作不可逆，例如，当你执行一个移除某个 `fragment`的操作，当这个 transaction 被提交后，这个`fragment`就被销毁了，用户也无法再返回这个`fragment`。如果调用了`addToBackStack()`被 remove 的这个`fragment`的状态只是变为了`STOPPED`,当用户点击返回时，这个`fragment`仍可以被访问，其状态变为`RESUMED`.


### 执行 transaction

使用 `FragmentManager` 来创建一个 `FragmentTransaction`: 
```kotlin
supportFragmentManager.commit {
   replace<ExampleFragment>(R.id.fragment_container)
   setReorderingAllowed(true)
   addToBackStack("name") // name can be null
}
```
`setReorderingAllowed(true)` 优化了 transaction 内的状态切换，使动画和过渡更流畅。


#### 获取已存在的 fragment

通过调用`findFFragmentById()`在 layout 容器中获取已存在的 Fragment，而不是在 fragment 在 `FragmentTransaction`中被添加时通过 XML 或者容器ID 来获取。

示例：
```kotlin
supportFragmentManager.commit {
   replace<ExampleFragment>(R.id.fragment_container)
   setReorderingAllowed(true)
   addToBackStack(null)
}

...

val fragment: ExampleFragment =
        supportFragmentManager.findFragmentById(R.id.fragment_container) as ExampleFragment
```

另一种方式是通过 Tag 来获取。

- 设置 Tag
    + 在 XML 布局文件中使用`android:tag`属性
    + 在 `FragmentTransaction` 中设置，当使用 `add() or replace()`操作时

示例：
```kotlin
supportFragmentManager.commit {
   replace<ExampleFragment>(R.id.fragment_container, "tag")
   setReorderingAllowed(true)
   addToBackStack(null)
}

...

val fragment: ExampleFragment =
        supportFragmentManager.findFragmentByTag("tag") as ExampleFragment
```

