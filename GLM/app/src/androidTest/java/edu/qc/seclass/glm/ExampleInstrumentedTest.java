package edu.qc.seclass.glm;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {

        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("edu.qc.seclass.glm", appContext.getPackageName());
    }
}
