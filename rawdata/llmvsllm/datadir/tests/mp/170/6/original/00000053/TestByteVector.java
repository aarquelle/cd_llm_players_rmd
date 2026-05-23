import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                    Field f = ByteVector.class.getDeclaredField("data");
            f.setAccessible(true);
            return (byte[]) f.get(v);
        } catch (Exception e) {
            throw new AssertionError(e);
        }
            Field f = ByteVector.class.getDeclaredField("length");
            f.setAccessible(true);
            return (Integer) f.get(v);
        } catch (Exception e) {
            throw new AssertionError(e);
        }
        ByteVector v = new ByteVector(4);
        v.putUTF8("\u20AC");

        assertTrue(data(v).length >= 5);
        assertArrayEquals(new byte[] {0, 3, (byte) 0xE2, (byte) 0x82, (byte) 0xAC},
                Arrays.copyOf(data(v), length(v)));
    }
}