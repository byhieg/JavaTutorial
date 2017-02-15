# 自动动手系列——实现List
该包代码 主要实现了ArrayList与LinkedList。该README对应介绍实现的内容与过程。下面分别是ArrayList与LinkedList实现
其ArrayList实现的内容和过程如下：

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

# 自己动手系列——实现一个简单的LinkedList

LinkedList与ArrayList都是List接口的具体实现类。LinkedList与ArrayList在功能上也是大体一致，但是因为两者具体的实现方式不一致，所以在进行一些相同操作的时候，其效率也是有差别的。
对于抽象的数据结构——线性表而言，线性表分为两种，一种是顺序存储结构的顺序表，另一种是通过指针来描述其逻辑位置的链表。
针对于具体的Java实现：

- 顺序存储的顺序表是用数组来实现的，以数组为基础进行封装各种操作而形成的List为ArrayList
- 链表是用指针来描述其逻辑位置，在Java中以双向链表为基础进行封装各种操作而形成的List为LinkedList

针对插入与删除操作，ArrayList每插入一个元素，首先需要判断数组的空间够不够，不够要进行扩容，在有足够的空间的基础上，在指定的index位置上插入元素，但是该index及以后的元素都要后移。虽然删除操作不需要判断空间够不够，但同样需要该index及以后的元素向前移动，这些移动的操作会增加时间的复杂度。但是对于LinkedList就不一样，因为使用指针来指示其逻辑的位置，所以插入与删除的操作的时间复杂度都是 ** O(1) **

虽然对于ArrayList而言，插入与删除的时间复杂度很高，但是对于查找指定位置的元素这种操作而言，就非常的快，因为可以通过数组直接得到该下标对应的元素。反而，LinkedList而言，无法直接返回指定位置的元素，需要一个个查询，其时间的复杂度就是 ** O(n) **

与实现[ArrayList教程](http://www.cnblogs.com/qifengshi/p/6377614.html)一样，实现的目的主要在于练手以及掌握官方实现的原理和一些技巧，因此很多需要与其他类配合的方法和功能，就先不在这里实现如`iterator`等

所以，实现的LinkedList的方法如下：

- add方法
- get方法
- indexOf方法
- remove方法

与实现ArrayList的名字一样，为SimpleLinkedList。[源码地址](https://github.com/byhieg/JavaTutorial/blob/master/src/main/java/cn/byhieg/collectiontutorial/listtutorial/SimpleLinkedList.java)，欢迎star,fork


## 构建一个双向链表

构建的代码如下：

```

    private static class Node<E>{
        E item;
        Node<E> next;
        Node<E> prev;

        public Node(E item, Node<E> next, Node<E> prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
    
```

常规的双向链表的构建方法，一个数字域存放数组，一个前指针指向一个Node类型的元素，一个后指针指向一个Node类型的元素。

对于LinkedList的实现而言，还需要以下三个成员变量

```

    private int size;

    private Node<E> first;

    private Node<E> last;
    
```

## Add方法
这里实现的add方法是简单的add(E e)以及add(int index,E e)两个方法，addAll()将其他集合转换LinkedList的方法，暂时放到以后去实现。

add方法两个重载方法，其分别对应不同的添加方式。先说add(E e)方法的实现。

```

    public boolean add(E element) {
        addAtLast(element);
        return true;
    }

```
不指定位置添加元素，则默认添加到了链表的最后。addAtLast的核心代码如下：

```

    private void addAtLast(E element) {
        Node<E> l = last;
        Node<E> node = new Node<E>(element, null, l);
        last = node;
        if (l == null) {
            first = node;
        } else {
            l.next = node;
        }
        size++;
    }

```

首先找到最后一位的Node元素，然后根据`element`创建一个新的Node元素，其next指向为null,prev指向为最后一位Node元素。在创建完Node元素之后，last就变成了先创建的Node元素，接下来只需要把新node元素加到链表中即可。即让l对象(原最后一位，现倒数第二位元素的next指针，指向新node元素)。至此，新node元素的next指向null,prev指向倒数第二个元素，倒数第二个元素的next指向新node，就将node成功加入链表。

上述的操作也可以看出，其插入的操作非常省时间，比起ArrayList，扩容，移动元素快很多。

add的第二个重载方法 add(int index ,E e),先看代码实现：

```

    public void add(int index, E element) {
        checkRangeForAdd(index);
        if (index == size) {
            addAtLast(element);
        } else {
            Node<E> l = node(index);
            addBeforeNode(element, l);
        }
    }
    
```

首先判断要插入的index是否在范围内，在的话，再执行后续的add操作。如果要插入的index刚好是最后一位，则执行上面讲的addAtLast，如果不是，则得到index所对应的Node元素，执行addBeforeNode。
获取index所对应的Node元素，是node方法，代码如下：

```

   private Node<E> node(int index) {
        if (index < (size << 1)) {
            Node<E> cursor = first;
            for (int i = 0; i < index; i++) {
                cursor = cursor.next;
            }
            return cursor;
        } else {
            Node<E> cursor = last;
            for (int i = size - 1; i > index; i--) {
                cursor = cursor.prev;
            }
            return cursor;
        }
    }

```
这里的查找采用二分查找，节省查找时间，而且也应用到了双向链表的特点。首先判断index在前一半的范围内，还是后一半的范围内。如果是前一半，则游标Node初始为first,用游标Node元素的next，不断指向index所在的元素。如果是后一半，则游标Node初始为last,用游标Node元素的prev,不断指向index所在的元素。

在指定元素的前面插入新节点的addBeforeNode的方法如下：

```

    private void addBeforeNode(E element, Node<E> specifiedNode) {
        Node<E> preNode = specifiedNode.prev;
        Node<E> newNode = new Node<>(element, specifiedNode, preNode);
        if (preNode == null) {
            first = newNode;
        } else {
            preNode.next = newNode;
        }
        specifiedNode.prev = newNode;
        size++;
    }

```

插入的方式很简单，新节点的prev是原index元素的prev,新节点的next是原index元素。剩下的操作是把该node放到链表中，让原index元素的prev的next为新节点，但是要判断preNode是不是空，是的话，表示newNode为第一个元素，就是first。

至此，一个add方法，就实现完了。

## get方法

get方法在有了上述node方法之后，就非常的简单。代码如下：

```
    public E get(int index) {
        checkRange(index);
        return node(index).item;
    }

```

checkRange检查index是否不在范围内。

```
   private void checkRange(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("指定index超过界限");
        }
    }

```

## indexOf方法

indexOf(Object o)用来得到指定元素的下标。

```

   public int indexOf(Object element) {
        Node<E> cursor = first;
        int count = 0;
        while (cursor != null) {
            if (element != null) {
                if (element.equals(cursor.item)) {
                    return count;
                }
            }else{
                if (cursor.item == null) {
                    return count;
                }
            }
            count ++;
            cursor = cursor.next;
        }
        return -1;
    }
    

```
与ArrayList一样，从第一位开始查找，首先先判断element是不是null,分成两种情况。

## remove方法
remove方法与add方法一样，同样有两个重载的方法，remove(Object o)与remove(int index)

先看简单的remove(int index)方法，代码如下：

```

    public E remove(int index) {
        checkRange(index);
        return deleteLink(index);
    }

```

deleteLink是将该index所对应的节点的链接删除的方法，其代码如下：

```

    private E deleteLink(int index) {
        Node<E> l = node(index);
        E item = l.item;
        Node<E> prevNode = l.prev;
        Node<E> nextNode = l.next;

        if (prevNode == null) {
            first = nextNode;
        }else{
            prevNode.next = nextNode;
            l.next = null;
        }

        if (nextNode == null) {
            last = prevNode;
        }else{
            nextNode.prev = prevNode;
            l.prev = null;
        }
        size--;
        l.item = null;
        return item;
    }

```

首先获得该index对应的Node元素，得到该Node元素的前一个元素和后一个元素。接下来，只需要将前一个元素和后一个元素直接相连即可，其他只需要额外判断前一个元素和后一个元素是否为null就行。在判断前一个元素是否为null的时候，只需要操作前一个元素，在判断后一个元素是否为null的时候，也只需要操作后一个元素。最后，将要删除的元素各个引用至为null。

remove另一个重载方法remove(Object o)，在实现了indexOf和deleteLink方法之后，就非常简单。

```

    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index < 0){
            return false;
        }
        deleteLink(index);
        return true;
    }

```

获取该元素对应对应的下标，然后执行deleteLink方法，完成remove操作。

## 总结
至此，一个功能简单的LinkedList就实现完成了，全部的代码可以看[源码地址](https://github.com/byhieg/JavaTutorial/blob/master/src/main/java/cn/byhieg/collectiontutorial/listtutorial/SimpleLinkedList.java)，欢迎star,fork。





