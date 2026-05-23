import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange az = CharRange.isIn('z','a');
        CharRange Notaz = CharRange.isNotIn('a', 'z');
        assertEquals('z', az.getEnd());
        assertEquals('z', Notaz.getEnd());
    }
}