
package com.Hileb.moremomostories.item.misc;

import com.Hileb.moremomostories.item.ItemBase;


public class ItemBasicBinary extends ItemBase {

	public int getValue() {
		return value;
	}

	public ItemBasicBinary setValue(int value) {
		this.value = value;
		return this;
	}

	int value = 0;//0 = yin, 1 = yang

	public ItemBasicBinary(String name) {
		super(name);
	}



}
