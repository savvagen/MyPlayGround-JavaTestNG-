import Utilities.Listeners.MyTestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(MyTestListener.class)
public class ListenerTests {


    @Test
    public void test1Success(){Assert.assertTrue(true);}

    @Test
    public void test2Failed(){
        Assert.assertTrue(false, "I am asserting false");
    }

    @Test(dependsOnMethods = "test2Failed")
    public void test3Skipped(){
        Assert.assertTrue(true);
    }




}
