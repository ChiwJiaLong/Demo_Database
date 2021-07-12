package sg.edu.rp.c346.id20031634.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    EditText etTask,etDate;
    Button btn,btn2;
    TextView tv;
    ListView lv;
    ArrayList<Task> al;
    ArrayAdapter<String> aa;
    boolean asc = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn=findViewById(R.id.buttonInsert);
        btn2=findViewById(R.id.btnGetTask);
        tv=findViewById(R.id.tvResult);
        lv=findViewById(R.id.lv);
        etTask=findViewById(R.id.editTextTask);
        etDate=findViewById(R.id.editTextDate);

        DBHelper db2 = new DBHelper(MainActivity.this);
        al = db2.getTasks(asc);
        db2.close();
        aa= new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                db.insertTask(etTask.getText().toString(),etDate.getText().toString());
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                ArrayList<String> tasks = db.getTaskContent();
                String text = "";
                for(int i =0; i< tasks.size(); i++){
                    text += i + ". " + tasks.get(i) + "\n";
                }
                tv.setText(text);

                DBHelper db2 =new DBHelper(MainActivity.this);
                al = db2.getTasks(asc);
                db2.close();
                asc = !asc;
                db2.close();
                aa= new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, al);
                lv.setAdapter(aa);

                al.clear();
                al.addAll(db.getTasks(asc));
                aa.notifyDataSetChanged();


            }
        });



    }
}