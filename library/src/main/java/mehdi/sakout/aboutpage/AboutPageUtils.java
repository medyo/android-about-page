package mehdi.sakout.aboutpage;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.TypedValue;

import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.util.List;

class AboutPageUtils {

    static Boolean isAppInstalled(Context context, String appName) {
        PackageManager pm = context.getPackageManager();
        boolean installed = false;
        List<PackageInfo> packages = pm.getInstalledPackages(0);

        for (PackageInfo packageInfo : packages) {
            if (packageInfo.packageName.equals(appName)) {
                installed = true;
                break;
            }
        }

        return installed;
    }

    @ColorInt
    static int resolveColorAttr(@NonNull Context context, @AttrRes int attr) {
        final TypedValue outValue = resolveAttr(context, attr);
        if (outValue.type >= TypedValue.TYPE_FIRST_COLOR_INT && outValue.type <= TypedValue.TYPE_LAST_COLOR_INT) {
            return outValue.data;
        }

        return ContextCompat.getColor(context, outValue.resourceId);
    }

    static int resolveResIdAttr(@NonNull Context context, @AttrRes int attr, int defaultValue) {
        try {
            return resolveAttr(context, attr).resourceId;
        } catch (Resources.NotFoundException e) {
            return defaultValue;
        }
    }

    @NonNull
    private static TypedValue resolveAttr(@NonNull Context context, @AttrRes int attr) {
        final TypedValue outValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(attr, outValue, true)) {
            throw new Resources.NotFoundException("'" + context.getResources().getResourceName(attr) + "' is not set.");
        }

        if (outValue.type == TypedValue.TYPE_ATTRIBUTE) {
            return resolveAttr(context, outValue.data);
        }

        return outValue;
    }

    static boolean isNightModeEnabled(@NonNull Context context) {
        final int nightMode = context.getResources().getConfiguration().uiMode;
        return (nightMode & Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES;
    }
}
