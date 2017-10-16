package android.mb00.codehub.ui;

import android.graphics.Color;


public class LanguageColor {

    public int color;

    public LanguageColor(String language) {

        switch(language) {
            case "1C Enterprise":
                color = Color.parseColor("#814ccc");
                break;
            case "ABAP":
                color = Color.parseColor("#e8274b");
                break;
            case "ActionScript":
                color = Color.parseColor("#882b0f");
                break;
            case "Ada":
                color = Color.parseColor("#02f88c");
                break;
            case "Agda":
                color = Color.parseColor("#315665");
                break;
            case "AGS Script":
                color = Color.parseColor("#b9d9ff");
                break;
            case "Alloy":
                color = Color.parseColor("#64c800");
                break;
            case "C":
                color = Color.parseColor("#555555");
                break;
            case "C#":
                color = Color.parseColor("#178600");
                break;
            case "C++":
                color = Color.parseColor("#f34b7d");
                break;
            case "CSS":
                color = Color.parseColor("#563d7c");
            case "Groovy":
                color = Color.parseColor("#e69f56");
                break;
            case "HTML":
                color = Color.parseColor("#e34c26");
            case "Java":
                color = Color.parseColor("#b07219");
                break;
            case "JavaScript":
                color = Color.parseColor("#f1e05a");
                break;
            case "Kotlin":
                color = Color.parseColor("#f18e33");
                break;
            case "Python":
                color = Color.parseColor("#3572a5");
                break;
            case "Ruby":
                color = Color.parseColor("#701516");
            case "Shell":
                color = Color.parseColor("#89e051");
                break;
            default:
                color = Color.parseColor("#ffffff");
        }

    }

}
