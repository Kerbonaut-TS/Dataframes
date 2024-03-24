package com.babelcoding;


public class Main {
    public static void main(String[] args) {

        Dataframes df = new Dataframes();
        df.read_json("path\\to\\file");
        df.head();

        double value = (double) df.loc("94", "width");

        System.out.println("Get value using labels: "  + value);

        value = (double) df.iloc(2, 0);

        System.out.println("Get value using coordinates: "  + value);
    }
}