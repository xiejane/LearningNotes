# Glide 的基本用法
[来源](https://guolin.blog.csdn.net/article/details/53759439)
[仓库](https://github.com/bumptech/glide/tree/v3.7.0)

## 开始使用
- 引入库：在 app/build.gradle 文件中添加依赖
    ```
    dependencies{
        implementation "com.github.bumptech.glide:glide:3.7.0"
    }
    ```
- Glide 中需要用到网络功能，需要在 AndroidManifest.xml 中声明网络权限
    + `<uses-permission android:name="android.permission.INTERNET" />`

- 加载图片资源
    ```
    // 加载本地图片
    File file = new File(getExternalCacheDir() + "/image.jpg");
    Glide.with(this).load(file).into(imageView);

    // 加载应用资源
    int resource = R.drawable.image;
    Glide.with(this).load(resource).into(imageView);

    // 加载二进制流
    byte[] image = getImageBytes();
    Glide.with(this).load(image).into(imageView);

    // 加载Uri对象
    Uri imageUri = getImageUri();
    Glide.with(this).load(imageUri).into(imageView);
    ```

- 其他常用方法
    ```
    Glide.with(this) // 创建一个加载图片的实例，可以接受 <b> Context / Activity / Fragment </b> 类型的参数
     .load(url)  // 加载图片
     .asGif()  // 指定图片格式
     .placeholder(R.drawable.loading)  // 在加载图片时的占位图片
     .error(R.drawable.error)  // 在加载失败后的占位图片
     .diskCacheStrategy(DiskCacheStrategy.NONE)  // 加载图片的策略，不采用缓存
     .override(100, 100)  // 指定图片大小
     .into(imageView);  // 将图片显示至页面组件
    ```

- 关键的三步： with()、load()、into()

