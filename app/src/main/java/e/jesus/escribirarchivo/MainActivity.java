package e.jesus.escribirarchivo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;

public class MainActivity extends AppCompatActivity {

    private EditText editor;
    private Button cerrar;
    private final static String ARCHIVO = "archivo.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editor = findViewById(R.id.editor);
        cerrar = findViewById(R.id.close);

        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();

        try {

            InputStream in = openFileInput(ARCHIVO);
            InputStreamReader tmp = new InputStreamReader(in);
            BufferedReader reader = new BufferedReader(tmp);
            String str;
            StringBuffer buf = new StringBuffer(); //inmutable

            while ((str = reader.readLine())!= null){
                buf.append(str + "\n");
            }

            in.close();
            editor.setText(buf.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

        try {
            OutputStreamWriter out = new OutputStreamWriter(openFileOutput(ARCHIVO, 0));
            out.write(editor.getText().toString());
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
