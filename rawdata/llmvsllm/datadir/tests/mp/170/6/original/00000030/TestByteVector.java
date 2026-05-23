import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                    Field f = ByteVector.class.getDeclaredField("length");
            f.setAccessible(true);
            return (Integer) f.get(v);
        } catch (Exception e) {
            throw new AssertionError(e);
        }
            Field f = ByteVector.class.getDeclaredField("data");
            f.setAccessible(true);
            return (byte[]) f.get(v);
        } catch (Exception e) {
            throw new AssertionError(e);
        }
        ByteVector v = new ByteVector(4);
        v.putInt(0x01020304);

        assertEquals(4, length(v));
        assertArrayEquals(new byte[] { 1, 2, 3, 4 }, Arrays.copyOf(data(v), length(v)));
    }
}