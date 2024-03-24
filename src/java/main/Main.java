package java.main;
import com.babelcoding.Dataframe;

public class Main {
    public static void main(String[] args) {

        Dataframe df = new Dataframe();
        df.read_json("D:\\Documents\\Workspace\\imgLab\\clustering\\tiles_now.json");
        df.head();

        double value = (double) df.loc("94", "width");

        System.out.println("Get value using labels: "  + value);

        value = (double) df.iloc(2, 0);

        System.out.println("Get value using coordinates: "  + value);
    }
}