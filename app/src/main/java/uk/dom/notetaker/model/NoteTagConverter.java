package uk.dom.notetaker.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public final class NoteTagConverter {

    public static String getStringFromArray(ArrayList<String> tagArray){

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("tagArray", new JSONArray(tagArray));
        }catch (JSONException e){
            throw new RuntimeException(e);
        }

        return jsonObject.toString();
    }

    public static ArrayList<String> getArrayFromString(String tagString){

        ArrayList<String> noteTags = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(tagString);
            JSONArray jsonArray = jsonObject.optJSONArray(tagString);

            for(int i = 0; i < jsonArray.length(); i++){
                noteTags.add(jsonArray.get(i).toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return noteTags;
    }


}
