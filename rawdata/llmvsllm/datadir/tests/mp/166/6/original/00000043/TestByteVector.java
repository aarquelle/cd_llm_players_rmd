import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3);
        bv.putUTF8("A\u0800B"); // payload: 0x41, 0xE0 0xA0 0x80, 0x42

        assertEquals(7, bv.length); // 2 header + 5 payload bytes

        int v = 0;
        v = v * 257 + (bv.data[0] & 0xFF);
        v = v * 257 + (bv.data[1] & 0xFF);
        v = v * 257 + (bv.data[2] & 0xFF);
        v = v * 257 + (bv.data[3] & 0xFF);
        v = v * 257 + (bv.data[4] & 0xFF);
        v = v * 257 + (bv.data[5] & 0xFF);
        v = v * 257 + (bv.data[6] & 0xFF);

        int expected = 0;
        expected = expected * 257 + 0x00;
        expected = expected * 257 + 0x05;
        expected = expected * 257 + 0x41;
        expected = expected * 257 + 0xE0;
        expected = expected * 257 + 0xA0;
        expected = expected * 257 + 0x80;
        expected = expected * 257 + 0x42;

        assertEquals(expected, v);
    }
}