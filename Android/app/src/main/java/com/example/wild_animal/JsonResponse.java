package com.example.wild_animal;

import android.view.View;
import android.widget.AdapterView;

import org.json.JSONException;
import org.json.JSONObject;

public interface JsonResponse {
	public void response(JSONObject jo) throws JSONException;

    void onItemClick(AdapterView<?> adapterView, View view, int i, long l);
}
