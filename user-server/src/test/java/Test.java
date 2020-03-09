import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigInteger;
import java.security.MessageDigest;

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
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode(s));

    }
}
