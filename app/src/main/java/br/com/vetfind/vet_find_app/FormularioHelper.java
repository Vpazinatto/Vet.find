package br.com.vetfind.vet_find_app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;

import br.com.vetfind.vet_find_app.modelo.Animal;

public class FormularioHelper {

    private final EditText campoNome;
    private final EditText campoIdade;
    private final EditText campoSexo;
    private final EditText campoEspecie;
    private final EditText campoRaca;
    private final ImageView campoFoto;

    private Animal animal;

    public FormularioHelper(FormularioActivity activity) {
        campoNome = (EditText) activity.findViewById(R.id.formulario_nome);
        campoIdade = (EditText) activity.findViewById(R.id.formulario_idade);
        campoSexo = (EditText) activity.findViewById(R.id.formulario_sexo);
        campoEspecie = (EditText) activity.findViewById(R.id.formulario_especie);
        campoRaca = (EditText) activity.findViewById(R.id.formulario_raca);
        campoFoto = (ImageView) activity.findViewById(R.id.formulario_foto);
        animal = new Animal();
    }

    public Animal getAnimal() {
        animal.setNome(campoNome.getText().toString());
        animal.setIdade(campoIdade.getText().toString());
        animal.setSexo(campoSexo.getText().toString());
        animal.setEspecie(campoEspecie.getText().toString());
        animal.setRaca(campoRaca.getText().toString());
        animal.setCaminhoFoto((String) campoFoto.getTag());
        return animal;
    }

    public void preencheFormulario(Animal animal) {
        campoNome.setText(animal.getNome());
        campoIdade.setText(animal.getIdade());
        campoSexo.setText(animal.getSexo());
        campoEspecie.setText(animal.getEspecie());
        campoRaca.setText(animal.getRaca());
        carregaFoto(animal.getCaminhoFoto());
        this.animal = animal;
    }

    public void carregaFoto(String caminhoFoto) {
        if (caminhoFoto != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
            campoFoto.setImageBitmap(bitmapReduzido);
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
            campoFoto.setTag(caminhoFoto);
        }
    }
}
