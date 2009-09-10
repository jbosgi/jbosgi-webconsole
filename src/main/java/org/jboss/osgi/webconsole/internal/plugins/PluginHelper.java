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

/**
 * A Console plugin
 * 
 * @author thomas.diesler@jboss.com
 * @since 12-Mar-2009
 */
public abstract class PluginHelper
{
   public static String getHeader()
   {
      String HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"
      + "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"xhtml1-transitional.dtd\">"
      + "<html xmlns=\"http://www.w3.org/1999/xhtml\">"
      + "<head>"
      + "<meta http-equiv=\"Content-Type\" content=\"text/html; utf-8\">"
      + "<link rel=\"icon\" href=\"{6}/res/imgs/favicon.ico\">"
      + "<title>{0} - {2}</title>"
      + "<script src=\"{5}/res/ui/jquery-1.3.2.min.js\" language=\"JavaScript\"></script>"
      + "<script src=\"{5}/res/ui/jquery.tablesorter-2.0.3.min.js\" language=\"JavaScript\"></script>"
      + "<script src=\"{5}/res/ui/admin.js\" language=\"JavaScript\"></script>"
      + "<script src=\"{5}/res/ui/ui.js\" language=\"JavaScript\"></script>"
      + "<script language=\"JavaScript\">"
      + "appRoot = \"{5}\";"
      + "pluginRoot = appRoot + \"/{6}\";"
      + "</script>"
      + "<link href=\"{5}/res/ui/admin.css\" rel=\"stylesheet\" type=\"text/css\">"
      + "</head>"
      + "<body>"
      + "<div id=\"main\">"
      + "<div id=\"lead\">"
      + "<h1>"
      + "{0}<br>{2}"
      + "</h1>"
      + "<p>"
      + "<a target=\"_blank\" href=\"{3}\" title=\"{1}\"><img src=\"{5}/res/imgs/jboss_logo.png\" width=\"123\" height=\"103\" border=\"0\"></a>"
      + "</p></div>";
      
      return HEADER;
   }

   public static String getFooter()
   {
      String FOOTER = "<img src=\"{0}/res/imgs/logo.png\" width=\"55\" height=\"21\" border=\"0\">&nbsp;"
      + "<a target=\"_blank\" href=\"http://felix.apache.org/site/apache-felix-web-console.html\">Apache Felix Web Console</a>"
      + "</body>"
      + "</html>";
      
      return FOOTER;
   }
}