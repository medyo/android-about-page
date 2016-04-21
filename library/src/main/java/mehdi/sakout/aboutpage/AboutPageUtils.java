package mehdi.sakout.aboutpage;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by medyo on 4/19/16.
 */
public class AboutPageUtils {

    public static Boolean isAppInstalled(Context context, String appName){
        PackageManager pm = context.getPackageManager();
        boolean installed;
        try {
            pm.getPackageInfo(appName, PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            installed = false;
        }
        return installed;
    }
}
