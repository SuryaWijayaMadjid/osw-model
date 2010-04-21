/*
 *  Copyright 2010 Vodafone Group Services Ltd.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *    
 */
package org.onesocialweb.model.atom;

public class DefaultAtomContent extends DefaultAtomText implements AtomContent {

	private String src;

	@Override
	public String getSrc() {
		return src;
	}

	@Override
	public void setSrc(final String src) {
		this.src = src;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[AtomContent ");
		buffer.append(super.toString());
		if (src != null) {
			buffer.append("src:" + src + " ");
		}
		buffer.append("]\n");

		return buffer.toString();
	}

	@Override
	public boolean hasSrc() {
		return (src != null);
	}

}
