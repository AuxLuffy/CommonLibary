package com.lenovo.service.basicpubliclibrary.validation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.service.basicpubliclibrary.R;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Checked;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Pattern;

import java.util.List;

/**
 * created at 2017-09-08 by zhaotong
 * 一款强大的简单易用的格式验证框架，采用注解的形式对于Edittext等控件实现输入内容的合法性检验
 */
public class ValidateActivity extends AppCompatActivity implements Validator.ValidationListener {
    //第一步，对于要校验的框架作注解
    @NotEmpty(trim = true, message = "Please input your id")
    @Order(value = 1)
    private EditText mEditUserid;
    @Order(value = 2)
    @NotEmpty(trim = true)
    @Pattern(regex = "^((\\d{3,4}-)*\\d{7,8}(-\\d)?)|((\\+\\d{2,3})?\\s*1(3[0-9]|4[57]|5[0-35-9]|7[0135678]|8[0-9])\\d{8})*$", message = "Your phone is illegal!")
    private EditText mEditPhone;
    @Order(value = 3)
    @NotEmpty(trim = true)
    @Email(message = "Your email is incorrect!")
    private EditText mEditEmail;
    @Order(value = 4)
    @NotEmpty(trim = true, message = "password is null")
    @Password()
    private EditText mEditPassword;
    @Order(value = 5)
    @NotEmpty(trim = true, message = "password is null")
    @ConfirmPassword()
    private EditText mEditConfirmPw;
    @Order(value = 6)
    @Checked(message = "请务必同意该条款")
//    private SwitchButton mBtn;
    private CheckBox checkBox;
    private TextView mTxtLogin;
    private Validator validator = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate);
        initView();
        //第二部，实例化一个Validator对象
        validator = new Validator(this);
        //设置校验按顺序进行
        validator.setValidationListener(this);
    }

    private void initView() {
        mEditUserid = (EditText) findViewById(R.id.editUserId);
        mEditPhone = (EditText) findViewById(R.id.editPhone);
        mEditEmail = (EditText) findViewById(R.id.editEmail);
        mEditPassword = (EditText) findViewById(R.id.editPassword);
        mEditConfirmPw = (EditText) findViewById(R.id.editRepeatPw);
//        mBtn = (SwitchButton) findViewById(R.id.switchBtn);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        mTxtLogin = (TextView) findViewById(R.id.txtLogin);
        mTxtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //使用validate(true)将会在后台使用AsycTask执行校验
//                validator.validate(true);
                validator.validate();
            }
        });
    }

    @Override
    public void onValidationSucceeded() {
        //如果全部通过校验，提示登录成功
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
//如果有未通过校验的选项，则会列出所有未通过校验的错误项
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // 将错误信息放入对应的位置
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
