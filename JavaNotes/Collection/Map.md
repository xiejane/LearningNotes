# Map

Map<K,V>是一种键-值映射表，当我们调用 put(K key, V value)方法时，就把 key 和 value 做了映射并放入Map。当我们调用 V get(K key)时，就可以通过 Key 获取到对应的 value。如果 key 不存在，则返回 null。

和 List 类似，Map 也是一个接口，最常用的实现类是 HashMap。

查询某个 Key 是否存在，调用 boolean containKey(K key) 方法。

一个 key 只能关联一个 value。 放入相同的 key，只会把原有的 key-value 对应的 value 给替换掉。

在一个 Map 中，虽然 key 不能重复，但 value 是可以重复的。
