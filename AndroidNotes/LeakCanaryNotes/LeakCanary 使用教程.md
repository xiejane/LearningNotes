# LeakCanary 使用教程

## LeakCanary

一个探测安卓项目内存泄露情况的库
[开源仓库](https://github.com/square/leakcanary)

## 开始

在 app/build.gradle 下添加依赖

```
dependencies {
  // debugImplementation because LeakCanary should only run in debug builds.
  debugImplementation 'com.squareup.leakcanary:leakcanary-android:{{leak_canary.release }}'
}
```

不需要其他操作，运行 app 后即可进行内存泄露的检测

- 可检测的泄露情况
    + 销毁 `Activity` 实例
    + 销毁 `Fragment` 实例
    + 销毁 fragment 的 `view` 实例
    + 销毁 `ViewModle` 实例

## Fundamentals

### 内存泄露
内存泄露：应用长期保持一个不再需要的对象的引用。即不需要的引用未及时销毁。

- 常见内存泄露
    + `backstack`内的`Fragment`的实例在销毁 Fragment 视图的时候没有被情况
    + 将 `Activity` 实例作为 `Context` 字段，存储在一个对象中，由于配置改变这个对象被重新创建
    + 在生命周期内，注册了一个指向对象的 监听器、广播接收器或者 RxJava 的订阅，但是在生命周期结束后没有被注销

### LeakCanary 的工作流程

#### 1.Detecting retained objects 探测仍存活的对象
LeakCanary 钻入 Android 的生命周期内部，在`Activities and Fragments` 被销毁、进行垃圾回收时，进行探测。这些被销毁的对象的 <font color=red>弱引用</font> 被存储在了一个`ObjectWatcher`内。
可以自定义观测任何一个不再需要的对象：
`AppWatcher.objectWatcher.watch(myDetachedView, "View was detached")`

如果在等待5s后并且执行了垃圾回收后，这个弱引用还没有被清除，那么就认为这些对象仍存活，并且很有可能会导致泄露。
LeakCanary 的日志：
```
D LeakCanary: Watching instance of com.example.leakcanary.MainActivity
  (Activity received Activity#onDestroy() callback) 

... 5 seconds later ...

D LeakCanary: Scheduling check for retained objects because found new object
  retained
```
LeakCanary 在 dumping the heap 之前会等待存活的对象数达到一个限定值，然后在探测到最后一个存活对象时将提示信息在弹窗内弹出。

- 默认的限定值
    + app 可见：5 （前台运行）
    + app 不可见：1（后台运行）


#### 2.Dumping the heap 转存堆
当存活的对象数达到限定值后，LeakCanary 将 java 的 heap 备份到一个`.hprof`文件内，并将该文件存储到 Android 的文件系统。


#### 3.Analyzing the heap 分析堆
#### 4.Categorizing leaks 分类泄露

