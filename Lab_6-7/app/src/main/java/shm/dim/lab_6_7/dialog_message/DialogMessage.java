package shm.dim.lab_6_7.dialog_message;

import android.content.Context;
import android.support.v7.app.AlertDialog;

public class DialogMessage {

    private static AlertDialog ad;

    public static void create(Context context, String msg) {
        AlertDialog.Builder b = new AlertDialog.Builder(context);
        b.setMessage(msg).setPositiveButton("Ok", (dialog, which) -> {});
        ad = b.create();
    }

    public static void show() {
        ad.show();
    }
}