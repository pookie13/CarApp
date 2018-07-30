package limocity.cbistech.com.limocity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import java.lang.reflect.Field;


/**
 * This being an abstract class acts as base class for all the fragments in the application.
 * The class handles general toast, progress, activity reference and MODES
 * Created by piyush on 18/11/15.
 */
abstract public class BaseFragment extends AppCompatDialogFragment {

    private static final Field sChildFragmentManagerField;

    static {
        Field f = null;
        try {
            f = Fragment.class.getDeclaredField("mChildFragmentManager");
            f.setAccessible(true);
        } catch (NoSuchFieldException e) {
        }
        sChildFragmentManagerField = f;
    }

    public BaseActivity baseActivity;
    public ModeListener modeListener;
    public View view;
    protected MODE currentMode;
    View progress;
    Object progressTag;

    public BaseFragment() {
        setStyle(DialogFragment.STYLE_NO_TITLE, 0);
    }

    public boolean setCurrentMode(MODE mode) {
        currentMode = mode;
        return false;
    }

    public MODE getCurrentMode() {
        return this.currentMode;
    }

    public boolean isUpdateNecessary() {
        return false;
    }

    public void setModeListener(ModeListener modeListener) {
        this.modeListener = modeListener;
    }

    /**
     * called when the fragment needs to update the UI
     */
    public void refreshUI() {

    }

    @Override
    public void onDetach() {
        super.onDetach();

        if (sChildFragmentManagerField != null) {
            try {
                sChildFragmentManagerField.set(this, null);
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (modeListener != null && getCurrentMode() != null) {
            modeListener.onModeChanged(true, getCurrentMode());
        }
    }

    private void makeBundle() {
        Bundle bundle = getArguments();
        if (bundle == null) {
            bundle = new Bundle();
            setArguments(bundle);
        }
    }

    public BaseFragment addBundleLong(String key, long value) {
        makeBundle();
        getArguments().putLong(key, value);
        return this;
    }

    public BaseFragment addBundleInt(String key, int value) {
        makeBundle();
        getArguments().putInt(key, value);
        return this;
    }

    public BaseFragment addBundleSting(String key, String value) {
        makeBundle();
        getArguments().putString(key, value);
        return this;
    }

    public BaseFragment addBundleBoolean(String key, boolean value) {
        makeBundle();
        getArguments().putBoolean(key, value);
        return this;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

//    protected boolean isNetConnected() {
//        return baseActivity.isNetConnected();
//    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        baseActivity = (BaseActivity) activity;
    }

    public abstract int getLayoutId();

    public abstract void onLayoutCreated(View view);

    public String getHeader() {
        return "";
    }

    public boolean showAction() {
        return false;
    }

    public void showToast(Object object) {
        baseActivity.showToast(object.toString());
    }

    @Override
    public void onPause() {
        super.onPause();
        hideKeyBord();
    }

    protected String getScreenName() {
        return getVisibleFragment();
    }

    @Override
    public void onStop() {
        super.onStop();
        System.gc();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(getLayoutId(), null);
            onLayoutCreated(view);
//            if (view.findViewById(R.id.header_name)!=null) {
//                TextView viewById = (TextView) view.findViewById(R.id.header_name);
//                viewById.setText(getHeader());
//            }
        }
        return view;
    }

    public String getVisibleFragment() {
        return getClass().getSimpleName();
    }

    public void hideKeyBord() {
        View view = baseActivity.getCurrentFocus();
        if (view != null) {

            InputMethodManager imm = (InputMethodManager) baseActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
            boolean b = imm.hideSoftInputFromWindow(baseActivity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    public void hideKeyBord(TextView view) {
        InputMethodManager imm =
                (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    public enum MODE {
        LOGIN, SIGNUP,TOP_UP_ENTER_NUMBER,OPRATER,SUCCESS,FORGOT
    }

    public interface ModeListener {
        void onModeChanged(boolean status, MODE mode);
        void onModeChanged(boolean status, MODE mode, Object mobilenum);
    }
}
