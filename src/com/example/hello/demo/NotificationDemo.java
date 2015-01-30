package com.example.hello.demo;

import com.example.hello.R;

import android.app.Activity;
import android.app.Notification;
import android.app.Notification.BigPictureStyle;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;

public class NotificationDemo extends Activity implements OnClickListener{
	/** Notification������ */
	private NotificationCompat.Builder mCompatBuilder;
	
	private Button btnClearNotification,btnTest1,btnTest2,btnTest3;
	/** Notification���� */
	public NotificationManager mNotificationManager;
	/** Notification������ */
	Builder mBuilder;
	/** Notification��ID */
	int notifyId = 101;
	/** Notification��ID */
	int notifyId2 = 101;
	
	private BigPictureStyle mNotificationStyle;
	
	private int number = 111;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		//createNotification(this);
		
		setContentView(R.layout.demo_notification);
		btnClearNotification = (Button)findViewById(R.id.btn_clear);
		btnClearNotification.setOnClickListener(this);
		btnTest1 = (Button)findViewById(R.id.btn_test1);
		btnTest1.setOnClickListener(this);
		btnTest2 = (Button)findViewById(R.id.btn_test2);
		btnTest2.setOnClickListener(this);
		btnTest3 = (Button)findViewById(R.id.btn_test3);
		btnTest3.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.btn_clear:
			mNotificationManager.cancel(notifyId2);
			break;
		case R.id.btn_test1:
			createNotification1(this);
			break;
		case R.id.btn_test2:
			createNotification2(this);
			break;
		case R.id.btn_test3:
			createNotification3(this, number);
			number++;
			break;
		}
		
	}
	
	/**
	 * @��ȡĬ�ϵ�pendingIntent,Ϊ�˷�ֹ2.3�����°汾����
	 * @flags����:  
	 * �ڶ�����פ:Notification.FLAG_ONGOING_EVENT  
	 * ���ȥ���� Notification.FLAG_AUTO_CANCEL 
	 */
	public PendingIntent getDefalutIntent(int flags){
		PendingIntent pendingIntent= PendingIntent.getActivity(this, 1, new Intent(), flags);
		return pendingIntent;
	}
	
	private void createNotification1(Context c){
		mBuilder = new Notification.Builder(this);
		mBuilder.setContentTitle("���Ա���")
		.setContentText("��������")
		.setNumber(5)
		.setContentIntent(getDefalutIntent(Notification.FLAG_AUTO_CANCEL))
		.setTicker("����֪ͨ����")//֪ͨ�״γ�����֪ͨ��������������Ч����
		.setWhen(System.currentTimeMillis())//֪ͨ������ʱ�䣬����֪ͨ��Ϣ����ʾ
		.setPriority(Notification.PRIORITY_DEFAULT)//���ø�֪ͨ���ȼ�
//		.setAutoCancel(true)//���������־���û��������Ϳ�����֪ͨ���Զ�ȡ��  
		.setOngoing(false)//ture��������Ϊһ�����ڽ��е�֪ͨ������ͨ����������ʾһ����̨����,�û���������(�粥������)����ĳ�ַ�ʽ���ڵȴ�,���ռ���豸(��һ���ļ�����,ͬ������,������������)
		.setDefaults(Notification.DEFAULT_VIBRATE)//��֪ͨ������������ƺ���Ч������򵥡���һ�µķ�ʽ��ʹ�õ�ǰ���û�Ĭ�����ã�ʹ��defaults���ԣ�������ϣ�
		//Notification.DEFAULT_ALL  Notification.DEFAULT_SOUND ������� // requires VIBRATE permission
		.setSmallIcon(R.drawable.ic_launcher);
		Bitmap b = BitmapFactory.decodeResource(c.getResources(), R.drawable.ic_appwidget_music_play);
		mNotificationStyle = new Notification.BigPictureStyle().bigPicture(b);
		mBuilder.setStyle(mNotificationStyle);
		
		mNotificationManager.notify(notifyId, mBuilder.build());
	}
	
	private void createNotification2(Context c){
		mCompatBuilder = new NotificationCompat.Builder(this);
		
		RemoteViews mRemoteViews = new RemoteViews(getPackageName(), R.layout.view_custom);
		mRemoteViews.setImageViewResource(R.id.custom_icon, R.drawable.sing_icon);
//		view_custom.setInt(R.id.custom_icon,"setBackgroundResource",R.drawable.icon);
		mRemoteViews.setTextViewText(R.id.tv_custom_title, "����ͷ��");
		mRemoteViews.setTextViewText(R.id.tv_custom_content, "������ʿ�ٷ�����");
		  
		NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
		String[] events = {"dd", "33", "44"};
		// Sets a title for the Inbox style big view
		inboxStyle.setBigContentTitle("����ͼ����:");
		// Moves events into the big view
	    for (int i=0; i < events.length; i++) {
	    	inboxStyle.addLine(events[i]);
	    }
	    
		mCompatBuilder.setContent(mRemoteViews)
		 			  .setSmallIcon(R.drawable.ic_launcher)
					  .setContentIntent(getDefalutIntent(Notification.FLAG_AUTO_CANCEL))
					  .setTicker("����֪ͨ����")//֪ͨ�״γ�����֪ͨ��������������Ч����
					  .setWhen(System.currentTimeMillis())//֪ͨ������ʱ�䣬����֪ͨ��Ϣ����ʾ
					  .setPriority(Notification.PRIORITY_DEFAULT)//���ø�֪ͨ���ȼ�
					  .setOngoing(false)
					  .setStyle(inboxStyle);
			         
		mNotificationManager.notify(notifyId2, mCompatBuilder.build());
	}
	
	private void createNotification3(Context c, int id){         
		Notification n = new Notification(R.drawable.ic_launcher, "Hello,there!", System.currentTimeMillis());              
		n.flags = Notification.FLAG_AUTO_CANCEL;
		                  
		n.setLatestEventInfo(
				c, 
		        "Hello,there!",  
		        "Hello,there,I'm john.",  
		        getDefalutIntent(Notification.FLAG_AUTO_CANCEL)); 
		RemoteViews bigContent = new RemoteViews(getPackageName(), R.layout.big_content);
		n.bigContentView = bigContent;
		
		mNotificationManager.notify(id, n); 
		
	}
	
	
	
}
