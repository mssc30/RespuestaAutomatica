package net.mssc.respuestaautomatica;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class Receiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //COMO SE ESTAN ESCUCHANDO DOS BROADCAST, UNO CON EL NUMERO, Y EL OTRO NO,
        //ES NECESARIO VERIFICAR CUAL DE LOS DOS TRAE EL NUMERO

        //OBTENER EL ESTADO DEL TELEFONO CON TELEPHONY MANAGER
        String estado = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);

        //SI EL ESTADO ES TIMBRANDO
        if(estado.equals(TelephonyManager.EXTRA_STATE_RINGING)){

            //OBTENER EL NUMERO DE TELEFONO
            String numero = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);

            //SI EL NUMERO ES DIFERENTE DE NULL
            if(numero!=null){
                //OBTENER LAS PREFERENCIAS GUARDADAS, EL NUMERO Y EL MENSAJE REGISTRADOS
                SharedPreferences misPreferencias = PreferenceManager.getDefaultSharedPreferences(context);
                String numGuardado = misPreferencias.getString("NUMERO", "");

                //SI EL NUMERO DE LA LLAMADA ENTRANTE ES IGUAL QUE EL NUMERO GUARDADO
                if(numero.equals(numGuardado)){
                    //OBTENER EL MENSAJE DE PREFERENCES
                    String mensaje = misPreferencias.getString("MENSAJE", "");

                    //ENVIAR EL MENSAJE CON SMSMANAGER
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(numGuardado, null, mensaje, null, null);

                    //INDICAR QUE EL MENSAJE SE ENVIO
                    Toast.makeText(context, "Mensaje Enviado", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}