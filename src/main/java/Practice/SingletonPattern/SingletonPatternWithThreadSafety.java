package Practice.SingletonPattern;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SingletonPatternWithThreadSafety {
   //purpose of Singleton Pattern is to achieve only one object creation of the class.
   // In the multithreading environment/ parallel operations, this won't work without making the method synchronized.
    //If we apply synchronized keyword at the method level on getInstance method, then how it will work - lets discuss:
    // Suppose there are 4 threads. 1st thread comes and checks of the instance == null, yes its null, then a new instance will be created and will be given to the thread.
    // Now thread 1 will apply a lock on this synchronized method and till the time this lock is present, no other thread will be able to access this method and won't get the instance/ same object.
    // So all the other threads will be waiting for thread 1 to complete its operations.
    // This will unnecessarily increase the execution time of the tests.
    // To avoid this condition, we will apply the synchronized keyword inside the method on the if condition.
    // After this, any new thread can call this static getInstance method. If thread 1 is already using the instance and thread 2 calls this method, it will check if instance==null.
    //If instance!= null then the same instance will be given to the thread 2 and so on to thread 3 and thread 4 irrespective of thread 1 is completing its operation or not. The lock won't be applied to
    // the static getInstance method.
    //The volatile keyword in Java is used to indicate that a variable's value can be modified by different threads. Used with the syntax, volatile dataType variableName = x; It ensures that changes made to a volatile variable by one thread are immediately visible to other threads.
    // SO we will make the instance variable volatile .

    private volatile static SingletonPatternWithThreadSafety instance;

    private SingletonPatternWithThreadSafety(){
        // Added following two lines of code to block reflection attack.
        if(instance!=null)
            throw new IllegalArgumentException(" Object already exists");
    }

    public synchronized static SingletonPatternWithThreadSafety getInstance(){
        if(instance==null){
            synchronized (SingletonPatternWithThreadSafety.class){
                if(instance==null){
                    instance= new SingletonPatternWithThreadSafety();
                }
            }
        }
        return  instance;
    }

    public  void display(){
        System.out.println("I am from Singleton Class");
    }

    public void callingThread(){
        Runnable task= () -> {
            SingletonPatternWithThreadSafety.getInstance().display();
        };

        Thread t1= new Thread(task);
        Thread t2= new Thread(task);
        Thread t3= new Thread(task);
        Thread t4= new Thread(task);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try{
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //By using Reflection Attack we can create two objects of the Singleton Class that we have created.
    // The following code can achieve this.
    // We will get the .class reflection and the declared constructor of that class. And will store it in Constructor variable of type of that class.
    // Then we will set accessible=true. then we will call newInstance() method on that Constructor variable and store that new instance in the object of that same class.
    // Now we will get the hashcode of the first object and the second object created by the Reflection attack.
    //If both the values are different then the reflection attack is successful and Singleton Pattern is not achieved.
    // If both the values are same, then reflection attack is not successful and Singleton pattern is achieved.
    // To achieve the Singleton pattern in this scenario, we can add a condition in the Private Constructor of the class to check if
    // the instance is not null, then throw an exception.
    public void reflectionAttackCase(){
        SingletonPatternWithThreadSafety s1= SingletonPatternWithThreadSafety.getInstance();
        SingletonPatternWithThreadSafety s2= null;

        try {
            Constructor<SingletonPatternWithThreadSafety> constructor=  SingletonPatternWithThreadSafety.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            s2= constructor.newInstance();
            s2.display();
            System.out.println("s1.hashCode() = " + s1.hashCode());
            System.out.println("s2.hashCode() = " + s2.hashCode());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }
    public static void main(String[] args) {

        SingletonPatternWithThreadSafety.getInstance().callingThread();
        SingletonPatternWithThreadSafety.getInstance().reflectionAttackCase();
    }
}
