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
import app.app.ussdcode.adapters.UssdCodeAdapter;
import app.app.ussdcode.models.ModelUssdCode;

public class UssdCodlar extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private SqlData sqlData;
    UssdCodeAdapter adapter;
    ArrayList<ModelUssdCode> dataModalArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ussdcodlar);

        ListView ussdlist = findViewById(R.id.ussdlist);

        sqlData = new SqlData(this);
        dataModalArrayList = new ArrayList<>();
        ussdlist.setDivider(null);
        ussdlist.setDividerHeight(1);
        firebaseDatabase = FirebaseDatabase.getInstance();

        String company = getIntent().getExtras().getString("tarif").trim();
        databaseReference = firebaseDatabase.getReference(company);
        adapter = new UssdCodeAdapter(dataModalArrayList, this);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataModalArrayList.clear();
                ussdlist.setAdapter(null);
                for (DataSnapshot dataSnapshot : snapshot.child("Ussd Codlar").getChildren()) {
                    String daq_name = dataSnapshot.getKey();
                   String Codec = Objects.requireNonNull(dataSnapshot.child("Code").getValue()).toString();
                    dataModalArrayList.add(new ModelUssdCode(Codec,daq_name,company));
                }
                ussdlist.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
