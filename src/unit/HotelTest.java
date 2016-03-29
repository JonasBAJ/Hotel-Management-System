package unit;

import logic.Hotel;
import org.junit.Test;

import static org.junit.Assert.*;


public class HotelTest {

    @Test
    public void hashCodeEqualsTrueTest()
    {
        Hotel h1 = new Hotel("Hotel1");
        Hotel h2 = new Hotel("Hotel1");
        assertTrue(h1.equals(h2) && h2.equals(h1));
        assertEquals(h1.hashCode(), h2.hashCode());
    }

    @Test
    public void hashCodeEqualsFalseTest()
    {
        Hotel h1 = new Hotel("Hotel1");
        Hotel h2 = new Hotel("Hotel2");
        assertFalse(h1.equals(h2) && h2.equals(h1));
    }
}