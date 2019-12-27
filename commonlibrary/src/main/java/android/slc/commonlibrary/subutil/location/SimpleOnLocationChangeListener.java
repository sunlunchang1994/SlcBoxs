package android.slc.commonlibrary.subutil.location;

import android.location.Location;
import android.os.Bundle;

import android.slc.commonlibrary.subutil.SlcLocationUtils;

public abstract class SimpleOnLocationChangeListener implements SlcLocationUtils.OnLocationChangeListener {
    @Override
    public void getLastKnownLocation(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }
}
