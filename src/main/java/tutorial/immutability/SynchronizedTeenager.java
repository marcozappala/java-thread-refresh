package tutorial.immutability;

class PersonAsMutableObject {

    private String name;
    private int age;


    public PersonAsMutableObject(String name, int age) {
        validateAge(age);
        this.name = name;
        this.age = age;
    }

    private static void validateAge(int age) {
        if (age > 110 || age < 1) {
            throw new IllegalArgumentException();
        }
    }

    public synchronized String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void set(String name, int age) {
        validateAge(age);
        synchronized (this) {
            this.name = name;
            this.age = age;
        }
    }

    public void setAge(int age) {
        validateAge(age);
        this.age = age;
    }


    @Override
    public String toString() {
        return "PersonAsMutableObject{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class SynchronizedTeenager {
    public static void main(String[] args) {
        PersonAsMutableObject teenager = new PersonAsMutableObject("Vittoria", 27);
        Thread updater = new Thread(new PersonUpdater(teenager, 25));
        Thread updater2 = new Thread(new PersonUpdater(teenager, 26));
        updater.start();
        updater2.start();
        try {
//            if we don't wait, the main thread could finish before the other two threads complete.
            updater.join(1000);
            updater2.join(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(teenager);

    }
}


class PersonUpdater implements Runnable{

    private final PersonAsMutableObject person;
    private int newAge;

    public PersonUpdater(PersonAsMutableObject person, int newAge) {
        this.person = person;
        this.newAge = newAge;
    }

    @Override
    public void run() {
//        the two following lines should be synchronized:
//
//        synchronized (teenager) {
//            this.teenager.setAge(this.newAge);
//            this.teenager.setName("youngerVittoria" + this.newAge);
//        }
//
//        If not, we COULD have the two threads interleave execution between
//        these two statements, having so an output like (eg):
//        TeenagerAsMutableObject{name='youngerVittoria25', age=26}
//
//        synchronized (teenager) {
//            this.teenager.setAge(this.newAge);
//            this.teenager.setName("youngerVittoria" + this.newAge);
//        }
        this.person.setAge(this.newAge);
        this.person.setName("youngerVittoria" + this.newAge);


    }
}
