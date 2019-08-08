/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoomEntity;

import java.util.ArrayList;

/**
 *
 * @author noahjamesy
 */
public class Entity {
    
        /**
          default value
          array[value,equivalent]
       */
        boolean uppercase_columns = false;
        
    
        private String datatype_equivalent_values [][] = {
             {"i","int"},
             {"v","String"},
             {"d","double"}
        };
        
        private String datatype_default_values[][] = {
            {"int","0"},
            {"String","\"\""},
            {"double","0"},
        };

    public String[][] getDatatypeDefaultValues() {
        return datatype_default_values;
    }

    public void setDatatypeDefaultValues(String[][] datatype_default_values) {
        this.datatype_default_values = datatype_default_values;
    }

    public void setUppercaseColumns(boolean uppercase_columns) {
        this.uppercase_columns = uppercase_columns;
    }
        
        
    
        
        

        public String[][] getDatatypeEquivalentValues() {
             return datatype_equivalent_values;
        }

        public void setDatatypeEquivalentValues(String[][] datatype_equivalent_values) {
             this.datatype_equivalent_values = datatype_equivalent_values;
        }
        
        
        
        

        /**
             @param str value to compare
             @param equivalents list of value similarities, overrides the default 

             @return an array of column name & [equivalent value of str  | null]
        */
        public String[] getDataType(String str, String [][]equivalents){
               String [] data = new String[2];
               for(String[]d : equivalents){
                        String find = "-" + d[0].charAt(0);
              		if(str.indexOf(find) > 0){
                                
				data[0] = str.replace(find,"");
                                data[1] = d[1];
                                return data;
                        }
               }

              return null;
        }
        
        /**
         *   The method uses the default value of datatype_equivalent_values.
             @param str value to compare
             @return an array of column name & [equivalent value of str  | null]
        */
        public String[] getDataType(String str){
               return getDataType(str,datatype_equivalent_values);
        }
        
        /**
        @param result - can be obtain from getDataType
        @return the generated entity
        */
        public String generate(String raw_data){
            String return_val = "";
            String [] data = raw_data.split("\n");
            for(int i =0 ; i < data.length; i++){
                String [] result = getDataType(data[i]);
                return_val += String.format("@ColumnInfo(name = \"%s\")\n%s %s;\n\n",isUpperCase(result[0]),result[1],result[0]);	
            }
            return return_val;
        }
        
        public String generateMysql(String raw_data){
            ArrayList<String[]> data = mysqlTableToArrayList(raw_data);
            String return_val = "";
            for(int i =0 ; i < data.size(); i++){
                String []array_data = data.get(i);
                String val = array_data[0] + "-"+ array_data[1].charAt(0);
                String isNull = array_data[2];
                String isPrimaryKey = array_data[3];
                String isAutoIncrement = array_data[4];
                
                String [] result = getDataType(val);
                String default_value = getDefaultValue(result[1]);
                   
                    return_val += String.format("%s%s@ColumnInfo(name = \"%s\")\n%s %s = %s;\n\n",nonNullMysql(isNull),primaryKeyMysql(isPrimaryKey,isAutoIncrement),isUpperCase(result[0]),result[1],result[0],default_value);	
            }
            
            return return_val;
        }
        /*
        'id', 'int(11)', 'NO', 'PRI', NULL, 'auto_increment'
'name', 'varchar(45)', 'YES', '', NULL, ''
'uname', 'varchar(45)', 'YES', '', NULL, ''
'upass', 'varchar(45)', 'YES', '', NULL, ''
'status', 'varchar(45)', 'YES', '', NULL, ''
'access_token', 'varchar(50)', 'YES', '', NULL, ''
'auth_key', 'varchar(50)', 'YES', '', NULL, ''
        
        */
        
        public String getDefaultValue(String datatype){
            for(String[] d : datatype_default_values){
                if(datatype.equals(d[0])){
                    return d[1];
                }
            }
            return  "";
        }

        public String nonNullMysql(String is){
            return is.equals("NO") ? "@NonNull\n" : "";
        }
        
        public String primaryKeyMysql(String is,String s){
            if(is.equals("PRI")){ 
                if(s.equals("auto_increment")){
                    return "@PrimaryKey(autoGenerate = true)\n";
                }
                return "@PrimaryKey\n";
            }
            return "";
        }
        
        public ArrayList<String[]> mysqlTableToArrayList(String raw_data){
            String [] columns = raw_data.replace(" ", "").replace("'", "").split("\n");
            ArrayList<String[]> list = new ArrayList<>(); 
            for(String c : columns){
                String [] column_info = c.split(",");
                list.add(column_info);
            }
            return list;
        }
        
        public String isUpperCase(String data){
            return uppercase_columns ? data.toUpperCase() : data;
        }
}
