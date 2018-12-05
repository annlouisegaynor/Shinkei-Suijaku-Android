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
    private static boolean endgame;

    private int hits = 0;
    private int misses = 0;
    private int counter = 1;

    private int[] id = new int[2];
    private int[] value = new int[2];

    private TextView hitTextView;
    private TextView missTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initTextViews();
        initCards();
        initArrayList();
    }

    public void initTextViews(){
        hitTextView = findViewById(R.id.hitTextView);
        missTextView = findViewById(R.id.missTextView);
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
        for (int y = 0; y < (cards.length/2); y++) {
            iconList.add(y);
            iconList.add(y);
        }
        Collections.shuffle(iconList);

        //  CHEATS HAHAHAHAHAHAHAHA
        int character = 0;
        String line = "";

        for (int a = 0; a < iconList.size(); a++) {
            line += iconList.get(a) + " ";
            character++;
            if (character == 4) {
                line += "\n";
                character = 0;
            }
        }
        Log.d("cheats b0i", "\n" +line);
    }

    public boolean sameValues() {
        if (value[0] == value[1]) {
            hits++;
            hitTextView.setText(hits +"");
            return true;
        }
        else{
            misses++;
            missTextView.setText(misses +"");
            return false;
        }
    }

    public static boolean isEndGame(){
        for(int i=0; i < cards.length; i++){
            if( cards[i].isEnabled() == false){
                endgame = true;
            }
            else return false;
        }
        return true;
    }

    public void onCardClick(View v){
        for (int i = 0; i < cards.length; i++) {
            if (cards[i].getId() == v.getId()) {
                cards[i].setText(iconList.get(i) +"");

                if (counter == 1) {
                    id[0] = i;
                    value[0] = iconList.get(i);
                }
                if (counter == 2) {
                    id[1] = i;
                    value[1] = iconList.get(i);

                    if (sameValues()) {
                        cards[id[0]].setEnabled(false);
                        cards[id[1]].setEnabled(false);
                        counter = 0;
                    }
                }

                if (counter == 3){
                    cards[id[0]].setText(R.string.a02_cards_default);
                    cards[id[1]].setText(R.string.a02_cards_default);
                    counter = 0;
                }
                counter++;
            }
        }
        if (isEndGame() == true){
            Toast.makeText(this, "YOU WON!\nHITS: " +hits +"   MISSES: " +misses, Toast.LENGTH_SHORT).show();
        }
    }
}
