import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                String s = "A\u0001\u07FF\u0800Z";
        byte[] expectedBytes = new byte[]{
                0x41,
                0x01,
                (byte) 0xDF, (byte) 0xBF,
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80,
                0x5A
        };
        int expectedLen = expectedBytes.length;

        ByteVector bv = new ByteVector(2);
        bv.putUTF8(s);

        assertEquals(2 + expectedLen, bv.length);

        Field dataField = ByteVector.class.getDeclaredField("data");
        dataField.setAccessible(true);
        byte[] data = (byte[]) dataField.get(bv);

        byte[] actual = Arrays.copyOfRange(data, 0, bv.length);

        byte[] expected = new byte[2 + expectedLen];
        expected[0] = (byte) (expectedLen >>> 8);
        expected[1] = (byte) expectedLen;
        expected[2] = expectedBytes[0];
        expected[3] = expectedBytes[1];
        expected[4] = expectedBytes[2];
        expected[5] = expectedBytes[3];
        expected[6] = expectedBytes[4];
        expected[7] = expectedBytes[5];
        expected[8] = expectedBytes[6];
        expected[9] = expectedBytes[7];

        assertArrayEquals(expected, actual);
    }
}