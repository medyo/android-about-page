package mehdi.sakout.aboutpage;

import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.StyleRes;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.widget.TextViewCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

/**
 * The main class of this library with many predefined methods to add Elements for common items in
 * an About page. This class creates a {@link android.view.View} that can be passed as the root view
 * in {@link Fragment#onCreateView(LayoutInflater, ViewGroup, Bundle)} or passed to the {@link android.app.Activity#setContentView(View)}
 * in an activity's {@link android.app.Activity#onCreate(Bundle)} (Bundle)} method
 * <p>
 * To create a custom item in the about page, pass an instance of {@link mehdi.sakout.aboutpage.Element}
 * to the {@link AboutPage#addItem(Element)} method.
 *
 * @see Element
 */
public class AboutPage {
    private static final String LOG_TAG = AboutPage.class.getSimpleName();

    private final Context mContext;
    private final LayoutInflater mInflater;
    private final View mView;
    private CharSequence mDescription;
    private int mImage = 0;
    private boolean mIsRTL = false;
    private Typeface mCustomFont;

    /**
     * The AboutPage requires a context to perform it's functions. Give it a context associated to an
     * Activity or a Fragment. To avoid memory leaks, don't pass a
     * {@link android.content.Context#getApplicationContext() Context.getApplicationContext()} here.
     *
     * @param context
     */
    public AboutPage(Context context) {
        this(context, AboutPageUtils.resolveResIdAttr(context, R.attr.aboutStyle, R.style.about_About));
    }

    public AboutPage(Context context, boolean forceEnableDarkMode) {
        this(context, forceEnableDarkMode ? R.style.about_AboutBase_Dark : R.style.about_AboutBase_Light);
    }

    public AboutPage(Context context, @StyleRes int style) {
        this.mContext = new ContextThemeWrapper(context, style);
        this.mInflater = LayoutInflater.from(this.mContext);
        this.mView = mInflater.inflate(R.layout.about_page, null);
    }

    /**
     * Provide a valid path to a font here to use another font for the text inside this AboutPage
     *
     * @param path
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage setCustomFont(String path) {
        //TODO: check if file exists
        mCustomFont = Typeface.createFromAsset(mContext.getAssets(), path);
        return this;
    }

    /**
     * Provide a typeface to use as custom font
     *
     * @param typeface
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage setCustomFont(Typeface typeface) {
        mCustomFont = typeface;
        return this;
    }

    /**
     * Convenience method for {@link AboutPage#addEmail(java.lang.String, java.lang.String)} but with
     * a predefined title string
     *
     * @param email the email address to send to
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage addEmail(String email) {
        return addEmail(email, mContext.getString(R.string.about_contact_us));
    }

    /**
     * Add a predefined Element that opens the users default email client with a new email to the
     * email address passed as parameter
     *
     * @param email the email address to send to
     * @param title the title string to display on this item
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage addEmail(String email, String title) {
        Element emailElement = new Element();
        emailElement.setTitle(title);
        emailElement.setIconDrawable(R.drawable.about_icon_email);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        emailElement.setIntent(intent);

        addItem(emailElement);
        return this;
    }

    /**
     * Convenience method for {@link AboutPage#addFacebook(java.lang.String, java.lang.String)} but with
     * a predefined title string
     *
     * @param id the facebook id to display
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage addFacebook(String id) {
        return addFacebook(id, mContext.getString(R.string.about_facebook));
    }

    /**
     * Add a predefined Element that the opens Facebook app with a deep link to the specified user id
     * If the Facebook application is not installed this will open a web page instead.
     *
     * @param id    the id of the Facebook user to display in the Facebook app
     * @param title the title to display on this item
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage addFacebook(String id, String title) {
        Element facebookElement = new Element();
        facebookElement.setTitle(title);
        facebookElement.setIconDrawable(R.drawable.about_icon_facebook);
        facebookElement.setIconTint(R.color.about_facebook_color);
        facebookElement.setValue(id);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);

        if (AboutPageUtils.isAppInstalled(mContext, "com.facebook.katana")) {
            intent.setPackage("com.facebook.katana");
            int versionCode = 0;
            try {
                versionCode = mContext.getPackageManager().getPackageInfo("com.facebook.katana", 0).versionCode;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

            if (versionCode >= 3002850) {
                Uri uri = Uri.parse("fb://facewebmodal/f?href=" + "http://m.facebook.com/" + id);
                intent.setData(uri);
            } else {
                Uri uri = Uri.parse("fb://page/" + id);
                intent.setData(uri);
            }
        } else {
            intent.setData(Uri.parse("http://m.facebook.com/" + id));
        }

        facebookElement.setIntent(intent);

        addItem(facebookElement);
        return this;
    }

    /**
     * Convenience method for {@link AboutPage#addTwitter(String, String)} but with
     * a predefined title string
     *
     * @param id the Twitter id to display
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage addTwitter(String id) {
        return addTwitter(id, mContext.getString(R.string.about_twitter));
    }


    /**
     * Add a predefined Element that the opens the Twitter app with a deep link to the specified user id
     * If the Twitter application is not installed this will open a web page instead.
     *
     * @param id    the id of the Twitter user to display in the Twitter app
     * @param title the title to display on this item
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage addTwitter(String id, String title) {
        Element twitterElement = new Element();
        twitterElement.setTitle(title);
        twitterElement.setIconDrawable(R.drawable.about_icon_twitter);
        twitterElement.setIconTint(R.color.about_twitter_color);
        twitterElement.setValue(id);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);

        if (AboutPageUtils.isAppInstalled(mContext, "com.twitter.android")) {
            intent.setPackage("com.twitter.android");
            intent.setData(Uri.parse(String.format("twitter://user?screen_name=%s", id)));
        } else {
            intent.setData(Uri.parse(String.format("http://twitter.com/intent/user?screen_name=%s", id)));
        }

        twitterElement.setIntent(intent);
        addItem(twitterElement);
        return this;
    }

    /**
     * Convenience method for {@link AboutPage#addPlayStore(String, String)} but with
     * a predefined title string
     *
     * @param id the package id of the app to display
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage addPlayStore(String id) {
        return addPlayStore(id, mContext.getString(R.string.about_play_store));
    }

    /**
     * Add a predefined Element that the opens the PlayStore app with a deep link to the
     * specified app application id.
     *
     * @param id    the package id of the app to display
     * @param title the title to display on this item
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage addPlayStore(String id, String title) {
        Element playStoreElement = new Element();
        playStoreElement.setTitle(title);
        playStoreElement.setIconDrawable(R.drawable.about_icon_google_play);
        playStoreElement.setIconTint(R.color.about_play_store_color);
        playStoreElement.setValue(id);

        Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=" + id);
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        playStoreElement.setIntent(goToMarket);

        addItem(playStoreElement);
        return this;
    }

    /**
     * Convenience method for {@link AboutPage#addYoutube(String, String)} but with
     * a predefined title string
     *
     * @param id the id of the channel to deep link to
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage addYoutube(String id) {
        return addYoutube(id, mContext.getString(R.string.about_youtube));
    }

    /**
     * Add a predefined Element that the opens the Youtube app with a deep link to the
     * specified channel id.
     * <p>
     * If the Youtube app is not installed this will open the Youtube web page instead.
     *
     * @param id    the id of the channel to deep link to
     * @param title the title to display on this item
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage addYoutube(String id, String title) {
        Element youtubeElement = new Element();
        youtubeElement.setTitle(title);
        youtubeElement.setIconDrawable(R.drawable.about_icon_youtube);
        youtubeElement.setIconTint(R.color.about_youtube_color);
        youtubeElement.setValue(id);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(String.format("http://youtube.com/channel/%s", id)));

        if (AboutPageUtils.isAppInstalled(mContext, "com.google.android.youtube")) {
            intent.setPackage("com.google.android.youtube");
        }

        youtubeElement.setIntent(intent);
        addItem(youtubeElement);

        return this;
    }

    /**
     * Convenience method for {@link AboutPage#addInstagram(String, String)} (String, String)} but with
     * a predefined title string
     *
     * @param id the id of the instagram user to deep link to
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage addInstagram(String id) {
        return addInstagram(id, mContext.getString(R.string.about_instagram));
    }

    /**
     * Add a predefined Element that the opens the Instagram app with a deep link to the
     * specified user id.
     * <p>
     * If the Instagram app is not installed this will open the Intagram web page instead.
     *
     * @param id    the user id to deep link to
     * @param title the title to display on this item
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage addInstagram(String id, String title) {
        Element instagramElement = new Element();
        instagramElement.setTitle(title);
        instagramElement.setIconDrawable(R.drawable.about_icon_instagram);
        instagramElement.setIconTint(R.color.about_instagram_color);
        instagramElement.setValue(id);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://instagram.com/_u/" + id));

        if (AboutPageUtils.isAppInstalled(mContext, "com.instagram.android")) {
            intent.setPackage("com.instagram.android");
        }

        instagramElement.setIntent(intent);
        addItem(instagramElement);

        return this;
    }

    /**
     * Convenience method for {@link AboutPage#addGitHub(String, String)} but with
     * a predefined title string
     *
     * @param id the id of the GitHub user to display
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage addGitHub(String id) {
        return addGitHub(id, mContext.getString(R.string.about_github));
    }

    /**
     * Add a predefined Element that the opens the a browser and displays the specified GitHub
     * users profile page.
     *
     * @param id    the GitHub user to link to
     * @param title the title to display on this item
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage addGitHub(String id, String title) {
        Element gitHubElement = new Element();
        gitHubElement.setTitle(title);
        gitHubElement.setIconDrawable(R.drawable.about_icon_github);
        gitHubElement.setIconTint(R.color.about_github_color);
        gitHubElement.setValue(id);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(String.format("https://github.com/%s", id)));

        gitHubElement.setIntent(intent);
        addItem(gitHubElement);

        return this;
    }

    /**
     * Convenience method for {@link AboutPage#addWebsite(String, String)} but with
     * a predefined title string
     *
     * @param url the URL to open in a browser
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage addWebsite(String url) {
        return addWebsite(url, mContext.getString(R.string.about_website));
    }

    /**
     * Add a predefined Element that the opens a browser and loads the specified URL
     *
     * @param url   the URL to open in a browser
     * @param title the title to display on this item
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage addWebsite(String url, String title) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }
        Element websiteElement = new Element();
        websiteElement.setTitle(title);
        websiteElement.setIconDrawable(R.drawable.about_icon_link);
        websiteElement.setValue(url);

        Uri uri = Uri.parse(url);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, uri);

        websiteElement.setIntent(browserIntent);
        addItem(websiteElement);

        return this;
    }

    /**
     * Add a custom {@link Element} to this AboutPage
     *
     * @param element
     * @return this AboutPage instance for builder pattern support
     * @see Element
     */
    public AboutPage addItem(Element element) {
        LinearLayout wrapper = mView.findViewById(R.id.about_providers);
        wrapper.addView(createItem(element));
        wrapper.addView(getSeparator(), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mContext.getResources().getDimensionPixelSize(R.dimen.about_separator_height)));
        return this;
    }

    /**
     * Set the header image to display in this AboutPage
     *
     * @param resource the resource id of the image to display
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage setImage(@DrawableRes int resource) {
        this.mImage = resource;
        return this;
    }

    /**
     * Add a new group that will display a header in this AboutPage
     * <p>
     * A header will be displayed in the order it was added. For e.g:
     * <p>
     * <code>
     * new AboutPage(this)
     * .addItem(firstItem)
     * .addGroup("Header")
     * .addItem(secondItem)
     * .create();
     * </code>
     * <p>
     * Will display the following
     * [First item]
     * [Header]
     * [Second item]
     *
     * @param name the title for this group
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage addGroup(String name) {

        TextView textView = new TextView(mContext);
        textView.setText(name);
        TextViewCompat.setTextAppearance(textView, AboutPageUtils.resolveResIdAttr(mContext, R.attr.aboutGroupTextAppearance, R.style.about_groupTextAppearance));
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        if (mCustomFont != null) {
            textView.setTypeface(mCustomFont);
        }

        int padding = mContext.getResources().getDimensionPixelSize(R.dimen.about_group_text_padding);
        textView.setPadding(padding, padding, padding, padding);


        if (mIsRTL) {
            textView.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
            textParams.gravity = Gravity.END | Gravity.CENTER_VERTICAL;
        } else {
            textView.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
            textParams.gravity = Gravity.START | Gravity.CENTER_VERTICAL;
        }
        textView.setLayoutParams(textParams);

        ((LinearLayout) mView.findViewById(R.id.about_providers)).addView(textView);
        return this;
    }

    /**
     * Turn on the RTL mode.
     *
     * @param value
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage isRTL(boolean value) {
        this.mIsRTL = value;
        return this;
    }

    public AboutPage setDescription(CharSequence description) {
        this.mDescription = description;
        return this;
    }

    /**
     * Create and inflate this AboutPage. After this method is called the AboutPage
     * cannot be customized any more.
     *
     * @return the inflated {@link View} of this AboutPage
     */
    public View create() {
        TextView description = mView.findViewById(R.id.description);
        ImageView image = mView.findViewById(R.id.image);

        if (mImage > 0) {
            image.setImageResource(mImage);
        }

        if (!TextUtils.isEmpty(mDescription)) {
            description.setText(mDescription);
        }

        if (mCustomFont != null) {
            description.setTypeface(mCustomFont);
        }

        return mView;
    }

    private View createItem(final Element element) {
        LinearLayout wrapper = new LinearLayout(mContext);
        wrapper.setOrientation(LinearLayout.HORIZONTAL);
        wrapper.setClickable(true);

        if (element.getOnClickListener() != null) {
            wrapper.setOnClickListener(element.getOnClickListener());
        } else if (element.getIntent() != null) {
            wrapper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        mContext.startActivity(element.getIntent());
                    } catch (ActivityNotFoundException e) {
                        Log.e(LOG_TAG, "failed to launch intent for '" + element.getTitle() + "' element", e);
                    }
                }
            });

        }

        wrapper.setBackgroundResource(AboutPageUtils.resolveResIdAttr(mContext, R.attr.selectableItemBackground, android.R.color.transparent));

        int padding = mContext.getResources().getDimensionPixelSize(R.dimen.about_text_padding);
        wrapper.setPadding(padding, padding, padding, padding);
        LinearLayout.LayoutParams wrapperParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        wrapper.setLayoutParams(wrapperParams);


        TextView textView = new TextView(mContext);
        TextViewCompat.setTextAppearance(textView, AboutPageUtils.resolveResIdAttr(mContext, R.attr.aboutElementTextAppearance, R.style.about_elementTextAppearance));
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(textParams);
        if (mCustomFont != null) {
            textView.setTypeface(mCustomFont);
        }

        ImageView iconView = null;

        if (element.getIconDrawable() != null) {
            iconView = new ImageView(mContext);
            int size = mContext.getResources().getDimensionPixelSize(R.dimen.about_icon_size);
            LinearLayout.LayoutParams iconParams = new LinearLayout.LayoutParams(size, size);
            iconView.setLayoutParams(iconParams);
            int iconPadding = mContext.getResources().getDimensionPixelSize(R.dimen.about_icon_padding);
            iconView.setPadding(iconPadding, 0, iconPadding, 0);

            if (Build.VERSION.SDK_INT < 21) {
                Drawable drawable = VectorDrawableCompat.create(iconView.getResources(), element.getIconDrawable(), iconView.getContext().getTheme());
                iconView.setImageDrawable(drawable);
            } else {
                iconView.setImageResource(element.getIconDrawable());
            }

            Drawable wrappedDrawable = DrawableCompat.wrap(iconView.getDrawable());
            wrappedDrawable = wrappedDrawable.mutate();

            final boolean isNightModeEnabled = AboutPageUtils.isNightModeEnabled(mContext);
            final int iconColor = AboutPageUtils.resolveColorAttr(mContext, R.attr.aboutElementIconTint);
            if (!element.getSkipTint()) {
                if (element.getAutoApplyIconTint()) {
                    if (isNightModeEnabled) {
                        if (element.getIconNightTint() != null) {
                            DrawableCompat.setTint(wrappedDrawable, ContextCompat.getColor(mContext, element.getIconNightTint()));
                        } else {
                            DrawableCompat.setTint(wrappedDrawable, iconColor);
                        }
                    } else {
                        if (element.getIconTint() != null) {
                            DrawableCompat.setTint(wrappedDrawable, ContextCompat.getColor(mContext, element.getIconTint()));
                        } else {
                            DrawableCompat.setTint(wrappedDrawable, iconColor);
                        }
                    }

                } else if (element.getIconTint() != null) {
                    DrawableCompat.setTint(wrappedDrawable, ContextCompat.getColor(mContext, element.getIconTint()));
                } else if (isNightModeEnabled) {
                    DrawableCompat.setTint(wrappedDrawable, iconColor);
                }

            }

        } else {
            int iconPadding = mContext.getResources().getDimensionPixelSize(R.dimen.about_icon_padding);
            textView.setPadding(iconPadding, iconPadding, iconPadding, iconPadding);
        }

        textView.setText(element.getTitle());


        if (mIsRTL) {

            final int gravity = element.getGravity() != null ? element.getGravity() : Gravity.END;

            wrapper.setGravity(gravity | Gravity.CENTER_VERTICAL);
            //noinspection ResourceType
            textParams.gravity = gravity | Gravity.CENTER_VERTICAL;
            wrapper.addView(textView);
            if (element.getIconDrawable() != null) {
                wrapper.addView(iconView);
            }

        } else {
            final int gravity = element.getGravity() != null ? element.getGravity() : Gravity.START;
            wrapper.setGravity(gravity | Gravity.CENTER_VERTICAL);
            //noinspection ResourceType
            textParams.gravity = gravity | Gravity.CENTER_VERTICAL;
            if (element.getIconDrawable() != null) {
                wrapper.addView(iconView);
            }
            wrapper.addView(textView);
        }

        return wrapper;
    }

    private View getSeparator() {
        return mInflater.inflate(R.layout.about_page_separator, null);
    }
}
