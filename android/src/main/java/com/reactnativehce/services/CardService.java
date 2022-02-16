package com.reactnativehce.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;
import android.util.Log;

import com.reactnativehce.IHCEApplication;
import com.reactnativehce.apps.nfc.NFCTagType4;
import com.reactnativehce.utils.BinaryUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class CardService extends HostApduService {
    private static final String TAG = "CardService";
    private static byte[] SELECT_APDU_HEADER = BinaryUtils.HexStringToByteArray("00A40400");
    private static final byte[] CMD_OK = BinaryUtils.HexStringToByteArray("9000");
    private static final byte[] CMD_ERROR = BinaryUtils.HexStringToByteArray("6A82");
    private static final byte[] PPSE_APDU_SELECT_RESP = {
    (byte) 0x6F,  // FCI Template
    (byte) 0x23,  // length = 35
    (byte) 0x84,  // DF Name
    (byte) 0x0E,  // length("2PAY.SYS.DDF01")
    // Data (ASCII values of characters used):
    '2', 'P', 'A', 'Y', '.', 'S', 'Y', 'S', '.', 'D', 'D', 'F', '0', '1',
    (byte) 0xA5, // FCI Proprietary Template
    (byte) 0x11, // length = 17
    (byte) 0xBF, // FCI Issuer Discretionary Data
    (byte) 0x0C, // length = 12
    (byte) 0x0E,
    (byte) 0x61, // Directory Entry
    (byte) 0x0C, // Entry length = 12
    (byte) 0x4F, // ADF Name
    (byte) 0x07, // ADF Length = 7
    // Tell the POS (point of sale terminal) that we support the standard
    // Visa credit or debit applet: A0000000031010
    // Visa's RID (Registered application provider IDentifier) is 5 bytes:
    (byte) 0xA0, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x03,
    // PIX (Proprietary application Identifier eXtension) is the last 2 bytes.
    // 10 10 (means visa credit or debit)
    (byte) 0x10, (byte) 0x10,
    (byte) 0x87,  // Application Priority Indicator
    (byte) 0x01,  // length = 1
    (byte) 0x01,
    (byte) 0x90, // SW1  (90 00 = Success)
    (byte) 0x00  // SW2
  };

    private ArrayList<IHCEApplication> registeredHCEApplications = new ArrayList<IHCEApplication>();
    private IHCEApplication currentHCEApplication = null;

    @Override
    public byte[] processCommandApdu(byte[] commandApdu, Bundle extras) {
      if (currentHCEApplication != null) {
        Log.i(TAG, "ok do do");
        return currentHCEApplication.processCommand(commandApdu);
      }

      byte[] header = Arrays.copyOfRange(commandApdu, 0, 4);

      if (Arrays.equals(SELECT_APDU_HEADER, header)) {
        for (IHCEApplication app : registeredHCEApplications) {
          if (app.assertSelectCommand(commandApdu)) {
            Log.i(TAG,BinaryUtils.ByteArrayToHexString(PPSE_APDU_SELECT_RESP));
            currentHCEApplication = app;
           return CMD_OK;
//             return PPSE_APDU_SELECT_RESP;
          }
        }
      }

      return CMD_ERROR;
    }

    @Override
    public void onCreate() {
      Log.i(TAG, "Starting service");

      SharedPreferences prefs = getApplicationContext()
        .getSharedPreferences("hce", Context.MODE_PRIVATE);

      String type = prefs.getString("type", "text");
      String content = prefs.getString("content", "No text provided");

      registeredHCEApplications.add(new NFCTagType4(type, content));
    }

    @Override
    public void onDeactivated(int reason) {
      Log.i(TAG, "Finishing service: " + reason);
    }
}
