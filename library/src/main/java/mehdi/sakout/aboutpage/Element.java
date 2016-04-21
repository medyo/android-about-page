package mehdi.sakout.aboutpage;

import android.content.Intent;
import android.graphics.drawable.Drawable;

/**
 * Created by medyo on 3/25/16.
 */
public class Element {

    private String tag;
    private String title;
    private Integer icon;
    private Integer color;
    private String value;
    private Intent intent;

    public Element(){

    }

    public Element(String tag, String title, Integer icon) {
        this.tag = tag;
        this.title = title;
        this.icon = icon;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getIcon() {
        return icon;
    }

    public void setIcon(Integer icon) {
        this.icon = icon;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }
}
