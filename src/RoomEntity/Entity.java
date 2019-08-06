/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoomEntity;

/**
 *
 * @author noahjamesy
 */
public class Entity {
    
        /**
          default value
          array[value,equivalent]
       */
        private String datatype_equivalent_values [][] = {
             {"i","int"},
             {"v","String"},
             {"d","Double"}
        };

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
                        String find = "-" + d[0];
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
                return_val += String.format("@ColumnInfo(name = \"%s\")\n%s %s;\n\n",result[0],result[1],result[0]);	
            }
            return return_val;
        }
}
