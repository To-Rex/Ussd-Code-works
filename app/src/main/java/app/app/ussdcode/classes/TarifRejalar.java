package app.app.ussdcode.classes;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

import app.app.ussdcode.R;
import app.app.ussdcode.SqlData;
import app.app.ussdcode.adapters.MobiuzTarifAdapter;
import app.app.ussdcode.models.ModelTarifRejalar;

public class TarifRejalar extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private SqlData sqlData;
    MobiuzTarifAdapter adapter;
    ArrayList<ModelTarifRejalar> dataModalArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tarif_rejalar);

        sqlData = new SqlData(this);
        ListView tarif_list = findViewById(R.id.tarif_list);
        dataModalArrayList = new ArrayList<>();
        tarif_list.setDivider(null);
        tarif_list.setDividerHeight(1);

        firebaseDatabase = FirebaseDatabase.getInstance();

        String company = getIntent().getExtras().getString("tarif").trim();
        databaseReference = firebaseDatabase.getReference(company);
        adapter = new MobiuzTarifAdapter(dataModalArrayList, this);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataModalArrayList.clear();
                tarif_list.setAdapter(null);
                for (DataSnapshot dataSnapshot : snapshot.child("Tarif Rejalar").getChildren()) {
                    String tarif_name = dataSnapshot.getKey();
                    String tarif_narxi = Objects.requireNonNull(dataSnapshot.child("Narxi").getValue()).toString();
                    String tarif_ulanish = Objects.requireNonNull(dataSnapshot.child("Ulanish").getValue()).toString();
                    String tarif_havolasi = Objects.requireNonNull(dataSnapshot.child("Link").getValue()).toString();
                    String tarif_mal = Objects.requireNonNull(dataSnapshot.child("Mal").getValue()).toString();
                    dataModalArrayList.add(new ModelTarifRejalar(tarif_name,
                            tarif_narxi,tarif_ulanish, company,tarif_havolasi,tarif_mal));
                }
                tarif_list.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}