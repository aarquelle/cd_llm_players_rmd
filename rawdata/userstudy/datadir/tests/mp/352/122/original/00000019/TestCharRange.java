import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange charR = new CharRange();
        CharRange charRa = CharRange.isIn('a', 'z');
        
        char expectedEnd = 'z';
       assertEquals("The end character should be 'z'", expectedEnd, charRa.getEnd());

    }
}