# Android UI 开发详解

## Layout_gravity 和 gravity 的区别
[来源](https://blog.csdn.net/eclipsexys/article/details/8683026)

- 属性对比
    + layout_gravity：表示组件自身在父组件中的位置
    + gravity：表示组件的子组件在组件中的位置
- 使用注意：最外层的 LinearLayout 的属性`androidrientation="?"`是不能少的
    + `androidrientation="vertical"`
        * `android:layout_gravity="?"`只有设置横向的时候才生效
            - eg：left / right / center_horizontal
    + `androidrientation="horizental"`
        * `android:layout_gravity="?"`只有设置纵向的时候才生效
            - eg：top / bottom / center_vertical
- center 属性：不管横向纵向，总有一个方向起作用

## Layout 各种布局
[来源](https://blog.csdn.net/eclipsexys/article/details/9346477)
- LinearLayout(线性布局)：提供了控件水平垂直排列的模型，同时可以通过设置子控件的 weight 布局参数控制各个控件在布局中的相对大小
    + 水平、垂直
        * fill-parent：占满整个屏幕
        * wrap-content：刚好适合控件内容的大小
    + 对齐方式 gravity 的取值
        * top：不改变大小，位置置于容器的顶部
        * bottom：不改变大小，位置置于容器的底部
        * left：不改变大小，位置置于容器的左边
        * right：不改变大小，位置置于容器的右边
        * center_vertical：不改变大小，位置置于容器的纵向中央部分
        * center_horizontal：不改变大小，位置置于容器的横向中央部分
        * center：不改变大小，位置置于容器的纵向和横向的中央部分
        * fill_vertical：可能的话，纵向延伸可以填满容器
        * fill_horizontal：可能的话，横向延伸可以填满容器
        * fill：可能的话，纵向和横向延伸可以填满容器

- AbsoluteLayout(坐标布局)
    + 可以让子元素指定准确的x/y坐标值，并显示在屏幕上。(0, 0)为左上角，当向下或向右移动时，坐标值将变大。
    + AbsoluteLayout 没有页边框，允许元素之间互相重叠（尽管不推荐）。
    + 我们通常不推荐使用 AbsoluteLayout，除非你有正当理由要使用它，因为它使界面代码太过刚性，以至于在不同的设备上可能不能很好地工作。
    + `Android：layout_x/layout_y=”56px”`确定控件位置
- RelativeLayout(相对布局)
    + 允许子元素指定他们相对于其它元素或父元素的位置（通过ID指定）。因此，你可以以右对齐，或上下，或置于屏幕中央的形式来排列两个元素。元素按顺序排列，因此如果第一个元素在屏幕的中央，那么相对于这个元素的其它元素将以屏幕中央的相对位置来排列。如果使用XML来指定这个layout，在你定义它之前，被关联的元素必须定义
    + 只能设置Bool类型的值，“true”或“false”
        * Android：layout_centerInparent，将当前控件放置于起父控件的横向和纵向的中央部分
        * Android：layout_centerHorizontal,使当前控件置于父控件横向的中央部分
        * Android：layout_centerVertival,使当前控件置于父控件纵向的中央部分
        * Android：layout_alignParentBottom,使当前控件的底端和父控件底端对齐
        * Android：layout_alignParentLeft,使当前控件的左端和父控件左端对齐
        * Android：layout_alignParentRight,使当前控件的右端和父控件右端对齐
        * Android：layout_alignParentTop,使当前控件的顶端和父控件顶端对齐
        * Android：layout_alignParentBottom,使当前控件的底端和父控件底端对齐
    + Android：layout_below / layout_above/ layout_toLeftOf/ layout_toRightOf =“@id/” 使当前控件置于给出id的空间的下方/上方/左边/右边
    + Android:layout_marginBottom / layout_marginLeft / layout_marginRight / layout_marginTop=”30px” 使当前控件底部/左边/右边/顶部空出相应像素空间

- FrameLayout(单帧布局)
    + 是最简单的一个布局对象。它被定制为你屏幕上的一个空白备用区域，之后你可以在其中填充一个单一对象 — 比如，一张你要发布的图片。所有的子元素将会固定在屏幕的左上角；你不能为FrameLayout中的一个子元素指定一个位置。后一个子元素将会直接在前一个子元素之上进行覆盖填充，把它们部份或全部挡住（除非后一个子元素是透明的）
    + Android:src=”@drawable/” 属性指定所需图片的文件位置，用 ImageView 显示图片时，也应当用`android：src`指定要显示的图片

- TableLayout(表格布局)
    + 行列的形式管理子控件，每一行为一个TableRow的对象，TableRow也可以添加子控件
    + android：collapseColumns=“n” 隐藏TableLayout里面的TableRow的列n
    + android：stretchColumns=“n” 设置列n为可延伸的列
    + android：shrinkColumns=“n” 设置列n为可收缩的列
