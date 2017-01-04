import com.magicbeans.util.Memcached;

/**
 * Created by Eric Xie on 2017/1/4 0004.
 */
public class test {


    public static void main(String[] args) {
        Memcached.getInstance().add("key","123",60);
        System.out.println(Memcached.getInstance().get("key"));
    }

}
