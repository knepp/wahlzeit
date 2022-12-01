package org.wahlzeit.services;

import org.junit.Test;

public class FlowerLogTest {
    @Test
    public void testErrorLogging() {
        FlowerLog.logError("This is a test log.");
    }
}
