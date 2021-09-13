# Retrofit2 教程
[来源](http://blog.csdn.net/zxw136511485/article/details/78589732)  
[官网](https://square.github.io/retrofit/)

> A type-safe HTTP client for Android and Java.


## 基础
- 引入
    + `implementation 'com.squareup.retrofit2:retrofit:(insert latest version)'`
    + Retrofit 的网络请求时交给 okhttp 处理的，因此，也需要引入 okhttp
- 使用
    + 1. 定义接口：
        ```java
        public interface ApiService{

            @GET("StudentInq")
            Call<ResponseBody> getStudent();
        }
        ```
    + 2. 实例化 Retrofit
        * 定义服务请求的 URL
        * 创建 Retrofit 的实例
        * 创建接口实例
        * 调用接口中的方法，同步请求调用`execute()`，异步请求调用`enqueue()`
        ```java
        // 定义服务请求的 URL
        public static final String API_URL = "http://localhost:8080/mServer/"

        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_URL).build();

        ApiService service = retrofit.create(ApiService.class);

        Call<ResponseBody> call = service.getStudent();
        call.enqueue(new CallBack<ResponseBody>(){
            @Override
            public void onResponse(){}

            @Override
            pulic void onFailure(){}
        });
        ```

- 注解
    + 请求方法类
    
    | 序号 | 名称 | 作用 |
    | --- | --- | --- |
    | 1 | GET | 通过请求 URI 得到资源 | 
    | 2 | POST | 向指定资源提交数据进行处理请求（例如提交表单或者上传文件）。数据被包含在请求体中，POST 请求可能会导致新的资源创建或已有资源的修改 |
    | 3 | PUT | 请求服务器保存客户端传送的数据到 URI 指定文档 |
    | 4 | DELETE | 请求服务器删除 URI 指定页面 |
    | 5 | PATCH | 局部资源更新 | 
    | 6 | HEAD | 仅获取文档首部 |
    | 7 | OPTIONS | 返回服务器针对特定资源所支持的 HTTP 请求方法 |
    | 8 | HTTP | 用于替代以上 7 个注解以及其他扩展方法，有三个属性： `method path hasBody`|

    + 标记类

    | 分类 | 名称 | 作用 |
    | --- | --- | --- |
    | 表单请求 | FormUrlEncoded | 表示请求体(body)是一个 Form 表单，以键值对的形式组成，可以调用 request.getParameter() 方法得到参数， Content-Type:application/x-www-form-urlencoded |
    | | Multipart | 表示请求体(body)是一个文件上传的 Form 表单，适用于传输大块的二进制数据，可以通过调用 request.getInputStream() 或者 request.getReader() 得到表单中的数据，Content-Type:multipart/form-data |
    | 标记 | Streaming | 表示响应体的数据使用流的形式返回，如果未使用该注解，默认会把数据全部载入内存，之后通过流获取数据也是读取内存中的数据，所以返回数据较大时，就需要使用注解 |

    + 参数类

    | 分类 | 名称 | 作用 |
    | --- | --- | --- |
    | 作用于方法 | Headers | 用于添加请求头 |
    | 作用于方法参数 | Header | 用于添加不固定值的请求头 |
    | | Body | 用于非表单请求体 |
    | | Field | (表单提交) @
    + 
    

## 实践