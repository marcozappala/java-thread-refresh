import static org.junit.Assert.assertTrue;

import java.security.cert.PKIXRevocationChecker.Option;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


/**
 * Test well known java behaviour, just to refresh together with enhancing testing skills
 */
@RunWith(MockitoJUnitRunner.class)
public class OptionalObjTest {

  @Test(expected = NullPointerException.class)
  public void getOfNullOptionalThrowsNullPointerException() {
    Optional<String> optionalString = null;
    optionalString.get();
  }

  @Test(expected = NoSuchElementException.class)
  public void getOfEmptyOptionalThrowsNoSuchElementException() {
    Optional<String> optionalString = Optional.empty();
    optionalString.get();
  }

  @Test(expected = NoSuchElementException.class)
  public void getOptionalOfNullableThrowsNoSuchElementException() {
    String nullableString = null;
    Optional<String> optionalString = Optional.ofNullable(nullableString);
    optionalString.get();

  }

  @Test
  public void shouldReturnAnOptionalOfTheStringRequested(){
    MyClassWithOptionals optCl = new MyClassWithOptionals();

    Optional<String> optionalWithString = optCl.getAnOptionaStringOf("whatever");

    optionalWithString = optCl.getAnOptionaStringOf("not whatever");
    assertTrue(optionalWithString.get() == "not whatever");

    optCl.tryToSubstituteTheInstanceOf(optionalWithString);
    assertTrue(optionalWithString.get() == "not whatever");
    

  }


  class MyClassWithOptionals {
    public final Optional<String> finalOptional = Optional.empty();

    public Optional<String> getAnOptionaStringOf(String value) {
      return Optional.ofNullable(value);
    }


    public void tryToSubstituteTheInstanceOf(Optional<String> optionalString) {
      optionalString = Optional.ofNullable("");
    }
  }



}
