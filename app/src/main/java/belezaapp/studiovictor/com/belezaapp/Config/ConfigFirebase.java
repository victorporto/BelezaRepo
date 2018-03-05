package belezaapp.studiovictor.com.belezaapp.Config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by mathe on 09/02/2018.
 */

public final class ConfigFirebase {

    private static DatabaseReference referenciaFirebaseTeste = FirebaseDatabase.getInstance().getReference().child("Teste");
    private static FirebaseAuth authFirebase = FirebaseAuth.getInstance();


    public static DatabaseReference getRefFirebaseTeste() {
        return referenciaFirebaseTeste;
    }

    /*
    public static DatabaseReference getRefFirebaseTesteSaloes() {
        referenciaFirebase = FirebaseDatabase.getInstance().getReference().child("Teste");
        return referenciaFirebase;
    }
    */

    public static FirebaseAuth getAuthFirebase() {
        return  authFirebase;
    }


}