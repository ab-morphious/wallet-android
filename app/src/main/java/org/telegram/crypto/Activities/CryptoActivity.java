package org.telegram.crypto.Activities;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import org.telegram.crypto.Fragments.CryptoFragment;
import org.telegram.messenger.R;

public class CryptoActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //top level container
        LinearLayout container = new LinearLayout(CryptoActivity.this);

        //host layout for CryptoFragment
        LinearLayout fragContainer = new LinearLayout(this);
        fragContainer.setOrientation(LinearLayout.HORIZONTAL);
        fragContainer.setId(R.id.fragment_container);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, CryptoFragment.newInstance());
        fragmentTransaction.commit();

        //add fragment to host layout
        container.addView(fragContainer);
        setContentView(container);
    }
}
