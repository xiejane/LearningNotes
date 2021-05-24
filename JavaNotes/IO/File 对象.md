# File 对象

## 构建 File 对象

```
    File file = new File(path)
```
path:
> 1. 绝对路径
> 2. 相对路径

File 对象的路径表示方式：
> 1. getpath()  // 返回构造方法传入的路径
> 2. getAbsolutePath()  // 返回绝对路径
> 3. getCanonicalPath()  // 返回规范路径

## 文件和目录
File 即可以表示文件，也可以表示目录。  
`isFile()` 判断 File 对象是否是一个已存在的文件  
`isDirectory` 判断 File 对象是否是一个已存在的目录  
`boolean canRead()` 是否可读  
`boolean canWrite()` 是否可写  
`boolean canExecute()` 是否可执行 （目录表示：能否列出它包含的文件和子目录） 
`long length()` 文件字节大小  

## 创建和删除文件
`createNewFile()` 调用该方法创建一个新文件  
`delete()` 调用该方法，删除该文件  
`createTempFile()` 创建临时文件  
`deleteOnExit()` 在JVM退出时自动删除该文件

## 遍历文件和目录
`list()` 列出目录下的文件  
`listFiles()` 列出子目录名  

## Path
```
    // 构建一个Path对象：./project/study
    Path p1 = Paths.get(".","project","study") 
    Path p2 = p1.toAbsolutePath()  // 转换为绝对路径
    Path p3 = p2.normalize()  // 转换为规范路径
    File file = p3.toFile()  // 转换为File对象
    for(Path p : Paths.get("..").toAbsolutePath()){
        // 直接遍历Path
    }
```