import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4);
        bv.putUTF8("A\u0800B"); // 'A' (1 byte) + U+0800 (3 bytes) + 'B' (1 byte) => 5 bytes

        assertEquals(7, bv.length); // 2 length bytes + 5 UTF-8 bytes

        byte[] d = bv.data;
        int computed = 0;
        computed = (computed * 31) + (d[0] & 0xFF); // length high
        computed = (computed * 31) + (d[1] & 0xFF); // length low
        computed = (computed * 31) + (d[2] & 0xFF); // 'A'
        computed = (computed * 31) + (d[3] & 0xFF); // 0xE0
        computed = (computed * 31) + (d[4] & 0xFF); // 0xA0
        computed = (computed * 31) + (d[5] & 0xFF); // 0x80
        computed = (computed * 31) + (d[6] & 0xFF); // 'B'

        int expected = 0;
        expected = (expected * 31) + 0x00;
        expected = (expected * 31) + 0x05;
        expected = (expected * 31) + 0x41;
        expected = (expected * 31) + 0xE0;
        expected = (expected * 31) + 0xA0;
        expected = (expected * 31) + 0x80;
        expected = (expected * 31) + 0x42;

        assertEquals(expected, computed);
    }
}