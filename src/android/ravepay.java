package cordova.plugin.ravepay;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;  
import android.widget.Toast;
import android.util.Log;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import android.content.Context;
import android.content.Intent;

import org.parceler.Parcels;
import org.parceler.Parcel;
import android.content.Intent;
import android.net.Uri;

import org.apache.cordova.PluginResult;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.flutterwave.raveandroid.Meta;
import com.flutterwave.raveandroid.RaveConstants;
import com.flutterwave.raveandroid.RavePayActivity;
import com.flutterwave.raveandroid.RavePayManager;
import com.flutterwave.raveandroid.Utils;
import com.flutterwave.raveandroid.responses.SubAccount;

import com.flutterwave.raveandroid.account.AccountFragment;
import com.flutterwave.raveandroid.card.CardFragment;
import com.flutterwave.raveandroid.ghmobilemoney.GhMobileMoneyFragment;
import com.flutterwave.raveandroid.mpesa.MpesaFragment;

import static com.flutterwave.raveandroid.RaveConstants.PERMISSIONS_REQUEST_READ_PHONE_STATE;
import static com.flutterwave.raveandroid.RaveConstants.RAVEPAY;
import static com.flutterwave.raveandroid.RaveConstants.RAVE_PARAMS;
import static com.flutterwave.raveandroid.RaveConstants.RAVE_REQUEST_CODE;

/**
 * This class echoes a string called from JavaScript.
 */
public class ravepay extends CordovaPlugin {
    
    private CallbackContext callbackContext;
    private Activity activity = null;
    private JSONObject configurations;
    
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext; 
        JSONObject configurations = args.getJSONObject(0);
        if (action.equals("pay")) {
                new RavePayManager(this.cordova.getActivity())
                                        .setAmount(BigDecimal.valueOf(configurations.getDouble("setAmount")).floatValue())
                                        .setCountry(configurations.optString("setCountry"))
                                        .setCurrency(configurations.optString("setCurrency"))
                                        .setEmail(configurations.optString("setEmail"))
                                        .setfName(configurations.optString("setfName"))
                                        .setlName(configurations.optString("setlName"))
                                        .setNarration(configurations.optString("setNarration"))
                                        .setPublicKey(configurations.optString("setPublicKey"))
                                        .setSecretKey(configurations.optString("setSecretKey"))
                                        .setTxRef(configurations.optString("setTxRef"))
                                        .acceptAccountPayments(configurations.optBoolean("acceptAccountPayments"))
                                        .acceptCardPayments(configurations.optBoolean("acceptCardPayments"))
                                        .acceptMpesaPayments(configurations.optBoolean("acceptMpesaPayments"))
                                        .acceptGHMobileMoneyPayments(configurations.optBoolean("acceptGHMobileMoneyPayments"))
                                        .onStagingEnv(configurations.optBoolean("onStagingEnv"))
                                        .allowSaveCardFeature(configurations.optBoolean("allowSaveCardFeature"))
                                        .initialize();
            this.cordova.setActivityResultCallback(this);
            return true;
        }
        return false;
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RaveConstants.RAVE_REQUEST_CODE && data != null) {
            String message = data.getStringExtra("response");
            if (resultCode == RavePayActivity.RESULT_SUCCESS) {
                this.callbackContext.success(message);
            }
            else if (resultCode == RavePayActivity.RESULT_ERROR) {
                this.callbackContext.success(message);
            }
            else if (resultCode == RavePayActivity.RESULT_CANCELLED) {
                this.callbackContext.success(message);
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private <T> T getConfiguration(JSONObject configurations, String name, T defaultValue) {
        if (configurations.has(name)) {
            try {
                return (T)configurations.get(name);
            } catch (JSONException ex) {
                return defaultValue;
            }
        } else {
            return defaultValue;
        }
    }
}