package io.github.pengrad.uw_arcamera;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import eu.livotov.zxscan.ScannerView;

public class MainActivity extends AppCompatActivity implements ScannerView.ScannerViewEventListener, View.OnClickListener {

    private ScannerView mScanner;
    private View mButtonScan;
    private View mViewLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScanner = (ScannerView) findViewById(R.id.scanner);
        mScanner.setScannerViewEventListener(this);

        mButtonScan = findViewById(R.id.buttonScan);
        mButtonScan.setOnClickListener(this);
        mViewLogo = findViewById(R.id.imageLogo);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mScanner.setVisibility(View.GONE);
        mViewLogo.setVisibility(View.VISIBLE);
        mButtonScan.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScanner.stopScanner();
    }

    @Override
    public void onClick(View v) {
        mViewLogo.setVisibility(View.GONE);
        mButtonScan.setVisibility(View.GONE);
        mScanner.setVisibility(View.VISIBLE);
        mScanner.startScanner();
    }

    @Override
    public void onScannerReady() {
        Log.d("+++", "onScannerReady");
    }

    @Override
    public void onScannerFailure(int i) {
        Log.d("+++", "onScannerFailure " + i);
    }

    @Override
    public boolean onCodeScanned(String s) {
        mScanner.stopScanner();
        mButtonScan.setVisibility(View.VISIBLE);
        Toast.makeText(this, "Code: " + s, Toast.LENGTH_LONG).show();
        return true;
    }
}
