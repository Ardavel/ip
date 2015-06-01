package ip.scoring;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Wojciech Szałapski
 */
public class NormalDistributionTest {
    
    private NormalDistribution sutOnline;
    
    private NormalDistribution sutOffline;
    
    public NormalDistributionTest() {
    }
    
    @Before
    public void setUp() {
        sutOnline = new NormalDistribution();
        sutOffline = new NormalDistribution();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void shouldComputeTheSameOnlineAndOffline() {
        int n = 100;
        double delta = 1e-5;
        
        List<Double> samples = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            samples.add(Math.random() * 10);
            sutOnline.processSample(samples.get(i));
        }
        
        sutOffline.initializeFromScratch(samples);
        
        assertEquals(sutOffline.getMean(), sutOffline.getMean(), delta);
        assertEquals(sutOffline.getStandardDeviation(), sutOffline.getStandardDeviation(), delta);
        assertEquals(sutOffline.getStandardDeviationFactor(), sutOffline.getStandardDeviationFactor(), delta);
    }
}
