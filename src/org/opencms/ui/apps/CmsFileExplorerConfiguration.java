/*
 * This library is part of OpenCms -
 * the Open Source Content Management System
 *
 * Copyright (c) Alkacon Software GmbH (http://www.alkacon.com)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * For further information about Alkacon Software, please see the
 * company website: http://www.alkacon.com
 *
 * For further information about OpenCms, please see the
 * project website: http://www.opencms.org
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package org.opencms.ui.apps;

import static org.opencms.gwt.shared.CmsGwtConstants.QuickLaunch.Q_EXPLORER;

import org.opencms.db.CmsUserSettings;
import org.opencms.file.CmsObject;
import org.opencms.gwt.shared.CmsQuickLaunchData;
import org.opencms.main.OpenCms;
import org.opencms.ui.components.OpenCmsTheme;

import java.util.Locale;

import com.google.common.base.Optional;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Resource;

/**
 * The file explorer app configuration.<p>
 */
public class CmsFileExplorerConfiguration extends A_CmsWorkplaceAppConfiguration implements I_CmsHasADEQuickLaunchData {

    /** The app id. */
    public static final String APP_ID = "explorer";

    /**
     * @see org.opencms.ui.apps.I_CmsHasADEQuickLaunchData#getADEQuickLaunchData(org.opencms.file.CmsObject, java.lang.String)
     */
    public Optional<CmsQuickLaunchData> getADEQuickLaunchData(CmsObject cms, String context) {

        CmsUserSettings settings = new CmsUserSettings(cms);
        if (!settings.usesNewWorkplace() || !getVisibility(cms).isActive()) {
            return Optional.absent();
        } else {
            return Optional.of(
                new CmsQuickLaunchData(
                    Q_EXPLORER,
                    null,
                    null,
                    getName(OpenCms.getWorkplaceManager().getWorkplaceLocale(cms)),
                    getImageLink(),
                    false));
        }

    }

    /**
     * @see org.opencms.ui.apps.I_CmsWorkplaceAppConfiguration#getAppCategory()
     */
    @Override
    public String getAppCategory() {

        return "Main";
    }

    /**
     * @see org.opencms.ui.apps.I_CmsWorkplaceAppConfiguration#getAppInstance()
     */
    public I_CmsWorkplaceApp getAppInstance() {

        return new CmsFileExplorer();
    }

    /**
     * @see org.opencms.ui.apps.I_CmsWorkplaceAppConfiguration#getButtonStyle()
     */
    @Override
    public String getButtonStyle() {

        return I_CmsAppButtonProvider.BUTTON_STYLE_TRANSPARENT;
    }

    /**
     * @see org.opencms.ui.apps.I_CmsWorkplaceAppConfiguration#getHelpText(java.util.Locale)
     */
    @Override
    public String getHelpText(Locale locale) {

        return Messages.get().getBundle(locale).key(Messages.GUI_EXPLORER_HELP_0);
    }

    /**
     * @see org.opencms.ui.apps.I_CmsWorkplaceAppConfiguration#getIcon()
     */
    public Resource getIcon() {

        return new ExternalResource(getImageLink());
    }

    /**
     * @see org.opencms.ui.apps.I_CmsWorkplaceAppConfiguration#getId()
     */
    public String getId() {

        return APP_ID;
    }

    public String getImageLink() {

        return OpenCmsTheme.getImageLink("apps/explorer.png");
    }

    /**
     * @see org.opencms.ui.apps.I_CmsWorkplaceAppConfiguration#getName(java.util.Locale)
     */
    @Override
    public String getName(Locale locale) {

        return Messages.get().getBundle(locale).key(Messages.GUI_EXPLORER_TITLE_0);
    }

    /**
     * @see org.opencms.ui.apps.I_CmsWorkplaceAppConfiguration#getOrder()
     */
    @Override
    public int getOrder() {

        return 3;
    }
}
