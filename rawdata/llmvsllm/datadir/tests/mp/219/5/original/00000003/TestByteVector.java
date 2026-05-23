import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector();
        v.putUTF8("\u00A9"); // © should be encoded as C2 A9 in modified UTF-8/UTF-8 for this range

        assertArrayEquals(new byte[] { 0x00, 0x02, (byte) 0xC2, (byte) 0xA9 }, java.util.Arrays.copyOf(v.data, v.length));
    }
}