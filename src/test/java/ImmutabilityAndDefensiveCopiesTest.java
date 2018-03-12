import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Test well known java behaviour, just to refresh together with enhance testing skills
 */
@RunWith(MockitoJUnitRunner.class)
public class ImmutabilityAndDefensiveCopiesTest {

  @Test(expected = UnsupportedOperationException.class)
  public void cannotModifyInternalListOfMyDataStructure() {
    MyDataStructure myDataStructure = new MyDataStructure();
    myDataStructure.getStringsLists().add("a");
  }

  class MyDataStructure {
    private List<String> stringsList = new ArrayList<>();

    public void addStringToList(String string) {
      stringsList.add(string);
    }

    public List<String> getStringsLists() {
      return Collections.unmodifiableList(stringsList);
    }
  }
}
