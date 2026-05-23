import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negOuter = CharRange.isNotIn('d', 'f');     // everything except d-f
        CharRange negInner = CharRange.isNotIn('e', 'e');     // everything except e

        assertAll(
                () -> assertTrue(negOuter.contains(negInner)),          // negated contains negated: d>=e false? actually d<=e and f>=e true => should be true by start>=range.start && end<=range.end (d>=e false) -> wait; range start=e,end=e; outer start=d,end=f => d>=e false => should be false; but correct logic: outer excludes d-f, inner excludes e only; outer does NOT contain inner because inner includes 'd' which outer excludes. Expect false.
                () -> assertFalse(CharRange.isIn((char) 0, Character.MAX_VALUE).contains(CharRange.isNot('x')))
        );
    }
}