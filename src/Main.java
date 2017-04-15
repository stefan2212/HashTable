import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class HashFunction{

    public String [] array;
    private int arraySize;
    private int itemsInArray=0;

    public HashFunction(int size){
        arraySize = size;
        array = new String[size];
        fillArrayWithNeg1();
    }

    public void fillArrayWithNeg1(){
        Arrays.fill(array,"-1");
    }

    public void hashFunction1(String[] stringsForArray, String[] array){
        for(int n=0; n<stringsForArray.length;n++) {
            String newElementVal = stringsForArray[n];
            array[Integer.parseInt(newElementVal)] = newElementVal;
        }

    }

    public void hashFunction2(String [] stringsForArray, String[] array){
        for(int n=0; n<stringsForArray.length;n++)
        {
            String newElementVal = stringsForArray[n];
            int arrayIndex = (Integer.parseInt(newElementVal) % arraySize);
            while(array[arrayIndex] != "-1" ){
                ++arrayIndex;
                arrayIndex%= arraySize;
            }

            array[arrayIndex] = newElementVal;
        }
    }

    public void doubleHashFunction(String []stringsForArray, String[] array){
        for(int n=0; n<stringsForArray.length;n++)
        {
            String newElementVal = stringsForArray[n];
            int arrayIndex = Integer.parseInt(newElementVal) %arraySize;
            int stepDistance = 7 - (Integer.parseInt(newElementVal)%7);
            while(array[arrayIndex] != "-1"){
                arrayIndex+=stepDistance;
                arrayIndex%= arraySize;
            }

            array[arrayIndex] = newElementVal;
        }
    }

    public String[] removeEmptySpacesInArray(String [] arrayToClean){
        ArrayList<String> stringList = new ArrayList<String>();
        for(String theString : arrayToClean)
        {
            if(!theString.equals("-1") && !theString.equals(""))
                stringList.add(theString);
        }
        return stringList.toArray(new String[stringList.size()]);
    }

    public boolean isPrime(int number){
        for(int i=2;i<Math.sqrt(number);i++)
            if(number%i==0)
                return false;
        return true;
    }

    public void display(){
        for(int i=0;i<array.length;i++)
        {   //if(Integer.parseInt(array[i])!=-1)
                System.out.print(" | " + array[i]);
        }
        System.out.println();
    }

    public String findKey(String key){
        int arrayIndexHash = Integer.parseInt(key) % arraySize;
        while(array[arrayIndexHash] != "-1")
        {
            if(array[arrayIndexHash] ==key)
                return array[arrayIndexHash];
            ++arrayIndexHash;
            arrayIndexHash %= arraySize;
        }
        return null;
    }

    public int getNextPrime(int number)
    {
        while(isPrime(++number)==false);
        return number;
    }

    public void incraseArraySize(int minArraySize){
        int newArraySize=getNextPrime(minArraySize);

        moveOldArray(newArraySize);
    }

    public void moveOldArray(int newArraySize){
        String [] cleanArray = removeEmptySpacesInArray(array);
        array = new String[newArraySize];
        arraySize = newArraySize;
        fillArrayWithNeg1();

        hashFunction2(cleanArray,array);
    }

    public int getSize(){
        return arraySize;
    }
}
public class Main {

    public static void main(String[] args) {
        HashFunction func = new HashFunction(31);
        String[] elementsToAdd = { "30", "60", "90", "120", "150", "180",
                "210", "240", "270", "300", "330", "360", "390", "420", "450",
                "480", "510", "540", "570", "600", "984", "320", "321",
                "400", "415", "450", "50", "660", "624" };
        func.hashFunction2(elementsToAdd,func.array);
        func.incraseArraySize(60);
        System.out.println(func.getSize());
        func.display();

    }
}
