package ar.edu.utn.frsf.isi.dam.hibridas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class HTMLMaterial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_htmlmaterial);
        WebView myWebView = (WebView) findViewById(R.id.webview2);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.loadUrl("file:///android_asset/pmmat/pm.html");
        myWebView.addJavascriptInterface(new GeoServicioInterface(this), "AndroidGeo");

    }
}
