package com.example.geoquiz;
/**
 * @author Marales Razvan
 * @version 1.0
 */

import java.sql.NClob;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

		private Button mTrueButton;
		private Button mFalseButton;
		private Button mNextButton;
		private Button mBackButton;
		private Button mCheatButton;
		
		private TextView mQuestionTextView = null;
		
		private boolean mIsCheater;
		
		public final static String EXTRA_ANSWEAR_IS_TURE = "com.example.geoquiz.isTrueAnswer";
		public final static String EXTRA_ANSWEAR_SHOWN = "com.example.geoquiz.answear_shown";
		
		private trueFalse[] mQuestionBank = new trueFalse[] {
				 new trueFalse(R.string.question2, true),
				 new trueFalse(R.string.question4, false),
				 new trueFalse(R.string.question3, true),
				 new trueFalse(R.string.question1, true),
				 new trueFalse(R.string.question1, false)
				 
		 };
		 
		 private int mCurentIndex = 0;
		 

		 
	private void updateQuestion(){
		int question = mQuestionBank[mCurentIndex].getQuestion();
		mQuestionTextView.setText(question);
	}
	
	private void isTrueAnswer(boolean userPressTrue){
		boolean answerIsTrue = mQuestionBank[mCurentIndex].isTrueQuestion();
		
		int message = 0;
		if(mIsCheater)
			message = R.string.Judgment;
		else if(userPressTrue == answerIsTrue){
			message = R.string.Correct;
			mCurentIndex = (mCurentIndex + 1 ) % mQuestionBank.length ;
			updateQuestion();
		}else{
			message = R.string.Wrong;
		}
		
		Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();;
	}

	@Override    
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {   
		  if (data == null) { 
			  return;       
			  }    
	  mIsCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWEAR_SHOWN , false);   
	  }
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mCheatButton = (Button) findViewById(R.id.buttonCheat);
		mCheatButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(MainActivity.this, CheatActivity.class);
				boolean getAnswear = mQuestionBank[mCurentIndex]
						.isTrueQuestion();
				i.putExtra(MainActivity.EXTRA_ANSWEAR_IS_TURE, getAnswear);
				startActivityForResult(i, 0);

			}
		});

		mQuestionTextView = (TextView) findViewById(R.id.question1);
		int mQuestion = mQuestionBank[mCurentIndex].getQuestion();
		mQuestionTextView.setText(mQuestion);

		mNextButton = (Button) findViewById(R.id.buttonNext);
		mNextButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				mCurentIndex = (mCurentIndex + 1) % mQuestionBank.length;
				updateQuestion();
				mIsCheater = false;
			}
		});

		updateQuestion();

		// Get the reference for the buttons
		mTrueButton = (Button) findViewById(R.id.buttonTrue);
		mFalseButton = (Button) findViewById(R.id.buttonFalse);

		mTrueButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				isTrueAnswer(true);

			}
		});

		mFalseButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				isTrueAnswer(false);

			}
		});

		mBackButton = (Button) findViewById(R.id.buttonBack);
		mBackButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if ((mCurentIndex - 1) > -1) {
					mCurentIndex = (mCurentIndex - 1) % mQuestionBank.length;
					updateQuestion();

					mIsCheater = false;
				}
			}
		});

	}
  

}
