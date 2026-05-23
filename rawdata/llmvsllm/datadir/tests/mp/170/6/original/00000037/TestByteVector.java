import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                    Field f = ByteVector.class.getDeclaredField("length");
            f.setAccessible(true);
            return ((Integer) f.get(v)).intValue();
        } catch (Exception e) {
            throw new AssertionError(e);
        }
            Field f = ByteVector.class.getDeclaredField("data");
            f.setAccessible(true);
            return (byte[]) f.get(v);
        } catch (Exception e) {
            throw new AssertionError(e);
        }
        ByteVector v = new ByteVector(8);
        v.putLong(0x0102030405060708L);

        assertEquals(8, length(v));
        assertArrayEquals(new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }, Arrays.copyOf(data(v), length(v)));
    }
}