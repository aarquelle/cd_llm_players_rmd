import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange az = CharRange.isIn('a','z');
        CharRange Notaz = new CharRange('a', 'z' , true);
        assertEquals('a', az.getStart());
        assertEquals('a', Notaz.getStart());
    }
}