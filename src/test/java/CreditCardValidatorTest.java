import org.junit.Before;
import org.junit.Test;


public class CreditCardValidatorTest {

    private CreditCardValidator validator;

    @Before
    public void setup() {
        validator = new CreditCardValidator();
    }

    @Test
    public void shouldPassValidNumber() {
        validator.validate("0");
    }

}
