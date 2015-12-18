package matigian.pruebajason2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by italiano Leo on 18-12-2015.
 */
public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {

    private List<Deporte> listaDeporte;

    public Adaptador(List<Deporte> mlistaDeporte) {
        listaDeporte = mlistaDeporte;
    }

    @Override
    public Adaptador.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerlayout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        
        Deporte deporte = listaDeporte.get(position);

        holder.nombreDeporte.setText(deporte.getDeporte());
        holder.numeroJugadores.setText(deporte.getJugadores());


    }

    @Override
    public int getItemCount() {
        return listaDeporte.size();
    }

    public static class ViewHolder  extends RecyclerView.ViewHolder {

         TextView nombreDeporte;
        TextView numeroJugadores;

        public ViewHolder(View itemView) {
            super(itemView);

            nombreDeporte = (TextView)itemView.findViewById(R.id.NombreDeporte);
            numeroJugadores = (TextView)itemView.findViewById(R.id.NumeroJugadores);

        }
    }
}
