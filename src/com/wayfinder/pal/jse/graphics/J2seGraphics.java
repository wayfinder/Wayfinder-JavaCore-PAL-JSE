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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import java.awt.image.ImageObserver;

import com.wayfinder.pal.graphics.WFFont;
import com.wayfinder.pal.graphics.WFGraphics;
import com.wayfinder.pal.graphics.WFImage;

public class J2seGraphics implements WFGraphics {
	
	Graphics2D g;
	private ImageObserver mImageObserver;
	WFFont mFont;
	
	public J2seGraphics(Graphics2D aG, ImageObserver imgObserver) {
		g = aG;
		mImageObserver = imgObserver;		
	}

	@Override
	public void drawImage(WFImage img, int x, int y, int anchor) {		
		Object obj = img.getNativeImage();
        if(obj instanceof Image) {
        	Image image = (Image)obj;
        	
        	int width = image.getWidth(mImageObserver);
            if ( (anchor & ANCHOR_RIGHT) == ANCHOR_RIGHT ) {
                x -= width;
            } else if ( (anchor & ANCHOR_HCENTER) == ANCHOR_HCENTER ) {
                x -= width / 2;
            }
            int height = image.getHeight(mImageObserver);
            if ( (anchor & ANCHOR_BOTTOM) == ANCHOR_BOTTOM ) {
                y -= height;
            } else if ( (anchor & ANCHOR_VCENTER) == ANCHOR_VCENTER ) {
                y -= height / 2;
            }
            
//            System.out.println("J2seGraphics.drawImage() w= "+width+" h= "+height);
        	        	
        	g.drawImage(image, x, y, mImageObserver);
        }
	}

	@Override
	public void drawLine(int x1, int y1, int x2, int y2, int stroke) {	
		g.setStroke(new BasicStroke(stroke));
		g.drawLine(x1, y1, x2, y2);		
	}
	
	@Override
	public void drawConnectedLine(int x, int y, int thickness) {
		// 
		
	}
	
	@Override
	public void drawPolygon(int[] x, int[] y) {
		// 
		
	}

	/* (non-Javadoc)
	 * @see com.wayfinder.pal.graphics.WFGraphics#drawPath(int[], int[], int, int)
	 */
	public void drawPath(int[] xCoords, int[] yCoords, int nbrCoords, int width) {
		g.setStroke(new BasicStroke(width));
		g.drawPolyline(xCoords, yCoords, nbrCoords);			
	}

	@Override
	public void fillPolygon(int[] xPoints, int[] yPoints, int length) {
		g.fillPolygon(xPoints, yPoints, length);		
	}

	@Override
	public void drawText(String str, int x, int y, int anchor) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		int width = mFont.getStringWidth(str);		
        if((anchor & ANCHOR_RIGHT) == ANCHOR_RIGHT ) {
            x -= width;
        } else if ((anchor & ANCHOR_HCENTER) == ANCHOR_HCENTER ) {
            x -= width / 2;
        } 
        
        int height = mFont.getFontHeight();
        if ((anchor & ANCHOR_BOTTOM) == ANCHOR_BOTTOM ) {
            y -= height;
        } else if ((anchor & ANCHOR_VCENTER) == ANCHOR_VCENTER ) {
            y -= height / 2;
        } else if ((anchor & ANCHOR_TOP) == ANCHOR_TOP ) {
            y += height;
        }
        
		g.drawString(str, x, y);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);				
	}

	@Override
	public void drawText(String str, int x, int y, int maxWidth, int anchor) {
		//XXX Not implemented! 
		drawText(str, x, y, anchor);
	}
	
	@Override
	public void drawText(String str, int x, int y, int maxWidth, int anchor, String suffix) {
		//XXX Not implemented! 
		drawText(str, x, y, anchor);
	}
	
	@Override
	public void drawRotatedText(String str, int x, int y, double tanTheta) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);		
		final double angle = Math.atan(tanTheta);	
		y += mFont.getFontHeight() / 2;
		g.rotate(angle, x, y);		
		g.drawString(str, x-mFont.getStringWidth(str)/2, y);
		g.rotate(-angle, x, y);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
	}
	
	@Override
	public boolean supportRotatedTexts() {
		return true;
	}
	
	@Override
	public void drawRect(int x, int y, int width, int height) {
		g.drawRect(x, y, width, height);
	}

	@Override
	public void setColor(int c) {
		g.setColor(new Color(c));		
	}

	@Override
	public boolean supportsPath() {
		return true;
	}

	@Override
	public boolean supportsPolygon() {
		return true;
	}

	@Override
	public void fillRect(int x, int y, int width, int heigth) {
		g.fillRect(x, y, width, heigth);		
	}

	@Override
	public void setFont(WFFont font) {
		mFont = font;
		if(font instanceof J2seFont) {
			J2seFont seFont = (J2seFont)font;
			Font f = seFont.getJ2SEFont();
			g.setFont(f);
		}		
	}
	
	@Override
	public void fillTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
		Path2D.Double p = new Path2D.Double();
		p.moveTo(x1, y1);
		p.lineTo(x2, y2);
		p.lineTo(x3, y3);
		p.closePath();
		g.fill(p);		
	}
	
	@Override
	public void drawRGB(int[] argbData, int offset, int scanlength, int x, int y, int width, int height, boolean processAlpha) {
		// 
	}
	
	@Override
	public void allowAntialias(boolean arg0) {
		// 
	}


	@Override
	public int getClipHeight() {
		return (int)g.getClip().getBounds().getHeight();
	}

	@Override
	public int getClipWidth() {
		return (int)g.getClip().getBounds().getWidth();
	}

	@Override
	public int getClipX() {
		return (int)g.getClip().getBounds().getX();
	}

	@Override
	public int getClipY() {
		return (int)g.getClip().getBounds().getY();
	}

	@Override
	public int getColor() {
		return g.getColor().getRGB();
	}

	@Override
	public void setClip(int x, int y, int width, int height) {
		g.setClip(x, y, width, height);		
	}
}
