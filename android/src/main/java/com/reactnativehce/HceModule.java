package com.reactnativehce;
import java.util.*;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.reactnativehce.services.CardService;
import com.reactnativehce.apps.NFCFeature;

public class HceModule extends ReactContextBaseJavaModule {
  private static ReactApplicationContext reactContext;
  private  NFCFeature nfcFeature;

  private static final String DURATION_SHORT_KEY = "SHORT";
  private static final String DURATION_LONG_KEY = "LONG";

  HceModule(ReactApplicationContext context) {
    super(context);
    reactContext = context;
    nfcFeature = new NFCFeature(context);
  }

  @Override
  public String getName() {
    return "Hce";
  }

  @ReactMethod
  public void setContent(String type, String content, Promise promise) {
    SharedPreferences.Editor editor = reactContext.getApplicationContext().getSharedPreferences("hce", Context.MODE_PRIVATE)
      .edit();

    editor.putString("type", type);
    editor.putString("content", content);

    editor.apply();

    promise.resolve(null);
  }

  @ReactMethod
  public void setEnabled(Boolean enabled, Promise promise) {
    reactContext.getApplicationContext().getPackageManager()
      .setComponentEnabledSetting(
        new ComponentName(reactContext.getApplicationContext(), CardService.class),
        enabled ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED : PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
        PackageManager.DONT_KILL_APP
      );

    promise.resolve(enabled);
  }

  @ReactMethod
    public void checkSupport(Promise promise) {
    nfcFeature.getInfo();
    WritableMap map = new WritableNativeMap();
    map.putBoolean("FEATURE_NFC", nfcFeature.getFEATURE_NFC());
    map.putBoolean("FEATURE_NFC_HOST_CARD_EMULATION", nfcFeature.getFEATURE_NFC_HOST_CARD_EMULATION_NFCF());
    map.putBoolean("FEATURE_NFC_BEAM", nfcFeature.getFEATURE_NFC_BEAM());
    map.putBoolean("FEATURE_NFC_HOST_CARD_EMULATION_NFCF", nfcFeature.getFEATURE_NFC_HOST_CARD_EMULATION_NFCF());
    map.putBoolean("FEATURE_NFC_OFF_HOST_CARD_EMULATION_ESE", nfcFeature.getFEATURE_NFC_OFF_HOST_CARD_EMULATION_ESE());
    map.putBoolean("FEATURE_NFC_OFF_HOST_CARD_EMULATION_UICC", nfcFeature.getFEATURE_NFC_OFF_HOST_CARD_EMULATION_UICC());
    promise.resolve(map);
    }
}


