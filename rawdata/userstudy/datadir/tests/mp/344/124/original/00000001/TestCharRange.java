import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange az = new CharRange(a,z, false);
        CharRange Notaz = new CharRange(a, z , true);
        AssertEquals('a', az.getStart());
        AssertEquals('a', Notaz.getStart());
    }
}