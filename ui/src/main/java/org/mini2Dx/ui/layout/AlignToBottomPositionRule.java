/**
 * Copyright 2015 Thomas Cashman
 */
package org.mini2Dx.ui.layout;

import org.mini2Dx.ui.UiElement;
import org.mini2Dx.ui.theme.UiElementStyle;
import org.mini2Dx.ui.theme.UiTheme;

/**
 *
 */
public class AlignToBottomPositionRule implements PositionRule {
	private UiElementStyle currentStyle;
	private float parentHeight;
	private float targetY;

	@Override
	public void onScreenResize(UiTheme theme, UiElementStyle style, float columnSize, float totalHeight) {
		currentStyle = style;
		parentHeight = totalHeight;
	}

	@Override
	public void onContentSizeChanged(UiElement<?> element, float targetWidth, float targetHeight) {
		targetY = parentHeight - targetHeight / 2f - currentStyle.getMarginBottom();
	}

	@Override
	public float getTargetPosition() {
		return targetY;
	}

}