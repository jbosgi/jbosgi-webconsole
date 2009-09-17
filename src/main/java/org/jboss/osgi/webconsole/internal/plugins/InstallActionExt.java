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
package org.jboss.osgi.webconsole.internal.plugins;

//$Id: EventAdminPlugin.java 86577 2009-04-01 07:53:06Z thomas.diesler@jboss.com $

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.felix.webconsole.internal.core.InstallAction;
import org.jboss.osgi.spi.service.DeployerService;
import org.jboss.osgi.spi.util.BundleDeployment;
import org.jboss.osgi.spi.util.BundleDeploymentFactory;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.Version;
import org.osgi.service.log.LogService;

/**
 * A Console plugin
 * 
 * @author thomas.diesler@jboss.com
 * @since 06-Jul-2009
 */
public class InstallActionExt extends InstallAction
{
   protected void installBackground(final File bundleFile, final String location, final int startlevel, final boolean doStart, boolean refreshPackages)
   {
      BundleContext context = getBundleContext();
      ServiceReference sref = context.getServiceReference(DeployerService.class.getName());
      if (sref == null)
      {
         getLog().log(LogService.LOG_WARNING, "Cannot obtain service: " + DeployerService.class.getName());
         super.installBackground(bundleFile, location, startlevel, doStart, refreshPackages);
         return;
      }
      final DeployerService deployer = (DeployerService)context.getService(sref);
      
      Thread t = new InstallHelper(this, "Background Install " + bundleFile, bundleFile, refreshPackages)
      {
         protected Bundle doRun(InputStream bundleStream) throws BundleException
         {
            URL bundleURL = getBundleURL(bundleFile);
            
            BundleDeployment dep = BundleDeploymentFactory.createBundleDeployment(bundleURL);
            dep.setStartLevel(startlevel);
            dep.setAutoStart(doStart);

            deployer.deploy(new BundleDeployment[] { dep });
            
            Bundle bundle = getBundle(dep);
            if (bundle == null)
               throw new IllegalStateException("Cannot obtain installed bundle: " + dep);

            return bundle;
         }

      };

      t.start();
   }

   private Bundle getBundle(BundleDeployment info)
   {
      String symbolicName = info.getSymbolicName();
      Version version = Version.parseVersion(info.getVersion());

      Bundle bundle = null;
      for (Bundle aux : getBundleContext().getBundles())
      {
         if (aux.getSymbolicName().equals(symbolicName))
         {
            String auxVersion = (String)aux.getHeaders().get(Constants.BUNDLE_VERSION);
            if (version == null || version.equals(auxVersion))
            {
               bundle = aux;
               break;
            }
         }
      }
      return bundle;
   }
   
   private URL getBundleURL(final File bundleFile) throws BundleException
   {
      URL bundleURL;
      try
      {
         bundleURL = bundleFile.toURL();
      }
      catch (MalformedURLException ex)
      {
         throw new BundleException(ex.getMessage());
      }
      return bundleURL;
   }
}