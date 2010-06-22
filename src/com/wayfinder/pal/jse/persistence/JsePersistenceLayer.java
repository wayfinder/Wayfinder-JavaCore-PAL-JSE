/*******************************************************************************
 * Copyright (c) 1999-2010, Vodafone Group Services
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions 
 * are met:
 * 
 *     * Redistributions of source code must retain the above copyright 
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above 
 *       copyright notice, this list of conditions and the following 
 *       disclaimer in the documentation and/or other materials provided 
 *       with the distribution.
 *     * Neither the name of Vodafone Group Services nor the names of its 
 *       contributors may be used to endorse or promote products derived 
 *       from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING 
 * IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY 
 * OF SUCH DAMAGE.
 ******************************************************************************/
package com.wayfinder.pal.jse.persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;

import com.wayfinder.pal.persistence.PersistenceLayer;
import com.wayfinder.pal.persistence.SecondaryCacheStorage;
import com.wayfinder.pal.persistence.SettingsConnection;
import com.wayfinder.pal.persistence.WFFileConnection;

public class JsePersistenceLayer implements PersistenceLayer {
	
	private String m_BaseFileDirectory = null;
	
	public JsePersistenceLayer() {
		setInitialBaseFolder();		
	}
	
	@Override
	public synchronized WFFileConnection openFile(String path) throws IOException {
		return new JseFileConnection(path);
	}
	
	@Override
	public InputStream getResourceAsStream(String resource) throws IOException {
        InputStream in = getClass().getResourceAsStream("/"+resource);
        if (in == null) {
            throw new FileNotFoundException("JsePersistenceLayer.getResourceAsStream() could not find "+resource);
        }
        return in;     
    }
	
	@Override
	public SettingsConnection openSettingsConnection(String settingsType) {				
		return new JseSettingsConnection(settingsType, this);
	}

	@Override
	public synchronized void setBaseFileDirectory(String path) {
    	if (path == null || !path.endsWith("/")) {
    		throw new IllegalArgumentException("Path must be not null and ends with '/'");
    	}
		m_BaseFileDirectory = path;
	}
	
	@Override
	public synchronized String getBaseFileDirectory() {
		return m_BaseFileDirectory;
	}
	
	@Override
	public SecondaryCacheStorage openSecondaryCacheStorage(String name) throws IOException {
		return new JseSecondaryCacheStorage(m_BaseFileDirectory,name);
	}
	
	@Override
	public String[] listFiles(String folder, String extension) {
		File path = new File(folder);
		return path.list(new JseFileNameFilter(extension));
	}
	
	private void setInitialBaseFolder() {
		try {
			File []roots = File.listRoots();
			if(roots != null) {
				File f = roots[0];
				m_BaseFileDirectory = f.getAbsolutePath() + "tmp/wayfinder/core/";				
			}
		} catch (Exception e) {
			System.out.println("JsePersistenceLayer.JsePersistenceLayer() e= "+e);
		}		
	}
	
	private static class JseFileNameFilter implements FilenameFilter {
		
		private String m_Filter;
		
		public JseFileNameFilter(String filter) {
			m_Filter = filter;
		}

		@Override
		public boolean accept(File dir, String name) {			
			return name.endsWith(m_Filter);
		}		
	}

}
