import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange notB = CharRange.isNot('b');
CharRange nonUniverse = CharRange.isIn('a', 'z');
assertFalse(nonUniverse.contains(notB));
assertTrue(CharRange.isIn((char) 0, Character.MAX_VALUE).contains(notB));
    }
}