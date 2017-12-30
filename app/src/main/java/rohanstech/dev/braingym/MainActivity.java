package rohanstech.dev.braingym;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    Button btn0;
    Button btn1;
    Button btn2;
    Button btn3;
    Button playAgainButton;

    TextView resultTextView ;
    TextView pointsTextView;
    TextView sumTextView;
    TextView timerTextView;

    RelativeLayout gameRelativeLayout;


    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;

    public void playAgain(View view) {
        score = 0 ;
        numberOfQuestions = 0 ;
        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        generateQuetion();

        new CountDownTimer(30100,1000)
        {
            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000) + "s");
            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
            }
        }.start();
    }

    public void generateQuetion()
    {
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);
        answers.clear();
        int incorrectAnswer;

        for (int i = 0; i < 4; i++)
        {
            if (i == locationOfCorrectAnswer)
            {
                answers.add(a + b);
            } else
            {
                incorrectAnswer = rand.nextInt(41);
                while (incorrectAnswer == a + b)
                {
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }

        btn0.setText(Integer.toString(answers.get(0)));
        btn1.setText(Integer.toString(answers.get(1)));
        btn2.setText(Integer.toString(answers.get(2)));
        btn3.setText(Integer.toString(answers.get(3)));

    }

    public void chooseAnswer(View view) {
       if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer)))
       {
            score++;
            resultTextView.setText("Correct!");
       }else{

           resultTextView.setText("Wrong!");
       }
        numberOfQuestions++;
       pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
       generateQuetion();

    }


    public void start(View view) {
        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(View.VISIBLE);

        playAgain(findViewById(R.id.playAgainBtn));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        sumTextView = findViewById(R.id.sumTextView);
         btn0 = findViewById(R.id.btn0);
         btn1 = findViewById(R.id.btn1);
         btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        resultTextView = findViewById(R.id.resultTextView);
        pointsTextView = findViewById(R.id.pointsTextView);
        timerTextView= findViewById(R.id.timerTextView);
        playAgainButton=findViewById(R.id.playAgainBtn);
        gameRelativeLayout = findViewById(R.id.gameRelativeLayout);


    }


}
