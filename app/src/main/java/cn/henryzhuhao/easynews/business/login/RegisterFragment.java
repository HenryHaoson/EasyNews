package cn.henryzhuhao.easynews.business.login;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cn.henryzhuhao.easynews.MainActivity;
import cn.henryzhuhao.easynews.R;
import cn.henryzhuhao.easynews.app.App;
import cn.henryzhuhao.easynews.app.AppContants;
import cn.henryzhuhao.easynews.business.login.presenter.RegisterPresenter;
import cn.henryzhuhao.easynews.business.login.view.RegisterView;
import cn.henryzhuhao.easynews.entity.RegisterBean;
import cn.henryzhuhao.mainframe.frame.base.BaseFragment;

/**
 * Created by HenryZhuhao on 2017/6/22.
 */

public class RegisterFragment extends BaseFragment implements RegisterView {
    public RegisterPresenter presenter;
    public EditText _emailText;
    public EditText _passwordText;
    public EditText _passwordTextConfirm;
    public Button _registerButton;
    ProgressDialog progressDialog ;
    public static RegisterFragment newInstance() {
        
        Bundle args = new Bundle();
        
        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void initView() {
        progressDialog=new ProgressDialog(getContext());
        _emailText= (EditText) view.findViewById(R.id.register_email);
        _passwordText= (EditText) view.findViewById(R.id.register_password);
        _passwordTextConfirm= (EditText) view.findViewById(R.id.register_password_confirm);
        _registerButton= (Button) view.findViewById(R.id.btn_register);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        presenter=new RegisterPresenter(this);
    }

    @Override
    public void initListener() {
        _registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }
    public void register() {

        if (!validate()) {
            return;
        }

        _registerButton.setEnabled(false);
        showLoadingContentView();
        RegisterBean bean=new RegisterBean();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        bean.setUsername(email);
        bean.setPassword(password);
        bean.setBirthday("1996-1-1");
        bean.setImageUrl("111");
        bean.setIntroduction("stub");
        bean.setNickname("nick");
        bean.setSex(1);
        Log.e("tag","registering");
        presenter.register(bean);
        Log.e("tag","registering");
    }

    public boolean validate() {
        boolean valid = true;
        String password = _passwordText.getText().toString();
        String passwordConfirm=_passwordTextConfirm.getText().toString();


        if (password.isEmpty() || password.length() < 1 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } if(!password.equals(passwordConfirm)) {
            _passwordTextConfirm.setError("Two passwords do not match");
            valid = false;
        }else {
            _passwordTextConfirm.setError(null);
            _passwordText.setError(null);
        }

        return valid;
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
        progressDialog.setMessage("Registering...");
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
        return R.layout.fragment_register;
    }

    @Override
    public void registerSuccess(Boolean isSuccess) {
        Log.e("tag","register"+isSuccess.toString());
        removeLoadingContentView();
        _registerButton.setEnabled(true);
        if(isSuccess){
            App.getInstance().setLoginStatus(AppContants.LOGINSTATUS_LOGIN);
            ((MainActivity) getActivity()).show(((MainActivity) getActivity()).getPreFragment());}
    }
    @Override
    public void registerFailed(String errorMsg) {
        Log.e("register",errorMsg);
    }
}
