/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import org.json.simple.*;

/**
 *
 * @author longnh
 */


public class JsonFactory {
//    public JsonFactory(){
//        System.out.println("new JsonFactory initialized!");
//    }
    
    public JSONObject create_json(Type type){
        JSONObject j = new JSONObject();
        j.put("result", "");
        switch(type){
            case OK:
                j.put("result", Status.OK);
                break;
            case ERROR:
                j.put("result", Status.ERROR);
                break;
        }
        return j;
    }
    
    public JSONArray create_json_array(){
        return new JSONArray();
    }
}
