package br.com.vetfind.vet_find_app;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import br.com.vetfind.vet_find_app.DAO.AnimalDAO;
import br.com.vetfind.vet_find_app.DAO.ClienteDAO;
import br.com.vetfind.vet_find_app.DAO.VeterinarioDAO;
import br.com.vetfind.vet_find_app.modelo.Animal;
import br.com.vetfind.vet_find_app.modelo.Usuario;
import br.com.vetfind.vet_find_app.modelo.Veterinario;

public class MapaFragment extends SupportMapFragment implements OnMapReadyCallback {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng myPosition = getAdressCoordenates("Rua joanópolis 727, Cidade Jardim, Campinas");
        if (myPosition != null) {
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(myPosition, 17);
            googleMap.moveCamera(update);
        }

        VeterinarioDAO veterinarioDAO= new VeterinarioDAO(getContext());
        for (Veterinario veterinario : veterinarioDAO.getVeterinarios()) {
            LatLng coordenada = getAdressCoordenates(veterinario.getEndereco());
            if (coordenada != null) {

                BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.ic_doctor);

                MarkerOptions marker = new MarkerOptions();
                marker.position(coordenada);
                marker.title(veterinario.getNome());
                marker.snippet(veterinario.getEmail());
                marker.icon(icon);
                googleMap.addMarker(marker);
            }
        }
        veterinarioDAO.close();

        new Localizador(getContext(), googleMap);
    }

    private LatLng getAdressCoordenates(String adress) {
        try {
            Geocoder geocoder = new Geocoder(getContext());
            List<Address> results = geocoder.getFromLocationName(adress, 1);
            if (!results.isEmpty()) {
                LatLng posicao = new LatLng(results.get(0).getLatitude(), results.get(0).getLongitude());
                return posicao;
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
