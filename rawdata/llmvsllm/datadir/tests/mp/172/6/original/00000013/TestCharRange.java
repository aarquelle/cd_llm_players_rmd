import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("The Range must not be null");
        CharRange.is('a').contains((CharRange) null);
    }
}