package es.uji.ei1048.weatherApp.e2e;

import es.uji.ei1048.weatherApp.main.WeatherAppFacade;
import org.junit.After;
import org.junit.Before;

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