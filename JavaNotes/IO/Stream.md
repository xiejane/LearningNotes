# Stream

## InputStream
InputStearm 是Java标准库提供的最基本的输入流，位于`java.io`这个包里。  
InputStearm 不是接口，而是一个抽象类，是所有输入流的超类。  
```
public abstract int read() throws IOException;
```
这个方法会读取输入流的下一个字节，并返回字节表示的int值（`0~255`），如果已经读到末尾，返回`-1`表示不能继续读取了。  

`FileInputStream` 是 `InputStream` 的一个子类，表示从文件流中读取数据。
示例：
```
public void readFile() throws IOException{
    //创建一个FileInputStream对象
    InputStream input = new FileInputStream("src/readme.txt")
    for(;;){
        int n = input.read(); //反复调用read()方法，直至文件结束
        if(n ==-1){
            break;
        }
        System.out.println(n);
    }
    input.close();  //关闭流，会彻底释放对应的底层资源
}
```

使用try...finally来保证InputStream在无论是否发生IO错误的时候都能正确的关闭。  
更好的替代方式`try(resource)`，只需要编写try语句，让编译器自动为我们关闭资源。
```
    try(InputStream input = new FileInputStream("src/readme.txt")){
        int n;
        while((n=input.read())!=-1){
            System.out.println(n);
        }
    }  //编译器在此自动写入 finally 并调用close()
```
实际上，编译器并不会特别地为InputStream加上自动关闭。编译器只看`try(resource=...)`中的对象是否实现了`java.lang.AutoCloseable`接口，如果实现了，就自动加上finally语句并调用close()方法。InputStream和OutputStream都实现了这个接口。

### 缓冲
InputStream 提供两个重载方法来支持读取多个字节：
> 1. int read(byte[] b)：读取若干字节并填充到byte[]数组，返回读取的字节数
> 2. int read(byte[] b, int off, int len)： 指定byte[]数组的偏移量和最大填充数

### 阻塞
在调用InputStream的read()方法读取数据是，read()方法是阻塞的，必须等它执行完成后才能执行下一步代码。

## OutputStream
OutputStream 是Java标准库提供的最基本的输出流。  
OutputStream 也是抽象类，是所有输出流的超类。  
```
public abstract void write(int b) throws IOException;
```
这个方法会写入一个字节到输出流，虽然传入的是int参数，但只会写入一个字节。  
`flash()`方法，目的是将缓冲区的内容真正地输出到目的地。

`write(byte[] b)`也有重载方法支持一次性写入若干字节。
```
public void writeFile() throws IOException{
    try(OutputStream output = new FileOutputStream("out/readme.txt")){
        output.write("Hello".getBytes("UTF-8"));
    }
}
```

在`try(resource){...}`语句中可以同时使用多个资源，用`;`隔开。

