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

    private GoogleSignInHelper(Context context, GoogleSignInOptions googleSignInOptions) {
        // Specify the application context
        this.googleSignInClient = GoogleSignIn.getClient(context, googleSignInOptions);
    }

    public static synchronized GoogleSignInHelper getInstance(Context context,
                                                              GoogleSignInOptions googleSignInOptions) {
        // If Instance is null then initialize new Instance
        if (instance == null) {
            instance = new GoogleSignInHelper(context, googleSignInOptions);
        }
        // Return mInstance
        return instance;
    }

    /**
     * For situations where you only want the Client and DO NOT want to create another one.
     */
    public static synchronized GoogleSignInHelper getInstance() {
        return instance;
    }

    public synchronized GoogleSignInClient getClient() {
        return this.googleSignInClient;
    }
}
