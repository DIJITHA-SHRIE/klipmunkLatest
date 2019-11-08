package com.telcco.klipmunk;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import static android.util.Patterns.EMAIL_ADDRESS;

public class ForgotPassword extends AppCompatActivity {
    @BindView(R.id.ed_forgotpassword)
    EditText ed_forgotpassword;

    @BindView(R.id.bt_submit)
    Button bt_submit;

    @BindView(R.id.bt_cancel)
    Button bt_cancel;
    private ForgotPasswordViewModel forgotPassViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);

        bt_submit.setOnClickListener(v -> {
            String useremail = ed_forgotpassword.getText().toString().trim();

            if (validateForgotPassword(useremail)) {
                //doLogin
                doForgotPassword(useremail);

            }

        });

        forgotPassViewModel = ViewModelProviders.of(this).get(ForgotPasswordViewModel.class);

        forgotPassViewModel.getForgotPassword().observe(this, forgotModel -> {
            if (forgotModel != null) {
                Toast.makeText(this, forgotModel.getMsg(), Toast.LENGTH_SHORT).show();
                finish();
            }

        });

        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(ForgotPassword.this,LogIn.class);
                startActivity(a);
            }
        });






    }

    private boolean validateForgotPassword(String useremail) {
        if (useremail.equals("")) {
            Toast.makeText(ForgotPassword.this, "Enter the registered email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!EMAIL_ADDRESS.matcher(useremail).matches()) {
            Toast.makeText(ForgotPassword.this, "Enter the valid email", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    private void doForgotPassword(final String useremail) {

        forgotPassViewModel.setForgotPassword(useremail);

    }
}
