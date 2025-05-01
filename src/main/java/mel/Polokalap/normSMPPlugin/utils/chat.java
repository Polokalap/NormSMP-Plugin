package mel.Polokalap.normSMPPlugin.utils;

import mel.Polokalap.normSMPPlugin.NormSMPPlugin;

public class chat {

    public static String replace(String toReplace) {

        return NormSMPPlugin.getInstance().getConfig().getString(toReplace)
                .replaceAll("&prefix", NormSMPPlugin.getInstance().getConfig().getString("PREFIX")
                .replaceAll("&sp", null)
                );

    }

}
