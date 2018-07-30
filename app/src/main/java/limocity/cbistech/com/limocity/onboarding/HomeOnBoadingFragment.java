package limocity.cbistech.com.limocity.onboarding;

import android.os.Bundle;
import android.view.View;

import limocity.cbistech.com.limocity.BaseFragment;
import limocity.cbistech.com.limocity.R;

/**
 * Created by piyush on 10/12/17.
 */

public class HomeOnBoadingFragment extends BaseFragment {
    private PaperOnboardingPageApp mElements;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_onboarding_home_layout;
    }

    public static HomeOnBoadingFragment newInstance(PaperOnboardingPageApp elements) {
        HomeOnBoadingFragment fragment = new HomeOnBoadingFragment();
        Bundle args = new Bundle();
        args.putSerializable("elements", elements);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onLayoutCreated(View view) {
        if(this.getArguments() != null) {
            this.mElements = (PaperOnboardingPageApp)this.getArguments().get("elements");
        }
//        ((ImageView)view.findViewById(R.id.onboading_image)).setImageResource(mElements.getContentIconRes());
//        ((TextView)view.findViewById(R.id.onboading_heading)).setText(mElements.getTitleText());
//        ((TextView)view.findViewById(R.id.onboading_description)).setText(mElements.getDescriptionText());

    }
}
