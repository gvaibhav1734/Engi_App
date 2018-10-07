package com.example.lenovo.engineer;

import android.content.Context;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

/**
 * Singleton class to implement Google SignIn.
 */
public class GoogleSignInHelper {
    private static GoogleSignInHelper instance;
    private GoogleSignInClient googleSignInClient;
    private GoogleSignInAccount googleSignInAccount;

    private GoogleSignInHelper(Context context, GoogleSignInOptions googleSignInOptions) {
        setup(context,googleSignInOptions);
    }

    private void setup(Context context, GoogleSignInOptions googleSignInOptions) {
        if (this.googleSignInAccount == null) {
            this.googleSignInAccount = GoogleSignIn.getLastSignedInAccount(context);
        }
        if(this.googleSignInClient == null){
            this.googleSignInClient = GoogleSignIn.getClient(context,googleSignInOptions);
        }
    }
    public static synchronized GoogleSignInHelper getInstance(Context context,
                                                              GoogleSignInOptions googleSignInOptions) {
        // If Instance is null then initialize new Instance
        if (instance == null) {
            instance = new GoogleSignInHelper(context, googleSignInOptions);
        } else if(instance.googleSignInClient==null){
            instance.setup(context,googleSignInOptions);
        } else if(instance.googleSignInAccount==null){
            instance.setup(context,null);
        }
        // Return mInstance
        return instance;
    }

    public synchronized GoogleSignInClient getClient() {
        return this.googleSignInClient;
    }

    public synchronized GoogleSignInAccount getAccount() {
        return this.googleSignInAccount;
    }
}
