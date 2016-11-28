# Android About Page Support for China APP
一键创建关于页面，支持了国内主流应用的应用内跳转。

## apk demo
- [查看apkdemo](https://github.com/sunxu3074/android-about-page/blob/master/resources/demo.apk)

## 效果图
<img src="/resources/m1l_preview.png" width="80%" alt="Android About Page Cover"/>

## 效果图gif (由于时间很长，gif很大，建议观看视频)
- ![video.gif](https://github.com/sunxu3074/android-about-page/blob/master/resources/preview_video.gif)
- [优酷观看](http://v.youku.com/v_show/id_XMTgzOTkyMDQ2MA)
- [Youtube观看](https://www.youtube.com/watch?v=vab9m76BfYY)

## 代码示例
```java
View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.ic_cn_preview)
                .setDescription("在原有aboutpage基础上支持了中国主流应用，包括：支付宝、知乎、新浪微博、qq、饭否、简书、豆瓣。由于应用内部限制暂时不支持：微信、Acfun、Bilibili、掘金。")
                .addGroup("Support for China APP")
                .addAlipay("aex06786lptrb4cy1g54w15")
                .addQQ("393353762")
                .addZhihu("taken2016")
                .addSinaWeibo("1291760303")
                .addDouban("39114237")
                .addJianshu("d86f33ff1f72")
                .addFanfou("wangxing")
                .addGoldXitu("56eac9bdb0146000293a233b")
```

## 说明 (饭否是用了蘑菇饭作为默认应用)

| 应用名称        | 版本名称  |
| ------------- |:-------------:| -----:|
| 支付宝 | 9.9.5|
| 知乎 | 4.10.1 (443)|
| 新浪微博 | 6.3.1|
| QQ | 6.5.8.2910|
| 饭否 | 1.5.5|
| 简书 | 1.11.5 |
| 豆瓣 | 4.7.0 (85) |

## 导入姿势

下载工程，把library做module导入，再add module dependency即可。

## 感谢

- [在线反编译](http://www.javadecompilers.com/)
- [AlipayZeroSdk](https://github.com/fython/AlipayZeroSdk)
- [TopActivity](https://github.com/109021017/android-TopActivity)
- 反编译不易，望大家支持，欢迎支付宝赞赏我...





- - - - - - - - - - 
#### 原工程Readme
- - - - - - - - - - 
## Android About Page
Create an awesome About Page for your Android App in 2 minutes

<img src="/resources/cover.png" width="80%" alt="Android About Page Cover"/>

This library allows to generate beautiful About Pages with less effort, it's fully customizable and supports opening specific intent

```java
View aboutPage = new AboutPage(this)
  .isRTL(false)
  .setImage(R.drawable.dummy_image)
  .addItem(versionElement)
  .addItem(adsElement)
  .addGroup("Connect with us")
  .addEmail("elmehdi.sakout@gmail.com")
  .addWebsite("http://medyo.github.io/")
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
compile 'com.github.medyo:android-about-page:1.1.1'
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
| ------------- |:-------------:| -----:|
| setTitle(String) | Set title of the element|
| setColor(Int) | Set color of the element|
| setIcon(Int) | Set icon of the element|
| setValue(String) | Set Element value like Facebook ID|
| setTag(String) | Set a unique tag value to the element|
| setIntent(Intent) | Set an intent to be called on `onClickListener` |
| setGravity(Gravity) | Set a Gravity for the element  |
| setOnClickListener(View.OnClickListener) | If `intent` isn't suitable for you need, implement your custom behaviour by overriding the click listener|


## Sample Project
[medyo/android-about-page/app/](https://github.com/medyo/android-about-page/tree/master/app)

## Translations
The library does supports the following languages :

* English (default)
* German (by [vanniktech](https://github.com/vanniktech))
* Italian (by [greenaddress](https://github.com/greenaddress))
* Croatian (by [skmlcd](https://github.com/skmlcd))
* Arabic (by [zecharyah](https://github.com/zecharyah))
* Slovenian (by [skmlcd](https://github.com/skmlcd))
* Ukrainian (by [NumezmaT](https://github.com/NumezmaT))
* Russian (by [NumezmaT](https://github.com/NumezmaT))
* Romanian (by [Vally79](https://github.com/Vally79))
* Portuguese Brazil (by [rbaprado](https://github.com/rbaprado))
* French (by [medyo](https://github.com/medyo))
* Simplified Chinese (by [whiskeyfei](https://github.com/whiskeyfei))
* Spanish (by [danramirez](https://github.com/danramirez))
* Japanese (by [chibatching](https://github.com/chibatching))
* Turkish (by [tekseker](https://github.com/tekseker))
* Catalan (by [unxavi](https://github.com/unxavi))
* Czech (by [semanticer](https://github.com/semanticer))
* Hungarian (by [jbarat](https://github.com/jbarat))
* Korean (by [Alfex4936](https://github.com/Alfex4936))

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
