package com.evilmem.albert.evilmemory.Activities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.evilmem.albert.evilmemory.R;
import com.evilmem.albert.evilmemory.Activities.drawer;

import static com.evilmem.albert.evilmemory.R.id.button3;

public class MainActivity extends drawer{

    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    Button button_coma, button_equal, button_CE, button_suma, button_resta, button_prod, button_div;

    TextView operands, result;

    int x,a,b,res;

    String symbol;

    Toolbar toolbar;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    Boolean divByzero=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.ic_dialpad_black_24dp);

        operands = (TextView) findViewById(R.id.operands);
        result = (TextView) findViewById(R.id.result);

        sharedPreferences = getSharedPreferences("myApp", Context.MODE_PRIVATE);
        editor = getSharedPreferences("myApp", 0).edit();


        Initialize_buttons();

    }

    protected void Initialize_buttons(){

        button0 = ((Button) findViewById(R.id.button_0));
        button0.setOnClickListener(listener);

        button1 = ((Button) findViewById(R.id.button_1));
        button1.setOnClickListener(listener);

        button2 = ((Button) findViewById(R.id.button_2));
        button2.setOnClickListener(listener);

        button3 = ((Button) findViewById(R.id.button_3));
        button3.setOnClickListener(listener);

        button4 = ((Button) findViewById(R.id.button_4));
        button4.setOnClickListener(listener);

        button5 = ((Button) findViewById(R.id.button_5));
        button5.setOnClickListener(listener);

        button6 = ((Button) findViewById(R.id.button_6));
        button6.setOnClickListener(listener);

        button7 = ((Button) findViewById(R.id.button_7));
        button7.setOnClickListener(listener);

        button8 = ((Button) findViewById(R.id.button_8));
        button8.setOnClickListener(listener);

        button9 = ((Button) findViewById(R.id.button_9));
        button9.setOnClickListener(listener);

        button_equal = ((Button) findViewById(R.id.button_equal));
        button_equal.setOnClickListener(listener);

        button_coma = ((Button) findViewById(R.id.button_coma));
        button_coma.setOnClickListener(listener);

        button_CE = ((Button) findViewById(R.id.button_CE));
        button_CE.setOnClickListener(listener);

        button_suma = ((Button) findViewById(R.id.button_suma));
        button_suma.setOnClickListener(listener);

        button_resta = ((Button) findViewById(R.id.button_resta));
        button_resta.setOnClickListener(listener);

        button_prod = ((Button) findViewById(R.id.button_prod));
        button_prod.setOnClickListener(listener);

        button_div = ((Button) findViewById(R.id.button_div));
        button_div.setOnClickListener(listener);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("operand", operands.getText().toString());
        outState.putString("result", result.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        operands.setText(savedInstanceState.getString("operand"));
        result.setText(savedInstanceState.getString("result"));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Phone:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:671980912"));
                startActivity(intent);
                return true;
            case R.id.Explorer:
                Intent internet = new Intent(Intent.ACTION_VIEW);
                internet.setData(Uri.parse("https://sites.google.com/jediupc.com/andqt2017tardes"));
                startActivity(internet);
                return true;
            case R.id.toast:
                editor.putBoolean("Toast", true);
                Toast.makeText(getApplicationContext(), "Toast Notification", Toast.LENGTH_SHORT).show();
                editor.apply();
                return true;
            case R.id.state:
                editor.putBoolean("Toast", false);
                editor.apply();
                Toast.makeText(getApplicationContext(), "State Notification", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_0:
                    x = x * 10 + 0;
                    break;
                case R.id.button_1:
                    x = x * 10 + 1;
                    break;
                case R.id.button_2:
                    x = x * 10 + 2;
                    break;
                case R.id.button_3:
                    x = x * 10 + 3;
                    break;
                case R.id.button_4:
                    x = x * 10 + 4;
                    break;
                case R.id.button_5:
                    x = x * 10 + 5;
                    break;
                case R.id.button_6:
                    x = x * 10 + 6;
                    break;
                case R.id.button_7:
                    x = x * 10 + 7;
                    break;
                case R.id.button_8:
                    x = x * 10 + 8;
                    break;
                case R.id.button_9:
                    x = x * 10 + 9;
                    break;
                case R.id.button_equal:
                    b = x;
                    switch (symbol) {
                        case "+":
                            res = a + b;
                            break;
                        case "-":
                            res = a - b;
                            break;
                        case "*":
                            res = a * b;
                            break;
                        case "/":
                            if(b!=0) {
                                res = a / b;
                            }else{
                                res=0;
                                if(sharedPreferences.getBoolean("Toast",true)){
                                    Toast.makeText(getApplicationContext(),"You've got ZERO friends",Toast.LENGTH_SHORT).show();
                                }else{
                                    int mId=1;

                                    NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                                    NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
                                    builder.setContentTitle("Multiplicate por cero!");
                                    builder.setContentText("Math error");
                                    builder.setSmallIcon(R.mipmap.ic_launcher);
                                    notificationManager.notify(1,builder.build());


                                    Intent resultIntent = new Intent(getApplicationContext(), Troll.class);

                                    TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
                                    // Añade la pila para el Intent,pero no el intent en sí
                                    stackBuilder.addParentStack(Troll.class);
                                    // Añadimos el intent que empieza la activity que está en el top de la pila
                                    stackBuilder.addNextIntent(resultIntent);

                                    //El pending intent será el que se ejecute cuando la notificación sea pulsada
                                    PendingIntent resultPendingIntent =
                                            stackBuilder.getPendingIntent(
                                                    0,
                                                    PendingIntent.FLAG_UPDATE_CURRENT
                                            );
                                    builder.setContentIntent(resultPendingIntent);

                                    // mId nos permite actualizar las notificaciones en un futuro
                                    // Notificamos
                                    notificationManager.notify(mId, builder.build());

                                    Notification noti = builder.build();
                                    noti.flags |= Notification.FLAG_INSISTENT;
                                    //noti.flags |= Notification.FLAG_NO_CLEAR;
                                    noti.flags |= Notification.FLAG_SHOW_LIGHTS;
                                    notificationManager.notify(mId, noti);
                                }
                            }
                            break;
                    }
                    result.setText(String.valueOf(res));
                    break;
                case R.id.button_coma:
                    break;
                case R.id.button_CE:

                    x = 0;
                    a = 0;
                    b = 0;
                    res = 0;
                    result.setText(null);
                    break;
                case R.id.button_suma:

                    a = x;
                    x = 0;
                    symbol = "+";
                    break;
                case R.id.button_resta:

                    a = x;
                    x = 0;
                    symbol = "-";
                    break;
                case R.id.button_prod:

                    a = x;
                    x = 0;
                    symbol = "*";
                    break;
                case R.id.button_div:
                    a = x;
                    x = 0;
                    symbol = "/";
                    break;

            }

            operands.setText(String.valueOf(x));


        }
    };
}

