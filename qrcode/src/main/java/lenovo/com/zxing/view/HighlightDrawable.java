package lenovo.com.zxing.view;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;

public class HighlightDrawable extends LayerDrawable
{
    // The color filter to apply when the button is pressed
    ColorFilter pressedFilter = new LightingColorFilter(Color.LTGRAY, 1);
    // Alpha value when the button is disabled
    
    private int _disabledAlpha = 150;
    // Alpha value when the button is enabled
    private int _fullAlpha = 255;

    public HighlightDrawable(Drawable d)
    {
        super(new Drawable[] { d });
    }

    @Override
    protected boolean onStateChange(int[] states)
    {
        boolean enabled = false;
        boolean pressed = false;

        for (int state : states)
        {
            if (state == android.R.attr.state_enabled) enabled = true;
            else if (state == android.R.attr.state_pressed) pressed = true;
        }

        mutate();
        if (enabled && pressed)
        {
            setColorFilter(pressedFilter);
        }
        else if (!enabled)
        {
            setColorFilter(null);
            setAlpha(_disabledAlpha);
        }
        else
        {
            setColorFilter(null);
            setAlpha(_fullAlpha);
        }

        invalidateSelf();

        return super.onStateChange(states);
    }

    @Override
    public boolean isStateful()
    {
        return true;
    }
}
