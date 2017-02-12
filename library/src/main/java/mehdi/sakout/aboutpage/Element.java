package mehdi.sakout.aboutpage;

import android.content.Intent;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.view.View;

public class Element {

    private String tag;
    private String title;
    private Integer iconDrawable;
    private Integer colorDay;
    private Integer colorNight;
    private String value;
    private Intent intent;
    private Integer gravity;
    private Boolean autoIconColor = true;

    private View.OnClickListener onClickListener;


    public Element() {

    }

    public Element(String tag, String title, Integer iconDrawable) {
        this.tag = tag;
        this.title = title;
        this.iconDrawable = iconDrawable;
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public Element setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        return this;
    }

    public Integer getGravity() {
        return gravity;
    }

    public Element setGravity(Integer gravity) {
        this.gravity = gravity;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public Element setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Element setTitle(String title) {
        this.title = title;
        return this;
    }

    @DrawableRes public Integer getIconDrawable() {
        return iconDrawable;
    }

    public Element setIconDrawable(@DrawableRes Integer iconDrawable) {
        this.iconDrawable = iconDrawable;
        return this;
    }

    @ColorRes public Integer getIconTint() {
        return colorDay;
    }

    public Element setIconTint(@ColorRes Integer color) {
        this.colorDay = color;
        return this;
    }

    @ColorRes public Integer getIconNightTint() {
        return colorNight;
    }

    public Element setIconNightTint(@ColorRes Integer colorNight) {
        this.colorNight = colorNight;
        return this;
    }

    public String getValue() {
        return value;
    }

    public Element setValue(String value) {
        this.value = value;
        return this;
    }

    public Intent getIntent() {
        return intent;
    }

    public Element setIntent(Intent intent) {
        this.intent = intent;
        return this;
    }

    public Boolean getAutoIconColor() {
        return autoIconColor;
    }

    public Element setAutoIconColor(Boolean autoIconColor) {
        this.autoIconColor = autoIconColor;
        return this;
    }
}
