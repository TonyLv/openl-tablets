package org.openl.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.*;
import java.io.InputStream;
import java.io.IOException;

/**
 * @author Aleh Bykhavets
 * @deprecated
 */
@Deprecated
public class ConfigManager {

    private final Log log = LogFactory.getLog(ConfigManager.class);

    /** PriorityQueue arranges locators according their priority */
    private PriorityQueue<ConfigLocator> locators;

    public ConfigManager() {
        locators = new PriorityQueue<ConfigLocator>();
    }

    /**
     * Adds new locator to the ConfigManager.
     * <p>
     * Adding ConfigLocator will be served according to its priority.
     * 
     * @param locator new locator
     */
    public void addLocator(ConfigLocator locator) {
        locators.add(locator);
    }

    /**
     * Creates ConfigSet from input stream.
     * <p>
     * It can return <code>null</code> if cannot fetch config data from input
     * stream.
     * 
     * @param is input stream
     * @return ConfigSet or <code>null</code> if failed
     */
    public ConfigSet createFromStream(InputStream is) {
        ConfigSet result = null;
        try {
            Properties props = new Properties();
            props.load(is);

            result = new ConfigSet();
            result.addProperties(props);
        } catch (IOException e) {
            if (log.isErrorEnabled()) {
                log.error("Failed to load properties!", e);
            }
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                if (log.isErrorEnabled()) {
                    log.error("Failed to close InputStream!", e);
                }
            }
        }

        return result;
    }

    /**
     * Seeks config data.
     * <p>
     * First it tries to find ConfigSet using locator with highest priority. If
     * there no config data was found then <code>null</code> is returned.
     * 
     * @param configName name of resource with config data
     * @return config data or <code>null</code>
     */
    public ConfigSet locate(String configName) {
        for (ConfigLocator locator : locators) {
            InputStream is = locator.locate(configName);

            if (is != null) {
                return createFromStream(is);
            }
        }
        if (log.isWarnEnabled()) {
            log.warn("Failed to locate config '" + configName + "'");
        }
        return null;
    }

    /**
     * Finds config and updates all properties if possible.
     * <p>
     * If no config data was found then properties won't be touched.
     * 
     * @param configName name of config resource
     * @param properties config properties to be updated
     */
    public void updateProperties(String configName, Collection<ConfigProperty<?>> properties) {
        ConfigSet set = locate(configName);
        if (set == null) {
            return;
        }

        set.updateProperties(properties);
    }
}