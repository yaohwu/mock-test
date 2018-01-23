# mock-test 从动态代理到单元测试

## 问题：

单元测试目的是针对应用中某些模块进行功能验证。
但是在单元测试中我们可能会遇到一些问题:

1. 其他的协同模块没有开发完，单元测试进行不下去
1. 被测试模块需要和一些不容易构造、比较复杂的对象进行交互，测试和测试边界很难划分
1. 不能肯定其它模块的正确性，无法准确的定位问题

情况1，我们这边很少会遇到，最好是能够测试驱动。
情况2，这种现象非常多，比如actionCMD的方法就需要构造HttpServletRequest和HttpServletResponce对象，或者说其他的诸如Calculator等复杂对象。
情况3，由于我们的部分代码耦合很严重，因此这类情况也很普遍。

我们先来看一种最简单的测试。

## 例子

我们有一个计算器Calculato类，里面依赖了一个int计算模块的加法器。

```java
public class Calculator {
    private Add adder;
    public int add(int x, int y) {
        //do something special
        return adder.add(x, y);
    }
    public void setAdder(Add adder) {
        this.adder = adder;
    }
}
```

```java
public interface Add {

    /**
     * 计算x+y
     *
     * @param x x
     * @param y y
     * @return x+y
     */
    int add(int x, int y);
}
```

加法器Add的实现在别的模块中，在Calculator类中我们只是调用了Add的接口。

现在我们想对Calculator类进行测试，不想因为Add的实现错误或者别的因素影响我们针对Calculator的测试。

### 普通测试方案

### Mock测试方案