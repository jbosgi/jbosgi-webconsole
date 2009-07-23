/*
 * JBoss, Home of Professional Open Source
 * Copyright 2005, JBoss Inc., and individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.osgi.webconsole.internal;

//$Id$

import org.apache.felix.webconsole.internal.compendium.ComponentConfigurationPrinter;
import org.apache.felix.webconsole.internal.core.SetStartLevelAction;
import org.apache.felix.webconsole.internal.misc.ConfigurationRender;
import org.apache.felix.webconsole.internal.obr.BundleRepositoryRender;
import org.apache.felix.webconsole.internal.obr.InstallFromRepoAction;
import org.apache.felix.webconsole.internal.obr.RefreshRepoAction;
import org.apache.felix.webconsole.internal.servlet.OsgiManager;
import org.apache.felix.webconsole.internal.system.GCAction;
import org.apache.felix.webconsole.internal.system.VMStatPlugin;
import org.jboss.osgi.webconsole.internal.plugins.BundlesPlugin;
import org.jboss.osgi.webconsole.internal.plugins.ConfigManagerPlugin;
import org.jboss.osgi.webconsole.internal.plugins.EventAdminPlugin;
import org.jboss.osgi.webconsole.internal.plugins.InstallActionExt;
import org.jboss.osgi.webconsole.internal.plugins.LicensePlugin;
import org.jboss.osgi.webconsole.internal.plugins.LogServicePlugin;
import org.osgi.framework.BundleContext;

/**
 * Web Console integration
 * 
 * Improve console extensibility https://issues.apache.org/jira/browse/FELIX-1013
 * 
 * @author thomas.diesler@jboss.com
 * @since 12-Mar-2009
 */
public class WebConsole extends OsgiManager
{
   private static final long serialVersionUID = 1L;

   private BundleContext bundleContext;

   public WebConsole(BundleContext bundleContext)
   {
      super(bundleContext);
      this.bundleContext = bundleContext;
   }

   @Override
   protected String getDefaultManagerRoot()
   {
      return "/jboss-osgi";
   }

   @Override
   protected String[] getPluginClasses()
   {
      /*
       PLUGIN_CLASSES =
            "org.apache.felix.webconsole.internal.compendium.ComponentConfigurationPrinter",
            "org.apache.felix.webconsole.internal.compendium.ComponentsServlet",
            "org.apache.felix.webconsole.internal.compendium.ConfigManager",
            "org.apache.felix.webconsole.internal.compendium.LogServlet",
            "org.apache.felix.webconsole.internal.core.BundlesServlet",
            "org.apache.felix.webconsole.internal.core.InstallAction",
            "org.apache.felix.webconsole.internal.core.SetStartLevelAction",
            "org.apache.felix.webconsole.internal.deppack.DepPackServlet",
            "org.apache.felix.webconsole.internal.misc.EventAdminServlet",
            "org.apache.felix.webconsole.internal.misc.LicenseServlet",
            "org.apache.felix.webconsole.internal.misc.ConfigurationRender",
            "org.apache.felix.webconsole.internal.misc.ShellServlet",
            "org.apache.felix.webconsole.internal.obr.BundleRepositoryRender",
            "org.apache.felix.webconsole.internal.obr.InstallFromRepoAction",
            "org.apache.felix.webconsole.internal.obr.RefreshRepoAction",
            "org.apache.felix.webconsole.internal.system.GCAction",
            "org.apache.felix.webconsole.internal.system.VMStatPlugin"
       */
      
      String[] PLUGIN_CLASSES = new String[] { 
            ComponentConfigurationPrinter.class.getName(),
            //ComponentsServlet.class.getName(),
            ConfigManagerPlugin.class.getName(),
            LogServicePlugin.class.getName(),
            BundlesPlugin.class.getName(),
            InstallActionExt.class.getName(), 
            SetStartLevelAction.class.getName(),
            // DepPackServlet.class.getName(),
            EventAdminPlugin.class.getName(),
            LicensePlugin.class.getName(),
            ConfigurationRender.class.getName(),
            // ShellServlet.class.getName(),
            BundleRepositoryRender.class.getName(),
            InstallFromRepoAction.class.getName(), 
            RefreshRepoAction.class.getName(), 
            GCAction.class.getName(), 
            VMStatPlugin.class.getName() 
            };
      
      return PLUGIN_CLASSES;
   }
}