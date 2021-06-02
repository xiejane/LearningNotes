# RecyclerView

## 组成部分

### 布局代码

- layout.xml
    - 包含 RecyclerView 视图
- item.xml
    - 定义 RecyclerView 的单个元素样式


### 逻辑代码（Java代码）

- Fragment 类
    - 初始化 视图时声明并初始化 RecyclerView
```
        RecyclerView goodsRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recyclerSortGoods);
        GridLayoutManager layoutManager = new GridLayoutManager(mVmcSettings, mColumn);
        goodsRecyclerView.setLayoutManager(layoutManager);
        adapter = new SortGoodsAdapter();
        goodsRecyclerView.setAdapter(adapter);
```

- Adpater 类  
Adapter： 调用 Adapter 方法，绑定视图和数据  
自定义需要的 Adapter 类，实现对 item 的操作
    - 必须实现的方法
        - onCreateViewHolder: 绑定 item 的布局文件，并返回 ViewHolder
        - onBindViewHolder: 给 ViewHolder 绑定数据，用于显示在 UI 上
        - getItemCount: 返回列表需要显示的 item 数
    - ViewHolder： 列表中的每个元素都由 ViewHolder 对象定义，创建 ViewHolder 时,没有关联任何数据，创建后 RecyclerView 将其绑定至数据。
        - 需要实现的类，用于初始化 item 的组件，绑定布局文件内的组件，便于后续操作

- LayoutManager
    - LinearLayoutManager
    - GridLayoutManager
    - StaggeredGridLayoutManager

<meta http-equiv="refresh" content="1">