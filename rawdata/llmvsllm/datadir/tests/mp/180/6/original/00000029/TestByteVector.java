import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putByte(0x55);

        bv.putUTF8("\u0080\u0800"); // UTF-8 bytes: C2 80 E0 A0 80, length=5

        byte[] d = bv.data;

        int packed = 0;
        packed = (packed * 31) ^ (bv.length & 0xFF);
        packed = (packed * 31) ^ (d[0] & 0xFF);
        packed = (packed * 31) ^ (d[1] & 0xFF);
        packed = (packed * 31) ^ (d[2] & 0xFF);
        packed = (packed * 31) ^ (d[3] & 0xFF);
        packed = (packed * 31) ^ (d[4] & 0xFF);
        packed = (packed * 31) ^ (d[5] & 0xFF);
        packed = (packed * 31) ^ (d[6] & 0xFF);
        packed = (packed * 31) ^ (d[7] & 0xFF);

        int expectedPacked = 0;
        expectedPacked = (expectedPacked * 31) ^ 8;
        expectedPacked = (expectedPacked * 31) ^ 0x55;
        expectedPacked = (expectedPacked * 31) ^ 0x00;
        expectedPacked = (expectedPacked * 31) ^ 0x05;
        expectedPacked = (expectedPacked * 31) ^ 0xC2;
        expectedPacked = (expectedPacked * 31) ^ 0x80;
        expectedPacked = (expectedPacked * 31) ^ 0xE0;
        expectedPacked = (expectedPacked * 31) ^ 0xA0;
        expectedPacked = (expectedPacked * 31) ^ 0x80;

        assertEquals(expectedPacked, packed);
        assertTrue(d.length >= bv.length);
    }
}