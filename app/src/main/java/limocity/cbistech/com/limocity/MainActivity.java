package limocity.cbistech.com.limocity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import limocity.cbistech.com.limocity.onboarding.DotsIndicator;
import limocity.cbistech.com.limocity.onboarding.HomeOnBoadingFragment;
import limocity.cbistech.com.limocity.onboarding.HomePagerAdapter;
import limocity.cbistech.com.limocity.onboarding.MyPagerAdapter;
import limocity.cbistech.com.limocity.onboarding.OnBoadingFragment;
import limocity.cbistech.com.limocity.onboarding.OnBoardActivity;
import limocity.cbistech.com.limocity.onboarding.PaperOnboardingPageApp;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private ViewPager viewPager;
    private DotsIndicator dotsIndicator;
    private ArrayList<HomeOnBoadingFragment> baseFragments = new ArrayList<>();
    private HomePagerAdapter pagerAdapter;
    private LinearLayout firstcar;
    private LinearLayout secondcar;
    private LinearLayout thirdcar;
    private LinearLayout fourcar;


    @Override
    protected void onBackendConnected() {

    }

    @Override
    protected void onLayoutCreated() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        dotsIndicator = (DotsIndicator) findViewById(R.id.dots_indicator);
        LinearLayout pickup_date = (LinearLayout) findViewById(R.id.pickup_date);
        LinearLayout drop_time = (LinearLayout) findViewById(R.id.drop_time);
        firstcar = (LinearLayout) findViewById(R.id.firstcar);
        secondcar = (LinearLayout) findViewById(R.id.secondcar);
        thirdcar = (LinearLayout) findViewById(R.id.thirdcar);
        fourcar = (LinearLayout) findViewById(R.id.fourcar);
        TextView continuebutton = (TextView) findViewById(R.id.continuebutton);

        firstcar.setOnClickListener(this);
        secondcar.setOnClickListener(this);
        thirdcar.setOnClickListener(this);
        fourcar.setOnClickListener(this);

        continuebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ConfirmActivity.class);
                startActivity(i);
            }
        });


        pickup_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        // eReminderTime.setText( selectedHour + ":" + selectedMinute);
                        showToast("time will appear now ");
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        drop_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        // eReminderTime.setText( selectedHour + ":" + selectedMinute);
                        showToast("time will appear now ");
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        ArrayList<PaperOnboardingPageApp> dataForOnboarding = getDataForOnboarding();
        for (int i = 0; i < dataForOnboarding.size(); i++) {
            baseFragments.add(HomeOnBoadingFragment.newInstance(dataForOnboarding.get(i)));
        }

        pagerAdapter = new HomePagerAdapter(getSupportFragmentManager(), baseFragments);
        viewPager.setAdapter(pagerAdapter);
        dotsIndicator.setViewPager(viewPager);
    }


    private ArrayList<PaperOnboardingPageApp> getDataForOnboarding() {

        // prepare data
        PaperOnboardingPageApp scr1 = new PaperOnboardingPageApp("Welcome To Aiwa Card",
                "Aiwa Cards offers the comfort of Mobile recharge & DTH bill payments right from your own mobile. Smart, Easy & Safe, with privacy and security guaranteed, users can recharge any mobile phone and DTH bills in India, 24x7 from anywhere, using their own mobile",
                Color.parseColor("#346afe"), R.drawable.photo);
        PaperOnboardingPageApp scr2 = new PaperOnboardingPageApp("Mobile Recharge",
                "- Fastest and smartest way to do online recharges & digital payments\n" +
                        "- Instant recharges in less than 10 seconds\n" +
                        "You can find details about Topup Vouchers, Special Tariff Vouchers (STV), Combo Vouchers and Full Talk Time offers.",
                Color.parseColor("#FFB47469"), R.drawable.photo);
        PaperOnboardingPageApp scr3 = new PaperOnboardingPageApp("Easy Way to Transfer Fund",
                "Aiwa Card  is a complete payment solution giving you the power to pay in just One Click.Aiwa Card is convenient, fast and secure." +
                        "No need to re-load money again and again.So don’t just pay. Aiwa it!",
                Color.parseColor("#9B90BC"), R.drawable.photo);

        ArrayList<PaperOnboardingPageApp> elements = new ArrayList<>();
        elements.add(scr1);
        elements.add(scr2);
        elements.add(scr3);
        return elements;
    }

    @Override
    public void onClick(View view) {
        int sdk = android.os.Build.VERSION.SDK_INT;

        switch (view.getId()) {
            case R.id.firstcar:
                if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    firstcar.setBackgroundDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.carselectorselec));
                    secondcar.setBackgroundDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.carselectornormal));
                    thirdcar.setBackgroundDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.carselectornormal));
                    fourcar.setBackgroundDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.carselectornormal));
                } else {
                    firstcar.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.carselectorselec));
                    secondcar.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.carselectornormal));
                    thirdcar.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.carselectornormal));
                    fourcar.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.carselectornormal));
                }
                break;
            case R.id.secondcar:
                if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    secondcar.setBackgroundDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.carselectorselec));
                    firstcar.setBackgroundDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.carselectornormal));
                    thirdcar.setBackgroundDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.carselectornormal));
                    fourcar.setBackgroundDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.carselectornormal));
                } else {
                    secondcar.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.carselectorselec));
                    firstcar.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.carselectornormal));
                    thirdcar.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.carselectornormal));
                    fourcar.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.carselectornormal));
                }
                break;
            case R.id.thirdcar:
                if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    thirdcar.setBackgroundDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.carselectorselec));
                    secondcar.setBackgroundDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.carselectornormal));
                    firstcar.setBackgroundDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.carselectornormal));
                    fourcar.setBackgroundDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.carselectornormal));
                } else {
                    thirdcar.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.carselectorselec));
                    secondcar.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.carselectornormal));
                    firstcar.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.carselectornormal));
                    fourcar.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.carselectornormal));
                }
                break;
            case R.id.fourcar:
                if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    fourcar.setBackgroundDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.carselectorselec));
                    secondcar.setBackgroundDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.carselectornormal));
                    thirdcar.setBackgroundDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.carselectornormal));
                    firstcar.setBackgroundDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.carselectornormal));
                } else {
                    fourcar.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.carselectorselec));
                    secondcar.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.carselectornormal));
                    thirdcar.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.carselectornormal));
                    firstcar.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.carselectornormal));
                }
                break;
        }
    }
}
