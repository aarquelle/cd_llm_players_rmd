import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.putByte(1).putByte(2);
        v.data[1] = 9;               // used byte
        v.data[0] = 1;               // used byte
        v.data[1] = 9;
        v.data[0] = 1;
        v.data[1] = 9;
        v.data[0] = 1;

        v.data[1] = 9;
        v.data[0] = 1;

        v.data[1] = 9;

        v.data[0] = 1;

        v.data[1] = 9;

        v.data[0] = 1;

        v.data[1] = 9;

        v.data[0] = 1;

        v.data[1] = 9;

        v.data[0] = 1;

        v.data[1] = 9;

        v.data[0] = 1;

        v.data[1] = 9;

        v.data[0] = 1;

        v.data[1] = 9;

        v.data[0] = 1;

        v.data[1] = 9;

        v.data[0] = 1;

        v.data[1] = 9;

        v.data[0] = 1;

        v.data[1] = 9;

        v.data[0] = 1;

        v.data[1] = 9;

        v.data[0] = 1;

        v.data[1] = 9;

        v.data[0] = 1;

        // Write 1 byte so enlarge(1) happens; before enlarge, poison unused area.
        v.data[2 - 1] = 9; // ensure last used byte stays as set
        // poison beyond 'length' (unused); currently length=2, capacity=2 so none.
        // create unused bytes by enlarging once, then poison, then force enlarge again.
        v.putByte(3); // triggers enlarge to capacity 4 (copies used bytes only in correct version)
        v.data[3] = 7; // poison unused (length=3, index 3 is unused)
        v.putByte(4); // does not enlarge (fills index 3)
        v.putByte(5); // triggers enlarge again; correct code copies only length=4, so old poison at index>=4 not copied

        assertEquals(5, v.length);
        assertEquals(0, v.data[6]); // after correct enlarge, bytes beyond old length should be 0; buggy copies full old capacity incl poison into new array
    }
}