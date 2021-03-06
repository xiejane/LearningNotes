# Compose 编程思想

Jetpack Compose 是一个适用于 Android 的新式***声明性界面工具包***。Compose 提供声明性 API，让您可在不以命令方式改变前端视图的情况下呈现应用界面，从而使编写和维护应用界面变得更加容易。

## 声明性范式编程

在过去的几年中，整个行业已开始转向声明性界面模型，该模型大大简化了与构建和更新界面关联的工程设计。该技术的工作原理是在概念上从头开始重新生成整个屏幕，然后仅执行必要的更改。此方法可避免手动更新有状态视图层次结构的复杂性。Compose 是一个声明性界面框架。

## 简单的可组合函数

使用`Compose`，您可以通过定义一组接受数据而生成界面元素的可组合函数来构建界面。

可组合函数需要注意的点：
- 此函数带有`@Compose` 注释，所有可组合函数都必须带有此注释。
	+ 该注释告知 Compose 编译器：将数据转换为界面
- 此函数接收数据。
	+ 组合函数可以接收一些参数，这些参数可让应用逻辑描述界面。
- 此函数可以在界面中显示文本（示例函数使用了`Text()`）。调用 `Text()`函数，实际上会创建文本界面元素。
	+ 可组合函数通过调用其他可组合函数来生成界面层次结构。
- 此函数不会返回任何内容。
	+ 生成界面的 Compose 函数不需要返回任何内容，因为它们描述所需的屏幕状态，而不是构造界面微件。
- 此函数快速、幂等且没有副作用
	+ 使用统一参数多次调用此函数时，它的行为方式相同，并且它不使用其他值。
	+ 此函数描述界面而没有任何副作用，如修改属性或全局变量。


## 声明性范式转变

在 Compose 的声明性方法中，微件相对无状态，并且不提供 setter 或 getter 函数。实际上，微件不会以对象形式提供。您可以通过调用带有不同参数的同一可组合函数来更新界面。

当用户与界面交互时，界面会发起 `onClick` 等事件。这些事件应通知应用逻辑，应用逻辑随后可以改变应用的状态。当状态发生变化时，系统会使用新数据再次调用可组合函数。这会导致重新绘制界面元素，此过程称为“**重组**”。

## 动态内容
由于可组合函数是用 Kotlin 而不是 XML 编写的，因此它们可以像其他任何 Kotlin 代码一样动态。

## 重组
在命令式界面模型中，如需更改某个微件，您可以在该微件上调用 setter 以更改其内部状态。在 Compose 中，您可以使用新数据再次调用可组合函数。这样做会导致函数进行重组 -- 系统会根据需要使用新数据重新绘制函数发出的微件。Compose 框架可以智能地仅重组已更改的组件

重组是指在输入更改时再次调用可组合函数的过程。当函数的输入更改时，会发生这种情况。当 Compose 根据新输入重组时，它仅调用可能已更改的函数或 lambda，而跳过其余函数或 lambda。通过跳过所有未更改参数的函数或 lambda，Compose 可以高效地重组。

切勿依赖于执行可组合函数所产生的附带效应，因为可能会跳过函数的重组。附带效应是指对应用的其余部分可见的任何更改。例如，以下操作全部都是危险的附带效应：
- 写入共享对象的属性
- 更新 `ViewModel` 中的可观察项
- 更新共享偏好设置

在Compose编程中的注意事项：
- 可组合函数可以按任何顺序执行。
	+ 如果某个可组合函数包含对其他可组合函数的调用，这些函数可以按任何顺序运行。
- 可组合函数可以并行执行。
	+ 为了确保应用正常运行，所有可组合函数都不应有附带效应，而应通过始终在界面线程上执行的 onClick 等回调触发附带效应。
- 重组会跳过尽可能多的可组合函数和 lambda。
- 重组是乐观的操作，可能会被取消。
	+ 只要 Compose 认为某个可组合项的参数可能已更改，就会开始重组。重组是乐观的操作，也就是说，Compose 预计会在参数再次更改之前完成重组。如果某个参数在重组完成之前发生更改，Compose 可能会取消重组，并使用新参数重新开始。
- 可组合函数可能会像动画的每一帧一样非常频繁地运行。
	+ 如果您的可组合函数需要数据，它应为相应的数据定义参数。然后，您可以将成本高昂的工作移至组成操作线程之外的其他线程，并使用 `mutableStateOf` 或 `LiveData` 将相应的数据传递给 Compose。
