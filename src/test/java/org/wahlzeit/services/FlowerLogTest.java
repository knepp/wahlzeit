package org.wahlzeit.services;

import org.junit.Test;

public class FlowerLogTest {
    @Test
    public void testErrorLogging() {
        FlowerLog.logError("This is a test log.");
        FlowerLog.logError("This is a second test log.");
        FlowerLog.logError("This is a third test log.");
    }
}
