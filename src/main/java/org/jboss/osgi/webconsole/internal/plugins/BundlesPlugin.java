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

//$Id$

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import org.apache.felix.webconsole.internal.core.BundlesServlet;
import org.jboss.osgi.deployment.deployer.DeployerService;
import org.jboss.osgi.deployment.deployer.Deployment;
import org.jboss.osgi.deployment.deployer.DeploymentRegistryService;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.Version;
import org.osgi.service.log.LogService;

/**
 * A Console plugin
 * 
 * @author thomas.diesler@jboss.com
 * @since 12-Mar-2009
 */
public class BundlesPlugin extends BundlesServlet
{
   private static final long serialVersionUID = 1L;

   @Override
   protected String getHeader()
   {
      return PluginHelper.getHeader();
   }

   @Override
   protected boolean actionUninstall(Bundle bundle)
   {
      BundleContext context = getBundleContext();
      ServiceReference sref = context.getServiceReference(DeployerService.class.getName());
      if (sref == null)
      {
         getLog().log(LogService.LOG_WARNING, "Cannot obtain service: " + DeployerService.class.getName());
         return super.actionUninstall(bundle);
      }
      DeployerService service = (DeployerService)context.getService(sref);
      
      sref = context.getServiceReference(DeploymentRegistryService.class.getName());
      DeploymentRegistryService registry = (DeploymentRegistryService)context.getService(sref);
      
      String symbolicName = bundle.getSymbolicName();
      Version version = bundle.getVersion();
      Deployment dep = registry.getDeployment(symbolicName, version);
      if (dep == null)
      {
         getLog().log(LogService.LOG_WARNING, "Cannot find bundle deployment for: " + bundle);
         return super.actionUninstall(bundle);
      }
      
      try
      {
         service.undeploy(new Deployment[] { dep });
         return true;
      }
      catch (BundleException be)
      {
         getLog().log(LogService.LOG_ERROR, "Cannot uninstall", be);
         return false;
      }
   }

   @Override
   protected void endResponse(HttpServletRequest request, PrintWriter pw)
   {
      // Footer redered before content in bundle plugin
      // https://issues.apache.org/jira/browse/FELIX-1020

      //String appRoot = (String)request.getAttribute(OsgiManager.ATTR_APP_ROOT);
      //String footer = MessageFormat.format(PluginHelper.getFooter(), new Object[]{ appRoot });
      //pw.println(footer);
   }
}