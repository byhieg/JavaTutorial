# Java IO 讲解

源码均在src目录下，测试的代码是在test目录下

## 字节流
在该目录下,`bytestreamio` 是 字节流的io类的例子。
分别有对应输入流的:

- FileInputStream
- BufferedInputStream
- ByteArrayInputStream

对应输出流的：

- FileOutputStream
- BufferedOutputStream
- ByteArrayOutputStream


## 字符流
在该目录下，`charsetstreamio`是 字符流的io类的例子。

分别对应输入流的：

- InputStreamReader
- FileReader
- BufferedWriter

还有一些涉及到socket，序列化，压缩，管道的类的例子，放到其他的文件夹中。

关于IO的一些资料，网上也有很多，就不在总结了，放出一些链接出来：

[深入分析IO](https://www.ibm.com/developerworks/cn/java/j-lo-javaio/#ibm-pcon)
