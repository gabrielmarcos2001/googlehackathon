package com.codesmore.codesmore.utils;

/**
 * Created by gabrielmarcos2001 on 6/12/13.
 */
public class EasingEquations {

    public static double linear(double t, double b, double c, double d){

        return c*t/d + b;
    }

    public static double quadraticIn(double t, double b, double c, double d){

        t /= d;
        return c*t*t + b;
    }

    public static double quadraticOut(double t, double b, double c, double d){

        t /= d;
        return -c * t*(t-2) + b;
    }

    public static double quadraticInOut(double t, double b, double c, double d){

        t /= d/2;
        if (t < 1) return c/2*t*t + b;
        t--;
        return -c/2 * (t*(t-2) - 1) + b;
    }

    public static double cubicIn(double t, double b, double c, double d){

        t /= d;
        return c*t*t*t + b;
    }

    public static double cubicOut(double t, double b, double c, double d){

        t /= d;
        t--;
        return c*(t*t*t + 1) + b;
    }

    public static double cubicInOut(double t, double b, double c, double d){

        t /= d/2;
        if (t < 1) return c/2*t*t*t + b;
        t -= 2;
        return c/2*(t*t*t + 2) + b;
    }

    public static double quarticIn(double t, double b, double c, double d){

        t /= d;
        return c*t*t*t*t + b;
    }

    public static double quarticOut(double t, double b, double c, double d){

        t /= d;
        t--;
        return -c * (t*t*t*t - 1) + b;
    }

    public static double quarticInOut(double t, double b, double c, double d){

        t /= d/2;
        if (t < 1) return c/2*t*t*t*t + b;
        t -= 2;
        return -c/2 * (t*t*t*t - 2) + b;
    }

    public static double quinticIn(double t, double b, double c, double d){

        t /= d;
        return c*t*t*t*t*t + b;
    }

    public static double quinticOut(double t, double b, double c, double d){

        t /= d;
        t--;
        return c*(t*t*t*t*t + 1) + b;
    }

    public static double quinticInOut(double t, double b, double c, double d){

        t /= d/2;
        if (t < 1) return c/2*t*t*t*t*t + b;
        t -= 2;
        return c/2*(t*t*t*t*t + 2) + b;
    }

    public static double sinusoidalIn(double t, double b, double c, double d){

        return -c * Math.cos(t/d * (Math.PI/2)) + c + b;
    }

    public static double sinusoidalOut(double t, double b, double c, double d){

        return c * Math.sin(t/d * (Math.PI/2)) + b;
    }

    public static double sinusoidalInOut(double t, double b, double c, double d){

        return -c/2 * (Math.cos(Math.PI*t/d) - 1) + b;
    }

    public static double exponentialIn(double t, double b, double c, double d){

        return c * Math.pow( 2, 10 * (t/d - 1) ) + b;
    }

    public static double exponentialOut(double t, double b, double c, double d){

        return c * ( -Math.pow( 2, -10 * t/d ) + 1 ) + b;
    }

    public static double exponentialInOut(double t, double b, double c, double d){

        t /= d/2;
        if (t < 1) return c/2 * Math.pow( 2, 10 * (t - 1) ) + b;
        t--;
        return c/2 * ( -Math.pow( 2, -10 * t) + 2 ) + b;
    }

    public static double circularIn(double t, double b, double c, double d){

        t /= d;
        return -c * (Math.sqrt(1 - t*t) - 1) + b;
    }

    public static double circularOut(double t, double b, double c, double d){

        t /= d;
        t--;
        return c * Math.sqrt(1 - t*t) + b;
    }

    public static double circularInOut(double t, double b, double c, double d){

        t /= d/2;
        if (t < 1) return -c/2 * (Math.sqrt(1 - t*t) - 1) + b;
        t -= 2;
        return c/2 * (Math.sqrt(1 - t*t) + 1) + b;
    }
}
