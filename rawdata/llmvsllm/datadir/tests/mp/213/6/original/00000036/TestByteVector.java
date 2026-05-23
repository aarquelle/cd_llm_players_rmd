import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.putByte(0x7F).putUTF8("A\u0800");

        assertEquals(7, v.length);
        assertEquals(0x7F000441E0A0, pack6(v.data, 0));
                | ((a[off + 1] & 0xFF) << 8)
                | (a[off + 2] & 0xFF);
        for (int i = 0; i < len; i++) r = (r << 8) | (a[off + i] & 0xFF);
        return r;
                | ((a[off + 1] & 0xFF) << 16)
                | ((a[off + 2] & 0xFF) << 8)
                | (a[off + 3] & 0xFF);
        for (int i = 0; i < len; i++) r = (r << 8) | (a[off + i] & 0xFF);
        return r;
        for (int i = 0; i < 6; i++) r = (r << 8) | (a[off + i] & 0xFF);
        return r;
        for (int i = 0; i < 6; i++) r = (r << 8) | (a[off + i] & 0xFF);
        return r;
    }
}