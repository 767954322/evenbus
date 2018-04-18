package com.wg.evenbus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends Activity {

	Button btn;
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		EventBus.getDefault().register(this);

		btn = (Button) findViewById(R.id.btn_try);
		tv = (TextView)findViewById(R.id.tv);

		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						SecondActivity.class);
				startActivity(intent);
			}
		});
	}

	/**
	 *
	 ThreadMode总共四个：
	 NAIN UI主线程
	 BACKGROUND 后台线程
	 POSTING 和发布者处在同一个线程
	 ASYNC 异步线程
	 * @param event
	 */
	@Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
	public void onEventMainThread(FirstEvent event) {

		String msg = "onEventMainThread收到了消息：" + event.getMsg();
		Log.d("harvic", msg);
		tv.setText(msg);
		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
	}
	@Override
	protected void onDestroy(){
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}
}
