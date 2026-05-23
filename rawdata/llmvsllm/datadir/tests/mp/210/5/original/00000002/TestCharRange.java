import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange range = CharRange.isNot('a');
        assertEquals("isNegated must use logical boolean semantics", "true", String.valueOf(range.isNegated()));
        assertEquals("bytecode should not contain IAND (bitwise '&') for boolean", -1, containsIandOpcode(CharRange.class));
            String resource = "/" + cls.getName().replace('.', '/') + ".class";
            java.io.InputStream in = cls.getResourceAsStream(resource);
            if (in == null) {
                return -1;
            }
            try {
                byte[] bytes = readAll(in);
                // IAND opcode is 0x7E
                for (int i = 0; i < bytes.length; i++) {
                    if ((bytes[i] & 0xFF) == 0x7E) {
                        return i;
                    }
                }
                return -1;
            } finally {
                in.close();
            }
        } catch (Exception ex) {
            return -1;
        }
        byte[] buf = new byte[4096];
        int n;
        while ((n = in.read(buf)) != -1) {
            out.write(buf, 0, n);
        }
        return out.toByteArray();
    }
}