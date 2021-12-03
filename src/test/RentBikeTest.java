package test;

import static org.junit.Assert.assertEquals;

import controllers.RentBikeServiceController;
import org.junit.Before;
import org.junit.Test;

public class RentBikeTest {
    private controllers.RentBikeServiceController rentBikeServiceController;

    @Before
    public void setUp() throws Exception {
        rentBikeServiceController = new RentBikeServiceController();
    }

    @Test
    public void test() {
        assertEquals(1,1);
    }
}
