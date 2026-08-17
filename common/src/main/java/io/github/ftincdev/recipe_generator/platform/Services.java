package io.github.ftincdev.recipe_generator.platform;

import io.github.ftincdev.recipe_generator.Constants;

import io.github.ftincdev.recipe_generator.platform.services.IFileSaver;
import io.github.ftincdev.recipe_generator.platform.services.IPlatformHelper;

import java.util.ServiceLoader;

public class Services {

    public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);
    public static final IFileSaver FILE_SAVER = load(IFileSaver.class);

    public static <T> T load(Class<T> clazz) {

        final T loadedService = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        Constants.LOG.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }
}
