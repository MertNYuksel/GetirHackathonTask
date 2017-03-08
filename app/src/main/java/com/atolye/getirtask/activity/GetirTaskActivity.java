package com.atolye.getirtask.activity;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.atolye.getirtask.DrawingView;
import com.atolye.getirtask.R;
import com.atolye.getirtask.base.BaseActivity;
import com.atolye.getirtask.request.Basvuru;
import com.atolye.getirtask.response.BaseResponse;
import com.atolye.getirtask.response.Element;

import java.util.ArrayList;

public class GetirTaskActivity extends BaseActivity {

    private DrawingView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getir_task);
        view = (DrawingView) findViewById(R.id.canvas);
        sendRequest(Request.Method.POST, "https://getir-bitaksi-hackathon.herokuapp.com/getElements", new Basvuru("mertnevzatyuksel@gmail.com", "Mert Nevzat Yüksel", "05058021831"), BaseResponse.class, sekilCevap, null);
    }

    private Response.Listener<BaseResponse> sekilCevap = new Response.Listener<BaseResponse>() {
        @Override
        public void onResponse(BaseResponse response) {
            view.drawResponse(response);
        }
    };


    private BaseResponse fakeResponse() {
        BaseResponse response = new BaseResponse();
        response.setElements(new ArrayList<Element>() {{
            add(new Element() {{
                setType("circle");
                setXPosition(320);
                setYPosition(41);
                setR(46);
                setColor("a70267");
            }});
            add(new Element() {{
                setType("rectangle");
                setXPosition(184);
                setYPosition(399);
                setWidth(37);
                setHeight(208);
                setColor("a70267");
            }});
        }});
        return response;
    }
}
