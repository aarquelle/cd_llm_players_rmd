import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        String outsideOfASCII = Character.toString((char) 128);
        ByteVector v = new ByteVector();
        v.putUTF8(outsideOfASCII);
        
        assertEquals(4, v.length);
    }
}