package headu.mpp.secretpartyreservationsystem.index;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestIndex {

    @Test
    public void testIndex(){
        String expected = "Hello World!";
        IndexController actual = new IndexController();
        assertEquals(expected,actual.index());
    }

}
