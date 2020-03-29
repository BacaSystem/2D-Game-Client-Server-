package game.Constant;

import game.data.GetConfigProperties;

public class LauncherConst {
    private static final String fileName = "launcher";

    public static final String LAUNCHER_TITLE = GetConfigProperties.getValue(fileName, "launcherTitle");
    public static final String NICK_LABEL = GetConfigProperties.getValue(fileName, "nickLabel");
    public static final String ONLINE_LABEL = GetConfigProperties.getValue(fileName, "onlineLabel");
    public static final String OFFLINE_LABEL = GetConfigProperties.getValue(fileName, "offlineLabel");
    public static final String IP_LABEL = GetConfigProperties.getValue(fileName, "ipLabel");
    public static final String PORT_LABEL = GetConfigProperties.getValue(fileName, "portLabel");
    public static final int WIDTH = Integer.parseInt(GetConfigProperties.getValue(fileName, "width"));
    public static final int HEIGHT = Integer.parseInt(GetConfigProperties.getValue(fileName, "height"));

    private LauncherConst() {
        throw new AssertionError();
    }
}
