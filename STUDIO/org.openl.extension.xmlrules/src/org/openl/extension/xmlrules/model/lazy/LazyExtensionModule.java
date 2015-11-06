package org.openl.extension.xmlrules.model.lazy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.openl.extension.xmlrules.model.ExtensionModule;
import org.openl.extension.xmlrules.model.single.ExtensionModuleInfo;
import org.openl.extension.xmlrules.model.single.WorkbookInfo;

public class LazyExtensionModule extends BaseLazyItem<ExtensionModuleInfo> implements ExtensionModule {
    public LazyExtensionModule(File file, String mainEntryName) {
        super(file, mainEntryName);
    }

    @Override
    public String getFormatVersion() {
        return getInfo().getFormatVersion();
    }

    @Override
    public String getFileName() {
        return getFile().getName();
    }

    @Override
    public List<LazyWorkbook> getWorkbooks() {
        List<LazyWorkbook> workbooks = new ArrayList<LazyWorkbook>();
        for (WorkbookInfo workbookInfo : getInfo().getWorkbooks()) {
            workbooks.add(new LazyWorkbook(getFile(), "", workbookInfo));
        }

        return workbooks;
    }
}