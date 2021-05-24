# RecyclerView

## 组成部分

### 布局代码

layout.xml
  - 包含 RecyclerView 视图
item.xml
  - 定义 RecyclerView 的单个元素样式


### 逻辑代码（Java代码）

RecyclerView 类

ViewHolder： 列表中的每个元素都由 ViewHolder 对象定义，创建 ViewHolder 时,没有关联任何数据，创建后 RecyclerView 将其绑定至数据。

Adapter： 调用 Adapter 方法，绑定视图和数据

LayoutManager