package limocity.cbistech.com.limocity.onboarding;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import limocity.cbistech.com.limocity.BaseActivity;
import limocity.cbistech.com.limocity.MainActivity;
import limocity.cbistech.com.limocity.R;

import java.util.ArrayList;

public class OnBoardActivity extends BaseActivity implements View.OnClickListener {


    private ArrayList<OnBoadingFragment> baseFragments = new ArrayList<>();
    private MyPagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private DotsIndicator dotsIndicator;
    private TextView leftText;
    private TextView rightText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_bording);

    }

    @Override
    protected void onBackendConnected() {

    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() != 0) {
            viewPager.setCurrentItem(0);
            return;
        }
        finish();
    }

    @Override
    protected void onLayoutCreated() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        leftText = (TextView) findViewById(R.id.onboading_left_txt);
        rightText = (TextView) findViewById(R.id.onboading_right_txt);
        dotsIndicator = (DotsIndicator) findViewById(R.id.dots_indicator);
//        boolean userLoggedin = BaseApplication.getDbInstance().isUserLoggedin();
//        if (userLoggedin) {
//            Intent intent = new Intent(limocity.cbistech.com.limocity.onboarding.OnBoardActivity.this, MainActivity.class);
//            startActivity(intent);
//            finish();
//            return;
//        }


        ArrayList<PaperOnboardingPageApp> dataForOnboarding = getDataForOnboarding();
        for (int i = 0; i < dataForOnboarding.size(); i++) {
            baseFragments.add(OnBoadingFragment.newInstance(dataForOnboarding.get(i)));
        }
        pagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), baseFragments);
        viewPager.setAdapter(pagerAdapter);
        dotsIndicator.setViewPager(viewPager);
        rightText.setText("Next");
        leftText.setText("skip");

        rightText.setOnClickListener(this);
        leftText.setOnClickListener(this);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        rightText.setText("Next");
                        leftText.setText("skip");

                        break;
                    case 1:
                        rightText.setText("Next");
                        leftText.setText("Previous");
                        break;
                    case 2:
                        rightText.setText("Finish");
                        leftText.setText("Previous");
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


//        if (!userLoggedin) {
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            final PaperOnboardingFragment onBoardingFragment = PaperOnboardingFragment.newInstance(getDataForOnboarding());
//
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.add(R.id.fragment_container, onBoardingFragment);
//            fragmentTransaction.commit();
//
//            onBoardingFragment.setOnRightOutListener(new PaperOnboardingOnRightOutListener() {
//                @Override
//                public void onRightOut() {
//                    startActivity(new Intent(OnBoardActivity.this, ActivityLogin.class));
//                    finish();
//                }
//            });
//        } else {

//        }
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.onboading_left_txt:
                String leftText = this.leftText.getText().toString();
                if (leftText.equalsIgnoreCase("skip")) {
                    startActivity(new Intent(limocity.cbistech.com.limocity.onboarding.OnBoardActivity.this, MainActivity.class));
                    finish();
                } else if (leftText.equalsIgnoreCase("Previous")) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                }
                break;
            case R.id.onboading_right_txt:
                String rightText = this.rightText.getText().toString();
                if (rightText.equalsIgnoreCase("Finish")) {
                    startActivity(new Intent(limocity.cbistech.com.limocity.onboarding.OnBoardActivity.this, MainActivity.class));
                    finish();
                } else if (rightText.equalsIgnoreCase("Next")) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                }
                break;
        }
    }
}


