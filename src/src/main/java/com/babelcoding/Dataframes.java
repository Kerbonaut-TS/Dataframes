package com.babelcoding;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.text.html.BlockView;
import java.io.FileReader;
import java.lang.module.FindException;
import java.util.Arrays;
import java.util.Set;

public class Dataframes {
    JSONObject dataframe;
    Set<String> index;
    Set<String> columns;

    public Dataframes() {

        System.out.println("Dataframe v0.1");

    }

    public void read_json(String filepath) {

        try {
            FileReader reader = new FileReader(filepath);

            // Read JSON file
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(reader);

            this.dataframe = (JSONObject) obj;
            this.index = dataframe.keySet();
            JSONObject firstrecord = (JSONObject) this.dataframe.get(this.getIndex(0));
            this.columns = firstrecord.keySet();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Object getColumn(int c) {
        return this.columns.toArray()[c];
    }

    public Object getIndex(int i) {
        return this.index.toArray()[i];
    }

    public Object loc(String index, String column) {

        JSONObject record = (JSONObject) this.dataframe.get(index);
        Object value = record.get(column);
        return value;
    }

    public Object iloc(int index, int column) {

        JSONObject record = (JSONObject) this.dataframe.get(this.getIndex(index));
        Object value = record.get(this.getColumn(column));
        return value;
    }

    public void head() {

        System.out.print("Index" + "\t\t");
        for (Object c : this.columns) System.out.print(c + "\t\t");
        System.out.println();
        int count = 0;

        // Iterate over JSON object keys
        for (Object i : this.index) {
            System.out.print(i+"\t \t");
            for (Object c : this.columns) {
                JSONObject record = (JSONObject) this.dataframe.get(i);
                Object value = record.get(c);
                String string_value = String.format("%.3f", (double) value);
                System.out.print(string_value+"\t \t");
            }//end if
            if(count>5) break;
            count++;
            System.out.println();
        }//end for
        System.out.println();
    }




}
