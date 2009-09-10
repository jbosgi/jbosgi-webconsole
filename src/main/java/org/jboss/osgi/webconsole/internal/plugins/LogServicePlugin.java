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

//$Id: BundlesPlugin.java 86576 2009-04-01 07:17:02Z thomas.diesler@jboss.com $

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import org.apache.felix.webconsole.internal.compendium.LogServlet;

/**
 * A Console plugin
 * 
 * @author thomas.diesler@jboss.com
 * @since 12-Mar-2009
 */
public class LogServicePlugin extends LogServlet
{
   private static final long serialVersionUID = 1L;

   @Override
   protected String getHeader()
   {
      return PluginHelper.getHeader();
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