package lenovo.com.zxing.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class HighlightImageView extends ImageView {

    @Override
    public void setImageDrawable(Drawable d) {
        // Replace the original background drawable (e.g. image) with a
        // LayerDrawable that
        // contains the original drawable.
        this.setBackgroundResource(0);
        HighlightDrawable layer = new HighlightDrawable(d);
        super.setImageDrawable(layer);
    }

    public HighlightImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public HighlightImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public HighlightImageView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public void setMargins(int i, int j, int k, int l) {

    }


}
