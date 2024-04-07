package com.babelcoding.Dataframes;

import com.babelcoding.Utils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Set;

import org.json.simple.parser.ParseException;

public class Dataframe {
    JSONObject dataframe;
    public Set<String> index;
    public Set<String> columns;

    public Dataframe() {
    this.dataframe = null;
    }

    public Dataframe(String json_string) {

        try {
            this.dataframe = this.parse_json(json_string);
            this.read_axis();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void read_json(String filepath) throws ParseException, IOException {

            FileReader reader = new FileReader(filepath);
            this.dataframe = this.parse_json(reader);
            this.read_axis();
    }

    private void read_axis(){

        this.index = dataframe.keySet();
        JSONObject firstrecord = (JSONObject) this.dataframe.get(this.getIndex(0));
        this.columns = firstrecord.keySet();

    }

    public void append(Dictionary new_record, String index) throws ParseException {

        String record_string = Dataframe.dict_to_json(new_record);

        if (dataframe == null) {
            record_string = "{ \"" +index + "\":" + record_string + "}";
            this.dataframe = this.parse_json(record_string);
        }else{
            dataframe.put(index, this.parse_json(record_string));
        }

         //update index
        this.read_axis();

    }


    private JSONObject parse_json(String filedata) throws ParseException {
        // parse JSON string
        JSONParser jsonParser = new JSONParser();
        Object json = jsonParser.parse(filedata);
        return (JSONObject)  json;
    }

    private JSONObject parse_json(FileReader filedata) throws ParseException, IOException {
        // Read JSON file
        JSONParser jsonParser = new JSONParser();
        Object json = jsonParser.parse(filedata);
        return (JSONObject)  json;
    }

    public Object getColumn(int c) {
        return this.columns.toArray()[c];
    }

    public Object getIndex(int i) {
        return this.index.toArray()[i];
    }

    public Object loc(Object index, Object column) {

        return this.loc(index.toString(), column.toString());
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

    public double[][] get2dArray() {

        double[][] a2dArray = new double[this.index.size()][this.columns.size()];
        int c = 0;
        int i = 0;

        // Iterate over JSON object keys
        for (Object idx : this.index) {
            for (Object col:this.columns){

                a2dArray[i][c] = (double) this.loc(idx, col);
                c++;
            }//for columns

            c=0;
            i++;
        }//for rows

        return a2dArray;
    }



    public void head(int MAX_ROWS) {

        int MAX_COLS = 15;
        int count_rows = 0;
        int count_cols = 0;

        System.out.print("Index" + "\t\t");
        for (int c =0; c<MAX_COLS; c++) {
            System.out.print(this.columns.toArray()[c] + "\t\t");

        }

        System.out.println();
        count_cols=0;

        // Iterate over JSON object keys
        for (Object i : this.index) {
            System.out.print(i+"\t \t");

            for (int c =0; c<MAX_COLS; c++) {

                JSONObject record = (JSONObject) this.dataframe.get(i);
                Object value = record.get(this.columns.toArray()[c]);
                String string_value = String.format("%.3f", (double) value);
                System.out.print(string_value+"\t \t");

            }//end if
            if(count_rows>MAX_ROWS) break;
            count_rows++;
            System.out.println();
        }//end for
        System.out.println();
    }

    public JSONObject get_jsonObject(){ return this.dataframe;}


    public void to_JSON(String filepath){
        String json_out = this.dataframe.toJSONString();
        Utils.writeFile(filepath, json_out);

    }


    public static  String dict_to_json(Dictionary record){

        // statistics to String
        String json_string = record.toString().replace("=", "\":").replace(", ", ", \"").replace("{", "\"").replace("}", "");
        json_string = "{" + json_string + "}";

        return json_string;
    }






}
