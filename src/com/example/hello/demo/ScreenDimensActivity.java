package com.example.hello.demo;

import com.example.hello.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ScreenDimensActivity extends Activity  {

	private boolean flag = true;
	private Button btnTest;
	private TextView tv;
	public static final int FLAG_HOMEKEY_DISPATCHED = 0x40000000;
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState); 
        //this.getWindow().setFlags(FLAG_HOMEKEY_DISPATCHED, FLAG_HOMEKEY_DISPATCHED);
        setContentView(R.layout.activity_main);
        
        btnTest = (Button)findViewById(R.id.test);	
        tv = (TextView)findViewById(R.id.test_text);	
		btnTest.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//Intent i = new Intent("com.android.internal.policy.impl.LockPatternKeyguardView.KEYGUARD");
				Log.i("MainActivityt", "send it");
				//sendBroadcast(i);
				//tv.setText(null);
				printWidth(tv);
			}
		});
    }
	
	private void printWidth(TextView TV){
		// Ҫ��ȡ��Ļ�Ŀ�͸ߵȲ�����������Ҫ����һ��DisplayMetrics������Ļ�Ŀ�ߵ����Դ�������������
        DisplayMetrics DM = new DisplayMetrics();
        // ��ȡ���ڹ�����,��ȡ��ǰ�Ĵ���,����getDefaultDisplay()���佫������Ļ��һЩ��Ϣд��DM������,���ͨ��getMetrics(DM)��ȡ
        getWindowManager().getDefaultDisplay().getMetrics(DM);

        int wdip = px2dip(getApplicationContext(), getApplicationContext()
                .getResources().getDisplayMetrics().widthPixels);
        int hdip = px2dip(getApplicationContext(), getApplicationContext()
                .getResources().getDisplayMetrics().heightPixels);

        // ��ӡ��ȡ�Ŀ�͸�
        TV.setText("densityDpi: "
                + DM.densityDpi
                + "\n"
                + "density: "
                + DM.density
                + "\n"
                + "scaledDensity: "
                + DM.scaledDensity
                + "\n"
                + "heightPixels(The absolute height of the display in pixels.): \n "
                + DM.heightPixels
                + "\n"
                + "widthPixels(The absolute width of the display in pixels.): \n "
                + DM.widthPixels
                + "\n"
                + "xdpi(The exact physical pixels per inch of the screen in the X dimension): \n "
                + DM.xdpi
                + "\n"
                + "ydpi(The exact physical pixels per inch of the screen in the Y dimension): \n "
                + DM.ydpi + "\n"

                + "wdip: " + wdip + "\n"

                + "hdip: " + hdip + "\n");
	}
	
	
	@Override
	public void onAttachedToWindow() {
		//this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);
		super.onAttachedToWindow();
	}
	
	/**
     * Change value from pix to dip
     * 
     * @param context
     * @param pxValue
     * @return
     */
    public final static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;

        return (int) (pxValue / scale + 0.5f);
    }

}
