# kotlin 特性问题

- Array<Int> 与 IntArray
    - Array<Int>：构造时调用`Integer.valueOf`，产生装箱的开销
    - IntArray：避免构造时的装箱，其他基本类型的Array也是如此
    ```kotlin
    //源码
    /** An iterator over a sequence of values of type `Int`. */
    public abstract class IntIterator : Iterator<Int> {
        override final fun next() = nextInt()

        /** Returns the next value in the sequence without boxing. */
        public abstract fun nextInt(): Int
    }
    ```