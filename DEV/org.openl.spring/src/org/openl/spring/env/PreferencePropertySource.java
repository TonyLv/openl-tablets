package org.openl.spring.env;

import java.util.prefs.Preferences;

import org.springframework.core.env.PropertySource;

public class PreferencePropertySource extends PropertySource<Preferences> {
    PreferencePropertySource(String name, String appName) {
        super(name, Preferences.userRoot().node("openl/" + appName));
    }

    @Override
    public Object getProperty(String name) {
        return getSource().get(name, null);
    }
}
