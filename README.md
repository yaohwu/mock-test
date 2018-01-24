# mock-test 从动态代理到单元测试

## 问题：

单元测试目的是针对应用中某些模块进行功能验证。
但是在单元测试中我们可能会遇到一些问题:

1. 其他的协同模块没有开发完，单元测试进行不下去
1. 被测试模块需要和一些不容易构造、比较复杂的对象进行交互，测试和测试边界很难划分
1. 不能肯定其它模块的正确性，无法准确的定位问题

情况1，我们这边很少会遇到，最好是能够测试驱动。
情况2，这种现象非常多，比如actionCMD的方法的测试就需要构造HttpServletRequest和HttpServletResponse对象，或者说其他的诸如Calculator等复杂对象。
情况3，由于我们的部分代码耦合很严重，因此这类情况也很普遍。

我们先来看一种最简单的测试。

## 例子

我们有一个计算器Calculator类，里面依赖了一个int计算模块的加法器。

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

现在我们想对Calculator类进行测试，不想因为Add实现的错误或者别的因素影响我们针对Calculator的测试。

### 普通测试方案

一般的情况下，我们要针对这个做测试，就只能去实现一下Add接口，确定Add的内部实现是正确的。

```java
    @Test
    public void useCommon() {
        Calculator calculator = new Calculator();
        //给一个Add的实现类
        calculator.setAdder(new Add() {
            @Override
            public int add(int x, int y) {
                return x + y;
            }
        });
        assertEquals(3, calculator.add(1, 2));
    }
```

这样做就会有遇到问题的风险，一个是例子中的Add接口比较简单，容易构造，如果是复杂的对象，比如我们前面说到的HttpServletRequest，就会非常麻烦；
其次是我们还要保证Add实现的逻辑得是完全正确的，针对一个Calculator的测试变成了针对Calculator测试并实现一个Add并对该Add实现进行测试。

因此一般我们不采用这种方案，付出的成本很大效果又不好。
因为我们不关心Add的实现，那么如果我们在Add内部方法调用之前就返回一个指定的值不就好了么。我们可以借助代理模式来实现这种方案。

### 静态代理测试方案

#### 代理模式

代理模式是为其他对象提供一种代理以便控制对这个对象的访问。

可以详细控制访问某个类的方法，可以在调用被代理对象的方法前做一些前置处理，
在调用被代理对象的方法后做一些后置处理。

比如 明星的经纪人（滑稽）。

假设我们有一个Add接口的默认实现类。

```java
/**
 * @author yaoh.wu
 */
public class DefaultAdder implements Add {

    @Override
    public int add(int x, int y) {
        return x + y;
    }
}

```

现在我想有一个加法器，可以在打印出加数和被加数，以及在计算结束后打印出结果。这种需求就包含了前置处理和后置处理，利用代理模式我们可以这样实现。

```java
/**
 * @author yaoh.wu
 */
public class DefaultAdderProxy implements Add {
    private DefaultAdder defaultAdder;

    public DefaultAdderProxy(DefaultAdder defaultAdder) {
        this.defaultAdder = defaultAdder;
    }

    @Override
    public int add(int x, int y) {
        //do before
        System.out.print("x: " + x + " y: " + y + "= ");
        int result = defaultAdder.add(x, y);
        //do after
        System.out.println(result);
        return result;
    }

}
```

这个例子中就包括了静态代理模式一般会包含的三个角色：

1. 抽象角色：Add接口
1. 真实角色：DefaultAdder类
1. 代理角色：DefaultAdderProxy类

这只是一个静态代理，这个代理DefaultAdderProxy类是需要我们自己编码的。

基于这种方式，我们可以给出一个静态代理测试方案。

首先我们得实现一个代理类。

```java
/**
 * @author yaoh.wu
 */
public class AdderProxyForTest implements Add {
    private Add adder;

    public AdderProxyForTest(Add adder) {
        this.adder = adder;
    }

    @Override
    public int add(int x, int y) {
        if (x == 1 && y == 2) {
            return 3;
        }
        return adder.add(x, y);
    }
}
```

测试方法：

```java
    @Test
    public void useStaticProxy() {
        AdderProxyForTest adderProxyForTest = new AdderProxyForTest(new Add() {
            @Override
            public int add(int x, int y) {
                //随意写，因为我们是用来测试的，注定走不到被代理类的方法。
                return 0;
            }
        });

        Calculator calculator = new Calculator();
        calculator.setAdder(adderProxyForTest);

        assertEquals(3, calculator.add(1, 2));

    }
```

这样做确实可以不顾及Add内部是如何实现的了，上面那个“随意写”的注释也说明了这一点，但是这样你还是得去构造一个复杂的对象，只不过完全可以不再用仔细的去实现其内部方法，同时还得自己去实现一个代理类。麻烦程度还是不小。

### 动态代理测试方案

上面说了静态代理，在测试的时候需要自己去构建代理类。我们可以使用动态代理来避免这个麻烦。

动态代理通过代码生成代理类，可以使用java原生的动态代理，也可以使用javaassist或者cglib等类库实现这样的操作。

还是原来的需求，在计算前打印加数和被加数，在计算后打印结果。

由于无法直接定义代理类，我们需要借助java.lang.reflect.InvocationHandler来定义我们需要的代理行为：

```java
/**
 * @author yaoh.wu
 */
public class AddInvocationHandler implements InvocationHandler {

    private Add delegate;

    public AddInvocationHandler(Add delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable, InvocationTargetException {
        if ("add".equals(method.getName())) {
            Integer x = (Integer) args[0];
            Integer y = (Integer) args[1];
            System.out.print("x=" + x + " y=" + y + " result=");
            Integer result = delegate.add(x, y);
            System.out.println(result);
            return result;
        }
        return method.invoke(delegate, args);
    }
}
```

然后创建一个工厂来生成代理：

```java
/**
 * @author yaoh.wu
 */
public class AdderProxyFactory {

    public static Add createAdderProxy(Add delegate) {
        return (Add) Proxy.newProxyInstance(
                delegate.getClass().getClassLoader(),
                delegate.getClass().getInterfaces(),
                new AddInvocationHandler(delegate));
    }
}
```

我们可以这样使用它：

```java
public static void main(String[] args) {
        Add adder = AdderProxyFactory.createAdderProxy(new DefaultAdder());
        adder.add(1, 2);
    }
```

好了，我们可以使用动态代理的方式进行测试，在使用之前，我们可以完善一下功能使其更加易用。

