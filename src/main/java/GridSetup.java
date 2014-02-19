import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GridSetup {

    private static final String REMOTE_HUB_URL="GRID_URL";
    private static final String BROWSER_NAME="CAPABILITIES_BROWSER_NAME";
    private static final String BROWSER_VERSION="CAPABILITIES_BROWSER_VERSION";
    private static final String BROWSER_PLATFORM="CAPABILITIES_BROWSER_PLATFORM";

    public RemoteWebDriver remote_driver;
    private DesiredCapabilities capability;

    public final String browser_name;
    public final String browser_version;
    public final Platform browser_platform;
    public final String remote_hub_url;

    public GridSetup(RemoteWebDriver remote_driver, DesiredCapabilities capability){

        browser_name=System.getProperty(BROWSER_NAME,"firefox");
        browser_version=System.getProperty(BROWSER_VERSION,"27.0.1");
        browser_platform=Platform.getCurrent();
        remote_hub_url=System.getProperty(REMOTE_HUB_URL,"http://localhost:4444/wd/hub" );

        this.capability = capability;
        this.remote_driver=remote_driver;
    }

    public void setBrowserCapabilities(){

        capability.setBrowserName(browser_name);
        capability.setPlatform(browser_platform);
        capability.setVersion(browser_version);

    }

    public void setRemoteWebDriver(){

        try{
        remote_driver = new RemoteWebDriver(new URL(remote_hub_url), capability);

        }catch (MalformedURLException e){
        e.printStackTrace();

        }
    }

}
