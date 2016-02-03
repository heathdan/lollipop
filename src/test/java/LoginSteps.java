import com.tw.cisco.b2b.helper.PageHelper;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;

/**
 * Created by aswathyn on 03/02/16.
 */
public class LoginSteps {
    PageHelper pageHelper = new PageHelper();

    @Given("^that the user \"([^\"]*)\" logged in as \"([^\"]*)\" and \"([^\"]*)\"$")
        public void that_the_user_logged_in_as_and(String arg1, String arg2, String arg3) throws Throwable {

        throw new PendingException();
    }
}
