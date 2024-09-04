package Utils;

import org.testng.asserts.SoftAssert;

public class VerificationHelper {

    private static SoftAssert softAssert = new SoftAssert();
    private static Boolean resultOf(){
  //      if ()
        return null;
    }

    public static void verifySoftAssert(String actual, String expected, String details){
        if (actual.equalsIgnoreCase(expected)) {
   //         softAssert.
        }
 //       System.out.println(details +" : \nActual value : "+actual+ "\nExpected Value : "+ expected + " \n Result: " + softAssert.assertEquals(actual,expected););
    }
}
