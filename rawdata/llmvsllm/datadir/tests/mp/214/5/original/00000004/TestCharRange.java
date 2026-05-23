import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                Class<?> sysClass = Class.forName("java.lang.System");
        java.lang.reflect.Method setProp = sysClass.getMethod("setProperty", String.class, String.class);
        java.lang.reflect.Method clearProp = sysClass.getMethod("clearProperty", String.class);

        setProp.invoke(null, "negated", "true");
        try {
            assertFalse(CharRange.is('a').isNegated());
        } finally {
            clearProp.invoke(null, "negated");
        }
    }
}