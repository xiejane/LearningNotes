# RecyclerView内的不同布局

## 使用不同的数据源和布局样式

在`adapter`内创建不同的`ItemType`，并为不同的`ItemType`创建对应的`ViewHolder`，每个`ItemType`都需要一个布局文件和数据源。

```java

    public enum ItemType {
        ITEMCTRL, ITEMSUGAR
    }

    // 必须重写该方法
    @Override
    public int getItemViewType(int position) {
        int num = 0;
        if (!Utils.listIsEmpty(list1)){
            num = list1.size();
        }
        if (position < num){
            return ItemType.ITEMSUGAR.ordinal();
        }else {
            return ItemType.ITEMCTRL.ordinal();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        if (viewType == ItemType.ITEMSUGAR.ordinal()){
            return new ViewHolderSugar(LayoutInflater.from(mContext).inflate(R.layout.a_layout_item_sugar, viewGroup, false), mListener);
        } else if (viewType == ItemType.ITEMCTRL.ordinal()){
            return new ViewHolderCtrl(LayoutInflater.from(mContext).inflate(R.layout.a_layout_item_sugar_ctrl, viewGroup, false), mListener);
        }
        return null;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ViewHolderSugar){
            if (!Utils.listIsEmpty(list1)){
                Log.d(TAG, "onBindViewHolder: sugar");
                CottonCandyCtrlBean bean = list1.get(i);
                ((ViewHolderSugar) viewHolder).mText.setText(bean.getName());
                ((ViewHolderSugar) viewHolder).mButton.setText(bean.getName());
                ((ViewHolderSugar) viewHolder).mIcon.setImageResource(R.drawable.icon_sugar);
            }
        }else if (viewHolder instanceof ViewHolderCtrl){
            if (!Utils.listIsEmpty(list2)){
                Log.d(TAG, "onBindViewHolder: control");
                CottonCandyCtrlBean bean = list2.get(i-list1.size());
                ((ViewHolderCtrl) viewHolder).mButton2.setText(bean.getName());
            }
        }
    }

```

## GridLayoutManager 支持不同列数的布局

> 如果不需要每个`ItemType`的列数不同，则不用处理这一步

GridLayoutManager 支持通过更改`SpanSizeLookup`，更改列数。


```java
    // 常用的构造方法
    public GridLayoutManager(Context context, int spanCount) {
        super(context);
        this.setSpanCount(spanCount);
    }

```
参数：`spanCount` 每行排列 item 数
参数：`spanSize` 每个 item 的占位比重
item 数等于`spanCount/spanSize`

在默认情况下，`spanSize = 1`, 创建 GridLayoutManager 时传入的`spanCount`就是列数，因此我们可以根据需要，修改这两个参数，使其有不同的列数。

`spanSize`是从`SpanSizeLookup`中获取的，因此修改该参数需要我们新建一个自己的类继承自`SpanSizeLookup`，只需要重写`getSpanSize`方法即可。

```java

    class MySpanSize extends GridLayoutManager.SpanSizeLookup{
        @Override
        public int getSpanSize(int position) {
            if (pisition < 4){ 
                return span;
            }else if ( position < 22){
                return mColumn;
            }else {
                return 24;
            }
        }
    }
    // 传入GridLayoutManager
    GridLayoutManager manager = new GridLayoutManager(this.mVmcSettings, 24);
    manager.setSpanSizeLookup(new MySpanSize());
```

