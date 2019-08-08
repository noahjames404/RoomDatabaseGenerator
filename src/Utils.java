/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author noahjamesy
 */
public class Utils {

    /**
     *array[mysql_datatype,equivalent_datatype_in_java]
     */
    public static final String [][] data_types_equivalent = {
        {"varchar","String"},
        {"text","String"},
        {"char","char"},
        {"tinytext","String"},
        {"text","String"},
        {"mediumtext","String"},
        {"longtext","String"},
        {"tinyint","int"},
        {"smallint","int"},
        {"mediumint","int"},
        {"int","int"},
        {"bigint","long"},
        {"float","float"},
        {"double","double"},
        {"decimal","double"},
        {"date","String"},
        {"datetime","String"},
        {"timestamp","String"},
        {"time","String"}, 
    };
    
    public static final String[][] default_value =  {
        {"String","\"\""},
        {"int","0"},
        {"double","0"},
        {"long","0"},
        {"float","0"},
        {"char","''"},
    };
}
