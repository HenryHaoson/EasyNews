package cn.henryzhuhao.easynews.business.login;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.henryzhuhao.easynews.MainActivity;
import cn.henryzhuhao.easynews.R;
import cn.henryzhuhao.easynews.app.App;
import cn.henryzhuhao.easynews.app.AppContants;
import cn.henryzhuhao.easynews.business.login.presenter.LoginPresenter;
import cn.henryzhuhao.easynews.business.login.view.LoginView;
import cn.henryzhuhao.mainframe.frame.base.BaseFragment;

/**
 * Created by HenryZhuhao on 2017/6/18.
 */

public class LoginFragment extends BaseFragment implements LoginView{

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    public LoginPresenter presenter;
    public EditText _emailText;
    public EditText _passwordText;
    public Button _loginButton;
    public TextView _signupLink;
    ProgressDialog progressDialog ;


    public static LoginFragment newInstance() {

        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initView() {
        _emailText = (EditText) view.findViewById(R.id.login_email);
        _passwordText = (EditText) view.findViewById(R.id.login_password);
        _loginButton = (Button) view.findViewById(R.id.btn_login);
        _signupLink= (TextView) view.findViewById(R.id.link_signup);
        progressDialog= new ProgressDialog(getContext(),
                R.style.AppTheme_Dark_Dialog);
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);
        showLoadingContentView();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        presenter.login(email,password);
    }

    public void onLoginSuccess() {

    }

    public void onLoginFailed() {
        Toast.makeText(getContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }


    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

//        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            _emailText.setError("enter a valid email address");
//            valid = false;
//        } else {
//            _emailText.setError(null);
//        }

        if (password.isEmpty() || password.length() < 1 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        presenter=new LoginPresenter(this);
    }

    @Override
    public void initListener() {
        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).startfragment(R.id.activity_container,new RegisterFragment().newInstance());
            }
        });

    }

    @Override
    public void showContentView() {

    }

    @Override
    public void hideContentView() {

    }

    @Override
    public void showLoadingContentView() {
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();
    }

    @Override
    public void removeLoadingContentView() {
        progressDialog.dismiss();
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_login;
    }

    @Override
    public void loginSucess(Boolean isSuccess) {
        removeLoadingContentView();
        _loginButton.setEnabled(true);
        if (isSuccess) {
            App.getInstance().setLoginStatus(AppContants.LOGINSTATUS_LOGIN);
            ((MainActivity) getActivity()).show(((MainActivity) getActivity()).getPreFragment());
        }else {
            Toast.makeText(getContext(),"loginFailed",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void loginFailed(String errorMsg) {

    }
}
