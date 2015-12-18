package matigian.pruebajason2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String resultado;
    private RecyclerView.LayoutManager lm;
    private RecyclerView rv;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = (RecyclerView)findViewById(R.id.recycler1);

        rv.setHasFixedSize(true);

        lm = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(lm);



        AsyncTask<Void,Void,String> task = new AsyncTask<Void, Void, String>() {
                        @Override
            protected String doInBackground(Void... params) {
                String resultado = new HttpConnection().connectToServer("http://www.mocky.io/v2/567378862500007e50995c58",15000);
                return resultado;
            }

            @Override
            protected void onPostExecute(String resultado) {
                super.onPostExecute(resultado);
                if(resultado!=null){
                    System.out.println(resultado);
                    //Adaptador que reciba la lista de deportes y las muestre
                    Adaptador adapter = new Adaptador(parsearLista(resultado));
                    rv.setAdapter(adapter);
                }
            }
        };
            task.execute();
    }

    private List<Deporte> parsearLista(String resultado){
        List<Deporte> listaDeportes = new ArrayList<Deporte>();

        try {
            JSONArray lista = new JSONArray(resultado);

            int size = lista.length();
            for(int i=0;i<size;i++){

                JSONObject objeto = lista.getJSONObject(i);

                Deporte deporte = new Deporte();

                deporte.setDeporte(objeto.getString("deporte"));
                deporte.setJugadores(objeto.getInt("jugadores"));

                listaDeportes.add(deporte);

            }
            return listaDeportes;

        } catch (JSONException e) {
            e.printStackTrace();
            return listaDeportes;
        }


    }
}
