import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {

    ByteVector bytevec = new ByteVector();
    byte[] arr = {1, 2, 3};

    bytevec.putByteArray(arr, 0, 3);

    assertEquals(3, bytevec.length);
    assertEquals((byte) 1, bytevec.data[0]);
}

    }
}