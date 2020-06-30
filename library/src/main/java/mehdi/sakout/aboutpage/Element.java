package mehdi.sakout.aboutpage;

import android.content.Intent;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import android.view.View;

/**
 * Element class represents an about item in the about page.
 * Use {@link AboutPage#addItem(mehdi.sakout.aboutpage.Element)} to add your
 * custom items to the AboutPage. This class can be constructed in a builder pattern type fashion.
 */
public class Element {

    private String title;
    private Integer iconDrawable;
    private Integer colorDay;
    private Integer colorNight;
    private String value;
    private Intent intent;
    private Integer gravity;
    private Boolean autoIconColor = false;
    private Boolean skipTint = false;

    private View.OnClickListener onClickListener;

    public Element() {

    }

    public Element(String title, Integer iconDrawable) {
        this.title = title;
        this.iconDrawable = iconDrawable;
    }

    /**
     * Get the onClickListener for this Element
     *
     * @return
     * @see android.view.View.OnClickListener
     */
    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    /**
     * Set the onClickListener for this Element. It will be invoked when this particular element
     * is clicked on the AboutPage. This method has higher priority than
     * {@link Element#setIntent(android.content.Intent)} when both methods are used
     *
     * @param onClickListener
     * @return this Element instance for builder pattern support
     * @see android.view.View.OnClickListener
     */
    public Element setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        return this;
    }

    /**
     * Get the gravity of the content of this Element
     *
     * @return See {@link android.view.Gravity}
     */
    public Integer getGravity() {
        return gravity;
    }

    /**
     * Set the Gravity of the content for this Element
     *
     * @param gravity See {@link android.view.Gravity}
     * @return this Element instance for builder pattern support
     */
    public Element setGravity(Integer gravity) {
        this.gravity = gravity;
        return this;
    }

    /**
     * @return the title for this Element
     */
    @Nullable
    public String getTitle() {
        return title;
    }

    /**
     * Set the title for this Element
     *
     * @param title the string value to set
     * @return this Element instance for builder pattern support
     */
    public Element setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Get the icon drawable for this Element that appears to the left of the title or to the
     * right of the title in RTL layout mode.
     *
     * @return the icon drawable resource of this Element
     */
    @DrawableRes
    @Nullable
    public Integer getIconDrawable() {
        return iconDrawable;
    }

    /**
     * Set the icon drawable for this Element that appears to the left of the title or to the
     * right of the title in RTL layout mode.
     *
     * @param iconDrawable the icon drawable resource to set
     * @return this Element instance for builder pattern support
     */
    public Element setIconDrawable(@DrawableRes Integer iconDrawable) {
        this.iconDrawable = iconDrawable;
        return this;
    }

    /**
     * @return the color resource identifier for this Elements icon
     */
    @ColorRes
    @Nullable
    public Integer getIconTint() {
        return colorDay;
    }

    /**
     * Set the color resource identifier for this Elements icon
     *
     * @param color the color resource identifier to use for this Element
     * @return this Element instance for builder pattern support
     */
    public Element setIconTint(@ColorRes Integer color) {
        this.colorDay = color;
        return this;
    }

    /**
     * Get the color resource identifier for this Elements icon when in night mode
     *
     * @return
     * @see AppCompatDelegate#setDefaultNightMode(int)
     */
    @ColorRes
    public Integer getIconNightTint() {
        return colorNight;
    }

    /**
     * Set the icon tint to be used for this Elements icon when in night mode. If no color
     * is specified the accent color of the current theme will be used in night mode.
     *
     * @param colorNight
     * @return
     */
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

    /**
     * Get the intent to be used for when this Element
     *
     * @return
     * @see Element#setIntent(android.content.Intent)
     */
    public Intent getIntent() {
        return intent;
    }

    /**
     * Set the intent to pass to the
     * {@link android.content.Context#startActivity(android.content.Intent)} method when this item
     * is clicked. This method has lower priority than
     * {@link mehdi.sakout.aboutpage.Element#setOnClickListener(android.view.View.OnClickListener)}
     * when both are used.
     *
     * @param intent the intent to be used
     * @return this Element instance for builder pattern support
     * @see android.content.Intent
     */
    public Element setIntent(Intent intent) {
        this.intent = intent;
        return this;
    }

    /**
     * @return the AutoIcon
     */
    public Boolean getAutoApplyIconTint() {
        return autoIconColor;
    }

    /**
     * Automatically apply tint to this Elements icon.
     *
     * @param autoIconColor
     * @return this Element instance for builder pattern support
     */
    public Element setAutoApplyIconTint(Boolean autoIconColor) {
        this.autoIconColor = autoIconColor;
        return this;
    }

    /**
     * Skip Applying a custom tint usefull when the provided drawable isn't a vectorDrawable
     * @param skipTint
     */
    public void setSkipTint(Boolean skipTint) {
        this.skipTint = skipTint;
    }

    public Boolean getSkipTint() {
        return skipTint;
    }
}
