import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange c1 = CharRange.isIn('g', 'o');
        CharRange c2 = CharRange.isIn('o', 'g');
        
        assertEquals('o', c1.getEnd());
        assertEquals('o', c2.getEnd());
    }
}