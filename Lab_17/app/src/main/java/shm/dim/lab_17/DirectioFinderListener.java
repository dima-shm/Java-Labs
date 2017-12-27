package shm.dim.lab_17;

import java.util.List;


public interface DirectioFinderListener {
    void onDirectionFinderStart();
    void onDirectionFinderSuccess(List<Route> route);
}

