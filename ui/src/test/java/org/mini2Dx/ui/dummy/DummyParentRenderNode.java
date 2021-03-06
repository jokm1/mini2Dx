/*******************************************************************************
 * Copyright 2019 See AUTHORS file
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package org.mini2Dx.ui.dummy;

import org.mini2Dx.ui.layout.LayoutState;
import org.mini2Dx.ui.render.ParentRenderNode;
import org.mini2Dx.ui.style.ParentStyleRule;
import org.mini2Dx.ui.style.StyleRule;

/**
 *
 */
public class DummyParentRenderNode extends ParentRenderNode<DummyParentUiElement, ParentStyleRule> {

	public DummyParentRenderNode(ParentRenderNode<?, ?> parent, DummyParentUiElement element) {
		super(parent, element);
		this.style = element.getStyleRule();
		this.preferredContentWidth = element.getPreferredContentWidth();
		this.preferredContentHeight = element.getPreferredContentHeight();
	}

	@Override
	protected ParentStyleRule determineStyleRule(LayoutState layoutState) {
		return element.getStyleRule();
	}

	@Override
	protected float determinePreferredContentWidth(LayoutState layoutState) {
		return element.getPreferredContentWidth();
	}

	@Override
	protected float determinePreferredContentHeight(LayoutState layoutState) {
		return element.getPreferredContentHeight();
	}

	@Override
	protected float determineXOffset(LayoutState layoutState) {
		return element.getX();
	}

	@Override
	protected float determineYOffset(LayoutState layoutState) {
		return element.getY();
	}
}
