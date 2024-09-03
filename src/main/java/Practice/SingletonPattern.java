package Practice;

public class SingletonPattern {
   //purpose of Singleton Pattern is to achieve only one object creation of the class.

    private static SingletonPattern instance;

    private SingletonPattern(){

    }

    public static SingletonPattern getInstance(){
        if(instance==null){
            instance= new SingletonPattern();
        }
        return  instance;
    }

    public  void display(){
        System.out.println("I am from Singleton Class");
    }

    public static void main(String[] args) {
        SingletonPattern obj= SingletonPattern.getInstance();
        obj.display();
    }
}
