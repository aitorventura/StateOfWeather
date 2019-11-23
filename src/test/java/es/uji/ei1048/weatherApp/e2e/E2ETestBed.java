package es.uji.ei1048.weatherApp.e2e;

import es.uji.ei1048.weatherApp.WeatherAppFacade;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class E2ETestBed {
    protected WeatherAppFacade weatherAppFacade;

    @Before
    public void setUp() throws Exception {
        weatherAppFacade = new WeatherAppFacade();

    }

    @After
    public void tearDown() throws Exception {
        weatherAppFacade = null;
    }

}