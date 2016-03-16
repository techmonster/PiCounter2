package edu.dtcc.nhollowa.picounter2;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.math.BigDecimal;


public class MyActivity extends AppCompatActivity {

    private TextView countTextView;
    private Integer count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        //REFERENCE THE TEXTVIEW UI ELEMENT ONT THE LAYOUT
        countTextView = (TextView) findViewById(R.id.textView);

        //INITIALIZE THE COUNTER
        count = 0;

        //CREATE A THREAD AND START IT
        Thread thread = new Thread(countNumbers);
        thread.start();
    }

    //INITIALIZE THE COUNTER TO ZERO EACH TIME THAT THE APPLICATION LAUNCHES


    @Override
    protected void onStart() {
        super.onStart();
        count = 0;
    }


//**************RUNNABLE***************/

private Runnable countNumbers = new Runnable() {
    private static final int DELAY = 1000;
    @Override
    public void run() {
        try {
            while (true) {
                count++;
                Thread.sleep(DELAY);
                threadHandler.sendEmptyMessage(0);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
};

//*************HANDLER******************/
public Handler threadHandler = new Handler() {
    int index = 1;
    BigDecimal d = new BigDecimal(Math.PI);
    String s = d.toString();
    StringBuilder sb = new StringBuilder();



    @Override
    public void handleMessage(android.os.Message message) {

        Character c = s.charAt(index-1);


        countTextView.setText(sb.append(c).toString());
        index++;
    }
};


}

