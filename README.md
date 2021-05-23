# Android About Page
Create an awesome About Page for your Android App in 2 minutes

<img src="/resources/cover.png" width="80%" alt="Android About Page Cover"/>

This library allows to generate beautiful About Pages with less effort, it's fully customizable and supports opening specific intent

```java
View aboutPage = new AboutPage(this)
  .isRTL(false)
  .setCustomFont(String) // or Typeface
  .setImage(R.drawable.dummy_image)
  .addItem(versionElement)
  .addItem(adsElement)
  .addGroup("Connect with us")
  .addEmail("elmehdi.sakout@gmail.com")
  .addWebsite("https://mehdisakout.com/")
  .addFacebook("the.medy")
  .addTwitter("medyo80")
  .addYoutube("UCdPQtdWIsg7_pi4mrRu46vA")
  .addPlayStore("com.ideashower.readitlater.pro")
  .addGitHub("medyo")
  .addInstagram("medyo80")
  .create();
```

## Setup
Available on Jcenter, Maven and JitPack

```groovy
implementation 'io.github.medyo:android-about-page:2.0.0'
```


## Usage
### 1. Add Description

```java
setDescription(String)
```

### 2. Add Image
```java
setImage(Int)
```

### 3. Add predefined Social network
The library has already some predefined social networks like :  

* Facebook
* Twitter
* Instagram
* Youtube
* PlayStore

```java
addFacebook(String PageID)
addTwitter(String AccountID)
addYoutube(String AccountID)
addPlayStore(String PackageName)
addInstagram(String AccountID)
addGitHub(String AccountID)
```

### 4. Add Custom Element
For example `app version` :

```java
Element versionElement = new Element();
versionElement.setTitle("Version 6.2");
addItem(versionElement)
```

### 5. Available attributes for Element Class


| Function        | Description  |
| ------------- |:-------------:|
| setTitle(String) | Set title of the element|
| setIconTint(Int) | Set color of the element|
| setSkipTint(Boolean) | Skip tinting the icon (useful when using non vector drawables)|
| setIconDrawable(Int) | Set icon of the element|
| setValue(String) | Set Element value like Facebook ID|
| setIntent(Intent) | Set an intent to be called on `onClickListener` |
| setGravity(Gravity) | Set a Gravity for the element  |
| setOnClickListener(View.OnClickListener) | If `intent` isn't suitable for you need, implement your custom behaviour by overriding the click listener|

### 6. How to use the library in a fragment
```java
 @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return new AboutPage(getContext())
                .isRTL(false)
                .setDescription(getString(R.string.app_description))
                .addGroup(getString(R.string.contact_group))
                .addEmail("us@example.com", "Email")
                .addGroup(getString(R.string.application_information_group))
                .addItem(new VersionElement())
                .create();
    }
```
snippet by [nrhoffmann](https://github.com/nrhoffmann)

### 7. Styling

The library supports day-night modes. The dependents may use the following
styling attributes to create a dedicated style for `AboutPage`. If the
dependents choose not to specify an explicit style, the library falls back to
sensible defaults.

First, declare an `AboutPage` style in your `styles.xml`.

```xml
<!-- Define a global style for AboutPage in your 'styles.xml' -->
<style name="Widget.App.AboutPage" parent="about_About">
  <item name="aboutBackground">#ffffff</item>
  <item name="aboutElementIconTint">#333333</item>
  <item name="aboutSeparatorColor">#999999</item>
  <item name="aboutDescriptionTextAppearance">@style/TextAppearance.App.AboutPage.Description</item>

  <!-- similarly, you can also apply the following text appearances -->
  <item name="aboutElementTextAppearance">@style/about_elementTextAppearance.Dark</item>
  <item name="aboutGroupTextAppearance">@style/about_groupTextAppearance</item>
</style>

<style name="TextAppearance.App.AboutPage.Description" parent="about_descriptionTextAppearance.Dark">
  <item name="android:textStyle">bold|italic</item>
</style>

```

To apply this style globally, assign its reference to `aboutStyle` attribute in
your app theme.

```xml
<style name="Theme.App" parent="Theme.AppCompat">
  <item name="aboutStyle">@style/Widget.AboutPage</item>
</style>
```

Or explicitly pass the style resource to the `AboutPage` constructor to apply it
on selective `AboutPage` instances.

```java
AboutPage aboutPage = new AboutPage(context, R.style.Widget_AboutPage);
```

### 8. Force Night/Day mode

We recommend that the dependents use
[`AppCompatDelegate.setDefaultNightMode()`](https://developer.android.com/reference/androidx/appcompat/app/AppCompatDelegate#setDefaultNightMode(int))
to force enable/disable the night mode across their apps. If the dependents are
unable to use the recommended approach, they can use the `AboutPage(Context,
boolean)` constructor to specify the desired mode. The dependents must note that
by using this constructor, the `AboutPage` will use its default styles, ignoring
any explicitly specified style.

```java
AboutPage aboutPage = AboutPage(context, true); // force enable dark mode.
AboutPage aboutPage = AboutPage(context, false); // force enable bright mode.
```

## Sample Project
[medyo/android-about-page/app/](https://github.com/medyo/android-about-page/tree/master/app)

## Translations
The library does supports the following languages :

* Arabic (by [zecharyah](https://github.com/zecharyah))
* Catalan (by [unxavi](https://github.com/unxavi))
* Croatian (by [skmlcd](https://github.com/skmlcd))
* Czech (by [semanticer](https://github.com/semanticer))
* Dutch (by [artaex](https://github.com/artaex))
* English (by [medyo](https://github.com/medyo))
* French (by [medyo](https://github.com/medyo))
* Georgian (by [tatocaster](https://github.com/tatocaster))
* German (by [vanniktech](https://github.com/vanniktech) && [nikothegreek](https://github.com/nikothegreek))
* Greek (by [jvoyatz](https://github.com/jvoyatz))
* Hungarian (by [jbarat](https://github.com/jbarat))
* Indian (by [kartikarora](https://github.com/kartikarora))
* Indonesian (by [hyuwah](https://github.com/hyuwah))
* Italian (by [greenaddress](https://github.com/greenaddress))
* Japanese (by [chibatching](https://github.com/chibatching))
* Korean (by [Alfex4936](https://github.com/Alfex4936))
* Persian (by [mortezasun](https://github.com/mortezasun))
* Polish (by [karmil32](https://github.com/karmil32))
* Portuguese Brazil (by [rbaprado](https://github.com/rbaprado))
* Romanian (by [Vally79](https://github.com/Vally79))
* Russian (by [NumezmaT](https://github.com/NumezmaT))
* Serbian (by [ljmocic](https://github.com/ljmocic))
* Simplified Chinese (by [whiskeyfei](https://github.com/whiskeyfei))
* Slovenian (by [skmlcd](https://github.com/skmlcd))
* Spanish (by [danramirez](https://github.com/danramirez))
* Swedish (by [Krillsson](https://github.com/Krillsson))
* Traditional Chinese (by [ppcrong](https://github.com/ppcrong))
* Turkish (by [tekseker](https://github.com/tekseker))
* Ukrainian (by [NumezmaT](https://github.com/NumezmaT))
* Uzbek (by [gladuz](https://github.com/gladuz))
* Norwegian Nynorsk (by [boxhock](https://github.com/boxhock))
* Norwegian Bokmål (by [boxhock](https://github.com/boxhock))
* Azerbaijani (by [Chingiz](https://github.com/Chingiz))
* Hebrew (by [evyatar100](https://github.com/evyatar100))
* Bulgarian (by [kstoyanov5](https://github.com/kstoyanov5))

Please make a Pull request to add a new language.

## ProGuard
Nothing to include

## License

~~~
The MIT License (MIT)
Copyright (c) 2016 Mehdi Sakout

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
~~~
