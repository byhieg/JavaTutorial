# 自己动手系列——实现一个简单的ArrayList
ArrayList是Java集合框架中一个经典的实现类。他比起常用的数组而言，明显的优点在于，可以随意的添加和删除元素而不需考虑数组的大小。处于练手的目的，实现一个简单的ArrayList，并且把实现的过程在此记录。
实现的ArrayList主要的功能如下：

-  默认构造器和一个参数的有参构造器
-  add方法
-  get方法
-  indexOf方法
-  contains方法
-  size方法
-  isEmpty方法
-  remove方法
-  sort方法

这个简单的ArrayList类 取名为`SimpleArrayList`，全部的代码查看[SimpleArrayList代码](https://github.com/byhieg/JavaTutorial/tree/master/src/main/java/cn/byhieg/collectiontutorial/listtutorial)

##  构造器

源码ArrayList一共有三个构造器，一个无参构造器，一个参数为int型有参构造器，一个参数为Collection型的有参构造器。参数为Collection型的构造器用来实现将其他继承Collection类的容器类转换成ArrayList。SimpleArrayList类因为还没有手动实现其他的容器类，所以实现的构造方法只有2个。代码如下：

```
    public SimpleArrayList(){
        this(DEFAULT_CAPACITY);
    }


    public SimpleArrayList(int size){
        if (size < 0){
            throw new IllegalArgumentException("默认的大小" + size);
        }else{
            elementData = new Object[size];
        }
    }
```

无参构造器中的 `DEFAULT_CAPACITY`是定义的私有变量，默认值是10，用来创建一个大小为10的数组。有参构造器中，int参数是用来生成一个指定大小的Object数组。将创建好的数组传给`elementData`。`elementData`是真正的用来存储元素的数组。

## add方法
add 方法用来往容器中添加元素，add方法有两个重载方法，一个是add(E e),另一个是add(int index, E e)。add本身很简单，但是要处理动态数组，即数组大小不满足的时候，扩大数组的内存。具体的代码如下：

```
    public void add(E e){
        isCapacityEnough(size + 1);
        elementData[size++] = e;
    }
```

方法`isCapacityEnough`就是来判断是否需要扩容，传入的参数就是最小的扩容空间。因为add一个元素，所以最小的扩容空间，即新的长度是所有元素+ 1。这里的size就是真正的元素个数。

```
   private void isCapacityEnough(int size){
        if (size > DEFAULT_CAPACITY){
            explicitCapacity(size);
        }
       if (size < 0){
            throw new OutOfMemoryError();
        }
    }
```
判断扩容的方法也很简单，判断需要扩容的空间是不是比默认的空间大。如果需要的空间比默认的空间大，就调用`explicitCapacity`进行扩容。这里有个size小于0的判断，出现size小于0主要是因为当size超过`Integer.MAX_VALUE`就会变成负数。

```
	private final static int MAX_ARRAY_LENGTH = Integer.MAX_VALUE - 8;

    private void explicitCapacity(int capacity){
        int newLength = elementData.length * 2;
        if (newLength - capacity < 0){
            newLength = capacity;
        }
        if (newLength > (MAX_ARRAY_LENGTH)){
            newLength = (capacity > MAX_ARRAY_LENGTH ? Integer.MAX_VALUE : MAX_ARRAY_LENGTH);
        }
        elementData = Arrays.copyOf(elementData, newLength);
    }
```

上面的代码是扩容的代码，首先，定义一个数组最大的容量的常量为最大值，这个值按照官方的源码中的解释是要有些VM保留了数组的头部信息在数组中，因此实际存放数据的大小就是整数的最大值 - 8 
然后设定一个要扩容的数组的大小，虽然上面说了有一个扩容空间的值 ** size + 1 **  ，这个是实际我们最小需要扩容的大小。但为了继续增加元素，而不频繁的扩容，因此一次性的申请多一些的扩容空间。这里newLength 打算申请为 数组长度的2倍，然后去判断这个长度是否满足需要的扩容空间的值。 即有了后续的两段代码

```
	  if (newLength - capacity < 0){
            newLength = capacity;
      }
      if (newLength > (MAX_ARRAY_LENGTH)){
            newLength = (capacity > MAX_ARRAY_LENGTH ? Integer.MAX_VALUE : MAX_ARRAY_LENGTH);
      }
```

如果2倍的长度仍然不满足，则申请到需要的扩容长度。在我们只增加一个元素的情况下，这个判断是永远不会生效的，但是如果有addAll方法，则增加的元素很多，就要导致一次申请2倍的长度是不够的。第二个判断是判断newLength的长度如果超过上面定义的数组最大长度则判断要需要的扩容空间是否大于数组最大长度，如果大于则newLength为 ** MAX_VALUE** ，否则为 ** MAX_ARRAY_LENGTH**。
最后，真正实现数组扩容到设定长度的方法就没意思了，调用`Arrays.copyOf(elementData, newLength)`得到一个扩容后的数组。
add的另一个重载方法也很简单。

```
   public void add(int index, E e) {
	   //判断是不是越界
        checkRangeForAdd(index);
        //判断需不需要扩容
        isCapacityEnough(size + 1);
        //将index的元素及以后的元素向后移一位
        System.arraycopy(elementData,index,elementData,index + 1,size - index);
        //将index下标的值设为e
        elementData[index] = e;
        size++;
    }
```

```
    private void checkRangeForAdd(int index){
	    //这里index = size是被允许的，即支持头，中间，尾部插入
        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException("指定的index超过界限");
        }
    }
```
至此，一个简单的add方法就实现完了。

## get方法
get方法用来得到容器中指定下标的元素。方法实现比较简单，直接返回数组中指定下标的元素即可。

```
    private void checkRange(int index) {
        if (index >= size || index < 0){
            throw new IndexOutOfBoundsException("指定的index超过界限");
        }
    }
    public E get(int index){
        checkRange(index);
        return (E)elementData[index];
    }
```


## indexOf方法
indexOf方法用来得到指定元素的下标。实现起来比较简单，需要判断传入的元素，代码如下：

```
	public int indexOf(Object o){
        if (o != null) {
            for (int i = 0 ; i < size ; i++){
                if (elementData[i].equals(0)){
                    return i;
                }
            }
        }else {
            for (int i = 0 ; i < size ; i++){
                if (elementData[i] == null) {
                    return i;
                }
            }
        }

        return -1;
    }
```

判断传入的元素是否为null，如果为null，则依次与null。如果不为空，则用`equals`依次比较。匹配成功就返回下标，匹配失败就返回-1。

## contains方法
contains用来判断该容器中是否包含指定的元素。在有了indexOf方法的基础上，contains的实现就很简单了。

```
	 public boolean contains(Object o){
        return indexOf(o) >= 0;
     }
```
## size方法
size方法用来得到容器类的元素个数，实现很简单，直接返回size的大小即可。
```
    public int size(){
        return size;
    }
```

## isEmpty方法
isEmpty方法用来判断容器是否为空，判断size方法的返回值是否为0即可。
```
    public boolean isEmpty(){
        return size() == 0;
    }
```
## remove方法
remove方法是用来对容器类的元素进行删除，与add一样，remove方法也有两个重载方法，分别是
remove(Object o)和remove(int index)

```
	 public E remove(int index) {
        E value = get(index);
        int moveSize = size - index - 1;
        if (moveSize > 0){
            System.arraycopy(elementData,index + 1, elementData,index,size - index - 1);
        }
        elementData[--size] = null;
        return value;
    }
    
    public boolean remove(Object o){
        if (contains(o)){
            remove(indexOf(o));
            return true;
        }else {
            return false;
        }
    }
```

第一个remove方法是核心方法，首先得到要删除的下标元素的值，然后判断index后面的要前移的元素的个数，如果个数大于零，则调用库方法，将index后面的元素向前移一位。最后`elementData[--size] = null;`缩减size大小，并将原最后一位置空。
第二个remove方法不需要向第一个方法一样，需要告诉使用者要删除的下标对应的元素，只需要判断是否删除成功即可。如果要删除的元素在列表中，则删除成功，如果不在则失败。因此调用`contains`方法就可以判断是否要删除的元素在列表中。在则调用`remove(int index)`,不在则返回失败。

## 总结
自此，一个简单的ArrayList就实现完了，实现的目的是为了弄清ArrayList动态数组的原理以及add与remove方法的内容实现。同时，也清楚了ArrayList最大的扩容空间就是Integer的最大值。该类的所有代码在[SimpleArrayList代码](https://github.com/byhieg/JavaTutorial/tree/master/src/main/java/cn/byhieg/collectiontutorial/listtutorial)


