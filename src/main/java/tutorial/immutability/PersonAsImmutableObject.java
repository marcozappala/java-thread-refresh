package tutorial.immutability;

/**
 * Immutable:
 * 1. Don't provide "setter" methods — methods that modify fields or objects referred to by fields.
 * 2. Make all fields final and private.
 * 3. Don't allow subclasses to override methods. The simplest way to do this is to declare the class as final. A more
 *      sophisticated approach is to make the constructor private and construct instances in factory methods.
 * 4. If the instance fields include references to mutable objects, don't allow those objects to be changed:
 *  - Don't provide methods that modify the mutable objects.
 *  - Don't share references to the mutable objects. Never store references to external, mutable objects passed to the constructor;
 *      if necessary, create copies, and store references to the copies. Similarly, create copies of your internal
 *      mutable objects when necessary to avoid returning the originals in your methods.
 */
public class PersonAsImmutableObject {
    private String name;
    private int age;

    public PersonAsImmutableObject(String name, int age) {
        validateAge(age);
        this.name = name;
        this.age = age;
    }

    private static void validateAge(int age) {
        if (age > 110 || age < 1) {
            throw new IllegalArgumentException();
        }
    }

    public String getName(){
        return this.name;
    }

    public int getAge() {
        return this.getAge();
    }

    public PersonAsImmutableObject newAge(int newAge) {
        return new PersonAsImmutableObject(this.name, newAge);
    }
}
