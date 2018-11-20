package ar.edu.utn.frsf.isi.dam.hibridas;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class GeoServicioInterface implements OnSuccessListener<Location> {

    private FusedLocationProviderClient mFusedLocationClient;
    Context contexto;
    String latLng;

    public GeoServicioInterface(Context c1){
        contexto = c1;
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(contexto);
        if (ActivityCompat.checkSelfPermission(contexto, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(contexto, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return ;
        }else{
            calcularUltimaUbicacion();
        }
    }

    private void calcularUltimaUbicacion(){
        if (ActivityCompat.checkSelfPermission(contexto, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(contexto, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
            mFusedLocationClient.getLastLocation().addOnSuccessListener(this);
        }
    }

    @JavascriptInterface
    public String getUbicacion(){
        return latLng;
    }

    @JavascriptInterface
    public void mostrarMsgAndroid(String texto){
        Toast.makeText(contexto,texto,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess(Location location) {
        Log.d("LOCATION"," "+location);
        if (location != null) {
            latLng = location.getLatitude() + ";" + location.getLongitude();
        } else {
            if (latLng == null || latLng.length() == 0) latLng = "N/D";
        }
    }
}
