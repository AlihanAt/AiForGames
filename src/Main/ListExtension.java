package Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://stackoverflow.com/questions/11447780/convert-two-dimensional-array-to-list-in-java#11447971
public class ListExtension {

    public static <T> List<T> twoDArrayToList(T[][] twoDArray) {
        List<T> list = new ArrayList<T>();
        for (T[] array : twoDArray) {
            list.addAll(Arrays.asList(array));
        }
        return list;
    }
}
