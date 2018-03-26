package com.example.android.boilerplate;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.subscribers.DisposableSubscriber;

import static android.text.TextUtils.isEmpty;
import static android.util.Patterns.EMAIL_ADDRESS;

public class Main2Activity extends AppCompatActivity {

   // private ActivityMain2Binding binding;
    private DisposableSubscriber<Boolean> disposableObserver = null;
    private Button buttonValid;
    private Flowable<CharSequence> _emailChangeObservable;
    private Flowable<CharSequence> _numberChangeObservable;
    private Flowable<CharSequence> _passwordChangeObservable;
    private TextView _btnValidIndicator;
    private TextInputEditText _email;
    private TextInputLayout _email_layout;
    private TextInputLayout _number_layout;
    private TextInputLayout _password_layout;
    private EditText _password;
    private EditText _number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
  //      setSupportActionBar(toolbar);

        _email= findViewById(R.id.demo_combl_email);
        _btnValidIndicator= findViewById(R.id.btn_demo_form_valid);
        _password= findViewById(R.id.demo_combl_password);
        _number= findViewById(R.id.demo_combl_num);
        _email_layout = findViewById(R.id.text_input_layout);
        _password_layout = findViewById(R.id.text_input_layout2);
       _number_layout  = findViewById(R.id.text_input_layout3);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        _emailChangeObservable =
                RxTextView.textChanges(_email).skip(1).toFlowable(BackpressureStrategy.LATEST);
        _passwordChangeObservable =
                RxTextView.textChanges(_password).skip(1).toFlowable(BackpressureStrategy.LATEST);
        _numberChangeObservable =
                RxTextView.textChanges(_number).skip(1).toFlowable(BackpressureStrategy.LATEST);

        _combineLatestEvents();

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        disposableObserver.dispose();

    }

    private void clearForm(){
        _email_layout.setError(null);
        _number_layout.setError(null);
        _password_layout.setError(null);
    }

    private void _combineLatestEvents() {

        disposableObserver =
                new DisposableSubscriber<Boolean>() {
                    @Override
                    public void onNext(Boolean formValid) {
                        if (formValid) {
                            clearForm();

                            _btnValidIndicator.setBackgroundColor(
                                    ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
                        } else {
                            _btnValidIndicator.setBackgroundColor(
                                    ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("error", "there was an error");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("completed","completed");
                    }
                };
        Flowable.combineLatest(
                _emailChangeObservable,
                _passwordChangeObservable,
                _numberChangeObservable,
                (newEmail, newPassword, newNumber) -> {
                    boolean emailValid = !isEmpty(newEmail) && EMAIL_ADDRESS.matcher(newEmail).matches();
                    if (!emailValid) {
                        _email_layout.setError("Invalid Email!");
                    }

                    boolean passValid = !isEmpty(newPassword) && newPassword.length() > 8;
                    if (!passValid) {
                        _password_layout.setError("Invalid Password!");
                    }

                    boolean numValid = !isEmpty(newNumber);
                    if (numValid) {
                        int num = Integer.parseInt(newNumber.toString());
                        numValid = num > 0 && num <= 100;
                    }
                    if (!numValid) {
                        _number_layout.setError("Invalid Number!");
                    }

                    return emailValid && passValid && numValid;
                })
                .subscribe(disposableObserver);



    }
}


