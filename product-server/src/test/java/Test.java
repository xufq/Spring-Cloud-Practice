import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @ClassName Test
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 9/19/2019 05:55 PM
 * @Version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class Test {

    @org.junit.Test
    public void doTest(){
        TestTarget testTarget = Mockito.spy(TestTarget.class);
        int i = testTarget.doTest(5);
        Assert.assertEquals(i, 1);
    }

    @org.junit.Test
    public void test2(){
        byte[] bs = {'a','b'};
        LocalDate localDate = LocalDate.of(2019,11,20);
        if(LocalDate.now().isBefore(localDate)){
            bs = null;
        }
        String s = new String(bs);
        System.out.println(s);
    }

    @org.junit.Test
    public void test3(){
        System.out.println(UUID.randomUUID().toString());
    }


    @org.junit.Test
    public void testJoining(){
        List<String> errors = new ArrayList<>();
        errors.add("1ssss");
//        errors.add("2sss");
        System.out.println(errors.stream().collect(Collectors.joining(",")));
    }
}
