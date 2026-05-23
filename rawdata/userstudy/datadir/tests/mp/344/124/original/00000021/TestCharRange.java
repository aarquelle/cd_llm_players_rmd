import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange az = CharRange.isIn('z','a');
        CharRange bz = CharRange.isIn('b', 'z');
        assertFalse(az.equals(bz));
    }
}