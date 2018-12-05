package com.example.akgaynor.activity04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class GameActivity extends AppCompatActivity {

    private static Button cards[] = new Button[16];
    private ArrayList<Integer> iconList = new ArrayList<Integer>();
    private TextView hitTextView = (TextView)findViewById(R.id.hitTextView);
    private TextView missTextView = (TextView)findViewById(R.id.missTextView);
    private static boolean endgame;

    private int hits = 0;
    private int misses = 0;
    private int counter;

    private int[] id = new int[2];
    private int[] value = new int[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initCards();
        initArrayList();
    }

    public void initCards(){
        cards[0] = (Button)findViewById(R.id.button1);
        cards[1] = (Button)findViewById(R.id.button2);
        cards[2] = (Button)findViewById(R.id.button3);
        cards[3] = (Button)findViewById(R.id.button4);
        cards[4] = (Button)findViewById(R.id.button5);
        cards[5] = (Button)findViewById(R.id.button6);
        cards[6] = (Button)findViewById(R.id.button7);
        cards[7] = (Button)findViewById(R.id.button8);
        cards[8] = (Button)findViewById(R.id.button9);
        cards[9] = (Button)findViewById(R.id.button10);
        cards[10] = (Button)findViewById(R.id.button11);
        cards[11] = (Button)findViewById(R.id.button12);
        cards[12] = (Button)findViewById(R.id.button13);
        cards[13] = (Button)findViewById(R.id.button14);
        cards[14] = (Button)findViewById(R.id.button15);
        cards[15] = (Button)findViewById(R.id.button16);
    }

    public void initArrayList(){
        for (int y = 0; y < (cards.length /2); y++) {
            iconList.add(y);
            iconList.add(y);
        }

        Collections.shuffle(iconList);

        //  CHEATS HAHAHAHAHAHAHAHA
        int newLine = 0;
        for (int a = 0; a < iconList.size(); a++) {
            newLine++;
            Log.d("cheats b0i", Integer.toString(iconList.get(a)));
            if (newLine == 4) {
                System.out.println();
                newLine = 0;
            }
        }
    }

    public boolean sameValues() {
        if (value[0] == value[1]) {
            return true;
        }
        return false;
    }

    public static boolean isEndGame(){
        for(int i=0; i < cards.length; i++){
            if( cards[i].isEnabled() == false){
                endgame = true;
            }
            else endgame = false;
        }
        return endgame;
    }

    public void onCardClick(View v){
        for (int i = 0; i < cards.length; i++) {
            if (cards[i].getId() == v.getId()) {
                cards[i].setText(iconList.get(i));
                counter++;

                if (counter == 3) {
                    if (sameValues()) {
                        cards[id[0]].setEnabled(false);
                        cards[id[1]].setEnabled(false);
                        hits++;
                        hitTextView.setText(hits +"");
                    } else {
                        cards[id[0]].setText(R.string.a02_cards_default);
                        cards[id[1]].setText(R.string.a02_cards_default);
                        misses++;
                        missTextView.setText(misses +"");
                    }
                    counter = 1;
                }
                if (counter == 1) {
                    id[0] = i;
                    value[0] = iconList.get(i);
                }
                if (counter == 2) {
                    id[1] = i;
                    value[1] = iconList.get(i);
                }
            }
        }
        if (isEndGame() == true){
            Toast.makeText(this, "YOU WON!\nHITS: " +hits +"   MISSES: " +misses, Toast.LENGTH_SHORT).show();
        }
    }
}
