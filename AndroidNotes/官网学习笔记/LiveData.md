# LiveData

liveData 是一种可观察的数据存储器类，LiveData 具有生命周期感知能力，意指它遵循其他应用组件（如 Activity、Fragment、Service）的生命周期。

LiveData 只会将更新通知给活跃的观察者（生命周期处于`started 或 resumed`状态）。 

- 使用 LiveData 的优势
    - 确保界面符合数据状态  
      不需要在每次应用数据发生变化时更新界面，而是在 `observer` 对象中更新界面
    - 不会发生内存泄露  
      观察者绑定到 `Lifecycle` 对象，并在其关联的生命周期遭到销毁后进行自我清理
    - 不会因 Activity 停止而导致崩溃  
      观察者的生命周期处于非活跃状态，则不会接收任何 LiveData 事件
    - 不再需要手动处理生命周期  
      界面组件只是观察相关数据，不会停止或恢复观察。LiveData 将自动管理这些操作，因为它在观察时可以感知相关的生命周期状态变化
    - 数据始终保持最新状态  
    - 适当的配置更改  
    - 共享资源
      使用单例模式扩展 LiveData 对象以封装系统服务，以便在应用中共享它们。LiveData 对象连接到系统服务一次，然后需要相应资源的任何观察者只需观察 LiveData 对象

- 使用 LiveData 对象
    - 创建 LiveData 的实例以存储某种类型的数据。这通常在 ViewModel 类中实现
    - 创建可定义 `onChanged()` 方法的`Observer`对象，该