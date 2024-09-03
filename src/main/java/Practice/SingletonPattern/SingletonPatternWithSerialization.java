package Practice.SingletonPattern;

import java.io.*;

public class SingletonPatternWithSerialization implements Serializable {
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
    // What about Serialization then? -  Any class that implements Serializable interface will face this issue.
    // When we convert a java object into any JSON/XML/ BYTE Stream then its serialization.
    // When we convert any JSON/XML/BYTE Stream back to java object then its De-Serialization.
    //During De-serialization process, a new object is getting created and it violets the principles of Singleton Pattern.
    // How we will solve this problem?
    // Add a readResolve method of the Object class and return the instance of the same class. This method will be called during De-serialization process.


    private volatile static SingletonPatternWithSerialization instance;

    private SingletonPatternWithSerialization(){
        // Added following two lines of code to block reflection attack.
        if(instance!=null)
            throw new IllegalArgumentException(" Object already exists");
    }

    public synchronized static SingletonPatternWithSerialization getInstance(){
        if(instance==null){
            synchronized (SingletonPatternWithSerialization.class){
                if(instance==null){
                    instance= new SingletonPatternWithSerialization();
                }
            }
        }
        return  instance;
    }

  protected  Object readResolve(){
        return  getInstance();
    }

    public void deSerializationProcess()  {

        try {
            SingletonPatternWithSerialization s1= SingletonPatternWithSerialization.getInstance();

            ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream("browser.json"));
            out.writeObject(s1);
            out.close();

            ObjectInputStream in= new ObjectInputStream(new FileInputStream("browser.json"));
            SingletonPatternWithSerialization s2= (SingletonPatternWithSerialization) in.readObject();
            in.close();

            System.out.println("s1.hashCode() = " + s1.hashCode());
            System.out.println("s2.hashCode() = " + s2.hashCode());
        } catch (Exception  e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {

//        SingletonPatternWithSerialization.getInstance().callingThread();
//        SingletonPatternWithSerialization.getInstance().reflectionAttackCase();
        SingletonPatternWithSerialization.getInstance().deSerializationProcess();
    }
}
