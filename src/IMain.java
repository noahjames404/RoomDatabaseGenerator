/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author noahjamesy
 */
public interface IMain {
    interface OnFinish{
        void onSuccess(String message);
        void onFailed(String message);
    }
    
    interface IPresenter{
        void generateEntity(String data_source,IMain.OnFinish callback);
        void loadGuidelines();
    }
    
    interface IViewer{
        void showGuidelines(String message);
    }
}
