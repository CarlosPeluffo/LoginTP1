package com.peluffo.logintp1.ui.login;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.peluffo.logintp1.model.Usuario;
import com.peluffo.logintp1.request.ApiClient;
import com.peluffo.logintp1.ui.registro.Registro;

public class LoginViewModel extends AndroidViewModel {
    private MutableLiveData<Integer> mensajeM;
    private Context context;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<Integer> getMensajeM() {
        if(mensajeM == null){
            mensajeM = new MutableLiveData<>();
        }
        return mensajeM;
    }

    public void iniciarSesion(String mail, String password){
        Usuario user = ApiClient.login(context,mail, password);
        if(user != null){
            Intent intent = new Intent(context, Registro.class);
            /*Bundle bundle = new Bundle();
            bundle.putSerializable("usuario", user);
            intent.putExtra("usuario", bundle);*/
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }else{
            mensajeM.setValue(0);
        }
    }
}
