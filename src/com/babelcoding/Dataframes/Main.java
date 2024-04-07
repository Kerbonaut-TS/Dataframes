package com.babelcoding.Dataframes;

import com.babelcoding.Dataframes.Dataframe;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws ParseException, IOException {

        Dataframe df = new Dataframe();
        df.read_json("D:\\Documents\\Workspace\\imgLab\\clustering\\tiles_now.json");
        df.head(10);

        double value = (double) df.loc("94", "width");

        System.out.println("Get value using labels: "  + value);

        value = (double) df.iloc(2, 0);

        System.out.println("Get value using coordinates: "  + value);
    }
}