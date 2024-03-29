
import RoomEntity.Entity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author noahjamesy
 */
public class MainPresenter implements IMain.IPresenter {
    Entity entity;
    
    IMain.IViewer iviewer;
    public MainPresenter(IMain.IViewer iviewer) {
        entity = new Entity();
//        entity.setDatatypeEquivalentValues(Utils.data_types_equivalent);
        entity.setDatatypeDefaultValues(Utils.default_value);
        this.iviewer = iviewer;
    }
    
    
    
    @Override
    public void generateEntity(String convert_type,boolean uppercase_columns, String data_source,IMain.OnFinish callback){
         try{
             entity.setUppercaseColumns(uppercase_columns);
             String result = "";
             switch(convert_type.toLowerCase()){
                 case "manual": 
                      result = entity.generate(data_source);
                     break;
                 case "mysql":
                      result = entity.generateMysql(data_source);
                     break;
             }
             callback.onSuccess(result);
         }catch(Exception ex){
             callback.onFailed("Invalid Syntax: " + ex);
             ex.printStackTrace();
         }
        
    }
  
    

    @Override
    public void loadGuidelines() {
        String result = "Mysql to Java Equivalents\n\n";
        for(String[] i : entity.getDatatypeEquivalentValues()){
            result += i[0] + "\t" + i[1] + "\n";
        }
        iviewer.showGuidelines(result);
    }
    
}
