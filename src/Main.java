import com.yaoh.factory.AdderProxyFactory;
import com.yaoh.operate.Add;
import com.yaoh.opreator.DefaultAdder;

/**
 * @author yaoh.wu
 */
public class Main {
    public static void main(String[] args) {

        Add adder = AdderProxyFactory.createAdderProxy(new DefaultAdder());

        adder.add(1, 2);
    }
}
