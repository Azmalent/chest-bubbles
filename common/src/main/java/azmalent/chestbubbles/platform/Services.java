package azmalent.chestbubbles.platform;

import azmalent.chestbubbles.platform.services.INetworkHelper;

import java.util.ServiceLoader;

public final class Services {
    public static final INetworkHelper NETWORK = load(INetworkHelper.class);

    public static <T> T load(Class<T> clazz) {
        return ServiceLoader.load(clazz)
            .findFirst()
            .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
    }
}
