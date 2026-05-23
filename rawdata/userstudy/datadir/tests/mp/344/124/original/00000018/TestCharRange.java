import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange Notaz = CharRange.isNotIn('a','z');
        CharRange az =CharRange.isIn('a','z');
        CharRange aw =CharRange.isIn('a','w');
        assertFalse(Notaz.equals(az));
        assertFalse(Notaz.equals(aw));
    }
}