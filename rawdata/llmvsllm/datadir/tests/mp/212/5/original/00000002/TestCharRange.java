import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                java.lang.reflect.Method m = CharRange.class.getDeclaredMethod("isNegated");
        Assert.assertEquals("public boolean CharRange.isNegated()", m.toString());
    }
}