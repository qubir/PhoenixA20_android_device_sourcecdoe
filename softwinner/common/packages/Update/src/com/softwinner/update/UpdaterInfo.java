
package com.softwinner.update;

import android.app.ActivityManagerNative;
import android.content.Context;
//import android.net.ethernet.EthernetNative;
import android.net.InterfaceConfiguration;
import android.os.Build;
import android.os.IBinder;
import android.os.INetworkManagementService;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemProperties;

public class UpdaterInfo {
    /**
     * info include: 1. id --> ro.build.id 2. brand --> ro.product.brand 3.
     * device --> ro.product.device 4. board --> Build.BOARD 5. mac -->
     * EthernetNative.getEthHwaddr("eth0") 6. android open version -->
     * ro.build.version.release 7. build time --> Build.UTC 8. builder -->
     * ro.build.user 9. fingerprint --> Build.FINGERPRINT
     */
    public static final String UNKNOWN = "unknown";

    public static final String postUrl = Utils.SERVER_URL;

    public static String updating_apk_version;// = "1";

    public static String brand;// = "softwinner";

    public static String device;// = "G10";

    public static String board;// = "CRANE-V1.2";

    public static String mac;//= "00.11.22.33.44.55";

    public static String firmware /*= "1.0"*/;

    public static String android;// = "2.3.4";

    public static String time;// = "20120301.092251";

    public static String builder;// = "ygwang";

    public static String fingerprint;// = "softwinners/apollo_mele/G10:2.3.4/GRJ22/eng.ygwang.20120301.092251:eng/test-keys";

    public static String country;

    private Context mContext;

    public UpdaterInfo(Context mContext) {
        this.mContext = mContext;
        onInit();
    }

    private void getcountry() {
        try {
            country = ActivityManagerNative.getDefault().getConfiguration().locale.getCountry();
        } catch (RemoteException e) {

        }
    }

    public static String makePostString() {
        return null;
    }

    private static String getString(String property) {
        return SystemProperties.get(property, UNKNOWN);
    }
    
    private String getMacAddr(){
        IBinder b = ServiceManager.getService(Context.NETWORKMANAGEMENT_SERVICE);
        INetworkManagementService networkManagement = INetworkManagementService.Stub.asInterface(b);
        if(networkManagement != null){
            InterfaceConfiguration iconfig = null;
            try{
                iconfig= networkManagement.getInterfaceConfig("eth0");
				return iconfig.getHardwareAddress();
            }catch(Exception e){
                e.printStackTrace();
				return "";
            }
        }else{
            return "";
        }
    }
    private String getVersionCode(){
	String packageName = mContext.getPackageName();
	int versionCode = 0;
	try{
	    versionCode = mContext.getPackageManager().getPackageInfo(
			packageName, 0).versionCode;
	}catch(Exception e){
            
	}
	return String.valueOf(versionCode);
    }
    private void onInit(){
    	getcountry();
    	updating_apk_version = getVersionCode();
    	brand=getString("ro.product.brand");
    	device=getString("ro.product.device");
    	board=getString("ro.product.board");
    	mac = getMacAddr();
    	firmware=getString("ro.product.firmware");
    	android=getString("ro.build.version.release");
    	time=getString("ro.build.date.utc");
    	builder=getString("ro.build.user");
    	fingerprint=getString("ro.build.fingerprint");
    }
}
