import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        var ch = new CharRange((char) 240, (char) 230, false);
        assertEquals(ch.getStart(), (char) 230);
        assertEquals(ch.getEnd(), (char) 240);
    }
}