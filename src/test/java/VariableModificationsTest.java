import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Test well known java behaviour, just to refresh together with enhance testing skills
 */
@RunWith(MockitoJUnitRunner.class)
public class VariableModificationsTest {

  @Test
  public void intWillNotBeModifiedByCalledMethod() {
    int myInt = 0;
    methodWhichWillModify(myInt);
    assertEquals(0, myInt);
  }

  @Test
  public void stringWillNotBeModifiedByCalledMethod(){
    String string = "ciao";
    methodWhichWillModify(string);
    assertEquals("ciao", string);
  }
  
  @Test
  public void complexObjectWillBeModifiedByCalledMethod() {
    AnObject anObject = new AnObject("ciao", 0);
    methodWhichWillModify(anObject);
    assertEquals("hello", anObject.string);
    assertEquals(1, anObject.integer);
  }

  @Test
  public void complexObjectWillNotBeSubstitutedByCalledMethod() {
    AnObject anObject = new AnObject("ciao", 0);
    methodWhichWouldSubstituteTheIstanceOf(anObject);
    assertEquals("ciao", anObject.string);
    assertEquals(0, anObject.integer);
  }

  private void methodWhichWillModify(int myInt) {
    myInt = 1;
  }

  private void methodWhichWillModify(String string) {
    string = "hello";
  }

  private void methodWhichWillModify(AnObject anObject) {
    anObject.string = "hello";
    anObject.integer = 1;
  }

  private void methodWhichWouldSubstituteTheIstanceOf(AnObject anObject) {
    anObject = new AnObject("hello", 1);
  }


  class AnObject {
    String string = "";
    int integer = 0;

    public AnObject(String string, int integer) {
      this.string = string;
      this.integer = integer;
    }
  }
}
