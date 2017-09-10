package com.lenovo.service.basicpubliclibrary.loadingart.progress;

import com.lenovo.service.basicpubliclibrary.loadingart.progress.sprite.Sprite;
import com.lenovo.service.basicpubliclibrary.loadingart.progress.style.ChasingDots;
import com.lenovo.service.basicpubliclibrary.loadingart.progress.style.Circle;
import com.lenovo.service.basicpubliclibrary.loadingart.progress.style.CubeGrid;
import com.lenovo.service.basicpubliclibrary.loadingart.progress.style.DoubleBounce;
import com.lenovo.service.basicpubliclibrary.loadingart.progress.style.FadingCircle;
import com.lenovo.service.basicpubliclibrary.loadingart.progress.style.FoldingCube;
import com.lenovo.service.basicpubliclibrary.loadingart.progress.style.MultiplePulse;
import com.lenovo.service.basicpubliclibrary.loadingart.progress.style.MultiplePulseRing;
import com.lenovo.service.basicpubliclibrary.loadingart.progress.style.Pulse;
import com.lenovo.service.basicpubliclibrary.loadingart.progress.style.PulseRing;
import com.lenovo.service.basicpubliclibrary.loadingart.progress.style.RotatingCircle;
import com.lenovo.service.basicpubliclibrary.loadingart.progress.style.RotatingPlane;
import com.lenovo.service.basicpubliclibrary.loadingart.progress.style.ThreeBounce;
import com.lenovo.service.basicpubliclibrary.loadingart.progress.style.WanderingCubes;
import com.lenovo.service.basicpubliclibrary.loadingart.progress.style.Wave;


/**
 * Created by ybq.
 */
public class SpriteFactory {

    public static Sprite create(Style style) {
        Sprite sprite = null;
        switch (style) {
            case ROTATING_PLANE:
                sprite = new RotatingPlane();
                break;
            case DOUBLE_BOUNCE:
                sprite = new DoubleBounce();
                break;
            case WAVE:
                sprite = new Wave();
                break;
            case WANDERING_CUBES:
                sprite = new WanderingCubes();
                break;
            case PULSE:
                sprite = new Pulse();
                break;
            case CHASING_DOTS:
                sprite = new ChasingDots();
                break;
            case THREE_BOUNCE:
                sprite = new ThreeBounce();
                break;
            case CIRCLE:
                sprite = new Circle();
                break;
            case CUBE_GRID:
                sprite = new CubeGrid();
                break;
            case FADING_CIRCLE:
                sprite = new FadingCircle();
                break;
            case FOLDING_CUBE:
                sprite = new FoldingCube();
                break;
            case ROTATING_CIRCLE:
                sprite = new RotatingCircle();
                break;
            case MULTIPLE_PULSE:
                sprite = new MultiplePulse();
                break;
            case PULSE_RING:
                sprite = new PulseRing();
                break;
            case MULTIPLE_PULSE_RING:
                sprite = new MultiplePulseRing();
                break;
            default:
                break;
        }
        return sprite;
    }
}
