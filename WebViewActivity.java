//this code is to open a certain URL
//at nowhere but inside the app
//the code snipplet in charity's donate method still do exist 
//but it opens the default browser of the device


public class WebViewActivity extends Activity {
 private WebView webView; 

 @Override
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_webview);
  
  webView = (WebView) findViewById(R.id.webView);
  webView.setWebViewClient(new MyWebViewClient());
  
  //need to find a way to reach the donationPageLink property of charity objects
  String url = "www.losev.org.tr/bagis/Bagis.html";
  webView.getSettings().setJavaScriptEnabled(true);
  webView.loadUrl(url);  
 }

 private class MyWebViewClient extends WebViewClient {
     
   //need to override not to let default browser take over
   @Override
     public boolean shouldOverrideUrlLoading(WebView view, String url) {
         view.loadUrl(url);
         return true;
     }
 } 
}