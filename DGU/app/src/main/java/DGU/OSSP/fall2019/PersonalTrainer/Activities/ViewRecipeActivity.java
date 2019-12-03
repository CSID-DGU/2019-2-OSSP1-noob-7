package DGU.OSSP.fall2019.PersonalTrainer.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import DGU.OSSP.fall2019.DGU.R;

public class ViewRecipeActivity extends AppCompatActivity {

    WebView browser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);

        browser = findViewById(R.id.webView);
        browser.setWebViewClient(new WebViewClient());
        browser.loadUrl(getIntent().getExtras().getString("recipeURL"));
    }
}
