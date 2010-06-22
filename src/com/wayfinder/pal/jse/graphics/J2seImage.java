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

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;

import com.wayfinder.pal.graphics.WFGraphics;
import com.wayfinder.pal.graphics.WFImage;

public class J2seImage extends WFImage{
	
	Image mImage;
	private ImageObserver mImageObserver;
	
	public J2seImage(Image img, ImageObserver imgObs) {
		super(img.getWidth(imgObs), img.getHeight(imgObs));
		mImage = img;
		mImageObserver = imgObs;
	}

	@Override
	public void drawNativeImage(WFGraphics g, int x, int y) {
		System.out.println("J2seImage.drawNativeImage()");
	}

	@Override
	protected void getNativeARGBData(int[] rgbData, int offset, int scanlength, 
							int x, int y, int width, int height) {
		
//		mImage.
//		System.out.println("J2seImage.getNativeARGBData()");
	}
	
	@Override
	public Object getNativeImage() {
		return mImage;
	}

	@Override
	public WFGraphics getWFGraphics() {		
		return new J2seGraphics((Graphics2D)mImage.getGraphics(), mImageObserver);
	}

	@Override
	public boolean hasNativeImage() {
		return true;
	}

	@Override
	public boolean isWritable() {
		// 
		return false;
	}

}
