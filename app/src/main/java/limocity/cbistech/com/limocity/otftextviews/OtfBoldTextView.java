package limocity.cbistech.com.limocity.otftextviews;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by ashok on 3/9/18.
 */

public class OtfBoldTextView extends TextView {
    public OtfBoldTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "ColabReg.otf");
        this.setTypeface(typeface);
    }
}
