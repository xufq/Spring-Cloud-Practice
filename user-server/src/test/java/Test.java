import com.xufq.practicecore.utils.EncryptUtil;
import com.xufq.practicecore.utils.UUIDUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.*;

/**
 * @ClassName Test
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 2/8/2020 02:28 PM
 * @Version 1.0
 */
public class Test {

    @org.junit.Test
    public void testEncryptUtil() throws Exception {
        String s = "-salkjfadslk123456";
        MessageDigest sha = MessageDigest.getInstance("SHA");
        sha.update(s.getBytes());
        System.out.println(new BigInteger(sha.digest()));
        String password = "123456";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode(password));

        int n = 20 - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        System.out.println("n:"+n);

        Map map = new TreeMap();
        map.put("123", null);

        String se1 = EncryptUtil.encode("123456");
        String se2 = EncryptUtil.encode("123456");
        System.out.println("match:"+EncryptUtil.match("123456",se1));
        System.out.println("match:"+EncryptUtil.match("123456",se2));
        System.out.println("match:"+EncryptUtil.match(se1,se2));

    }

    @org.junit.Test
    public void testUUID(){
        System.out.println(UUIDUtil.getUUID());
    }

}
