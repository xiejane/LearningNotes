# 简介及list

## 简介

集合定义：
在Java中，如果一个Java对象可以在内部持有若干其他Java对象，并对外提供访问接口，我们把这种Java对象称为集合。

Java的java.util包
提供以下三种类型的集合：
> 1. List: 一种有序列表的集合
> 2. Set: 一种保证没有重复元素的集合
> 3. Map: 一种通过键值对查找的映射表集合

Java集合设计的特点：
> 1. 实现了接口和实现类相分离，如： ArrayList, LinkedList
> 2. 支持泛型
> 3. Java访问集合总是通过统一的方式——迭代器来实现

--------


## List

### 基本说明

List 是最基础的一种集合，是一种有序列表。
List 的行为和数组几乎完全相同： List 内部按照放入元素的先后顺序存放，每个元素都可以通过索引确定自己的位置，List 的索引和数组一样，从 0 开始。

List<E> 接口有几个主要的方法：
> boolean add(E e); // 在末尾添加一个元素  
> boolean add(int index, E e);  //在指定索引添加一个元素  
> E remove(int index);  // 删除指定索引的元素  
> boolean remove(Object e);  // 删除某个元素  
> E get(int index);  // 获取指定索引的元素  
> int size()  // 获取链表大小（包含元素的个数）

比较 ArrayList 和 LinkedList:

| 比较项 | ArrayList | LinkedList |
|----|----|----|
| 获取指定元素 | 速度很快 | 需要从头开始查找元素 |
| 添加元素到末尾 | 速度很快 | 速度很快 |
| 在指定位置增加/删除 | 需要移动元素 | 不需要移动元素 |
| 内存占用 | 少 | 较大 |

通常情况下，优先使用 ArrayList。

### 特点

> 1. List 接口允许添加重复的元素
> 2. List 允许添加 `null`，但 List.of() 方法不接收 `null` 值

遍历List：
1. 索引遍历
```
import java.util.List;

public class Main{
	public static void main(String[] args){
		List<String> list = List.of("apple","pear","banana");
		for(int i=0; i<list.size(); i++){
			String s = list.get(i);
			System.out.println(s);
		}
	}
}
```

2. 迭代器遍历
通过 Iterator 遍历 List 永远是最高效的方式。

```
...	
		for(Iterator<String> it = list.iterator();it.hasNext();){
			String s = it.next();
			System.out.println(s);
		}
...
```

3. for each 方法
本质为使用 Iterator 方式，Java 编译器会自动把 for each 循环变成 Iterator 的调用。

```
...
		for(String s:list){
			System.out.println(s);
		}
...
```

### List 和 Array 转换

List 变为 Array有三种方法：

1. 调用 toArray() 方法直接返回一个 Object[] 数组。这种方法会丢失类型信息，所以实际应用很少。
```
	List<String> list = List.of("apple","pear","banaba");
	Object[] array = list.toArray();
```

2. 给 toArray(T[])传入一个类型相同的 Array, List 内部自动把元素复制到传入的 Array 中。 泛型参数 <T> 不是 List 接口定义的泛型参数 <E>，所以，实际上可传入其他类型的数组（需要类型匹配）。
```
	List<Integer> list = List.of(12,34,56);
	Integer[] array = list.toArray(new Integer[3])
```

3. 通过 List 接口定义的 T[] toArray(IntFunction<T[]> generator) 方法
```
	Integer[] array = list.toArray
```

Array 变为 List: 通过 List.of(T ...) 方法，返回的是只读List。
```
	Integer[] array = {1,2,3};
	List<Integer> list = List.of(array);
```

### 编写 equals 方法

eqauls()： 对比值是否相同
== ： 对比引用是否相同

equals() 方法要求必须满足的条件：
> 自反性： 对于非 null 的x来说，x.equals(x) 必须返回 true
> 对称性： 对于非 null 的x和y来说，x.equals(y) 为true，则 y.equals(x)必须为ture
> 传递性： 对于非 null 的x,y,z来说，x.equals(y) 为true, y.equals(z) 也为true，那么 x.equals(z) 也必须为 true
> 一致性：对于非 null 的x和y来说，只要x和y状态不变，x.equals(y)总是一致地返回true或false
> 对 null 的比较：即x.equals(null) 永远返回false

对于引用字段的比较，使用equals()，对于基本类型字段的比较，使用 ==

equals() 方法的正确编写方法：
> 1. 先确定实例“相等”的逻辑，即哪些字段相等，就认为实例相等
> 2. 用 instanof 判断传入的待比较的 Object 是不是当前类型，如果是，继续比较，否则返回false
> 3. 对引用类型用 Object.equals() 比较，对基本类型直接用 == 比较

使用 Object.equals() 比较两个引用类型是否相等的目的是省去判断 null 的麻烦。
如果不调用 List 的 contains()、indexOf() 这些方法，那么放入的元素就不需要实现 equals() 方法