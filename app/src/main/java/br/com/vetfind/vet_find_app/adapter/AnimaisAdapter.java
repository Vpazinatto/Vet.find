package br.com.vetfind.vet_find_app.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import br.com.vetfind.vet_find_app.ListaActivity;
import br.com.vetfind.vet_find_app.R;
import br.com.vetfind.vet_find_app.modelo.Animal;

public class AnimaisAdapter extends BaseAdapter{

    private final List<Animal> animais;
    private final Context context;

    public AnimaisAdapter(Context listaActivity ,List<Animal> animais)
    {
        this.animais = animais;
        this.context = listaActivity;
    }

    @Override
    public int getCount() {
        return animais.size();
    }

    @Override
    public Object getItem(int position) {
        return animais.get(position);
    }

    @Override
    public long getItemId(int position) {
        return animais.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Animal animal = animais.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.list_item, parent, false);
        }

        TextView campoNome = (TextView) view.findViewById(R.id.item_nome);
        campoNome.setText(animal.getNome());

        TextView campoRaca = (TextView) view.findViewById(R.id.item_raca);
        campoRaca.setText(animal.getRaca());

        TextView campoEspecie = (TextView) view.findViewById(R.id.item_especie);
        campoEspecie.setText(animal.getEspecie());

        TextView campoIdade = (TextView) view.findViewById(R.id.item_idade);

        if (campoIdade != null)
            campoIdade.setText(animal.getIdade());

        TextView campoSexo = (TextView) view.findViewById(R.id.item_sexo);

        if (campoSexo != null)
            campoSexo.setText(animal.getSexo());

        ImageView campoFoto = (ImageView) view.findViewById(R.id.item_foto);
        String caminhoFoto = animal.getCaminhoFoto();

        if (caminhoFoto != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
            campoFoto.setImageBitmap(bitmapReduzido);
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
        }

        return view;
    }
}
