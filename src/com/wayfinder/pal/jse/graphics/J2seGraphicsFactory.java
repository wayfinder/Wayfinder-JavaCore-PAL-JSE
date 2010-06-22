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
package com.wayfinder.pal.jse.graphics;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

import com.wayfinder.pal.graphics.WFFont;
import com.wayfinder.pal.graphics.WFGraphicsFactory;
import com.wayfinder.pal.graphics.WFImage;

public class J2seGraphicsFactory implements WFGraphicsFactory {

	private ImageObserver m_ImageObserver;
	
	public J2seGraphicsFactory(ImageObserver imgObs) {
		m_ImageObserver = imgObs;
	}

	@Override
	public WFImage createWFImage(String resourceName) {
		try {
			URL imgUrl = getClass().getResource(resourceName);
			Image img = ImageIO.read(imgUrl);
			return new J2seImage(img, m_ImageObserver);
		} catch (IOException e) {}
		return null;
	}

	@Override
	public WFImage createWFImage(int w, int h) {		
		BufferedImage img = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);		
		return new J2seImage(img,m_ImageObserver);
	}

	@Override
	public WFImage createWFImage(byte[] src, int offset, int length) {
		Image img = Toolkit.getDefaultToolkit().createImage(src, offset, length);		
		J2seImage seImage = new J2seImage(img, m_ImageObserver); 
		return seImage; 
	}

	@Override
	public WFImage createWFImage(int width, int height, int color) {		
		// 
		return null;
	}

	@Override
	public WFImage createWFImage(int[] rgb, int width, int height, boolean processAlpha) {
		BufferedImage img = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);		
		return new J2seImage(img,m_ImageObserver);
	}

	@Override
	public WFFont getWFFont(int size, int style) throws IllegalArgumentException {
		return new J2seFont(size, style);
	}

}
