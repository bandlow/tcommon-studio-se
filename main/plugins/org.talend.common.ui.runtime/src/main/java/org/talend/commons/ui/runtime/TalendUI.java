// ============================================================================
//
// Copyright (C) 2006-2022 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.commons.ui.runtime;

import org.talend.commons.ui.runtime.custom.ICustomUI;
import org.talend.commons.ui.runtime.custom.ICustomUIEngine;

/**
 * DOC cmeng  class global comment. Detailled comment
 */
public class TalendUI {

    private static TalendUI inst;

    private static Object lock = new Object();

    private boolean isStudio = true;

    private ICustomUIEngine stigmaUIEngine;

    private TalendUI() {
    }

    public static TalendUI get() {
        if (inst == null) {
            synchronized (lock) {
                if (inst == null) {
                    inst = new TalendUI();
                }
            }
        }
        return inst;
    }

    public void setStudio(boolean isStudio) {
        this.isStudio = isStudio;
    }

    public boolean isStudio() {
        return this.isStudio;
    }

    public ICustomUIEngine getStigmaUIEngine() {
        return this.stigmaUIEngine;
    }

    public void setStigmaUIEngine(ICustomUIEngine engine) {
        this.stigmaUIEngine = engine;
    }

    public void run(Runnable studioRun, ICustomUI stigmaRun) {
        if (isStudio()) {
            runInStudio(studioRun);
        } else {
            runInStigma(stigmaRun);
        }
    }

    public void runInStudio(Runnable run) {
        run.run();
    }

    public void runInStigma(ICustomUI ui) {
        stigmaUIEngine.run(ui);
    }

}
