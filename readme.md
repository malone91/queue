在新增的Concurrent包中，BlockingQueue很好的解决了多线程中，如何高效安全“传输”数据的问题。
通过这些高效并且线程安全的队列类，为我们快速搭建高质量的多线程程序带来极大的便利

首先队列Queue是一个接口，它有两个实现，一个实现的抽象类，一个继承的接口。
分别是AbstractQueue 和 BlockingQueue
由其单词含义就可以看出，抽象类具有非阻塞的属性，而接口意味着它是阻塞的。
继承AbstractQueue的类基本上是非阻塞的，比如PriorityQueue，ConcurrentLinkedQueue
实现BlockingQueue接口的实现类是阻塞的，比如DelayQueue，LinkedBlockingQueue,ArrayBlockingQueue，SynchronousQueue，
PriorityBlockingQueue等。

Queue接口自带的方法有 add remove element peek offer poll 前三个方法如果在队列为空或者满的情况下会抛出异常。

在src的code中，写了一个篮子，行为有放苹果，取苹果，使用了线程，数据容器为ArrayBlockingQueue。

扩展阅读，公众账号算法文摘： https://mp.weixin.qq.com/s/LA0iaJdjTaAxnBVAqD90yA 


wait/notify例子