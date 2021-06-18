# Activity
![](../resources/应用基础信息.png)
<meta http-equiv="refresh">

## 生命周期

- onCreate：定义用户界面的布局，创建视图、绑定数据
- onStart：将 Activity 显示在前台、跟用户交互前的最后准备，对用户可见
- onResume：与用户交互
- onPause：与用户无交互，有页面显示，无交互动作
- onStop：对用户不可见，屏幕被别的 Activity 占用
- onRestart：重新获得屏幕，并且已从 `onStop`状态结束的地方（保存当时的数据等）重新开始
- onDestory：生命周期的最后一个阶段，确保所有的资源已被释放