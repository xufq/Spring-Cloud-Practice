/**
 * @ClassName Test
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 10/29/2019 06:06 PM
 * @Version 1.0
 */
public class Test {

    public static void main(String[] args) {
        SuperTest superTest = new SuperTest();
        TestA a = new TestA();
        TestB b = new TestB();
        a.setSuperI(10);
        b.setSuperI(20);
        System.out.println(a.i);
        System.out.println(b.i);
        System.out.println(superTest.i);
    }
}
