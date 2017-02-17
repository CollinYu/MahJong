package edu.up.yu18.mahjong;

import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.media.Image;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.*;

import static android.graphics.PorterDuff.Mode.MULTIPLY;
import static android.graphics.PorterDuff.Mode.SRC;
import static android.graphics.PorterDuff.Mode.SRC_ATOP;
import static android.graphics.PorterDuff.Mode.SRC_OVER;

public class MainActivity extends AppCompatActivity{
    private ImageButton tile1;
    private ImageButton tile2;
    private ImageButton tile3;
    private ImageButton tile4;
    private boolean tile1IsPressed;
    private boolean tile2IsPressed;
    private boolean tile3IsPressed;
    private boolean tile4IsPressed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tile1IsPressed = false;
        tile1 = (ImageButton)findViewById(R.id.tile1);
        tile1.setOnClickListener(new yourTileListener());
        tile2 = (ImageButton)findViewById(R.id.tile2);
        tile2.setOnClickListener(new yourTileListener());
        tile3 = (ImageButton)findViewById(R.id.tile3);
        tile3.setOnClickListener(new yourTileListener());
        tile4 = (ImageButton)findViewById(R.id.tile4);
        tile4.setOnClickListener(new yourTileListener());
    }
        private class yourTileListener implements View.OnClickListener {
            public void onClick(View v) {
                if (v == tile1) {
                    if (!tile1IsPressed) {
                        tile1.setBackgroundColor(0xFF0000B2);
                        tile1IsPressed = true;
                    }
                    else {
                        tile1.setBackgroundColor(0xFF00B200);
                        tile1IsPressed = false;
                    }
                }if (v == tile2) {
                    if (!tile2IsPressed) {
                        tile2.setBackgroundColor(0xFF0000B2);
                        tile2IsPressed = true;
                    }
                    else {
                        tile2.setBackgroundColor(0xFF00B200);
                        tile2IsPressed = false;
                    }
                }if (v == tile3) {
                    if (!tile3IsPressed) {
                        tile3.setBackgroundColor(0xFF0000B2);
                        tile3IsPressed = true;
                    }
                    else {
                        tile3.setBackgroundColor(0xFF00B200);
                        tile3IsPressed = false;
                    }
                }if (v == tile4) {
                    if (!tile4IsPressed) {
                        tile4.setBackgroundColor(0xFF0000B2);
                        tile4IsPressed = true;
                    }
                    else {
                        tile4.setBackgroundColor(0xFF00B200);
                        tile4IsPressed = false;
                    }
                }


            }


    }

}
