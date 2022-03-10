package com.reactnativehce.apps;
import android.content.pm.PackageManager;
import com.facebook.react.bridge.ReactApplicationContext;
public class NFCFeature {
  private boolean FEATURE_NFC;
  private boolean FEATURE_NFC_HOST_CARD_EMULATION;
  private boolean FEATURE_NFC_BEAM;
  private boolean FEATURE_NFC_HOST_CARD_EMULATION_NFCF;
  private boolean FEATURE_NFC_OFF_HOST_CARD_EMULATION_ESE;
  private boolean FEATURE_NFC_OFF_HOST_CARD_EMULATION_UICC;
  private static ReactApplicationContext reactContext;
  public NFCFeature(ReactApplicationContext context) {
    reactContext = context;
    this.FEATURE_NFC = context.getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_NFC);
    this.FEATURE_NFC_HOST_CARD_EMULATION = context.getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_NFC_HOST_CARD_EMULATION);
    this.FEATURE_NFC_BEAM = context.getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_NFC_BEAM);
    this.FEATURE_NFC_HOST_CARD_EMULATION_NFCF = context.getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_NFC_HOST_CARD_EMULATION_NFCF);
    this.FEATURE_NFC_OFF_HOST_CARD_EMULATION_ESE = context.getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_NFC_OFF_HOST_CARD_EMULATION_ESE);
    this.FEATURE_NFC_OFF_HOST_CARD_EMULATION_UICC = context.getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_NFC_OFF_HOST_CARD_EMULATION_UICC);
  }

  public boolean getFEATURE_NFC_HOST_CARD_EMULATION_NFCF(){
    return this.FEATURE_NFC_HOST_CARD_EMULATION_NFCF;
  }
  public boolean getFEATURE_NFC_OFF_HOST_CARD_EMULATION_ESE(){
    return this.FEATURE_NFC_OFF_HOST_CARD_EMULATION_ESE;
  }
  public boolean getFEATURE_NFC_OFF_HOST_CARD_EMULATION_UICC(){
    return this.FEATURE_NFC_OFF_HOST_CARD_EMULATION_UICC;
  }

  public boolean getFEATURE_NFC_BEAM(){
    return this.FEATURE_NFC_BEAM;
  }

  public boolean getFEATURE_NFC(){
    return this.FEATURE_NFC;
  }

  public boolean getFEATURE_NFC_HOST_CARD_EMULATION(){
    return this.FEATURE_NFC_HOST_CARD_EMULATION;
  }

  public void getInfo(){
    this.FEATURE_NFC = reactContext.getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_NFC);
    this.FEATURE_NFC_HOST_CARD_EMULATION = reactContext.getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_NFC_HOST_CARD_EMULATION);
    this.FEATURE_NFC_BEAM = reactContext.getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_NFC_BEAM);
    this.FEATURE_NFC_HOST_CARD_EMULATION_NFCF = reactContext.getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_NFC_HOST_CARD_EMULATION_NFCF);
    this.FEATURE_NFC_OFF_HOST_CARD_EMULATION_ESE = reactContext.getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_NFC_OFF_HOST_CARD_EMULATION_ESE);
    this.FEATURE_NFC_OFF_HOST_CARD_EMULATION_UICC = reactContext.getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_NFC_OFF_HOST_CARD_EMULATION_UICC);
  }
}
