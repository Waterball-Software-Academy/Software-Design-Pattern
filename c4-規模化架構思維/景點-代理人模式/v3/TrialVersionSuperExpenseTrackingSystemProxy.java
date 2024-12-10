package v3;

import v2.VirtualSuperExpenseTrackingSystemProxy;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class TrialVersionSuperExpenseTrackingSystemProxy extends VirtualSuperExpenseTrackingSystemProxy {
    @Override
    public void exportCSV(String filename) {
        throw new UnsupportedOperationException("CSV exporting is not supported in the trial version.");
    }
}
