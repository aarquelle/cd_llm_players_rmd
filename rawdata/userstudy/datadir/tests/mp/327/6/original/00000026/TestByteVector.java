import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(4);
        v.putByte(1).putByte(2).putByte(3);

        var dataField = ByteVector.class.getDeclaredField("data");
        dataField.setAccessible(true);
        byte[] oldData = (byte[]) dataField.get(v);

        var enlargeMethod = ByteVector.class.getDeclaredMethod("enlarge", int.class);
        enlargeMethod.setAccessible(true);
        enlargeMethod.invoke(v, 10); // length=3, size=10 => needed=13, double=8 => choose 13

        byte[] newData = (byte[]) dataField.get(v);

        assertArrayEquals(new byte[] {1, 2, 3}, new byte[] {newData[0], newData[1], newData[2]});
        assertEquals(13, newData.length);
    }
}