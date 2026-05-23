import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange Notaz = CharRange.isIn('a','z');
        CharRange az =CharRange.isNotIn('a','z');
        CharRange aw =CharRange.isNotIn('a','w');
        assertFalse(Notaz.equals(az));
        assertFalse(Notaz.equals(aw));
    }
}