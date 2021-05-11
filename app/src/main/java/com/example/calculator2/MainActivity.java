package com.example.calculator2;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView Screen;
    private String input="",answer;
    private boolean clearResult;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Screen=findViewById(R.id.screen);

    }
    public void ButtonClick(View view){
        Button button=(Button) view;
        String data=button.getText().toString();
        switch (data){
            case "AC":
                input="";
                break;
            case "ans":
                clearResult=false;
                input+=answer;
                break;
            case "x":
                clearResult=false;
                solve();
                input+="*";
                break;
            case "^":
                clearResult=false;
                solve();
                input+="^";
                break;
            case "=":
                clearResult=true;
                solve();
                answer=input;
                break;
            case "__":
                if(input.length()>0){
                    clearResult=false;
                    String newText= input.substring(0,input.length()-1);
                    input=newText;
            }
                break;
            default:
                if(input==null){
                    input="";
                }
                if(data.equals("+") || data.equals("-") || data.equals("/")){
                    clearResult=false;
                    solve();
                }
                else if(clearResult==true)
                {
                    input="";
                    clearResult=false;
                }
                input+=data;
        }
        Screen.setText(input);
    }
    private void solve(){
        if(input.split("\\*").length==2) {
            String number[] = input.split("\\*");
            try {
                double mul = Double.parseDouble(number[0]) * Double.parseDouble(number[1]);
                input = mul+"";
            } catch (Exception e) {
                //toggle error
            }
        }
            else if(input.split("/").length==2) {
                String number[] = input.split("/");
                try {
                    double div = Double.parseDouble(number[0]) / Double.parseDouble(number[1]);
                    input = div+"";
                } catch (Exception e) {
                    //toggle error
                }
            }
                else if(input.split("\\^").length==2) {
                String number[] = input.split("\\^");
                try {
                    double pow = Math.pow(Double.parseDouble(number[0]), Double.parseDouble(number[1]));
                    input = pow+"";
                } catch (Exception e) {
                    //toggle error
                }
            }
                    else if(input.split("\\+").length==2) {
                String number[] = input.split("\\+");
                try {
                    double sum = Double.parseDouble(number[0]) + Double.parseDouble(number[1]);
                    input = sum+"";
                } catch (Exception e) {
                    //toggle error
                }
            }
                        else if(input.split("\\-").length>1) {
                String number[] = input.split("\\-");
                if (number[0]=="" && number.length==2) {
                    number[0] = 0 + "";
                }
                try {
                    double sub = Double.parseDouble(number[0]) - Double.parseDouble(number[1]);
                    input = sub+"";
                } catch (Exception e) {
                    //toggle error
                }
            }
                        String n[]=input.split("\\.");
                        if(n.length>1){
                            if(n[1].equals("0")){
                                input=n[0];
                            }
                        }
                        Screen.setText(input);
    }
}