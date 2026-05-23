import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(4);

        Field lengthF = ByteVector.class.getDeclaredField("length");
        lengthF.setAccessible(true);
        lengthF.setInt(v, 3);

        Field dataF = ByteVector.class.getDeclaredField("data");
        dataF.setAccessible(true);
        byte[] original = (byte[]) dataF.get(v);
        original[0] = 1;
        original[1] = 2;
        original[2] = 3;

        Method enlarge = ByteVector.class.getDeclaredMethod("enlarge", int.class);
        enlarge.setAccessible(true);
        enlarge.invoke(v, 10); // needs length+size=13, larger than 2*4=8

        byte[] grown = (byte[]) dataF.get(v);
        assertEquals(13, grown.length);
        assertArrayEquals(new byte[] { 1, 2, 3 }, new byte[] { grown[0], grown[1], grown[2] });
    }
}