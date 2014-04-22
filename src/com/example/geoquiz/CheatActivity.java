package com.example.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {

	public final static String EXTRA_ANSWEAR_SHOWN = "com.example.geoquiz.answear_shown";

	private boolean theAnswear;

	private void setAnswearShown(boolean isAnswearShown) {
		Intent data = new Intent();
		data.putExtra(EXTRA_ANSWEAR_SHOWN, isAnswearShown);
		setResult(RESULT_OK, data);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cheat);

		setAnswearShown(false);

		theAnswear = getIntent().getBooleanExtra(
				MainActivity.EXTRA_ANSWEAR_IS_TURE, false);

		Button GiveAnswear = (Button) findViewById(R.id.showAnswearButton);
		final TextView sendAnswear = (TextView) findViewById(R.id.answeatTextView);

		GiveAnswear.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if (theAnswear)
					sendAnswear.setText(R.string.True);
				else
					sendAnswear.setText(R.string.False);

				setAnswearShown(true);

			}
		});
	}
}
